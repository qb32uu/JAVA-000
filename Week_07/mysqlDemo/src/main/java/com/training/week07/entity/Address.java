package com.training.week07.entity;

import lombok.Data;

@Data
public class Address implements java.io.Serializable {
    private static final long serialVersionUID = -3434230950335774324L;

    private Long addressId;
    private Long areaId;
    private String detailedAddress;
    private String recipient;
    private String phone;
    private Long userId;
    private Long createTime;
    private Long changeTime;

    public Address() {
        super();
    }

    public Address(Long addressId, Long areaId, String detailedAddress, String recipient, String phone, Long userId,
            Long createTime, Long changeTime) {
        super();
        this.addressId = addressId;
        this.areaId = areaId;
        this.detailedAddress = detailedAddress;
        this.recipient = recipient;
        this.phone = phone;
        this.userId = userId;
        this.createTime = createTime;
        this.changeTime = changeTime;
    }

}