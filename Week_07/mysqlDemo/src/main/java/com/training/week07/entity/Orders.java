package com.training.week07.entity;

import lombok.Data;

@Data
public class Orders implements java.io.Serializable {
    private static final long serialVersionUID = -4086821997531182701L;

    private Long ordersId;
    private Long areaId;
    private String detailedAddress;
    private String recipient;
    private String phone;
    private Long userId;
    private Integer orderState;
    private Integer amountTotal;
    private Long createTime;
    private Long payTime;

    public Orders(Long ordersId, Long areaId, String detailedAddress, String recipient, String phone, Long userId,
            Integer orderState, Integer amountTotal, Long createTime, Long payTime) {
        super();
        this.ordersId = ordersId;
        this.areaId = areaId;
        this.detailedAddress = detailedAddress;
        this.recipient = recipient;
        this.phone = phone;
        this.userId = userId;
        this.orderState = orderState;
        this.amountTotal = amountTotal;
        this.createTime = createTime;
        this.payTime = payTime;
    }

    public Orders() {
        super();
    }
}