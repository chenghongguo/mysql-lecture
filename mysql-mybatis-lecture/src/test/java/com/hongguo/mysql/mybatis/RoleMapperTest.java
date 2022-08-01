package com.hongguo.mysql.mybatis;

import com.hongguo.mysql.mybatis.mapper.RoleMapper;
import com.hongguo.mysql.mybatis.model.SysRole;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void testSelectId() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = mapper.selectById(1L);
            Assert.assertNotNull(sysRole);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectId2() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = mapper.selectById2(1L);
            Assert.assertNotNull(sysRole);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> sysRoles = mapper.selectAll();
            sysRoles.forEach(System.out::println);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("test22");
            sysRole.setCreateTime(new Date());
            sysRole.setCreateBy(1L);
            sysRole.setEnabled(1);
            sysRole.setId(3L);
            int count = mapper.insert(sysRole);
            Assert.assertEquals(1, count);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("test22");
            sysRole.setCreateTime(new Date());
            sysRole.setCreateBy(1L);
            sysRole.setEnabled(1);
            int count = mapper.insert2(sysRole);
            Assert.assertEquals(1, count);
            Assert.assertEquals(5, sysRole.getId().longValue());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("test22");
            sysRole.setCreateTime(new Date());
            sysRole.setCreateBy(1L);
            sysRole.setEnabled(1);
            int count = mapper.insert3(sysRole);
            Assert.assertEquals(1, count);
            Assert.assertEquals(6, sysRole.getId().longValue());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
