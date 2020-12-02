package com.training.week07.enumerate;

/**
 * 有效性
 *
 */
public enum ValidityEnum implements BaseEnum {
    /**
     * 有效
     */
    VALID(1, "有效"),
    /**
     * 无效
     */
    INVALID(2, "无效");

    private Integer no;// 编号
    private String name;// 名字

    /**
     * 
     * @param no   编号
     * @param name 名字
     */
    private ValidityEnum(Integer no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public Integer getNo() {
        return no;
    }

    @Override
    public String getName() {
        return name;
    }

}
