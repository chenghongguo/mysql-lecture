package com.hongguo.mysql.spring.mybatis.mapper;

import com.hongguo.mysql.spring.mybatis.model.SysDict;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DictMapper {

    SysDict selectByPrimaryKey(Long id);

    List<SysDict> selectBySysDict(SysDict sysDict, RowBounds rowBounds);

    int insert(SysDict sysDict);

    int updateById(SysDict sysDict);

    int deleteById(Long id);

    List<SysDict> selectAll();
}
