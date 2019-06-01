package com.zmt.login.service;

import com.zmt.login.entity.OrderTest;

import java.util.List;

/**
 * Created by Administrator on 2019/3/20.
 * DOTO:IOrderTest接口复用
 */
public interface IOrderTestService {
    /*查询所有订单信息*/
    List<OrderTest> selectOrders();

    /*根据id查询订单信息*/
    OrderTest selectById(long id);

    /*根据OrderName模糊查询*/
    List<OrderTest> selectByOrderNameLike(String orderName);

    /*添加订单信息*/
    int addOrderTest(String orderNo, String orderName, String remark);

    /*修改订单信息*/
    int updateOrderTest(long id, String orderNo, String orderName, String remark);

    /*删除订单信息*/
    int deleteOrderTestBuId(long id);

    /*动态SQL*/
    List<OrderTest> selectOrdersByDynamicSQL(OrderTest orderTest);
}
