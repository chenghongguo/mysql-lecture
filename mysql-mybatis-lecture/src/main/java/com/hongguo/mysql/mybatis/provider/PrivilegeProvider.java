package com.hongguo.mysql.mybatis.provider;

public class PrivilegeProvider {

    public String selectById(final Long id) {
        return "select id, privilege_name, privilege_url from sys_privilege where id = #{id}";
    }
}
