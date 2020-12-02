package com.training.week07.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 模拟数据
 * 
 * @author Billion
 *
 */
public class MockData {
    private final static Random RANDOM = new Random();

    private static List<Long> areaIdList;
    private static int areaIdListSize;
    private static List<Long> addressIdList;
    private static int addressIdListSize;

    private static List<Long> userIdList;
    private static int userIdListSize;

    private static List<Long> productTypeIdList;
    private static int productTypeIdListSize;
    private static List<Long> spuIdList;
    private static List<Long> skuIdList;
    private static int skuIdListSize;

    // *******************设置
    public static void setAreaIdList(List<Long> areaIdList) {
        MockData.areaIdList = areaIdList;
        MockData.areaIdListSize = MockData.areaIdList.size();
    }

    public static void setAddressIdList(List<Long> addressIdList) {
        MockData.addressIdList = addressIdList;
        MockData.addressIdListSize = MockData.addressIdList.size();
    }

    public static void setUserIdList(List<Long> userIdList) {
        MockData.userIdList = userIdList;
        MockData.userIdListSize = MockData.userIdList.size();
    }

    public static void setProductTypeIdList(List<Long> productTypeIdList) {
        MockData.productTypeIdList = productTypeIdList;
    }

    public static void setSpuIdList(List<Long> spuIdList) {
        MockData.spuIdList = spuIdList;
    }

    public static void setSkuIdList(List<Long> skuIdList) {
        MockData.skuIdList = skuIdList;
        MockData.skuIdListSize = MockData.skuIdList.size();
    }

    // **生成基础数据及模拟测试时的用户数据
    public static List<Long> getSpuIdList() {
        return spuIdList;
    }

    public static Long getRandomAddressId() {
        return MockData.addressIdList.get(RANDOM.nextInt(MockData.addressIdListSize));
    }

    public static Long getRandomAreaId() {
        return MockData.areaIdList.get(RANDOM.nextInt(MockData.areaIdListSize));
    }

    public static Long getRandomUserId() {
        return MockData.userIdList.get(RANDOM.nextInt(MockData.userIdListSize));
    }

    public static Long getRandomProductTypeId() {
        return productTypeIdList.get(RANDOM.nextInt(productTypeIdListSize));
    }

    /**
     * 获取模拟购物的商品，skuId->计划购买数
     * 
     * 最少买1个商品最多买5个，每个商品最少买1个最多买10个
     * 
     * @return
     */
    public static Map<Long, Integer> getRandomSkuMap() {
        Map<Long, Integer> result = new HashMap<>();

        int i = RANDOM.nextInt(5) + 1;
        while (--i >= 0) {
            result.put(MockData.skuIdList.get(RANDOM.nextInt(MockData.skuIdListSize)), RANDOM.nextInt(10) + 1);
        }
        return result;
    }
}
