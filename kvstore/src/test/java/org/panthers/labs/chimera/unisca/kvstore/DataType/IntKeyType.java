package org.panthers.labs.chimera.unisca.kvstore.DataType;

import org.panthers.labs.chimera.unisca.kvstore.KVIndex;

import java.util.List;

public class IntKeyType {

    @KVIndex
    public int key;

    @KVIndex("id")
    public String id;

    public List<String> values;

    @Override
    public boolean equals(Object o) {
        if (o instanceof IntKeyType other) {
            return key == other.key && id.equals(other.id) && values.equals(other.values);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
