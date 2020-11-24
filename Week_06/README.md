# 电商交易场景（用户、商品、订单），简单的表结构

```sql
CREATE TABLE address
(
	address_id           BIGINT AUTO_INCREMENT,
	area_id              BIGINT NOT NULL,
	detailed_address     VARCHAR(32) NOT NULL,
	recipient            VARCHAR(32) NOT NULL,
	phone                VARCHAR(32) NOT NULL,
	user_id              BIGINT NOT NULL,
	PRIMARY KEY (address_id)
);

CREATE TABLE area
(
	area_id              BIGINT AUTO_INCREMENT,
	name                 VARCHAR(32) NOT NULL,
	PRIMARY KEY (area_id)
);

CREATE TABLE order_product
(
	order_product_id     BIGINT AUTO_INCREMENT,
	orders_id            BIGINT NOT NULL,
	sku_id               BIGINT NOT NULL,
	no                   INTEGER NOT NULL,
	PRIMARY KEY (order_product_id)
);

CREATE TABLE orders
(
	orders_id            BIGINT AUTO_INCREMENT,
	area_id              BIGINT NOT NULL,
	detailed_address     VARCHAR(32) NOT NULL,
	recipient            VARCHAR(32) NOT NULL,
	phone                VARCHAR(32) NOT NULL,
	user_id              BIGINT NOT NULL,
	order_state          INTEGER NOT NULL,
	amount_total         INTEGER NULL,
	reduction            INTEGER NULL,
	create_time          BIGINT NOT NULL,
	pay_time             BIGINT NULL,
	PRIMARY KEY (orders_id)
);

CREATE TABLE shopping
(
	shopping_id          BIGINT AUTO_INCREMENT,
	user_id              BIGINT NULL,
	sku_id               BIGINT NULL,
	no                   INTEGER NOT NULL,
	PRIMARY KEY (shopping_id)
);

CREATE TABLE sku
(
	sku_id               BIGINT AUTO_INCREMENT,
	name                 VARCHAR(32) NOT NULL,
	code                 VARCHAR(32) NOT NULL,
	spu_id               BIGINT NOT NULL,
	price                INTEGER NOT NULL,
	create_time          BIGINT NOT NULL,
	change_time          BIGINT NOT NULL,
	stock_stock          INTEGER NOT NULL,
	PRIMARY KEY (sku_id)
);

CREATE TABLE spu
(
	spu_id               BIGINT AUTO_INCREMENT,
	name                 VARCHAR(32) NOT NULL,
	product_type         INTEGER NOT NULL,
	code                 VARCHAR(32) NOT NULL,
	create_time          BIGINT NOT NULL,
	change_time          BIGINT NOT NULL,
	product_state        INTEGER NOT NULL,
	PRIMARY KEY (spu_id)
);

CREATE UNIQUE INDEX XAKproduct_code ON spu
(
	code
);

CREATE TABLE user
(
	user_id              BIGINT AUTO_INCREMENT,
	nickname             VARCHAR(32) NOT NULL,
	account              VARCHAR(32) NOT NULL,
	validity             INTEGER NOT NULL,
	remark               VARCHAR(1024) NOT NULL,
	create_time          BIGINT NOT NULL,
	password             VARCHAR(32) NOT NULL,
	salt                 VARCHAR(32) NOT NULL,
	change_time          BIGINT NOT NULL,
	PRIMARY KEY (user_id)
);

CREATE UNIQUE INDEX XAK_user_account ON user
(
	account
);

alter TABLE address COMMENT = '地址'  ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
	ALTER TABLE address MODIFY COLUMN  `address_id` BIGINT AUTO_INCREMENT COMMENT '地址ID';
		
	ALTER TABLE address MODIFY COLUMN  `area_id` BIGINT NOT NULL COMMENT '区域ID';
		
	ALTER TABLE address MODIFY COLUMN  `detailed_address` VARCHAR(32) NOT NULL COMMENT '详细地址';
		
	ALTER TABLE address MODIFY COLUMN  `recipient` VARCHAR(32) NOT NULL COMMENT '收件人';
		
	ALTER TABLE address MODIFY COLUMN  `phone` VARCHAR(32) NOT NULL COMMENT '收件人手机';
		
	ALTER TABLE address MODIFY COLUMN  `user_id` BIGINT NOT NULL COMMENT '用户ID';
		
	
alter TABLE area COMMENT = '区域'  ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
	ALTER TABLE area MODIFY COLUMN  `area_id` BIGINT AUTO_INCREMENT COMMENT '区域ID';
		
	ALTER TABLE area MODIFY COLUMN  `name` VARCHAR(32) NOT NULL COMMENT '名称';
		
	
alter TABLE order_product COMMENT = '订单商品'  ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
	ALTER TABLE order_product MODIFY COLUMN  `order_product_id` BIGINT AUTO_INCREMENT COMMENT '订单商品ID';
		
	ALTER TABLE order_product MODIFY COLUMN  `orders_id` BIGINT NOT NULL COMMENT '订单ID';
		
	ALTER TABLE order_product MODIFY COLUMN  `sku_id` BIGINT NOT NULL COMMENT 'skuID';
		
	ALTER TABLE order_product MODIFY COLUMN  `no` INTEGER NOT NULL COMMENT '数量';
		
	
alter TABLE orders COMMENT = '订单'  ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
	ALTER TABLE orders MODIFY COLUMN  `orders_id` BIGINT AUTO_INCREMENT COMMENT '订单ID';
		
	ALTER TABLE orders MODIFY COLUMN  `area_id` BIGINT NOT NULL COMMENT '区域ID';
		
	ALTER TABLE orders MODIFY COLUMN  `detailed_address` VARCHAR(32) NOT NULL COMMENT '详细地址';
		
	ALTER TABLE orders MODIFY COLUMN  `recipient` VARCHAR(32) NOT NULL COMMENT '收件人';
		
	ALTER TABLE orders MODIFY COLUMN  `phone` VARCHAR(32) NOT NULL COMMENT '收件人手机';
		
	ALTER TABLE orders MODIFY COLUMN  `user_id` BIGINT NOT NULL COMMENT '用户ID';
		
	ALTER TABLE orders MODIFY COLUMN  `order_state` INTEGER NOT NULL COMMENT '订单状态';
		
	ALTER TABLE orders MODIFY COLUMN  `amount_total` INTEGER NULL COMMENT '总金额';
		
	ALTER TABLE orders MODIFY COLUMN  `reduction` INTEGER NULL COMMENT '减免';
		
	ALTER TABLE orders MODIFY COLUMN  `create_time` BIGINT NOT NULL COMMENT '创建时间';
		
	ALTER TABLE orders MODIFY COLUMN  `pay_time` BIGINT NULL COMMENT '支付时间';
		
	
alter TABLE shopping COMMENT = '购物车'  ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
	ALTER TABLE shopping MODIFY COLUMN  `shopping_id` BIGINT AUTO_INCREMENT COMMENT '购物车ID';
		
	ALTER TABLE shopping MODIFY COLUMN  `user_id` BIGINT NULL COMMENT '用户ID';
		
	ALTER TABLE shopping MODIFY COLUMN  `sku_id` BIGINT NULL COMMENT 'skuID';
		
	ALTER TABLE shopping MODIFY COLUMN  `no` INTEGER NOT NULL COMMENT '数量';
		
	
alter TABLE sku COMMENT = 'sku'  ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
	ALTER TABLE sku MODIFY COLUMN  `sku_id` BIGINT AUTO_INCREMENT COMMENT 'skuID';
		
	ALTER TABLE sku MODIFY COLUMN  `name` VARCHAR(32) NOT NULL COMMENT '名称';
		
	ALTER TABLE sku MODIFY COLUMN  `code` VARCHAR(32) NOT NULL COMMENT '编号';
		
	ALTER TABLE sku MODIFY COLUMN  `spu_id` BIGINT NOT NULL COMMENT 'spuID';
		
	ALTER TABLE sku MODIFY COLUMN  `price` INTEGER NOT NULL COMMENT '价格_分';
		
	ALTER TABLE sku MODIFY COLUMN  `create_time` BIGINT NOT NULL COMMENT '创建时间';
		
	ALTER TABLE sku MODIFY COLUMN  `change_time` BIGINT NOT NULL COMMENT '修改时间';
		
	ALTER TABLE sku MODIFY COLUMN  `stock_stock` INTEGER NOT NULL COMMENT '存货状态';
		
	
alter TABLE spu COMMENT = 'spu'  ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
	ALTER TABLE spu MODIFY COLUMN  `spu_id` BIGINT AUTO_INCREMENT COMMENT 'spuID';
		
	ALTER TABLE spu MODIFY COLUMN  `name` VARCHAR(32) NOT NULL COMMENT '名称';
		
	ALTER TABLE spu MODIFY COLUMN  `product_type` INTEGER NOT NULL COMMENT '商品分类';
		
	ALTER TABLE spu MODIFY COLUMN  `code` VARCHAR(32) NOT NULL COMMENT '编号';
		
	ALTER TABLE spu MODIFY COLUMN  `change_time` BIGINT NOT NULL COMMENT '修改时间';
		
	ALTER TABLE spu MODIFY COLUMN  `create_time` BIGINT NOT NULL COMMENT '创建时间';
		
	ALTER TABLE spu MODIFY COLUMN  `product_state` INTEGER NOT NULL COMMENT '商品状态';
		
	
alter TABLE user COMMENT = '用户'  ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
	ALTER TABLE user MODIFY COLUMN  `user_id` BIGINT AUTO_INCREMENT COMMENT '用户ID';
		
	ALTER TABLE user MODIFY COLUMN  `nickname` VARCHAR(32) NOT NULL COMMENT '昵称';
		
	ALTER TABLE user MODIFY COLUMN  `account` VARCHAR(32) NOT NULL COMMENT '帐号';
		
	ALTER TABLE user MODIFY COLUMN  `validity` INTEGER NOT NULL COMMENT '有效性';
		
	ALTER TABLE user MODIFY COLUMN  `remark` VARCHAR(1024) NOT NULL COMMENT '备注';
		
	ALTER TABLE user MODIFY COLUMN  `create_time` BIGINT NOT NULL COMMENT '创建时间';
		
	ALTER TABLE user MODIFY COLUMN  `password` VARCHAR(32) NOT NULL COMMENT '密码';
		
	ALTER TABLE user MODIFY COLUMN  `salt` VARCHAR(32) NOT NULL COMMENT '盐';
		
	ALTER TABLE user MODIFY COLUMN  `change_time` BIGINT NOT NULL COMMENT '修改时间';		
```

