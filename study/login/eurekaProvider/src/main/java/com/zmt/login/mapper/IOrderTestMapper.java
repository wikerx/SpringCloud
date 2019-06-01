package com.zmt.login.mapper;

import com.zmt.login.entity.OrderTest;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mr.薛 on 2019/3/20.
 * DOTO: 无mapper、xml写法，无需映射
 */
@Mapper
public interface IOrderTestMapper {
    /*查询所有订单信息*/
    @Select("SELECT id,orderNo,orderName,remark FROM ordertest")
    List<OrderTest> selectOrders();

    /*根据id查询订单信息*/
    @Select("SELECT id,orderNo,orderName,remark FROM ordertest where id = #{id}")
    OrderTest selectById(@Param("id") long id);

    /*根据OrderName模糊查询*/
    @Select("SELECT id,orderNo,orderName,remark FROM ordertest where orderName LIKE concat('%',#{orderName},'%')")
    List<OrderTest> selectByOrderNameLike(@Param("orderName") String orderName);

    /*添加订单信息*/
    @Transactional
    @Insert("insert into ordertest(orderNo,orderName,remark) values(#{orderNo},#{orderName},#{remark})")
    int addOrderTest(@Param("orderNo") String orderNo, @Param("orderName") String orderName, @Param("remark") String remark);

    /*修改订单信息*/
    @Transactional
    @Update("update ordertest set orderNo = #{orderNo},orderName = #{orderName},remark = #{remark} where id = #{id}")
    int updateOrderTest(@Param("id") long id, @Param("orderNo") String orderNo, @Param("orderName") String orderName, @Param("remark") String remark);

    /*删除订单信息*/
    @Transactional
    @Delete("delete from ordertest where id = #{id}")
    int deleteOrderTestBuId(@Param("id") long id);

    /*动态SQL*/
    @SelectProvider(type = DynamicSql.class,method = "select1")
    List<OrderTest> selectOrdersByDynamicSQL(OrderTest orderTest);
}
