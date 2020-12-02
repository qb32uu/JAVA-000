package com.training.week07.entity;

import lombok.Data;

@Data
public class OrderProduct implements java.io.Serializable {
    private static final long serialVersionUID = -2881595548714855679L;

    private Long orderProductId;
    private Long ordersId;
    private Long skuId;
    private Integer price;
    private Integer no;

    public OrderProduct(Long orderProductId, Long ordersId, Long skuId, Integer price, Integer no) {
        super();
        this.orderProductId = orderProductId;
        this.ordersId = ordersId;
        this.skuId = skuId;
        this.price = price;
        this.no = no;
    }

    public OrderProduct() {
        super();
    }
}