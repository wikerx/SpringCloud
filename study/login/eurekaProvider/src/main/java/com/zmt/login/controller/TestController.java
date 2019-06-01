package com.zmt.login.controller;

import com.zmt.login.entity.OrderTest;
import com.zmt.login.service.IOrderTestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;

/**
 * @CLASSNAME :TestController
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/28  16:33
 * @Version :V1.0
 * @Status : 编写
 **/
@Controller
public class TestController {
    Log log = LogFactory.getLog(TestController.class);

    @Autowired
    IOrderTestService iOrderTestService;

    @Value("${server.port}")
    String port;
    String localname;
    String localip;

    /*返回字符*/
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        InetAddress ia=null;
        try {
            ia = ia.getLocalHost();
            localname = ia.getHostName();
            localip = ia.getHostAddress();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "localname:"+localname+"<br/>localip:"+localip+"<br/>port:" +port;
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String indexPage(HashMap<String, Object> map){
        log.info("*********************************");
        log.info("*       Welcome  To  Login      *");
        log.info("*********************************");
        map.put("result","啦啦啦啦啦...我来啦...");
        return "login";
    }

    /*查询所有订单信息 url:http://localhost/pay/selectOrders */
    @RequestMapping(value = "/selectOrders",method = RequestMethod.GET)
    @ResponseBody
    public List<OrderTest> selectOrders(){
        List<OrderTest> list = iOrderTestService.selectOrders();
        if(list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

    /*根据id查询订单信息 url:http://localhost/pay/selectById/1 */
    @GetMapping(value = "/selectById/{id}")
    @ResponseBody
    public OrderTest selectById(@PathVariable("id") long id){
        return iOrderTestService.selectById(id);
    }


    /*模糊订单信息 url:http://localhost/pay/selectByOrderNameLike/测试产品 */
    @GetMapping(value = "/selectByOrderNameLike/{orderName}")
    @ResponseBody
    public List<OrderTest> selectByOrderNameLike(@PathVariable("orderName") String orderName){
        return iOrderTestService.selectByOrderNameLike(orderName);
    }

    /*添加订单信息 url: http://localhost/pay/addOrderTest */
    @GetMapping(value = "/addOrderTest")
    @ResponseBody
    public String addOrderTest(){
        OrderTest orderTest = new OrderTest();
        orderTest.setOrdername("测试产品6");
        orderTest.setOrderno("2019012717243025006");
//        orderTest.setId(Long.parseLong("6"));
        orderTest.setRemark("测试产品测试数据6");
        int m = iOrderTestService.addOrderTest(orderTest.getOrderno(),orderTest.getOrdername(),orderTest.getRemark());
        if(m > 0){
            return "添加成功！";
        }else{
            return "添加失败！";
        }
    }

    /*修改订单信息 url: http://localhost/pay/updateOrderTest */
    @GetMapping(value = "/updateOrderTest")
    @ResponseBody
    public String updateOrderTest(){
        OrderTest orderTest = new OrderTest();
        orderTest.setOrdername("测试产品6");
        orderTest.setOrderno("2019012717243025006");
        orderTest.setId(Long.parseLong("6"));
        orderTest.setRemark("测试产品测试数据666");
        int m = iOrderTestService.updateOrderTest(orderTest.getId(), orderTest.getOrderno(),orderTest.getOrdername(),orderTest.getRemark());
        if(m > 0){
            return "修改成功！";
        }else{
            return "修改失败！";
        }
    }


    /*根据id查询订单信息 url:http://localhost/pay/deleteOrderTestBuId/6 */
    @GetMapping(value = "/deleteOrderTestBuId/{id}")
    @ResponseBody
    public String deleteOrderTestBuId(@PathVariable("id") long id){
        int m = iOrderTestService.deleteOrderTestBuId(id);
        if(m > 0){
            return "删除成功！";
        }else{
            return "删除失败！";
        }
    }

    /*动态SQL url:http://localhost/pay/selectOrdersByDynamicSQL  */
    @GetMapping(value = "/selectOrdersByDynamicSQL")
    @ResponseBody
    public List<OrderTest> selectOrdersByDynamicSQL(){
        OrderTest orderTest = new OrderTest();
        orderTest.setOrdername("测试产品6");
        orderTest.setOrderno("2019012717243025006");
        orderTest.setId(Long.parseLong("6"));
        orderTest.setRemark("测试产品测试数据666");
        List<OrderTest> list = iOrderTestService.selectOrdersByDynamicSQL(orderTest);
        return list;
    }
}
