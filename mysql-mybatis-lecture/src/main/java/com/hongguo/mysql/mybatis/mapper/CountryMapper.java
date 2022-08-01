package com.hongguo.mysql.mybatis.mapper;

import com.hongguo.mysql.mybatis.model.Country;

import java.util.List;

public interface CountryMapper {
    List<Country> selectAll();
}
