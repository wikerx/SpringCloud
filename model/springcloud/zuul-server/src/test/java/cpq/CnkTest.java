package cpq;
import com.utils.Md532Util;
import org.junit.Test;
import base.BaseTest;
import java.util.HashMap;

public class CnkTest extends BaseTest {

    String token = "ZzrkPKwQ5GJFq1IQllnkpg==";
    String userId = "68";

    @Test //首页集合接口
    public void index() {
        /****请求参数*****/
        params.add("interfaceCode","index"); //必传项必须在sign前面添加。

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。
//        params.add("userId", "0");
//        params.add("platform","baidu");

        String interfaceUrl = "common/index";
        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //彩票类型接口
    public void lottory() {
        /****请求参数*****/
        params.add("interfaceCode","lottery"); //必传项必须在sign前面添加。
        params.add("nameEn","dlt");

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。
//        params.add("page", "2");
//        params.add("limit","10");

        String interfaceUrl = "quickEnter/lottery";
        requestURL(BaseTest.url,interfaceUrl);
    }


    @Test //彩票预测接口
    public void forecast() {
        /****请求参数*****/
        params.add("interfaceCode","forecast"); //必传项必须在sign前面添加。
        params.add("nameEn","ssq");
        params.add("number","1");  //Integer


        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。
        params.add("page", "2");
        params.add("limit","5");

        String interfaceUrl = "quickEnter/forecast";
        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //意见反馈接口
    public void saveSuggest() {
        /****请求参数*****/
        params.add("interfaceCode","saveSuggest"); //必传项必须在sign前面添加。
        params.add("userId",userId);
        params.add("token",token);  //Integer
        params.add("contact", "18729788081");
        params.add("suggest","这就是我的意见");


        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。

//        params.add("appVersion",serverSign);
//        params.add("platform",serverSign);

        String interfaceUrl = "saveSuggest/saveSuggest";
        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //版本更新
    public void appcodeupdate() {
        /****请求参数*****/
        params.add("interfaceCode","versionUpdate"); //必传项必须在sign前面添加。
        params.add("appcode","0");
        params.add("platform","baidu");


        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。

//        params.add("appVersion",serverSign);
//        params.add("platform",serverSign);

        String interfaceUrl = "common/versionUpdate";
        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //获取服务器时间戳
    public void getTimestamp() {
        /****请求参数*****/
        params.add("interfaceCode","getTimestamp"); //必传项必须在sign前面添加。

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。

        String interfaceUrl = "common/getTimestamp";
        requestURL(BaseTest.url,interfaceUrl);
    }



    @Test //关于我们接口
    public void aboutUs() {
        /****请求参数*****/
        params.add("interfaceCode","aboutUs"); //必传项必须在sign前面添加。

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。

        String interfaceUrl = "common/aboutUs";
        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //签到日期接口
    public void signInTime() {
        /****请求参数*****/
        params.add("interfaceCode","signInTime"); //必传项必须在sign前面添加。
        params.add("userId",userId);
        params.add("token",token);  //Integer


        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。
//       params.add("nowDate","1531200535507");
//        params.add("appVersion",serverSign);
//        params.add("platform",serverSign);

        String interfaceUrl = "user/signInTime";
        requestURL(BaseTest.url,interfaceUrl);
    }


}
