package com.hongguo.mysql.mybatis;

import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.junit.Test;

public class ParameterTokenizerTest {

    @Test
    public void test() {
        PropertyTokenizer tokenizer = new PropertyTokenizer("orders[0].items[0].name");
        System.out.println(tokenizer.getName());
        System.out.println(tokenizer.getIndex());
        System.out.println(tokenizer.getChildren());
        System.out.println(tokenizer.getIndexedName());
        System.out.println("====1111====");

        if (tokenizer.hasNext()) {
            PropertyTokenizer next = tokenizer.next();
            System.out.println(next.getName());
            System.out.println(next.getIndex());
            System.out.println(next.getChildren());
            System.out.println(next.getIndexedName());
            System.out.println("========");
        }
    }
}
