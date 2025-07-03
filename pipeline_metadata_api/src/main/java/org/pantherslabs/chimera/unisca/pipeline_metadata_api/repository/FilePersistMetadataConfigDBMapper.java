package org.pantherslabs.chimera.unisca.pipeline_metadata_api.repository;

import org.pantherslabs.chimera.unisca.pipeline_metadata_api.dto.FilePersistMetadataConfig;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonSelectMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;

@Mapper
public interface FilePersistMetadataConfigDBMapper extends
    CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<FilePersistMetadataConfig>, CommonSelectMapper,
    CommonUpdateMapper {

  @SelectProvider(type=SqlProviderAdapter.class, method="select")
  Optional<FilePersistMetadataConfig> selectOne(SelectStatementProvider selectStatement);


  @SelectProvider(type=SqlProviderAdapter.class, method="select")
  List<FilePersistMetadataConfig> selectMany(SelectStatementProvider selectStatement);
}
