package com.hongguo.mysql.mybatis.model;

import com.hongguo.mysql.mybatis.enums.Enabled;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class SysRole implements Serializable {
    private static final long serialVersionUID = 2000462611200939036L;
    private Long id;
    private String roleName;
    private Enabled enabled;
    private CreateInfo createInfo;
    private Long createBy;
    private Date createTime;
    private List<SysPrivilege> privilegeList;
}

