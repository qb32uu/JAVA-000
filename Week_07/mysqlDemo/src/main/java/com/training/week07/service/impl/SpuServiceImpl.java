package com.training.week07.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.week07.base.AbsBaseService;
import com.training.week07.base.IBaseDao;
import com.training.week07.entity.Spu;
import com.training.week07.service.ISpuService;
import com.training.week07.utils.CreateDataUtils;
import com.training.week07.utils.MockData;

@Service("spuService")
public class SpuServiceImpl extends AbsBaseService implements ISpuService {

    @Autowired
    private IBaseDao spuDao;

    @Transactional
    @Override
    public void randomAdd(int no) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            while (--no >= 0) {
                spuDao.insert(conn, CreateDataUtils.createRandomSpu(MockData.getRandomProductTypeId()));
            }

            List<Spu> spuList = spuDao.getAll(conn);
            MockData.setSpuIdList(spuList.stream().map(e -> e.getSpuId()).collect(Collectors.toList()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
    }

    @Override
    public List<Spu> getAll() {
        Connection conn = null;
        try {
            conn = this.getConnection();
            return spuDao.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
        return null;
    }
}
