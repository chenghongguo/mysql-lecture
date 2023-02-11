package com.hongguo.mysql.mybatis;

import org.apache.ibatis.io.Resources;
import org.junit.Test;

import java.io.InputStream;

public class ResourcesTest {

    @Test
    public void test() throws Exception {
        InputStream urlAsStream = Resources.getUrlAsStream("file:/Users/hongguo_cheng/java-workspace/study/mysql-lecture/mysql-mybatis-lecture/src/main/resources/config.properties");
    }
}
