package com.hongguo.mysql.mybatis;

import com.hongguo.mysql.mybatis.mapper.PrivilegeMapper;
import com.hongguo.mysql.mybatis.model.SysPrivilege;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

public class PrivilegeMapperTest extends BaseMapperTest {

    @Test
    public void testSelectId() {
        SqlSession sqlSession = getSqlSession();
        try {
            PrivilegeMapper mapper = sqlSession.getMapper(PrivilegeMapper.class);
            SysPrivilege sysPrivilege = mapper.selectById(1L);
            Assert.assertNotNull(sysPrivilege);
        } finally {
            sqlSession.close();
        }
    }
}
