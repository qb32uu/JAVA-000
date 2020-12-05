package com.training.week07.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.week07.base.AbsBaseService;
import com.training.week07.base.IBaseDao;
import com.training.week07.entity.Address;
import com.training.week07.service.IAddressService;
import com.training.week07.utils.CreateDataUtils;
import com.training.week07.utils.MockData;

@Service("addressService")
public class AddressServiceImpl extends AbsBaseService implements IAddressService {

    @Autowired
    private IBaseDao addressDao;

    @Transactional
    @Override
    public void randomAdd(int no) {
        Connection conn = null;
        try {
            Random random = new Random();
            conn = this.getConnection();

            for (Long userId : MockData.getUserIdList()) {
                int randomNo = random.nextInt(no) + 1;
                for (int i = 0; i < randomNo; i++) {
                    addressDao.insert(conn, CreateDataUtils.createRandomAddress(userId, MockData.getRandomAreaId()));
                }
            }

            // 更新数据缓存
            List<Address> addressList = addressDao.getAll(conn);
            MockData.setAddressIdList(addressList.stream().map(e -> e.getAddressId()).collect(Collectors.toList()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
    }

    @Override
    public List<Address> getAll() {
        Connection conn = null;
        try {
            conn = this.getConnection();
            return addressDao.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
        return null;
    }
}
