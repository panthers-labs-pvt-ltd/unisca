package org.pantherslabs.chimera.unisca.pipeline_metadata_api.configuration;

import org.pantherslabs.chimera.unisca.pipeline_metadata_api.common.exception.DatabaseException;
import org.pantherslabs.chimera.unisca.pipeline_metadata_api.entity.DbEntity;
import jakarta.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


/**
 * The type My batis configuration.
 */
@Configuration
@MapperScan("org.pantherslabs.chimera.unisca.pipeline_metadata_api.repository")
public class MyBatisConfiguration {

  @Resource(name = "dataSource")
  private DataSource dataSource;

  /**
   * Sql session factory.
   *
   * @return the sql session factory bean
   * @throws DatabaseException the database exception
   */
  @Bean
  public SqlSessionFactory sqlSessionFactory() throws DatabaseException {
    try {
      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
      // sqlSessionFactoryBean.setTypeHandlersPackage(LocalDateTimeTypeHandler.class.getPackage().getName());
      sqlSessionFactoryBean.setTypeAliasesPackage(DbEntity.class.getPackage().getName());
      sqlSessionFactoryBean.setDataSource(dataSource);
      SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
      sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
      sqlSessionFactory.getConfiguration().setLazyLoadingEnabled(true);
      sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
      return sqlSessionFactory;
    } catch (Exception e) {
      throw new DatabaseException("Error configuring db", e);
    }
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource);
  }
}
