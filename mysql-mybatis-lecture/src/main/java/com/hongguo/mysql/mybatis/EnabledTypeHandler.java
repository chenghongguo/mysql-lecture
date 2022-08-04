package com.hongguo.mysql.mybatis;

import com.hongguo.mysql.mybatis.enums.Enabled;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnabledTypeHandler implements TypeHandler<Enabled> {

    private Map<Integer, Enabled> enabledMap;

    public EnabledTypeHandler() {
        enabledMap = Stream.of(Enabled.values()).collect(Collectors.toMap(Enabled::getValue, Function.identity()));
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, Enabled parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public Enabled getResult(ResultSet rs, String columnName) throws SQLException {
        Integer value = rs.getInt(columnName);
        return enabledMap.get(value);
    }

    @Override
    public Enabled getResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer value = rs.getInt(columnIndex);
        return enabledMap.get(value);
    }

    @Override
    public Enabled getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer value = cs.getInt(columnIndex);
        return enabledMap.get(value);
    }
}
