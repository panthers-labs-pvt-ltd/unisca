package org.pantherslabs.chimera.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * Taken from <a
 * href="https://github.com/mybatis/mybatis-3/blob/master/src/main/java/org/apache/ibatis/type/LocalDateTypeHandler.java">mybatis-3</a>
 */
public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {

  @Override
  public void setNonNullParameter(
      PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType) throws SQLException {
    ps.setObject(i, parameter);
  }

  @Override
  public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return rs.getObject(columnName, LocalDate.class);
  }

  @Override
  public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return rs.getObject(columnIndex, LocalDate.class);
  }

  @Override
  public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return cs.getObject(columnIndex, LocalDate.class);
  }
}
