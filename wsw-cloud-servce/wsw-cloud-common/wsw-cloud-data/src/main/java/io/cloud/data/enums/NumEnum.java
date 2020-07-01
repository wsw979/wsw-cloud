package io.cloud.data.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: wsw-cloud-servce
 * @description: 常量枚举
 * @author: wsw
 * @create: 2020-06-13 14:08
 **/
public enum NumEnum {

    /**
     * 0
     */
    ZERO(0, "0"),
    /**
     * 1
     */
    ONE(1, "1"),
    /**
     * 2
     */
    TWO(2, "2"),
    /**
     * 3
     */
    THREE(3, "3"),
    /**
     * 4
     */
    FOUR(4, "4"),
    /**
     * 5
     */
    FIVE(5, "5");


    NumEnum(Integer k, String v) {
        this.k = k;
        this.v = v;
    }

    @Getter
    @Setter
    private Integer k;
    @Getter
    @Setter
    private String v;
}
