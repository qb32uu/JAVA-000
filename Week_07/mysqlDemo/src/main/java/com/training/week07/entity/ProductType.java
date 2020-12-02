package com.training.week07.entity;

import lombok.Data;

@Data
public class ProductType implements java.io.Serializable {
    private static final long serialVersionUID = 3669148840840789269L;

    private Long productTypeId;
    private String name;
    private Long createTime;
    private Long changeTime;

    public ProductType() {
        super();
    }

    public ProductType(Long productTypeId, String name, Long createTime, Long changeTime) {
        super();
        this.productTypeId = productTypeId;
        this.name = name;
        this.createTime = createTime;
        this.changeTime = changeTime;
    }
}