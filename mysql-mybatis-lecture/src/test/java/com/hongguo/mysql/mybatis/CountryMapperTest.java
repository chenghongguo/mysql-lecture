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

    private void printCountryList(List<Country> countries) {
        countries.forEach(c ->
                System.out.printf("%-4d%4s%4s\n", c.getId(), c.getCountryname(), c.getCountrycode()));
    }
}
