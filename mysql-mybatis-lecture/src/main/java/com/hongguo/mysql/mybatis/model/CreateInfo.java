package com.hongguo.mysql.mybatis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CreateInfo {

    private Long createBy;

    private Date createTime;
}
