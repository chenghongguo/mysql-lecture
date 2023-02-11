package com.hongguo.mysql.mybatis.ognl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Stu {
    int age;
    String name;
    Pen pen;

    public static String sex;

    public void print(String msg) {
        System.out.println(msg + " " + age + name + pen);
    }

    public static String staticMethod(String msg) {
        return msg + ":static method result";
    }
}
