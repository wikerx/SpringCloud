package cpq;
import base.BaseTest;
import com.utils.Md532Util;
import org.junit.Test;

import java.util.HashMap;

public class SignInTest extends BaseTest {

    String token = "90dZe6z19sHxJE9bd2+9vQ==";
    String userId = "67";

    @Test //用户签到接口
    public void test_signIn() {
        /****请求参数*****/
        params.add("interfaceCode", "signIn");
        params.add("userId",userId);
        params.add("token",token);
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);

        String interfaceUrl = "user/signIn";
        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //积分明细
    public void test_getAppScoreDetail() {
        /****请求参数*****/
        params.add("interfaceCode", "getAppScoreDetail");
        params.add("userId",userId);
        params.add("token",token);
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


