package cpq;
import base.BaseTest;
import com.utils.Md532Util;
import org.junit.Test;

import java.util.HashMap;

public class UserTest extends BaseTest {

    @Test //注册
    public void test_register() throws Exception {
        /****请求参数*****/
        String s = Md532Util.MD5_32bit("CtL81DVA5JshAnoYc0vi").concat(Md532Util.MD5_32bit("123456"));

        params.add("interfaceCode", "register");
        params.add("telephone","18717729864");
        params.add("code","1234");
        params.add("password",s);
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);

        String interfaceUrl = "user/register";
        requestURL(BaseTest.test_url_cpq,interfaceUrl);
    }

    @Test //个人中心
    public void test_personal() {
        /****请求参数*****/
        params.add("interfaceCode","personal");
        params.add("userId","54");
        params.add("token","fMWomjm7IYmUbK0PYbdWAg==");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "user/personal";

        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //关注人分类
    public void test_UserClassify() {
        /****请求参数*****/
        params.add("interfaceCode", "userClassify");
        params.add("userId","66");
        //   params.add("token","fMWomjm7IYmUbK0PYbdWAg==");
//        params.add("telephone","18717729864");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);

        String interfaceUrl = "user/userClassify";
        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //积分明细
    public void test_getAppScoreDetail() {
        /****请求参数*****/
        params.add("interfaceCode", "getAppScoreDetail");
        params.add("userId","111");
        params.add("token","wm4YLE1yWpIqO9kGk8QuKA==");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        params.add("page","1");
        params.add("limit","20");

        String interfaceUrl = "user/getAppScoreDetail";
        requestURL(BaseTest.url,interfaceUrl);
    }
}
