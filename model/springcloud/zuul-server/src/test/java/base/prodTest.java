package base;

import com.utils.Md532Util;
import org.junit.Test;

import java.util.HashMap;

public class prodTest extends BaseTest {
    @Test
    public void drivingNewsCollect() {
        /****请求参数*****/
        params.add("interfaceCode", "drivingBanner");

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String) protoMap.get("sign");
        System.out.println("=====================" + serverSign);
        params.add("sign", serverSign);

        String interfaceUrl = "index/banner?module=driving";
        requestURL(BaseTest.prod_url_driving, interfaceUrl);
    }

    @Test //首页
    public void runriderIndex() {
        /****请求参数*****/
        params.add("interfaceCode","runriderIndex"); //必传项必须在sign前面添加。
        params.add("userId","109");

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。

        String interfaceUrl = "common/runriderIndex?module=runrider";
        requestURL(BaseTest.prod_url_runrider,interfaceUrl);
    }

    @Test //首页
    public void weeklylistIndex() {
        /****请求参数*****/
        params.add("interfaceCode","weeklylistIndex"); //必传项必须在sign前面添加。
        params.add("userId","109");
        params.add("planTime","2018-10-16");

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。

        String interfaceUrl = "common/weeklylistIndex?module=weeklylist";
        requestURL(BaseTest.prod_url_weeklylist,interfaceUrl);
    }

    @Test//首页banner
    public void freshplams_bannerList() {
        /****请求参数*****/
        params.add("interfaceCode", "freshBanner"); //必传项必须在sign前面添加。

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String) protoMap.get("sign");
        System.out.println("=====================" + serverSign);
        params.add("sign", serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。
        params.add("module", "freshpalms");//每个接口都带吧

        String interfaceUrl = "index/banner?module=freshplams";
        //   requestURL(BaseTest.url_freshpalms, interfaceUrl);
        requestURL(BaseTest.prod_url_freshpalms, interfaceUrl);
    }

    @Test
    public void iosadvanced_index() {
        /****请求参数*****/
        params.add("interfaceCode", "indexAll");
        // params.add("userId", "1");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String) protoMap.get("sign");
        System.out.println("=====================" + serverSign);
        params.add("sign", serverSign);

        String interfaceUrl = "index/all?module=iosadvanced";
        requestURL(BaseTest.prod_url_iosadvanced, interfaceUrl);
    }

    @Test //新疆首页
    public void topxinjiangIndex() {
        /****请求参数*****/
        params.add("interfaceCode","xinjiangIndex"); //必传项必须在sign前面添加。

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。

        String interfaceUrl = "common/xinjiangIndex?module=topxinjiang";
        requestURL(BaseTest.prod_url_topxinjiang,interfaceUrl);
    }

    @Test //维修首页
    public void aftersaleIndex() {
        /****请求参数*****/
        params.add("interfaceCode","aftersaleIndex"); //必传项必须在sign前面添加。

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。

        String interfaceUrl = "common/aftersaleIndex?module=habit";
        requestURL(BaseTest.prod_url_aftersale,interfaceUrl);
    }

    @Test //小习惯首页
    public void habit() {
        /****请求参数*****/
        params.add("interfaceCode","habitIndex"); //必传项必须在sign前面添加。
        params.add("userId","109");

        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        //如果是非必传项，就要在sign的后面添加，这样可加可不加。

        String interfaceUrl = "common/habitIndex?module=habit";
        requestURL(BaseTest.prod_url_habit,interfaceUrl);
    }

    @Test
    public void elitejob() {
        params.add("interfaceCode", "index");
        map = this.MultiValueMapToMap(params);
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);

        String interfaceUrl = "common/index?module=elitejob";
        requestURL(BaseTest.prod_url_elitejob,interfaceUrl);
    }

    @Test //菜谱首页集合接口
    public void recipes() {
        params.add("interfaceCode","recipesIndex");
        map = this.MultiValueMapToMap(params);
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);

        String interfaceUrl = "common/recipesIndex?module=recipes";
        requestURL(BaseTest.prod_url_recipes,interfaceUrl);
    }

    @Test //记账app
    public void recordbook() {
        params.add("interfaceCode","recordIndex");
        params.add("userId","109");
        map = this.MultiValueMapToMap(params);
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        params.add("time","2");
        params.add("states","1");
        String interfaceUrl = "common/recordIndex?module=recordbook";
        requestURL(BaseTest.prod_url_recordbook,interfaceUrl);
    }

    @Test
    public void repair() {
        params.add("interfaceCode", "indexAll");
        map = this.MultiValueMapToMap(params);
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String) protoMap.get("sign");
        System.out.println("=====================" + serverSign);
        params.add("sign", serverSign);

        String interfaceUrl = "index/all?module=repair";
        requestURL(BaseTest.prod_url_repair, interfaceUrl);
    }

    @Test //首页人员list
    public void scoreentry() {
        params.add("interfaceCode","scorePeople");
        map = this.MultiValueMapToMap(params);
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);

        String interfaceUrl = "common/scorePeople?module=scoreentry";
        requestURL(BaseTest.prod_url_scoreentry,interfaceUrl);
    }

    @Test //文章，此刻，首页的推荐接口
    public void tourism() {
        params.add("interfaceCode","tourismArticle");
        params.add("type","2");
        map = this.MultiValueMapToMap(params);
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        params.add("sign",serverSign);
        String interfaceUrl = "article/tourismArticle?module=tourism";
        requestURL(BaseTest.prod_url_tourism,interfaceUrl);
    }

    @Test //登录
    public void user() throws Exception{
        params.add("interfaceCode","login");
        params.add("telephone","17349781961");
        params.add("password",Md532Util.MD5_32bit("123456"));
        map = this.MultiValueMapToMap(params);
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "login/login?module=repair";

        requestURL(BaseTest.prod_url_user,interfaceUrl);
    }
}
