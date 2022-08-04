package com.hongguo.mysql.mybatis.mapper;

import com.hongguo.mysql.mybatis.model.SysRole;
import com.hongguo.mysql.mybatis.model.SysUser;

import java.util.List;

public interface UserMapper {

    int insert(SysUser sysUser);

    int insert2(SysUser sysUser);

    SysUser selectById(Long id);

    List<SysUser> selectAll();

    List<SysRole> selectRoleByUserId(Long userId);

    int updateById(SysUser sysUser);

    int deleteById(Long id);

    List<SysUser> selectByUser(SysUser sysUser);

    int updateByIdSelective(SysUser sysUser);

    SysUser selectByIdOrUserName(SysUser sysUser);

    List<SysUser> selectByIds(List<Long> ids);

    int insertList(List<SysUser> sysUsers);

    SysUser selectUserAndRoleById(Long id);

    SysUser selectUserAndRoleById2(Long id);

    SysUser selectUserAndRoleByIdSelect(Long id);

    List<SysUser> selectUserAndRoles();

    SysUser selectAllUserAndRolesSelect(Long id);
}
