package io.cloud.datasource.mybatis;

import java.lang.annotation.*;

/**
 * @program: wsw-cloud-servce
 * @description: 雪花主键
 * @author: wsw
 * @create: 2020-04-29 17:31
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoId {

    /**
     * @return id类型（默认为雪花id）
     */
    IdType value() default IdType.SNOWFLAKE;

    /**
     * id类型
     */
    enum IdType {
        /**
         * UUID去掉“-”
         */
        UUID,
        /**
         * 雪花id
         */
        SNOWFLAKE
    }

}
