package com.hongguo.mysql.mybatis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SysRole {
    private Long id;
    private String roleName;
    private Integer enabled;
    private CreateInfo createInfo;
    private List<SysPrivilege> privilegeList;
}

