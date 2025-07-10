package org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.dynamic_query.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FilterCondition {

    private String field;
    private String operator;
    private List<Object> value;

    private List<FilterCondition> group;
    private String logic = "AND";
    public FilterCondition() {
    }

    public boolean isGroup() {
        return group != null && !group.isEmpty();
    }

    // Parameterized constructor
    public FilterCondition(String field, String operator, List<Object> value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }
}
