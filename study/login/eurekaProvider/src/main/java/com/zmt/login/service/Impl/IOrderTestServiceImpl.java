package com.zmt.login.service.Impl;

import com.zmt.login.mapper.IOrderTestMapper;
import com.zmt.login.entity.OrderTest;
import com.zmt.login.service.IOrderTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CLASSNAME :IOrderTestServiceImpl
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/20  14:02
 * @Version :V1.0
 * @Status : 编写
 **/
@Service
public class IOrderTestServiceImpl implements IOrderTestService{
    @Autowired
    IOrderTestMapper orderTestDao;

    /*查询所有订单信息*/
    public List<OrderTest> selectOrders(){
        return orderTestDao.selectOrders();
    }

    /*根据id查询订单信息*/
    public OrderTest selectById(long id){
        return orderTestDao.selectById(id);
    }

    /*根据OrderName模糊查询*/
    public List<OrderTest> selectByOrderNameLike(String orderName){
        return orderTestDao.selectByOrderNameLike(orderName);
    }

    /*添加订单信息*/
    public int addOrderTest(String orderNo,String orderName,String remark){
        return orderTestDao.addOrderTest(orderNo,orderName,remark);
    }

    /*修改订单信息*/
    public int updateOrderTest(long id,String orderNo,String orderName,String remark){
        return orderTestDao.updateOrderTest(id,orderNo,orderName,remark);
    }

    /*删除订单信息*/
    public int deleteOrderTestBuId(long id){
        return orderTestDao.deleteOrderTestBuId(id);
    }

    /*动态SQL*/
    public List<OrderTest> selectOrdersByDynamicSQL(OrderTest orderTest){
        return orderTestDao.selectOrdersByDynamicSQL(orderTest);
    }
}
