package com.hongguo.mysql.mybatis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysRolePrivilege {
    private Long roleId;
    private Long privilegeId;
}
