package com.training.week07.rw;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.week07.base.AbsBaseService;
import com.training.week07.base.IBaseDao;
import com.training.week07.utils.RandomDataUtils;

@Service("rwService")
public class RwServiceImpl extends AbsBaseService implements IRwService {
    private final static Random RANDOM = new Random();

    @Autowired
    private IBaseDao rwDao;

    @Transactional
    @Override
    public void randomAdd(int no) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            while (--no >= 0) {
                rwDao.insert(conn, this.createRandomRw());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
    }

    /**
     * 生成读定测试表数据
     * 
     * @return
     */
    private Rw createRandomRw() {
        Rw result = new Rw();
        result.setName("读写名{" + RandomDataUtils.getCommonString(2) + "}");
        result.setCreateTime(System.currentTimeMillis() - RANDOM.nextInt(1000 * 3600 * 24 * 365));
        result.setChangeTime(result.getCreateTime() + 1000L * 3600 * 24 * RANDOM.nextInt(30));
        return result;
    }

    @Override
    public List<Rw> getAll() {
        Connection conn = null;
        try {
            conn = this.getConnection();
            return rwDao.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
        return null;
    }

    @Override
    public List<Rw> getAllBySlave() {
        Connection conn = null;
        try {
            conn = this.getConnection();
            return rwDao.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
        return null;
    }
}
