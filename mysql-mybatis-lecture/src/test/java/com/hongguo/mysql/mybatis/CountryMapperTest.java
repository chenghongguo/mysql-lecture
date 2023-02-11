package com.hongguo.mysql.mybatis;

import com.hongguo.mysql.mybatis.model.Country;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CountryMapperTest extends BaseMapperTest {

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            List<Country> countryList = sqlSession.selectList("selectAll");
            printCountryList(countryList);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            Country c = new Country();
            c.setCountryCode("ZHZH");
            c.setCountryName("zhongguo");
            int count = sqlSession.insert("insert", c);
            sqlSession.commit();
            System.out.println(c);
        } finally {
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countries) {
        countries.forEach(c ->
                System.out.printf("%-4d%4s%4s\n", c.getId(), c.getCountryCode(), c.getCountryName()));
    }
}
