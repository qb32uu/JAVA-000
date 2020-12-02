package com.training.week07.entity;

import lombok.Data;

@Data
public class Area implements java.io.Serializable {
    private static final long serialVersionUID = -5228588432824429721L;

    private Long areaId;
    private String name;
    private Long createTime;
    private Long changeTime;

    public Area() {
        super();
    }

    public Area(Long areaId, String name, Long createTime, Long changeTime) {
        super();
        this.areaId = areaId;
        this.name = name;
        this.createTime = createTime;
        this.changeTime = changeTime;
    }
}