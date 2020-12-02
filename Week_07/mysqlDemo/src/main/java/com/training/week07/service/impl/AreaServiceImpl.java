package com.training.week07.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.week07.base.IBaseDao;
import com.training.week07.base.AbsBaseService;
import com.training.week07.entity.Area;
import com.training.week07.service.IAreaService;
import com.training.week07.utils.CreateDataUtils;
import com.training.week07.utils.MockData;

@Service("areaService")
public class AreaServiceImpl extends AbsBaseService implements IAreaService {

    @Autowired
    private IBaseDao areaDao;

    @Transactional
    @Override
    public void randomAdd(int no) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            while (--no >= 0) {
                areaDao.insert(conn, CreateDataUtils.createRandomArea());
            }

            List<Area> arealist = areaDao.getAll(conn);
            MockData.setAreaIdList(arealist.stream().map(e -> e.getAreaId()).collect(Collectors.toList()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
    }

    @Override
    public List<Area> getAll() {
        Connection conn = null;
        try {
            conn = this.getConnection();
            return areaDao.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
        return null;
    }

}
