package cpq;
import base.BaseTest;
import com.utils.Md532Util;
import org.junit.Test;

import java.util.HashMap;

public class SignTest extends BaseTest {

    @Test //加盐
    public void test_salt() {
        /****请求参数*****/
        params.add("interfaceCode","salt");
        params.add("telephone","18516108220");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "user/salt";

        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //短信验证码
    public void testSendcode() {
        /****请求参数*****/
        params.add("interfaceCode", "sendCode");
//        params.add("telephone","17349781961");
        params.add("telephone","18516108222");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);

        String interfaceUrl = "user/sendCode";
        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //修改昵称
    public void test_updateNickName() {
        /****请求参数*****/
        params.add("interfaceCode","updateNickName");
        params.add("userId","67");
        params.add("nickname","我姑娘家家的");
        params.add("token","L6zpCLYG9FQ9v+Djyy2TnQ==");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "personalData/updateNickName";

        requestURL(BaseTest.url,interfaceUrl);
    }


    @Test //绑定身份证
    public void bindIdCard() {
        /****请求参数*****/
        params.add("interfaceCode","bindIdCard");
        params.add("userId","67");
        params.add("idCard","370481199110176721");
        params.add("realName","蒋振振");
        params.add("token","L6zpCLYG9FQ9v+Djyy2TnQ==");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "personalData/bindIdCard";

        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //修改个性签名
    public void test_updateUserSign() {
        /****请求参数*****/
        params.add("interfaceCode","updateUserSign");
        params.add("userId","67");
        params.add("userSign","外面下着雨");
        params.add("token","L6zpCLYG9FQ9v+Djyy2TnQ==");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "personalData/updateUserSign";

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

    @Test // 我的收藏 关注人
    public void test_ClassifyUser() {
        /****请求参数*****/
        params.add("interfaceCode", "collectUser");
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
        params.add("classifyId","1");
        params.add("page","1");
        params.add("limit","10");
        String interfaceUrl = "user/collectUser";
        requestURL(BaseTest.url,interfaceUrl);
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

    @Test //咨询分类
    public void test_NewsTypes() {
        /****请求参数*****/
        params.add("interfaceCode","findChannels");
//        params.add("userId","54");
//        params.add("token","fMWomjm7IYmUbK0PYbdWAg==");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "news/findChannels";

        requestURL(BaseTest.url,interfaceUrl);
    }
    @Test //咨询列表
    public void test_NewsList() {
        /****请求参数*****/
        params.add("interfaceCode","findNewsByChanel");
        params.add("channelId","1");
//        params.add("token","fMWomjm7IYmUbK0PYbdWAg==");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        params.add("page","4");
        params.add("limit","40");
        String interfaceUrl = "news/findNewsByChanel";

        requestURL(BaseTest.url,interfaceUrl);
    }


    @Test //第三方登录
    public void test_thirdPartyLogin() {
        /****请求参数*****/
        params.add("interfaceCode","thirdPartyLogin");
        params.add("userType","3");
        params.add("openid","787898454");
        params.add("username","18516108221");
        params.add("headerPic","http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsNEkXVj3nm3XRic63Wic5iczVFV2aAG9r8bG4z7F1kvjn4t1YDSfhOJ4XmY82oxpdKSHMOW8AGp2iaA/132");
        params.add("sex","2");

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "login/thirdPartyLogin";

        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //校验手机号和下发验证码
    public void test_verifyPhoneAndSendCode() {
        /****请求参数*****/
        params.add("interfaceCode","verifyPhoneAndSendCode");
        params.add("telephone","18717729864");

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "login/verifyPhoneAndSendCode";

        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //修改绑定的手机号
    public void test_modifyTel() {
        /****请求参数*****/
        params.add("interfaceCode","modifyTel");
        params.add("telephone","18717729864");
        params.add("userId","65");
        params.add("sendCode","0804");
      //  params.add("token","fMWomjm7IYmUbK0PYbdWAg==");

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "login/modifyTel";

        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //精选文章
    public void testRecommendNews() {
        /****请求参数*****/
        params.add("interfaceCode","recommendNews");


        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);

        params.add("page","1");
        params.add("limit","5");

        String interfaceUrl = "news/recommendNews";

        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //第三方信息绑定手机号实现登录
    public void test_bindTel() {
        /****请求参数*****/
        params.add("interfaceCode","bindTel");
        params.add("userType","2");
        params.add("openid","7878987787");
        params.add("username","xiayang");
        params.add("telephone","18516108222");
        params.add("sex","2");
        params.add("sendCode","4356");
        params.add("headerPic","http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsNEkXVj3nm3XRic63Wic5iczVFV2aAG9r8bG4z7F1kvjn4t1YDSfhOJ4XmY82oxpdKSHMOW8AGp2iaA/132");

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "login/bindTel";

        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //资讯文章详情
    public void testNewsDetail() {
        /****请求参数*****/
        params.add("interfaceCode","newsDetail");
        params.add("newsId","322");

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);


        params.add("userId","249");

        String interfaceUrl = "news/newsDetail";

        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //咨询列表
    public void test_NewshitOrCollect() {
        /****请求参数*****/
        params.add("interfaceCode","hitOrOCollect");
        params.add("userId","65");
        params.add("newsId","318");
        params.add("opid","2");
//        params.add("page","1");
//        params.add("limit","10");
//        params.add("token","fMWomjm7IYmUbK0PYbdWAg==");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "news/hitOrOCollect";
        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //签到
    public void test_signIn() {
        /****请求参数*****/
        params.add("interfaceCode","signIn");
        params.add("userId","82");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "checkIn/signIn";
        requestURL(BaseTest.url,interfaceUrl);
    }
    @Test //积分明细
    public void test_getAppScoreDetail() {
        /****请求参数*****/
        params.add("interfaceCode","getAppScoreDetail");
        params.add("userId","68");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "user/getAppScoreDetail";
        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //查询收藏的文章
    public void test_collectNews() {
        /****请求参数*****/
        params.add("interfaceCode","collectNews");
        params.add("userId","68");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "user/collectNews";
        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //登录
    public void test_login() {
        /****请求参数*****/
        params.add("interfaceCode","login");
        params.add("telephone","18717729864");
        params.add("password","67");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "user/login";
        requestURL(BaseTest.url,interfaceUrl);
    }
    @Test //竞彩分类
    public void test_Lotteryypes() {
        /****请求参数*****/
        params.add("interfaceCode","lotteryTypes");
//        params.add("userId","54");
//        params.add("token","fMWomjm7IYmUbK0PYbdWAg==");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "quickEnter/lotteryTypes";

        requestURL(BaseTest.url,interfaceUrl);
    }
}
