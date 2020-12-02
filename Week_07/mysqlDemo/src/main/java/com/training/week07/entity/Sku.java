package com.training.week07.entity;

import lombok.Data;

@Data
public class Sku implements java.io.Serializable {
    private static final long serialVersionUID = 4880527883128924101L;

    private Long skuId;
    private String name;
    private String code;
    private Long spuId;
    private Integer price;
    private Integer no;
    private Integer stockState;
    private Long createTime;
    private Long changeTime;

    public Sku(Long skuId, String name, String code, Long spuId, Integer price, Integer no, Integer stockState,
            Long createTime, Long changeTime) {
        super();
        this.skuId = skuId;
        this.name = name;
        this.code = code;
        this.spuId = spuId;
        this.price = price;
        this.no = no;
        this.stockState = stockState;
        this.createTime = createTime;
        this.changeTime = changeTime;
    }

    public Sku() {
        super();
    }
}