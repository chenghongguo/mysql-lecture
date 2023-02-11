package com.hongguo.mysql.mybatis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Country implements Serializable {
    private static final long serialVersionUID = 6624179006310607296L;
    private Integer id;
    private String countryName;
    private String countryCode;

}
