package org.panthers.labs.chimera.examples.config;

import org.panthers.labs.chimera.examples.mapper.CustomDataPipelineMapper;
import org.panthers.labs.chimera.examples.mapper.CustomMetaDataPipelineMapper;
import org.panthers.labs.chimera.examples.mapper.generated.DataPipelineMapper;
import org.panthers.labs.chimera.examples.mapper.generated.MetaDataPipelineMapper;
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
