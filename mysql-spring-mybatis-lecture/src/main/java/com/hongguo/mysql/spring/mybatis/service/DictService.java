package com.hongguo.mysql.spring.mybatis.service;

import com.hongguo.mysql.spring.mybatis.model.SysDict;

import java.util.List;

public interface DictService {
    SysDict findById(Long id);

    List<SysDict> findBySysDict(SysDict sysDict, Integer offset, Integer limit);

    List<SysDict> findAll();

    boolean saveOrUpdate(SysDict sysDict);

    boolean deleteById(Long id);
}
