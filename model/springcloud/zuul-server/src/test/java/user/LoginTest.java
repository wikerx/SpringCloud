package user;

import base.BaseTest;
import com.utils.Md532Util;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * module=tourism
 * module=recipes
 * module=elitejob
 * 根据module 进行多数据的切换
 */
public class LoginTest extends BaseTest {

//    @Before
//    public void module() {
//        params.add("module", "freshpalms");
//    }

    @Test //短信验证码
    public void testSendcode() {
        /****请求参数*****/
        params.add("interfaceCode", "sendCode");
//        params.add("telephone","17349781961");
        params.add("telephone", "13917506416");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String) protoMap.get("sign");
        System.out.println("=====================" + serverSign);
        params.add("sign", serverSign);

        String interfaceUrl = "login/sendCode?module=tourism";
        requestURL(BaseTest.test_url_user, interfaceUrl);
    }

    @Test //注册
    public void test_register() throws Exception {
        /****请求参数*****/
        String s = Md532Util.MD5_32bit("hMBTnlPuCmoUism8wI8O").concat(Md532Util.MD5_32bit("123456"));

        params.add("interfaceCode", "register");
        params.add("telephone", "18717729864");
        params.add("code", "1234");
        params.add("password", s);
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String) protoMap.get("sign");
        System.out.println("=====================" + serverSign);
        params.add("sign", serverSign);

        String interfaceUrl = "login/register?module=driving";
        requestURL(BaseTest.test_url_user, interfaceUrl);
    }

    @Test //登录
    public void test_login() throws Exception {
        /****请求参数*****/
        params.add("interfaceCode", "login");
        params.add("telephone", "18717729864");
        params.add("password", Md532Util.MD5_32bit("123456"));
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String) protoMap.get("sign");
        System.out.println("=====================" + serverSign);
        params.add("sign", serverSign);
        String interfaceUrl = "login/login?module=driving";

        requestURL(BaseTest.test_url_user, interfaceUrl);
    }

    @Test //第三方登录
    public void test_thirdPartyLogin() {
        /****请求参数*****/
        params.add("interfaceCode", "thirdPartyLogin");
        params.add("userType", "1");
        params.add("openid", "1");
        params.add("username", "小雨");
        params.add("headerPic", "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsNEkXVj3nm3XRic63Wic5iczVFV2aAG9r8bG4z7F1kvjn4t1YDSfhOJ4XmY82oxpdKSHMOW8AGp2iaA/132");
        params.add("sex", "2");

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String) protoMap.get("sign");
        System.out.println("=====================" + serverSign);
        params.add("sign", serverSign);
        String interfaceUrl = "login/thirdPartyLogin";

        requestURL(BaseTest.url_user, interfaceUrl);
    }

    @Test //修改密码
    public void test_updatePassword() throws Exception {
        String s = Md532Util.MD5_32bit("ghsyNpPDsqHhH20Fx/nF").concat(Md532Util.MD5_32bit("123456"));
        /****请求参数*****/
        params.add("interfaceCode", "updatePassword");
        params.add("userId", "48");
        params.add("password", Md532Util.MD5_32bit("12345"));//旧密码
        params.add("newPassword", s);//新密码
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        params.add("token", "123"); //token不进行签名
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String) protoMap.get("sign");
        System.out.println("=====================" + serverSign);
        params.add("sign", serverSign);
        String interfaceUrl = "login/updatePassword";

        requestURL(BaseTest.url_user, interfaceUrl);
    }

    @Test//忘记密码(重置密码)
    public void test_forgetPassword() throws Exception {
        /****请求参数*****/
        String s = Md532Util.MD5_32bit(Md532Util.MD5_32bit("FwFY/Zha+TzNR0DMSvOq").concat(Md532Util.MD5_32bit("12345")));
        params.add("interfaceCode", "forgetPassword");
        params.add("code", "9373");
        params.add("password", s);
        params.add("telephone", "17349781961");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String) protoMap.get("sign");
        System.out.println("=====================" + serverSign);
        params.add("sign", serverSign);
        String interfaceUrl = "personalData/forgetPassword";

        requestURL(BaseTest.test_url_user, interfaceUrl);
    }

    @Test//退出
    public void test_logout() {
        /****请求参数*****/
        params.add("interfaceCode", "logout");
        params.add("userId", "116");
        params.add("token", "gLd01QaFe/yDHEGBrzjoLg==");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String) protoMap.get("sign");
        System.out.println("=====================" + serverSign);
        params.add("sign", serverSign);
        params.add("module", "repair");
        String interfaceUrl = "login/logout";

        requestURL(BaseTest.url_user, interfaceUrl);
    }

    @Test //加盐
    public void test_salt() {
        /****请求参数*****/
        params.add("interfaceCode", "salt");
        params.add("telephone", "18717729864");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String) protoMap.get("sign");
        System.out.println("=====================" + serverSign);
        params.add("sign", serverSign);
        params.add("module", "driving");
        String interfaceUrl = "login/salt";

        requestURL(BaseTest.test_url_user, interfaceUrl);
    }
}
