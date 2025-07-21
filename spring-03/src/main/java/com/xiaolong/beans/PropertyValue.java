package com.xiaolong.beans;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/1 11:51
 */
public class PropertyValue {

    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}
