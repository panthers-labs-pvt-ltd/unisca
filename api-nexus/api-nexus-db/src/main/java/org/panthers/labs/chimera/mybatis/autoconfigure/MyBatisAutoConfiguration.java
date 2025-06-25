package org.panthers.labs.chimera.mybatis.autoconfigure;

import org.panthers.labs.chimera.mybatis.handler.LocalDateTimeTypeHandler;
import org.panthers.labs.chimera.mybatis.handler.LocalDateTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(MybatisProperties.class)
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
public class MyBatisAutoConfiguration {

  /** Customizes MyBatis configuration while preserving Spring Boot's defaults. */
  @Bean
  public ConfigurationCustomizer myBatisConfigurationCustomizer() {
    return configuration -> {
      configuration.setMapUnderscoreToCamelCase(true);
      configuration.setLazyLoadingEnabled(false);
      configuration
          .getTypeHandlerRegistry()
          .register(LocalDateTypeHandler.class, LocalDateTimeTypeHandler.class);
    };
  }
}
