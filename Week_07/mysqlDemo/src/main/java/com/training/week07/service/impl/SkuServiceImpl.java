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
import com.training.week07.entity.Sku;
import com.training.week07.service.ISkuService;
import com.training.week07.utils.CreateDataUtils;
import com.training.week07.utils.MockData;

@Service("skuService")
public class SkuServiceImpl extends AbsBaseService implements ISkuService {

    @Autowired
    private IBaseDao skuDao;

    @Transactional
    @Override
    public void randomAdd(int no) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            Random random = new Random();
            for (Long spuId : MockData.getSpuIdList()) {
                int randomNo = random.nextInt(no) + 1;
                for (int i = 0; i < randomNo; i++) {
                    skuDao.insert(conn, CreateDataUtils.createRandomSku(spuId));
                }
            }
            ;

            List<Sku> skuList = skuDao.getAll(conn);
            MockData.setSkuIdList(skuList.stream().map(e -> e.getSkuId()).collect(Collectors.toList()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
    }

    @Override
    public List<Sku> getAll() {
        Connection conn = null;
        try {
            conn = this.getConnection();
            return skuDao.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
        return null;
    }
}
