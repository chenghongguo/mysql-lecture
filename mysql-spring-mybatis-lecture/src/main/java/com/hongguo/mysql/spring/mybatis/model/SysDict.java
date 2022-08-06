package com.hongguo.mysql.spring.mybatis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SysDict implements Serializable {
    private static final long serialVersionUID = 2303884363793452924L;

    private Long id;
    private String code;
    private String name;
    private String value;
}
