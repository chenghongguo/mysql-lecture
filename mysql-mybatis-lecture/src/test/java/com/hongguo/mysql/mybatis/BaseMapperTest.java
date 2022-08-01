package com.hongguo.mysql.mybatis;

import com.hongguo.mysql.mybatis.mapper.CountryMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.InputStream;

public class BaseMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            InputStream in = CountryMapper.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
            assert in != null;
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
