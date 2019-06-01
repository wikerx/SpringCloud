## zuul

网关层(负责校验、权限等拦截）

因为JDK1.8中jre\lib\security中两个 jar 包替换的缘故。将下载后的local_policy.jar和US_export_policy.jar替换到JDK1.8的jre\lib\security文件夹即可。 地址：http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html

注意：若使用QQ邮箱发送邮件，则需要修改为spring.mail.host=smtp.qq.com，同时spring.mail.password改为QQ邮箱的授权码。
QQ邮箱->设置->账户->POP3/SMTP服务:开启服务后会获得QQ的授权码 
