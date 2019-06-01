package cpq;
import base.BaseTest;
import com.utils.Md532Util;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class PersonalTest extends BaseTest {

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

    @Test //修改密码
    public void test_updatePassword() throws Exception {
        String s = Md532Util.MD5_32bit("ghsyNpPDsqHhH20Fx/nF").concat(Md532Util.MD5_32bit("123456"));
        /****请求参数*****/
        params.add("interfaceCode","updatePassword");
        params.add("userId","48");
        params.add("password",Md532Util.MD5_32bit("12345"));//旧密码
        params.add("newPassword",s);//新密码
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        params.add("token","123"); //token不进行签名
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "personalData/updatePassword";

        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test//忘记密码(重置密码)
    public void test_forgetPassword() throws Exception{
        /****请求参数*****/
        String s = Md532Util.MD5_32bit(Md532Util.MD5_32bit("FwFY/Zha+TzNR0DMSvOq").concat(Md532Util.MD5_32bit("12345")));
        params.add("interfaceCode","forgetPassword");
        params.add("code","9373");
        params.add("password",s);
        params.add("telephone","17349781961");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "personalData/forgetPassword";

        requestURL(BaseTest.url,interfaceUrl);
    }

    @Test //头像上传
    public void test_headPicUpload() throws Exception{
        params.add("interfaceCode","headPicUpload");
        params.add("userId","65");
        map = this.MultiValueMapToMap(params);
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        params.add("file", new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        });
        String interfaceUrl = "personalData/headPicUpload";

        requestURL(BaseTest.url,interfaceUrl);
    }
}
