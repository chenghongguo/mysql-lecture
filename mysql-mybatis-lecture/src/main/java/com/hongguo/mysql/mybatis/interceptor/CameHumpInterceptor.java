package com.hongguo.mysql.mybatis.interceptor;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.*;

@Intercepts(
        @Signature(
                type = ResultSetHandler.class,
                method = "handleResultSets",
                args = {Statement.class}
        )
)
public class CameHumpInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        List<Object> list = (List<Object>) invocation.proceed();
        for (Object object : list) {
            if (object instanceof Map) {
                processMap((Map) object);
            } else {
                break;
            }
        }
        return list;
    }

    private void processMap(Map<String, Object> map) {
        Set<String> keySet = new HashSet<>(map.keySet());
        for (String key : keySet) {
            // 将以大写开头的字符串转换为小写，如果包含下划线的也会处理为驼峰
            // 此处只通过两个简单的标识判断是否进行转换
            if ((key.charAt(0) >= 'A' && key.charAt(0) <= 'Z')
                    || key.contains("_")) {
                Object value = map.get(key);
                map.remove(key);
                map.put(underlineToCameHump(key), value);
            }
        }
    }

    private String underlineToCameHump(String inputString) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (c == '_') {
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
            } else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }
}
