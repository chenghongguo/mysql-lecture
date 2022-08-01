package com.hongguo.mysql.jdbc.test;

import com.hongguo.mysql.jdbc.utils.DbUtils;
import org.junit.Test;

import java.sql.*;
import java.util.Map;

public class TestPreparedStatement {

    @Test
    public void testGreetings() throws Exception {
        try (Connection connection = DbUtils.getConnection();
             Statement statement = connection.createStatement()) {
            Map<String, String> greetingsSqlCommand = DbUtils.getSqlCommand("greetings-prepared.properties");
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.DROP.getCommand()));
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.CREATE.getCommand()));
            statement.executeUpdate(greetingsSqlCommand.get(SqlCommand.INSERT.getCommand()));
            PreparedStatement preparedStatement = connection.prepareStatement(greetingsSqlCommand.get(SqlCommand.QUERY.getCommand()));
            preparedStatement.setInt(1, 0);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    System.out.print(metaData.getColumnLabel(i));
                }
                System.out.println();
                while (resultSet.next()) {
                    for (int i = 1; i < columnCount; i++) {
                        if (i > 1) {
                            System.out.print(", ");
                        }
                        System.out.print(resultSet.getInt("id") + ", " + resultSet.getString("message"));
                    }
                    System.out.println();
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
