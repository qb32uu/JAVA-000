package com.training.week07.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.training.week07.entity.Address;
import com.training.week07.entity.Area;
import com.training.week07.entity.ProductType;
import com.training.week07.entity.Sku;
import com.training.week07.entity.Spu;
import com.training.week07.entity.User;
import com.training.week07.utils.MockData;

import lombok.extern.java.Log;

@Log
@Component
@Order(Integer.MAX_VALUE)
public class InitializeHelper implements ApplicationRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IBaseDao areaDao;
    @Autowired
    private IBaseDao addressDao;
    @Autowired
    private IBaseDao productTypeDao;
    @Autowired
    private IBaseDao spuDao;
    @Autowired
    private IBaseDao skuDao;
    @Autowired
    private IBaseDao userDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("初始化");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();

            List<Area> arealist = areaDao.getAll(conn);
            MockData.setAreaIdList(arealist.stream().map(e -> e.getAreaId()).collect(Collectors.toList()));

            List<Address> addressList = addressDao.getAll(conn);
            MockData.setAddressIdList(addressList.stream().map(e -> e.getAddressId()).collect(Collectors.toList()));

            List<ProductType> productTypeList = productTypeDao.getAll(conn);
            MockData.setProductTypeIdList(
                    productTypeList.stream().map(e -> e.getProductTypeId()).collect(Collectors.toList()));

            List<Spu> spuList = spuDao.getAll(conn);
            MockData.setSpuIdList(spuList.stream().map(e -> e.getSpuId()).collect(Collectors.toList()));

            List<Sku> skuList = skuDao.getAll(conn);
            MockData.setSkuIdList(skuList.stream().map(e -> e.getSkuId()).collect(Collectors.toList()));

            List<User> userList = userDao.getAll(conn);
            MockData.setUserIdList(userList.stream().map(e -> e.getUserId()).collect(Collectors.toList()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
