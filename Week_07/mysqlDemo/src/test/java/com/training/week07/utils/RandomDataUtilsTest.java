package com.training.week07.utils;

import lombok.extern.java.Log;

@Log
public class RandomDataUtilsTest {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(RandomDataUtils.getPhone());
        }
    }
}
