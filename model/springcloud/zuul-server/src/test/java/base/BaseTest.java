package base;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utils.AESUtil;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class BaseTest {
    protected final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    public static MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
    public static Map<String,Object> map = new HashMap<>();

    /**************************************************/
    /*******************本地开发环境*******************/
    /*************************************************/
    public static final String url = "http://localhost:8040/api/cpq-server/cpq-api/";
    public static final String url_user = "http://localhost:8040/api/user-server/user-api/";
    public static final String url_qyl = "http://localhost:8040/api/qyl-server/qyl-api/";
    public static final String url_news = "http://localhost:8040/api/cpq-news/cpq-news/";
    public static final String url_index = "http://localhost:8040/api/cpq-index/cpq-index/";
    public static final String url_elitejob = "http://localhost:8040/api/elitejob-server/elitejob/";
    public static final String url_recipes = "http://localhost:8040/api/recipes-server/recipes/";
    public static final String url_tourism = "http://localhost:8040/api/tourism-server/tourism/";
    public static final String url_recordbook = "http://localhost:8040/api/recordbook-server/recordbook/";
    public static final String url_repair = "http://localhost:8040/api/repair-server/repair/";
    public static final String url_scoreentry = "http://localhost:8040/api/scoreentry-server/scoreentry/";
    public static final String url_habit = "http://localhost:8040/api/habit-server/habit/";
    public static final String url_topxinjiang = "http://localhost:8040/api/topxinjiang-server/topxinjiang/";
    public static final String url_iosadvanced = "http://localhost:8040/api/iosadvanced-server/iosadvanced/";
    public static final String url_aftersale = "http://localhost:8040/api/aftersale-server/aftersale/";
    public static final String url_weeklylist = "http://localhost:8040/api/weeklylist-server/weeklylist/";
    public static final String url_freshpalms = "http://localhost:8040/api/freshpalms-server/freshpalms/";
    public static final String url_runrider = "http://localhost:8040/api/runrider-server/runrider/";
    public static final String url_renovation = "http://localhost:8040/api/renovation-server/renovation/";
    public static final String url_driving = "http://localhost:8040/api/driving-server/driving/";
    public static final String url_lifeworld = "http://localhost:8040/api/lifeworld-server/lifeworld/";
    public static final String url_emotional = "http://localhost:8040/api/emotional-server/emotional/";

     /**********************************************/
     /*******************测试环境*******************/
     /*********************************************/
    public static final String test_url_cpq = "http://116.62.108.143:80/api/cpq-server/cpq-api/";
    public static final String test_url_user = "http://192.168.2.61:80/api/user-server/user-api/";
    public static final String test_url_elitejob = "http://192.168.2.61:80/api/elitejob-server/elitejob/";
    public static final String test_url_elitejob1 = "http://www.aaaapi.top/api/elitejob-server/elitejob/";
    public static final String test_url_tourism = "http://192.168.2.61:80/api/tourism-server/tourism/";
    public static final String test_url_recipes = "http://192.168.2.61:80/api/recipes-server/recipes/";
    public static final String test_url_recordbook = "http://192.168.2.61:80/api/recordbook-server/recordbook/";
    public static final String test_url_repair = "http://192.168.2.61:80/api/repair-server/repair/";
    public static final String test_url_scoreentry = "http://192.168.2.61:80/api/scoreentry-server/scoreentry/";
    public static final String test_url_habit = "http://192.168.2.61:80/api/habit-server/habit/";
    public static final String test_url_topxinjiang = "http://192.168.2.61:80/api/topxinjiang-server/topxinjiang/";
    public static final String test_url_iosadvanced = "http://192.168.2.61:80/api/iosadvanced-server/iosadvanced/";
    public static final String test_url_aftersale = "http://192.168.2.61:80/api/aftersale-server/aftersale/";
    public static final String test_url_weeklylist = "http://192.168.2.61:80/api/weeklylist-server/weeklylist/";
    public static final String test_url_freshpalms = "http://192.168.2.61:80/api/freshpalms-server/freshpalms/";
    public static final String test_url_runrider = "http://192.168.2.61:80/api/runrider-server/runrider/";
    public static final String test_url_renovation = "http://192.168.2.61:80/api/renovation-server/renovation/";

    public static final String test_url_lifeworld = "http://192.168.2.61:80/api/lifeworld-server/lifeworld/";
    public static final String test_url_driving = "http://192.168.2.61:80/api/driving-server/driving/";
    public static final String test_url_emotional = "http://192.168.2.61:80/api/emotional-server/emotional/";


    /**********************************************/
    /*******************生产环境*******************/
    /*********************************************/
    public static final String prod_url_user = "http://aaaapi.top/api/user-server/user-api/";
    public static final String prod_url_elitejob = "http://aaaapi.top/api/elitejob-server/elitejob/";
    public static final String prod_url_tourism = "http://aacapi.top:/api/tourism-server/tourism/";
    public static final String prod_url_recipes = "http://aabapi.top/api/recipes-server/recipes/";
    public static final String prod_url_recordbook = "http://aaaapi.top/api/recordbook-server/recordbook/";
    public static final String prod_url_repair = "http://aaaapi.top/api/repair-server/repair/";
    public static final String prod_url_scoreentry = "http://aacapi.top/api/scoreentry-server/scoreentry/";
    public static final String prod_url_habit = "http://aacapi.top/api/habit-server/habit/";
    public static final String prod_url_topxinjiang = "http://aaaapi.top/api/topxinjiang-server/topxinjiang/";
    public static final String prod_url_iosadvanced = "http://aaaapi.top/api/iosadvanced-server/iosadvanced/";
    public static final String prod_url_aftersale = "http://aabapi.top/api/aftersale-server/aftersale/";
    public static final String prod_url_weeklylist = "http://aabapi.top/api/weeklylist-server/weeklylist/";
    public static final String prod_url_freshpalms = "http://aacapi.top/api/freshpalms-server/freshpalms/";
    public static final String prod_url_runrider = "http://aacapi.top/api/runrider-server/runrider/";

    public static final String prod_url_renovation = "http://aacapi.top/api/renovation-server/renovation/";
    public static final String prod_url_driving = "http://aacapi.top/api/driving-server/driving/";
    public static final String prod_url_lifeworld = "http://aaaapi.top/api/lifeworld-server/lifeworld/";

    @BeforeClass
    public static void before(){
        /*****一级参数*******/
        params.add("reqTime",String.valueOf(System.currentTimeMillis()));
//        params.add("reqTime","1530774611609");
        params.add("terminalType","ios");
        params.add("version","1.0");
        map = MultiValueMapToMap(params);
    }

    public void requestURL(String url,String interfaceUrl) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        request(url, interfaceUrl, rt, headers);
    }

    public void requestURL_json(String url,String interfaceUrl) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        request(url, interfaceUrl, rt, headers);
    }

    public void request(String url, String interfaceUrl, RestTemplate rt, HttpHeaders headers) {
        logger.info("请求参数：{}", JSON.toJSONString(params));
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        ResponseEntity<Object> entity = rt.exchange(url.concat(interfaceUrl), HttpMethod.POST, requestEntity, Object.class);
        LinkedHashMap body = (LinkedHashMap)entity.getBody();
        HttpHeaders entityHeaders = entity.getHeaders();
        List<String> tokenParams = entityHeaders.get("Token-Param");
        if(null != tokenParams && !tokenParams.isEmpty()){
            String tokenParam = tokenParams.get(0);
            logger.info("响应结果:{},tokenParam: {}",body,tokenParam);
            logger.info("==============返回给客户端的内容是上面的》=================================");
            JSONObject jsonObject = (JSONObject)JSON.toJSON(body);
            String dataStr = (String) jsonObject.get("data");
            if(null != dataStr) {
                byte[] decode = Base64.getDecoder().decode(dataStr.getBytes());
                byte[] decryptFrom = AESUtil.parseHexStr2Byte(new String(decode));
                String decrypt = AESUtil.decrypt(decryptFrom, tokenParam);
                jsonObject.put("data",decrypt);
            }
            logger.info("响应结果解密:{}", jsonObject);
        }else {
            // logger.info("响应结果:{}",body);
            logger.info("响应结果:{}",JSON.toJSON(body));
        }
    }

    public static Map<String,Object> MultiValueMapToMap(MultiValueMap<String,Object> multiValueMap){
        if(multiValueMap.isEmpty()){
            return new HashMap<>();
        }
        return multiValueMap.toSingleValueMap();
    }

    /**
     * 文件上传
     * @param url
     * @param interfaceUrl
     */
    public void requestURL_multiform(String url,String interfaceUrl) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        request(url, interfaceUrl, rt, headers);
    }
}
