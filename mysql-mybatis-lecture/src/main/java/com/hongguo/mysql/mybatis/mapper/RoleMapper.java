package com.hongguo.mysql.mybatis.mapper;

import com.hongguo.mysql.mybatis.handler.EnabledTypeHandler;
import com.hongguo.mysql.mybatis.model.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

//@CacheNamespace(
//        eviction = FifoCache.class,
//        flushInterval = 60000,
//        size = 512,
//        readWrite = true
//)
@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper {

    @Select("select id, role_name as roleName, enabled, create_by as createBy, create_time as createTime " +
            "from sys_role " +
            "where id = #{id}")
    SysRole selectById(Long id);

    SysRole selectRoleById(Long id);

    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled", typeHandler = EnabledTypeHandler.class),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("select id, role_name, enabled, create_by, create_time " +
            "from sys_role where id = #{id}")
    SysRole selectById2(Long id);

    @ResultMap("roleResultMap")
    @Select("select id, role_name, enabled, create_by , create_time from sys_role")
    List<SysRole> selectAll();

    @Select("select id, role_name, enabled, create_by, create_time from sys_role")
    List<Map<String, Object>> selectAllWithMap();

    @Insert("insert into sys_role (id, role_name, enabled, create_by, create_time)" +
            " values (#{id}, #{roleName}, #{enabled}, #{createBy},#{createTime, jdbcType=TIMESTAMP})")
    int insert(SysRole role);

    @Insert("insert into sys_role (role_name, enabled, create_by, create_time)" +
            " values (#{roleName}, #{enabled}, #{createBy},#{createTime, jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert2(SysRole role);

    @Insert("insert into sys_role (role_name, enabled, create_by, create_time)" +
            " values (#{roleName}, #{enabled}, #{createBy},#{createTime, jdbcType=TIMESTAMP})")
    @SelectKey(statement = "select last_insert_id()",
            keyProperty = "id",
            resultType = Long.class,
            before = false)
    int insert3(SysRole role);

    int updateById(SysRole sysRole);
}
