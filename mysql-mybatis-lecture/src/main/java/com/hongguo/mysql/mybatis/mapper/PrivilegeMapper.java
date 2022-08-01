package com.hongguo.mysql.mybatis.mapper;

import com.hongguo.mysql.mybatis.model.SysPrivilege;
import com.hongguo.mysql.mybatis.provider.PrivilegeProvider;
import org.apache.ibatis.annotations.SelectProvider;

public interface PrivilegeMapper {
    @SelectProvider(type = PrivilegeProvider.class, method = "selectById")
    SysPrivilege selectById(Long id);
}
