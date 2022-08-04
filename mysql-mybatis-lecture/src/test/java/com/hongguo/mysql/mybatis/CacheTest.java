package com.hongguo.mysql.mybatis;

import com.hongguo.mysql.mybatis.mapper.RoleMapper;
import com.hongguo.mysql.mybatis.mapper.UserMapper;
import com.hongguo.mysql.mybatis.model.SysRole;
import com.hongguo.mysql.mybatis.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

public class CacheTest extends BaseMapperTest {

    @Test
    public void testL1Cache() {
        SqlSession sqlSession = getSqlSession();
        SysUser user1;
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user1 = mapper.selectById(1L);
            user1.setUserName("New Name");

            SysUser user2 = mapper.selectById(1L);
            Assert.assertEquals("New Name", user2.getUserName());
            Assert.assertEquals(user1, user2);
        } finally {
            sqlSession.close();
        }

        System.out.println("开启新的session");

        sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user2 = mapper.selectById(1L);
            Assert.assertNotEquals("New Name", user2.getUserName());
            Assert.assertNotEquals(user1, user2);

            mapper.deleteById(2L);

            SysUser user3 = mapper.selectById(1L);
            Assert.assertNotEquals(user2, user3);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testL2Cache() {
        SqlSession sqlSession = getSqlSession();
        SysRole role1;
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            role1 = mapper.selectById(1L);
            role1.setRoleName("New Name");

            SysRole role2 = mapper.selectById(1L);
            Assert.assertEquals("New Name", role2.getRoleName());
            Assert.assertEquals(role1, role2);
        } finally {
            sqlSession.close();
        }

        System.out.println("开启新的session");

        sqlSession = getSqlSession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role2 = mapper.selectById(1L);
            Assert.assertEquals("New Name", role2.getRoleName());
            Assert.assertNotEquals(role1, role2);

            SysRole role3 = mapper.selectById(1L);
            Assert.assertNotEquals(role2, role3);
        } finally {
            sqlSession.close();
        }
    }
}
