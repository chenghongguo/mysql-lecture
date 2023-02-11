package com.hongguo.mysql.mybatis.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import org.junit.Before;
import org.junit.Test;

public class OgnlTest {

    private OgnlContext context;

    @Before
    public void before() {
        context = (OgnlContext) Ognl.createDefaultContext(this);
    }

    @Test
    public void testBase() throws Exception {
        context.put("a", "a");
        context.put("b", "b");
        context.setRoot("a");
        System.out.println(context.entrySet());

        Object ex = Ognl.parseExpression("#b");
        Object value = Ognl.getValue(ex, context, context.getRoot());
        System.out.println(value);
    }

    @Test
    public void testFieldAndMethod() throws Exception {
        Pen pen = new Pen(3, "red");
        Stu stu = new Stu(18, "zuo", pen);

        context.put("pen", pen);
        context.put("stu", stu);
        context.put("test", "T");

        context.setRoot(pen);

        Object expression = Ognl.parseExpression("color");
        Object value = Ognl.getValue(expression, context, context.getRoot());
        System.out.println(value);

        // Filed
        expression = Ognl.parseExpression("#stu.name,#stu.age");
        value = Ognl.getValue(expression, context, context.getRoot());
        System.out.println(value);

        // Method
        expression = Ognl.parseExpression("#stu.print(\"hello\")");
        value = Ognl.getValue(expression, context, context.getRoot());
        System.out.println(value);

        expression = Ognl.parseExpression("#pen.getColor()");
        value = Ognl.getValue(expression, context, context.getRoot());
        System.out.println(value);
    }

    @Test
    public void testMethod() throws Exception {
        Pen pen = new Pen(3, "red");
        Stu stu = new Stu(18, "zuo", pen);

        context.put("pen", pen);
        context.put("stu", stu);
        context.put("test", "T");

        context.setRoot(this);

        Object expression = Ognl.parseExpression("#stu.print(\"hello\")");
        Object value = Ognl.getValue(expression, context, context.getRoot());
        System.out.println("value = " + value);

        expression = Ognl.parseExpression("#pen.getColor().(#this + \"hhhhh\")");
        value = Ognl.getValue(expression, context, context.getRoot());
        System.out.println(value);
    }

    @Test
    public void testStaticProp() throws Exception {
        Pen pen = new Pen(3, "red");
        Stu stu = new Stu(18, "zuo", pen);

        context.put("pen", pen);
        context.put("stu", stu);
        context.put("test", "T");

        context.setRoot(this);
        Stu.sex = "MAN";

        Object expression = Ognl.parseExpression("@com.hongguo.mysql.mybatis.ognl.Stu@sex");
        Object value = Ognl.getValue(expression, context, context.getRoot());
        System.out.println(value);

        expression = Ognl.parseExpression("@com.hongguo.mysql.mybatis.ognl.Stu@staticMethod(\"hello\")");
        value = Ognl.getValue(expression, context, context.getRoot());
        System.out.println(value);
    }

    @Test
    public void testArray() throws Exception {
        Object expression = Ognl.parseExpression("#a=1,#b=2,#c=3,{#a,#b,#c}");
        Object value = Ognl.getValue(expression, context, context.getRoot());
        System.out.println(value);
        System.out.println(value.getClass());
    }
}
