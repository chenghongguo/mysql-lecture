package com.hongguo.mysql.mybatis.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Enabled {
    disabled(0),
    enabled(1);

    private final int value;
}
