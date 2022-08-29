package com.hongguo.mysql.mybatis;

import org.apache.ibatis.reflection.TypeParameterResolver;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestType {
    SubClassA<Long> sa = new SubClassA<>();

    public static void main(String[] args) throws Exception {
        Field f = ClassA.class.getDeclaredField("map");
        System.out.println(f.getGenericType()); // java.util.Map<K, V>
        System.out.println(f.getDeclaringClass()); // class com.hongguo.mysql.mybatis.ClassA
        System.out.println(f.getGenericType() instanceof ParameterizedType); // true

        Type type = TypeParameterResolver.resolveFieldType(f,
                ParameterizedTypeImpl.make(SubClassA.class, new Type[]{Long.class}, TestType.class));
        System.out.println(type.getClass()); // class org.apache.ibatis.reflection.TypeParameterResolver$ParameterizedTypeImpl

        ParameterizedType p = (ParameterizedType) type;
        System.out.println(p.getRawType()); // interface java.util.Map
        System.out.println(p.getOwnerType()); // null

        for (Type t : p.getActualTypeArguments()) {
            System.out.println(t); // class java.lang.Long
        }
    }
}
