package org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.dynamic_query.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class FilterRequest {
    private String table;
    private List<FilterCondition> filters;

}
