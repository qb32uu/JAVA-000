package com.training.week07.rw;

import java.util.List;

import com.training.week07.base.IBaseService;
import com.training.week07.datasource.ReadOnly;

/**
 * 地区
 * 
 * @author Billion
 *
 */
public interface IRwService extends IBaseService {
    /**
     * 随机增加no个对象
     * 
     * @param no
     */
    public void randomAdd(int no);

    /**
     * 获取全部数据（主库）
     * 
     * @return
     */
    public List<Rw> getAll();

    /**
     * 获取全部数据（从库）
     * 
     * @return
     */
    public List<Rw> getAllBySlave();
}
