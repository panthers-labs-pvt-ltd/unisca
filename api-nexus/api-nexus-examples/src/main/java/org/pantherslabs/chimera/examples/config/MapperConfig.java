package org.pantherslabs.chimera.examples.config;

import org.pantherslabs.chimera.examples.mapper.CustomDataPipelineMapper;
import org.pantherslabs.chimera.examples.mapper.CustomMetaDataPipelineMapper;
import org.pantherslabs.chimera.examples.mapper.generated.DataPipelineMapper;
import org.pantherslabs.chimera.examples.mapper.generated.MetaDataPipelineMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public CustomDataPipelineMapper customDataPipelineMapper(DataPipelineMapper dataPipelineMapper) {
        return new CustomDataPipelineMapper(dataPipelineMapper);
    }

    @Bean
    public CustomMetaDataPipelineMapper customMetaDataPipelineMapper(MetaDataPipelineMapper metaDataPipelineMapper) {
        return new CustomMetaDataPipelineMapper(metaDataPipelineMapper);
    }

}
