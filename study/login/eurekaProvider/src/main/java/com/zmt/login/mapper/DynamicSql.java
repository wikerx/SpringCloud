package com.zmt.login.mapper;

import com.zmt.login.entity.OrderTest;

/**
 * @CLASSNAME :DynamicSql
 * @Description :动态SQL
 * @Author :Mr.薛
 * @Data :2019/3/20  14:15
 * @Version :V1.0
 * @Status : 编写
 **/
public class DynamicSql {
    /**
     * 查询语句.
     * @param orderTest
     * @return
     */
    public String select1(OrderTest orderTest){
        StringBuffer sql = new StringBuffer("select * from ordertest where 1=1 ");
        if(orderTest.getOrdername() != null){//参数名称和实体对应
            sql.append(" and ordername = #{ordername}");
        }
        if(orderTest.getOrderno() != null){
            sql.append(" and orderno = #{orderno}");
        }
        if(orderTest.getRemark() != null){
            sql.append(" and remark = #{remark}");
        }
        return sql.toString();
    }
}
