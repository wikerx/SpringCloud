package user;

import base.BaseTest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.model.AppRequestRecord;
import com.utils.Md532Util;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppRequestRecordTest extends BaseTest {

    @Before
    public void module(){
        params.add("module","cpq-user");
    }

    @Test
    public void test() {
        params.add("interfaceCode", "appRequestRecord");
        map = this.MultiValueMapToMap(params);
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        List<AppRequestRecord> list = new ArrayList<>();
        AppRequestRecord appRequestRecord = new AppRequestRecord();
        appRequestRecord.setAppId("1");
        appRequestRecord.setAppName("菜谱");
        appRequestRecord.setIp("192.168.1.1");
        appRequestRecord.setDeviceCode("dsjadlksjlkjfsdafasdf123123");
        appRequestRecord.setCurrOperation("index");
        appRequestRecord.setOperationTime(String.valueOf(System.currentTimeMillis()));
        appRequestRecord.setOperationDesc("首页");
        appRequestRecord.setCurrView("banner");
        appRequestRecord.setViewDesc("banner跳转");
        Map<String,String> jsonObject = new HashMap<>();
        jsonObject.put("key1","value1");
        jsonObject.put("key2","value2");
        appRequestRecord.setJsonInfo(jsonObject.toString());
        list.add(appRequestRecord);

        AppRequestRecord appRequestRecord2 = new AppRequestRecord();
        appRequestRecord2.setAppId("2");
        appRequestRecord2.setAppName("景点");
        appRequestRecord2.setIp("192.168.1.2");
        appRequestRecord2.setDeviceCode("dsjadlksjlkjfsdafasdf123123");
        appRequestRecord2.setCurrOperation("login");
        appRequestRecord2.setOperationTime(String.valueOf(System.currentTimeMillis()));
        appRequestRecord2.setOperationDesc("登录");
        appRequestRecord2.setCurrView("login");
        appRequestRecord2.setViewDesc("登录");
        Map<String,String> jsonObject2 = new HashMap<>();
        jsonObject2.put("key3","value3");
        jsonObject2.put("key4","value4");
        appRequestRecord2.setJsonInfo(jsonObject2.toString());


        list.add(appRequestRecord2);
        System.out.println("====================="+JSON.toJSONString(list));
        params.add("record", JSON.toJSONString(list));
        String interfaceUrl = "appRequest/userRecord";
        requestURL_json(BaseTest.url_user,interfaceUrl);
    }
}
