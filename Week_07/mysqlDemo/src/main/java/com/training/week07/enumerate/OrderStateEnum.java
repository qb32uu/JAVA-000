package com.training.week07.enumerate;

/**
 * 订单状态
 *
 */
public enum OrderStateEnum implements BaseEnum {
    /**
     * 未付款
     */
    UNPAY(1, "未付款"),
    /**
     * 己付款
     */
    PAY(2, "己付款"),

    /**
     * 待收货
     */
    TO_BE_RECEIVED(3, "待收货"),

    /**
     * 己申请退款
     */
    TO_BE_REFUND(4, "己申请退款"),

    /**
     * 退款成功
     */
    REFUND(10, "退款成功"),

    /**
     * 己收货
     */
    RECEIVED(11, "己收货"),

    ;

    private Integer no;// 编号
    private String name;// 名字

    /**
     * 
     * @param no   编号
     * @param name 名字
     */
    private OrderStateEnum(Integer no, String name) {
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
