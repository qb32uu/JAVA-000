package com.training.week07.entity;

import lombok.Data;

@Data
public class Spu implements java.io.Serializable {
    private static final long serialVersionUID = 6257575316840232526L;

    private Long spuId;
    private String name;
    private Long productTypeId;
    private String code;
    private Integer productState;
    private Long createTime;
    private Long changeTime;

    public Spu() {
        super();
    }

    public Spu(Long spuId, String name, Long productTypeId, String code, Integer productState, Long createTime,
            Long changeTime) {
        super();
        this.spuId = spuId;
        this.name = name;
        this.productTypeId = productTypeId;
        this.code = code;
        this.productState = productState;
        this.createTime = createTime;
        this.changeTime = changeTime;
    }
}