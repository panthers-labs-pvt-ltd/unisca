package org.pantherslabs.chimera.swagger;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.media.Schema;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;

public class ChimeraAPIExampleResolver extends ModelResolver {

  private static final Logger logger = LoggerFactory.getLogger(ChimeraAPIExampleResolver.class);

  private final ChimeraAPIExampleLoader chimeraAPIExampleLoader;

  public ChimeraAPIExampleResolver(ResourceLoader resourceLoader) {
    super(new ObjectMapper());
    this.chimeraAPIExampleLoader = new ChimeraAPIExampleLoader(resourceLoader, new ObjectMapper());
  }

  public Schema<?> resolve(
      AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
    Schema<?> schema = super.resolve(type, context, chain);
    if (schema == null) return null;

    if ("array".equals(schema.getType())) {
      processArraySchema(type, context, schema);
      return schema;
    }

    Class<?> targetClass = resolveTargetClass(type);
    if (isCustomDto(targetClass)) {
      injectExampleIntoSchema(schema, targetClass.getSimpleName());
    }
    return schema;
  }

  private void processArraySchema(
      AnnotatedType type, ModelConverterContext context, Schema<?> arraySchema) {

    JavaType javaType = (JavaType) type.getType();
    JavaType elementType = javaType.getContentType();

    AnnotatedType elementAnnotatedType =
        new AnnotatedType().type(elementType).ctxAnnotations(type.getCtxAnnotations());

    Schema<?> elementSchema = context.resolve(elementAnnotatedType);

    Class<?> elementClass = elementType.getRawClass();
    if (isCustomDto(elementClass)) {
      injectExampleIntoSchema(elementSchema, elementClass.getSimpleName());
    }

    arraySchema.setItems(elementSchema);
  }

  private void injectExampleIntoSchema(Schema<?> schema, String className) {
    Object example = chimeraAPIExampleLoader.loadExample(className);
    if (example != null) {
      schema.setExample(example);
      logger.info("Injected example for schema: {}", className);
    }
  }

  private Class<?> resolveTargetClass(AnnotatedType type) {
    if (type.getType() instanceof JavaType javaType) {
      JavaType currentType = javaType;
      while (currentType != null
          && (currentType.isContainerType() || currentType.getRawClass() == ResponseEntity.class)) {
        if (currentType.isContainerType()) {
          currentType = currentType.getContentType();
        } else if (currentType.getBindings() != null && !currentType.getBindings().isEmpty()) {
          currentType = currentType.getBindings().getBoundType(0);
        } else {
          break;
        }
      }
      return currentType != null ? currentType.getRawClass() : null;
    }
    return null;
  }

  private boolean isCustomDto(Class<?> clazz) {
    return clazz != null
        && !clazz.getName().startsWith("java.")
        && !clazz.isPrimitive()
        && !clazz.isArray()
        && !clazz.getName().contains("AuditData");
  }
}
