package com.hongguo.mysql.mybatis.factory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.List;
import java.util.Properties;

public class MyObjectFactory extends DefaultObjectFactory {
    private static final long serialVersionUID = 8723485078604067826L;

    @Override
    public void setProperties(Properties properties) {
        System.out.println("定制属性：" + properties);
        super.setProperties(properties);
    }

    @Override
    public <T> T create(Class<T> type) {
        System.out.println("定制对象工厂的create方法构建单个对象");
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        System.out.println("定制对象工厂的create方法构建列表对象");
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return super.isCollection(type);
    }
}
