package com.hongguo.mysql.jdbc.test;

import com.hongguo.mysql.jdbc.utils.DbUtils;
import org.junit.Test;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class TestStatement {

    @Test
    public void testGreetings() throws Exception {
        try (Connection connection = DbUtils.getConnection();
             Statement statement = connection.createStatement()) {
            Map<String, String> greetingsSqlCommand = DbUtils.getSqlCommand("greetings.properties");
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.DROP.getCommand()));
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.CREATE.getCommand()));
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.INSERT.getCommand()));

            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            try (ResultSet resultSet = statement.executeQuery(greetingsSqlCommand.get(SqlCommand.QUERY.getCommand()));
                 CachedRowSet cachedRowSet = rowSetFactory.createCachedRowSet()) {
                cachedRowSet.populate(resultSet);
                while (cachedRowSet.next()) {
                    System.out.println(cachedRowSet.getInt(1) + ", " + cachedRowSet.getString(2) + ", " + cachedRowSet.getTimestamp(3));
                }
            }
        }
    }

    @Test
    public void testGreetingsWithReturnGeneratedKeys() throws Exception {
        try (Connection connection = DbUtils.getConnection();
             Statement statement = connection.createStatement()) {
            Map<String, String> greetingsSqlCommand = DbUtils.getSqlCommand("greetings.properties");
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.DROP.getCommand()));
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.CREATE.getCommand()));
            // 返回生成主键
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.INSERT.getCommand()), Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                int anInt = resultSet.getInt(1);
                System.out.println(anInt);
            }

            try (ResultSet rs = statement.executeQuery(greetingsSqlCommand.get(SqlCommand.QUERY.getCommand()))) {
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getTimestamp(3));
                }
            }
        }
    }

    @Test
    public void testGreetingsWithInsertRow() throws Exception {
        try (Connection connection = DbUtils.getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            Map<String, String> greetingsSqlCommand = DbUtils.getSqlCommand("greetings.properties");
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.DROP.getCommand()));
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.CREATE.getCommand()));
            // 返回生成主键
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.INSERT.getCommand()), Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                int anInt = resultSet.getInt(1);
                System.out.println(anInt);
            }

            try (ResultSet rs = statement.executeQuery(greetingsSqlCommand.get(SqlCommand.QUERY.getCommand()))) {
                while (rs.next()) {
                    int sum = rs.getInt("sum");
                    rs.updateInt("sum", sum + 100);
                    rs.updateRow();
                }
            }
        }
    }

    @Test
    public void testGreetingsWithUpdateRow() throws Exception {
        try (Connection connection = DbUtils.getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            Map<String, String> greetingsSqlCommand = DbUtils.getSqlCommand("greetings.properties");
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.DROP.getCommand()));
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.CREATE.getCommand()));
            // 返回生成主键
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.INSERT.getCommand()), Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                int anInt = resultSet.getInt(1);
                System.out.println(anInt);
            }

            try (ResultSet rs = statement.executeQuery(greetingsSqlCommand.get(SqlCommand.QUERY.getCommand()))) {
                while (rs.next()) {
                    int sum = rs.getInt("sum");
                    rs.updateInt("sum", sum + 100);
                    rs.updateRow();
                }
            }
        }
    }

    @Test
    public void testReadSqlCommand() throws Exception {
        Map<String, String> sqlCommand = DbUtils.getSqlCommand("greetings.properties");
        System.out.println(sqlCommand);
    }
}
