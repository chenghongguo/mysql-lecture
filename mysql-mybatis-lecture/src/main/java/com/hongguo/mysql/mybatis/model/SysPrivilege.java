package com.hongguo.mysql.mybatis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysPrivilege {
    private Long id;
    private String privilegeName;
    private String privilegeUrl;
}
