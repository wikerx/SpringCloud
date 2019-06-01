package user;
import base.BaseTest;
import com.utils.FileUtil;
import com.utils.Md532Util;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

public class PersonalTest extends BaseTest {

    @Before
    public void module(){
        params.add("module","tourism");
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

        requestURL(BaseTest.prod_url_user,interfaceUrl);
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

        requestURL(BaseTest.url_user,interfaceUrl);
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

        requestURL(BaseTest.url_user,interfaceUrl);
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

        requestURL(BaseTest.url_user,interfaceUrl);
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

        requestURL(BaseTest.url_user,interfaceUrl);
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
            File file = new File("E:\\img\\1527837077174.jpg");
            @Override
            public String getName() {
                return file.getName();
            }

            @Override
            public String getOriginalFilename() {
                return file.getName();
            }

            @Override
            public String getContentType() {
                return "multipart/form-data";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return file.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return FileUtil.getBytes(file.getAbsolutePath());
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return FileUtil.fileToInputStream(file.getAbsolutePath());
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        });
        String interfaceUrl = "personalData/headPicUpload";

        requestURL_multiform(BaseTest.url_user,interfaceUrl);
    }

    @Test //修改性别
    public void test_updatesex() throws Exception {
        /****请求参数*****/
        params.add("interfaceCode","updateSex");
        params.add("userId","121");
        params.add("sex","1");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);
        params.add("token","dS72YyTj8vixUNAZPmVK"); //token不进行签名
        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "personalData/updateSex";

        requestURL(BaseTest.url_user,interfaceUrl);
    }


    @Test //修改手机号
    public void test_modifyTel() throws Exception {
        /****请求参数*****/
        params.add("interfaceCode","modifyTel");
        params.add("userId","129");
        params.add("telephone","13133333331");
        params.add("sendCode","1234");
        params.add("token","fcG+23XeofnjqYUwCB6NsQ==");
        /****请求参数*****/
        /****计算sign*****/
        map = this.MultiValueMapToMap(params);

        /****计算sign*****/
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(map);
        String serverSign = (String)protoMap.get("sign");
        System.out.println("====================="+serverSign);
        params.add("sign",serverSign);
        String interfaceUrl = "user/modifyTel?module=repair";

        requestURL(BaseTest.test_url_user,interfaceUrl);
    }

}
