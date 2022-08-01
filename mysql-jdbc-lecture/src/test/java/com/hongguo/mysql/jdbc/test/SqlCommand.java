package com.hongguo.mysql.jdbc.test;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SqlCommand {
    CREATE("create"),
    DROP("drop"),
    INSERT("insert"),
    QUERY("query");

    private final String command;

    private static final Map<String, SqlCommand> sqlCommandMap =
            Arrays.stream(SqlCommand.values()).collect(Collectors.toMap(SqlCommand::getCommand, Function.identity()));

    SqlCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static boolean contains(String command) {
        return sqlCommandMap.containsKey(command);
    }
}
