package com.training.week07.entity;

import lombok.Data;

@Data
public class Shopping implements java.io.Serializable {
    private static final long serialVersionUID = 2601997627072813146L;

    private Long shoppingId;
    private Long userId;
    private Long skuId;
    private Integer no;
}