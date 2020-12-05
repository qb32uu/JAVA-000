package com.training.week07.rw;

import lombok.Data;

@Data
public class Rw implements java.io.Serializable {
    private static final long serialVersionUID = -5228588432824429721L;

    private Long rwId;
    private String name;
    private Long createTime;
    private Long changeTime;

    public Rw() {
        super();
    }

    public Rw(Long rwId, String name, Long createTime, Long changeTime) {
        super();
        this.rwId = rwId;
        this.name = name;
        this.createTime = createTime;
        this.changeTime = changeTime;
    }
}