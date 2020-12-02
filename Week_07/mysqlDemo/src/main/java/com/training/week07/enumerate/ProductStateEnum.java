package com.training.week07.enumerate;

/**
 * 商品状态
 *
 */
public enum ProductStateEnum implements BaseEnum {
    /**
     * 未上架
     */
    UNSHELF(1, "未上架"),
    /**
     * 己上架
     */
    SHELF(2, "己上架"),

    ;

    private Integer no;// 编号
    private String name;// 名字

    /**
     * 
     * @param no   编号
     * @param name 名字
     */
    private ProductStateEnum(Integer no, String name) {
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
