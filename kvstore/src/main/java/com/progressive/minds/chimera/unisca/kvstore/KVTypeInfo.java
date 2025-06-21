package com.progressive.minds.chimera.unisca.kvstore;

import com.google.common.base.Preconditions;
import com.progressive.minds.chimera.unisca.tags.annotation.Private;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Wrapper around types managed in a KVStore, providing easy access to their indexed fields.
 */
@Private
public class KVTypeInfo {

    private final Class<?> type;
    private final Map<String, KVIndex> indices;
    private final Map<String, KVTypeInfo.Accessor> accessors;

    public KVTypeInfo(Class<?> type) {
        this.type = type;
        this.accessors = new HashMap<>();
        this.indices = new HashMap<>();

        for (Field f : type.getDeclaredFields()) {
            KVIndex idx = f.getAnnotation(KVIndex.class);
            if (idx != null) {
                checkIndex(idx, indices);
                f.setAccessible(true);
                indices.put(idx.value(), idx);
                accessors.put(idx.value(), new KVTypeInfo.FieldAccessor(f));
            }
        }

        for (Method m : type.getDeclaredMethods()) {
            KVIndex idx = m.getAnnotation(KVIndex.class);
            if (idx != null) {
                checkIndex(idx, indices);
                Preconditions.checkArgument(m.getParameterCount() == 0,
                        "Annotated method %s::%s should not have any parameters.", type.getName(), m.getName());
                m.setAccessible(true);
                indices.put(idx.value(), idx);
                accessors.put(idx.value(), new KVTypeInfo.MethodAccessor(m));
            }
        }

        Preconditions.checkArgument(indices.containsKey(KVIndex.NATURAL_INDEX_NAME),
                "No natural index defined for type %s.", type.getName());

        for (KVIndex idx : indices.values()) {
            if (!idx.parent().isEmpty()) {
                KVIndex parent = indices.get(idx.parent());
                Preconditions.checkArgument(parent != null,
                        "Cannot find parent %s of index %s.", idx.parent(), idx.value());
                Preconditions.checkArgument(parent.parent().isEmpty(),
                        "Parent index %s of index %s cannot be itself a child index.", idx.parent(), idx.value());
            }
        }
    }

    private void checkIndex(KVIndex idx, Map<String, KVIndex> indices) {
        Preconditions.checkArgument(idx.value() != null && !idx.value().isEmpty(),
                "No name provided for index in type %s.", type.getName());
        Preconditions.checkArgument(
                !idx.value().startsWith("_") || idx.value().equals(KVIndex.NATURAL_INDEX_NAME),
                "Index name %s (in type %s) is not allowed.", idx.value(), type.getName());
        Preconditions.checkArgument(idx.parent().isEmpty() || !idx.parent().equals(idx.value()),
                "Index %s cannot be parent of itself.", idx.value());
        Preconditions.checkArgument(!indices.containsKey(idx.value()),
                "Duplicate index %s for type %s.", idx.value(), type.getName());
    }

    public Class<?> type() {
        return type;
    }

    public Object getIndexValue(String indexName, Object instance) throws Exception {
        return getAccessor(indexName).get(instance);
    }

    public Stream<KVIndex> indices() {
        return indices.values().stream();
    }

    KVTypeInfo.Accessor getAccessor(String indexName) {
        KVTypeInfo.Accessor a = accessors.get(indexName);
        Preconditions.checkArgument(a != null, "No index %s.", indexName);
        return a;
    }

    KVTypeInfo.Accessor getParentAccessor(String indexName) {
        KVIndex index = indices.get(indexName);
        return index.parent().isEmpty() ? null : getAccessor(index.parent());
    }

    String getParentIndexName(String indexName) {
        KVIndex index = indices.get(indexName);
        return index.parent();
    }

    /**
     * Abstracts the difference between invoking a Field and a Method.
     */
    interface Accessor {

        Object get(Object instance) throws ReflectiveOperationException;

        Class<?> getType();
    }

    private record FieldAccessor(Field field) implements Accessor {

        @Override
            public Object get(Object instance) throws ReflectiveOperationException {
                return field.get(instance);
            }

            @Override
            public Class<?> getType() {
                return field.getType();
            }
        }

    private record MethodAccessor(Method method) implements Accessor {

        @Override
            public Object get(Object instance) throws ReflectiveOperationException {
                return method.invoke(instance);
            }

            @Override
            public Class<?> getType() {
                return method.getReturnType();
            }
        }

}

