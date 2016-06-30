package com.wch.pojo.parser;

import com.wch.core.ClassUtils;

import javax.persistence.Table;

/**
 * Created by wch on 2016/5/23.
 */
public class TableParser {

    /**
     * @param clazz
     * @param <T>
     * @return
     */

    private String name;   // 表名

    public static <T> String parser( Class<T>  clazz) {

        Table table =  clazz.getAnnotation(Table.class);
        if (table != null) {
            String name = table.name();
            if (name != null) {
                return name;
            }
        }
        return null;
    }
}
