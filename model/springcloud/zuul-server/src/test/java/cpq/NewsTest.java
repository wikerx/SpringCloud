package cpq;
import base.BaseTest;
import com.utils.Md532Util;
import org.junit.Test;

import java.util.HashMap;

public class NewsTest extends BaseTest {

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

        requestURL(BaseTest.url_news,interfaceUrl);
    }

    @Test //咨询列表
    public void test_NewsList() {
        /****请求参数*****/
        params.add("interfaceCode","findNewsByChanel");
        params.add("channelId","1");
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
        String interfaceUrl = "news/findNewsByChanel";

        requestURL(BaseTest.url_news,interfaceUrl);
    }
    @Test //精选文章
    public void testRecommentNews() {
        /****请求参数*****/
        params.add("interfaceCode","recommentNews");


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

        requestURL(BaseTest.url_news,interfaceUrl);
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

        requestURL(BaseTest.url_news,interfaceUrl);
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
        requestURL(BaseTest.url_news,interfaceUrl);
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
        String interfaceUrl = "lottery/lotteryTypes";

        requestURL(BaseTest.url_news,interfaceUrl);
    }


    @Test //篮球资讯  CPQ
    public void test_BasketballNews() {
        /****请求参数*****/
        params.add("interfaceCode","basketballNews");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
//        params.add("page","1");
//        params.add("limit","5");
        String interfaceUrl = "basketball/basketballNews";

        requestURL(BaseTest.url,interfaceUrl);
    }
}
