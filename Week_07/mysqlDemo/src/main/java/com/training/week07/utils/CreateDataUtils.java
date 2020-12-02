package com.training.week07.utils;

import java.util.Random;
import java.util.UUID;

import com.training.week07.entity.Address;
import com.training.week07.entity.Area;
import com.training.week07.entity.Orders;
import com.training.week07.entity.ProductType;
import com.training.week07.entity.Sku;
import com.training.week07.entity.Spu;
import com.training.week07.entity.User;
import com.training.week07.enumerate.OrderStateEnum;
import com.training.week07.enumerate.ProductStateEnum;
import com.training.week07.enumerate.StockStateEnum;
import com.training.week07.enumerate.ValidityEnum;

public class CreateDataUtils {
    private final static Random RANDOM = new Random();

    /**
     * 生成随机用户
     * 
     * @return
     */
    public static User createRandomUser() {
        User result = new User();
        result.setNickname(RandomDataUtils.getCommonString(RANDOM.nextInt(2) + 2));
        result.setAccount(UUID.randomUUID().toString().replace("-", ""));
        result.setValidity(ValidityEnum.VALID.getNo());
        result.setRemark(RandomDataUtils.getRandomString(RANDOM.nextInt(20)));
        result.setSalt(UUID.randomUUID().toString().replace("-", ""));
        result.setPassword(HashUtils.getPasswordBySale(UUID.randomUUID().toString(), result.getSalt()));
        result.setCreateTime(System.currentTimeMillis() - RANDOM.nextInt(1000 * 3600 * 24 * 365));
        result.setChangeTime(result.getCreateTime() + 1000L * 3600 * 24 * RANDOM.nextInt(30));
        return result;
    }

    /**
     * 生成随机区域
     * 
     * @return
     */
    public static Area createRandomArea() {
        Area result = new Area();
        result.setName(RandomDataUtils.getCommonString(2) + "区");
        result.setCreateTime(System.currentTimeMillis() - RANDOM.nextInt(1000 * 3600 * 24 * 365));
        result.setChangeTime(result.getCreateTime() + 1000L * 3600 * 24 * RANDOM.nextInt(30));
        return result;
    }

    /**
     * 生成随机地址
     * 
     * @param userId 用户id
     * @param areaId 地区id
     * @return
     */
    public static Address createRandomAddress(Long userId, Long areaId) {
        Address result = new Address();
        result.setAreaId(areaId);
        result.setDetailedAddress(RandomDataUtils.getCommonString(RANDOM.nextInt(5) + 5));
        result.setPhone(RandomDataUtils.getPhone());
        result.setRecipient(RandomDataUtils.getCommonString(RANDOM.nextInt(2) + 2));
        result.setUserId(userId);
        result.setCreateTime(System.currentTimeMillis() - RANDOM.nextInt(1000 * 3600 * 24 * 365));
        result.setChangeTime(result.getCreateTime() + 1000L * 3600 * 24 * RANDOM.nextInt(30));
        return result;
    }

    /**
     * 生成随机商品类型
     * 
     * @return
     */
    public static ProductType createRandomProductType() {
        ProductType result = new ProductType();
        result.setName(RandomDataUtils.getCommonString(3) + "类");
        result.setCreateTime(System.currentTimeMillis() - RANDOM.nextInt(1000 * 3600 * 24 * 365));
        result.setChangeTime(result.getCreateTime() + 1000L * 3600 * 24 * RANDOM.nextInt(30));
        return result;
    }

    /**
     * 生成随机商品
     * 
     * @return
     */
    public static Spu createRandomSpu(Long productTypeId) {
        Spu result = new Spu();
        result.setCode(UUID.randomUUID().toString().replace("-", ""));
        result.setName("【" + RandomDataUtils.getCommonString(RANDOM.nextInt(3) + 2) + "】");
        result.setProductState(ProductStateEnum.SHELF.getNo());
        result.setProductTypeId(productTypeId);
        result.setCreateTime(System.currentTimeMillis() - RANDOM.nextInt(1000 * 3600 * 24 * 365));
        result.setChangeTime(result.getCreateTime() + 1000L * 3600 * 24 * RANDOM.nextInt(30));
        return result;
    }

    /**
     * 生成随机库存
     * 
     * @return
     */
    public static Sku createRandomSku(Long spuId) {
        Sku result = new Sku();
        result.setName(RandomDataUtils.getCommonString(RANDOM.nextInt(3) + 2) + "版");
        result.setCode(UUID.randomUUID().toString().replace("-", ""));
        result.setSpuId(spuId);
        result.setPrice(RANDOM.nextInt(1000_00));
        result.setNo(1_000_000_000);// 数量固定
        result.setStockState(StockStateEnum.SHELF.getNo());
        result.setCreateTime(System.currentTimeMillis() - RANDOM.nextInt(1000 * 3600 * 24 * 365));
        result.setChangeTime(result.getCreateTime() + 1000L * 3600 * 24 * RANDOM.nextInt(30));
        return result;
    }

    /**
     * 生成随机订单
     * 
     * @param addressId
     * @return
     */
    public static Orders createRandomOrders(Long areaId, Long userId) {
        Orders result = new Orders();
        result.setAreaId(areaId);
        result.setDetailedAddress("detailedAddress");
        result.setRecipient("recipient");
        result.setPhone("phone");
        result.setUserId(userId);
        result.setOrderState(OrderStateEnum.UNPAY.getNo());
        result.setAmountTotal(RANDOM.nextInt(1000_00));
        result.setCreateTime(System.currentTimeMillis() - RANDOM.nextInt(1000 * 3600 * 24 * 365));
        result.setPayTime(result.getCreateTime() + 1000L * 3600 * 24 * RANDOM.nextInt(30));
        return result;
    }
}
