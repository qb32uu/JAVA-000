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
import com.training.week07.entity.ProductType;
import com.training.week07.service.IProductTypeService;
import com.training.week07.utils.CreateDataUtils;
import com.training.week07.utils.MockData;

@Service("productTypeService")
public class ProductTypeServiceImpl extends AbsBaseService implements IProductTypeService {

    @Autowired
    private IBaseDao productTypeDao;

    @Transactional
    @Override
    public void randomAdd(int no) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            while (--no >= 0) {
                productTypeDao.insert(conn, CreateDataUtils.createRandomProductType());
            }

            List<ProductType> productTypeList = productTypeDao.getAll(conn);
            MockData.setProductTypeIdList(
                    productTypeList.stream().map(e -> e.getProductTypeId()).collect(Collectors.toList()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
    }

    @Override
    public List<ProductType> getAll() {
        Connection conn = null;
        try {
            conn = this.getConnection();
            return productTypeDao.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
        return null;
    }
}
