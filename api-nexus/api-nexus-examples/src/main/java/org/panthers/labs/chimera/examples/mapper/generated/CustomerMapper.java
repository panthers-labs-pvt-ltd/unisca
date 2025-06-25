package org.panthers.labs.chimera.examples.mapper.generated;

import static org.panthers.labs.chimera.examples.mapper.generated.CustomerDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import org.panthers.labs.chimera.examples.model.generated.Customer;
import jakarta.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface CustomerMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Customer>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8423362+05:30", comments="Source Table: test.customer")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, email, createdAt);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8413346+05:30", comments="Source Table: test.customer")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CustomerResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Customer> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8413346+05:30", comments="Source Table: test.customer")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CustomerResult")
    Optional<Customer> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8413346+05:30", comments="Source Table: test.customer")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, customer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8413346+05:30", comments="Source Table: test.customer")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, customer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8413346+05:30", comments="Source Table: test.customer")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8413346+05:30", comments="Source Table: test.customer")
    default int insert(Customer row) {
        return MyBatis3Utils.insert(this::insert, row, customer, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(email).toProperty("email")
            .map(createdAt).toProperty("createdAt")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8413346+05:30", comments="Source Table: test.customer")
    default int insertMultiple(Collection<Customer> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, customer, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(email).toProperty("email")
            .map(createdAt).toProperty("createdAt")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8413346+05:30", comments="Source Table: test.customer")
    default int insertSelective(Customer row) {
        return MyBatis3Utils.insert(this::insert, row, customer, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(name).toPropertyWhenPresent("name", row::getName)
            .map(email).toPropertyWhenPresent("email", row::getEmail)
            .map(createdAt).toPropertyWhenPresent("createdAt", row::getCreatedAt)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8423362+05:30", comments="Source Table: test.customer")
    default Optional<Customer> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, customer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8423362+05:30", comments="Source Table: test.customer")
    default List<Customer> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, customer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8423362+05:30", comments="Source Table: test.customer")
    default List<Customer> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, customer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8423362+05:30", comments="Source Table: test.customer")
    default Optional<Customer> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8423362+05:30", comments="Source Table: test.customer")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, customer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8423362+05:30", comments="Source Table: test.customer")
    static UpdateDSL<UpdateModel> updateAllColumns(Customer row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(name).equalTo(row::getName)
                .set(email).equalTo(row::getEmail)
                .set(createdAt).equalTo(row::getCreatedAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8423362+05:30", comments="Source Table: test.customer")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Customer row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(name).equalToWhenPresent(row::getName)
                .set(email).equalToWhenPresent(row::getEmail)
                .set(createdAt).equalToWhenPresent(row::getCreatedAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8423362+05:30", comments="Source Table: test.customer")
    default int updateByPrimaryKey(Customer row) {
        return update(c ->
            c.set(name).equalTo(row::getName)
            .set(email).equalTo(row::getEmail)
            .set(createdAt).equalTo(row::getCreatedAt)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2025-05-22T21:46:35.8423362+05:30", comments="Source Table: test.customer")
    default int updateByPrimaryKeySelective(Customer row) {
        return update(c ->
            c.set(name).equalToWhenPresent(row::getName)
            .set(email).equalToWhenPresent(row::getEmail)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .where(id, isEqualTo(row::getId))
        );
    }
}