package com.hongguo.mysql.mybatis.model;

import com.hongguo.mysql.mybatis.enums.Enabled;
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
    private Enabled enabled;
    private CreateInfo createInfo;
    private List<SysPrivilege> privilegeList;
}

