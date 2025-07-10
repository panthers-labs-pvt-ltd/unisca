package org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.dynamic_query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.dynamic_query.GenericSqlProvider;
import org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.dynamic_query.dto.FilterCondition;

import java.util.List;
import java.util.Map;

@Mapper
public interface GenericMapper {

    @SelectProvider(type = GenericSqlProvider.class, method = "buildQuery")
    List<Map<String, Object>> executeDynamicFilterQuery(
            @Param("table") String table,
            @Param("filters") List<FilterCondition> filters
    );
}