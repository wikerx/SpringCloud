package cpq;
import base.BaseTest;
import com.utils.Md532Util;
import org.junit.Test;

import java.util.HashMap;

public class CollectionTest extends BaseTest {

    String token = "L6zpCLYG9FQ9v+Djyy2TnQ==";
    String userId = "67";

    @Test //我的收藏 文章
    public void test_collectNews() {
        /****请求参数*****/
        params.add("interfaceCode", "collectNews");
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
        params.add("page","2");
        params.add("limit","10");

        String interfaceUrl = "user/collectNews";
        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //我的收藏 关注人
    public void test_collectUser() {
        /****请求参数*****/
        params.add("interfaceCode", "collectUser");
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

        String interfaceUrl = "user/collectUser";
        requestURL(BaseTest.url,interfaceUrl);
    }
}


