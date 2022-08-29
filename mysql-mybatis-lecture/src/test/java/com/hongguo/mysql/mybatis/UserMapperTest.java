package com.hongguo.mysql.mybatis;

import com.hongguo.mysql.mybatis.mapper.UserMapper;
import com.hongguo.mysql.mybatis.model.SysPrivilege;
import com.hongguo.mysql.mybatis.model.SysRole;
import com.hongguo.mysql.mybatis.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testInit() {
        getSqlSession();
    }

    @Test
    public void testSelectUserAndRolesSelect() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectAllUserAndRolesSelect(1L);
            Assert.assertNotNull(sysUser);
            System.out.println("用户名：" + sysUser.getUserName());
            for (SysRole sysRole : sysUser.getRoleList()) {
                System.out.println("\t角色名：" + sysRole.getRoleName());
                for (SysPrivilege privilege : sysRole.getPrivilegeList()) {
                    System.out.println("\t\t权限名：" + privilege.getPrivilegeName());
                }
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoles() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUsers = mapper.selectUserAndRoles();
            Assert.assertNotNull(sysUsers);
            System.out.println("用户数：" + sysUsers.size());
            for (SysUser sysUser : sysUsers) {
                System.out.println("用户名：" + sysUser.getUserName());
                for (SysRole sysRole : sysUser.getRoleList()) {
                    System.out.println("\t角色名：" + sysRole.getRoleName());
                    for (SysPrivilege privilege : sysRole.getPrivilegeList()) {
                        System.out.println("\t\t权限名：" + privilege.getPrivilegeName());
                    }
                }
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectUserAndRoleByIdSelect(1001L);
            Assert.assertNotNull(sysUser);
            System.out.println("调用sysUser.equals");
            sysUser.equals(null);
            System.out.println("调用sysUser.getRole()");
            Assert.assertNotNull(sysUser.getRole());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById2() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectUserAndRoleById2(1001L);
            Assert.assertNotNull(sysUser);
            Assert.assertNotNull(sysUser.getRole());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectUserAndRoleById(1001L);
            Assert.assertNotNull(sysUser);
            Assert.assertNotNull(sysUser.getRole());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUsers = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                SysUser sysUser = new SysUser();
                sysUser.setUserName("test" + i);
                sysUser.setUserPassword("123456");
                sysUser.setUserEmail("test@184.com");
                sysUser.setUserInfo("test list1");
                sysUser.setHeadImg(new byte[]{1, 3, 3});
                sysUser.setCreateTime(new Date());
                sysUsers.add(sysUser);
            }
            int count = mapper.insertList(sysUsers);
            for (SysUser sysUser : sysUsers) {
                System.out.println(sysUser.getId());
            }
            Assert.assertEquals(2, count);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIds() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<Long> ids = new ArrayList<>();
            ids.add(1L);
            ids.add(1001L);
            List<SysUser> sysUsers = mapper.selectByIds(ids);
            Assert.assertNotNull(sysUsers);
            Assert.assertTrue(sysUsers.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrUserName() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 只查ID
            SysUser query = new SysUser();
            query.setId(1001L);
            SysUser sysUser = mapper.selectByIdOrUserName(query);
            Assert.assertNotNull(sysUser);

            // 只查ID
            query = new SysUser();
            query.setUserName("test");
            SysUser sysUser1 = mapper.selectByIdOrUserName(query);
            Assert.assertNotNull(sysUser1);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelective() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectById(1001L);
            sysUser.setUserName("test");
            sysUser.setUserInfo("测试员");
            sysUser.setUserPassword("123abc");
            sysUser.setUserEmail("test@tk.com");
            sysUser.setHeadImg(new byte[]{2, 3, 4});
            int count = mapper.updateByIdSelective(sysUser);
            Assert.assertEquals(1, count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectBySysUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 只查用户名
            SysUser query = new SysUser();
            query.setUserName("admin");
            List<SysUser> sysUsers = mapper.selectByUser(query);
            Assert.assertNotNull(sysUsers);
            Assert.assertTrue(sysUsers.size() > 0);

            // 只查邮箱
            query = new SysUser();
            query.setUserEmail("test@163.com");
            sysUsers = mapper.selectByUser(query);
            Assert.assertTrue(sysUsers.size() > 0);

            // 查用户名&邮箱
            query = new SysUser();
            query.setUserName("test");
            query.setUserEmail("test@163.com");
            sysUsers = mapper.selectByUser(query);
            Assert.assertTrue(sysUsers.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = new SysUser();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("test@test.com");
            sysUser.setUserInfo("test info");
            sysUser.setHeadImg(new byte[]{1, 2, 3});
            sysUser.setCreateTime(new Date());
            int result = mapper.insert(sysUser);
            Assert.assertEquals(1, result);
            Assert.assertNull(sysUser.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = new SysUser();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserInfo("test info");
            sysUser.setHeadImg(new byte[]{1, 2, 3});
            sysUser.setCreateTime(new Date());
            int result = mapper.insert2(sysUser);
            Assert.assertEquals(1, result);
            SysUser sysUser1 = mapper.selectById(sysUser.getId());
            Assert.assertEquals("test@163.com", sysUser1.getUserEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectById(1L);
            Assert.assertNotNull(sysUser);
            Assert.assertEquals("admin", sysUser.getUserName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUsers = mapper.selectAll();
            Assert.assertNotNull(sysUsers);
            Assert.assertTrue(sysUsers.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> sysRoles = mapper.selectRoleByUserId(1L);
            Assert.assertNotNull(sysRoles);
            Assert.assertTrue(sysRoles.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = userMapper.selectById(1001L);
            sysUser.setUserInfo("admin_test info");
            sysUser.setUserName("admin_test");
            sysUser.setUserEmail("test@163.com");
            sysUser.setUserPassword("123");
            sysUser.setHeadImg(new byte[]{111});
            sysUser.setCreateTime(new Date());
            int count = userMapper.updateById(sysUser);
            Assert.assertEquals(1, count);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int count = userMapper.deleteById(1001L);
            Assert.assertEquals(1, count);
        } finally {
            sqlSession.close();
        }
    }
}
