package com.training.week07.service.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.week07.base.AbsBaseService;
import com.training.week07.base.IBaseDao;
import com.training.week07.entity.Address;
import com.training.week07.entity.OrderProduct;
import com.training.week07.entity.Orders;
import com.training.week07.entity.Sku;
import com.training.week07.enumerate.OrderStateEnum;
import com.training.week07.service.IOrdersService;
import com.training.week07.utils.CreateDataUtils;
import com.training.week07.utils.MockData;

@Service("ordersService")
public class OrdersServiceImpl extends AbsBaseService implements IOrdersService {

    @Autowired
    private IBaseDao ordersDao;
    @Autowired
    private IBaseDao orderProductDao;

    @Autowired
    private IBaseDao addressDao;
    @Autowired
    private IBaseDao skuDao;
    private final static Random RANDOM = new Random();

    @Override
    public void order(Long addressId, Map<Long, Integer> skuMap) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            conn.setAutoCommit(false); // 不自动提交

            // 先生成空订单，再根据订单号生成订单商品，最后更新订单金额
            Orders orders = saveEmptyOrders(conn, addressId);
            Integer amountTotal = saveOrderProducts(conn, orders, skuMap);
            updateOrder(conn, orders, amountTotal);
            conn.commit(); // 手动提交
            conn.setAutoCommit(true);// 自动提交
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback(); // 事务回滚
                    conn.setAutoCommit(true);// 自动提交
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
    }

    /**
     * 保存空订单
     * 
     * @param conn
     * @param addressId
     * @return
     * @throws SQLException
     */
    private Orders saveEmptyOrders(Connection conn, Long addressId) throws SQLException {
        Address address = addressDao.get(conn, addressId);
        Orders result = createOrders(address);

        BigInteger id = ordersDao.insert(conn, result);
        result.setOrdersId(id.longValue());
        return result;
    }

    /**
     * 更新订单（更新金额）
     * 
     * @param conn
     * @param orders
     * @param amountTotal
     */
    private void updateOrder(Connection conn, Orders orders, Integer amountTotal) throws SQLException {
        orders.setAmountTotal(amountTotal);
        ordersDao.update(conn, orders);
    }

    /**
     * 生成订单
     * 
     * @param address
     * @return
     * @throws SQLException
     */
    private Orders createOrders(Address address) throws SQLException {
        Orders result = new Orders();
        result.setAreaId(address.getAreaId());
        result.setDetailedAddress(address.getDetailedAddress());
        result.setRecipient(address.getRecipient());
        result.setPhone(address.getPhone());
        result.setUserId(address.getUserId());
        result.setOrderState(OrderStateEnum.UNPAY.getNo());
        result.setAmountTotal(0);
        result.setCreateTime(System.currentTimeMillis() - RANDOM.nextInt(1000 * 3600 * 24 * 365));
        result.setPayTime(result.getCreateTime() + 1000L * 3600 * 24 * RANDOM.nextInt(30));
        return result;
    }

    /**
     * 保存订单商品
     * 
     * @param conn
     * @param orders
     * @param skuMap
     * @return
     * @throws SQLException
     */
    private int saveOrderProducts(Connection conn, Orders orders, Map<Long, Integer> skuMap) throws Exception {
        int result = 0;
        List<OrderProduct> opList = new ArrayList<>();
        for (Entry<Long, Integer> entry : skuMap.entrySet()) {
            Sku sku = skuDao.get(conn, entry.getKey());
            int no = entry.getValue();
            if (no <= 0 || sku.getNo() < no) {
                throw new Exception("skuId=" + entry.getKey() + "库存不足");
            }

            // 减库存
            sku.setNo(sku.getNo() - no);
            skuDao.update(conn, sku);
            // 生成库存
            OrderProduct op = createOrderProduct(orders, sku, no);

            result += op.getPrice() * op.getNo(); // 合计订单金额
            opList.add(op);
        }

        orderProductDao.insert(conn, opList);
        return result;
    }

    /**
     * 生成单个商品
     * 
     * @param orders
     * @param sku
     * @param no
     * @return
     */
    public OrderProduct createOrderProduct(Orders orders, Sku sku, int no) {
        OrderProduct op = new OrderProduct();
        op.setOrdersId(orders.getOrdersId());
        op.setSkuId(sku.getSkuId());
        op.setPrice(sku.getPrice());
        op.setNo(no);

        return op;
    }

    @Override
    public void randomAdd(int no) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            List<Orders> orderList = new ArrayList<>();
            while (--no >= 0) {
                orderList.add(
                        CreateDataUtils.createRandomOrders(MockData.getRandomAreaId(), MockData.getRandomUserId()));
            }
//            areaId, Long userId
            ordersDao.insert(conn, orderList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
    }

    @Override
    public List<Orders> getAll() {
        Connection conn = null;
        try {
            conn = this.getConnection();
            return ordersDao.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
        return null;
    }
}
