package com.factory;


import com.alibaba.fastjson.JSON;
import com.model.InterfaceCodeVO;
import com.protocol.ProtocolUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

public enum ProtocolServiceFactory {
    instance;
    protected static final Logger logger = LoggerFactory.getLogger(ProtocolServiceFactory.class);

    public static ProtocolUtilService createService(String code, ApplicationContext ctx) {
        try {
            Environment env = ctx.getEnvironment();
//            InterfaceBeansManager interfaceBeansManager = InterfaceBeansManager.getInstance();
//            String beanId = InterfaceBeansManager.getInstance().getBeanConfigItem(code, "");
            String beanId = env.getProperty(code,"");
            if(StringUtils.isEmpty(beanId)){
//                logger.error("检查interfaceBean.xml文件code是否正确:{}",code);
                logger.error("检查interfaceCode 表code是否正确:{}",code);
                return null;
            }
            InterfaceCodeVO vo = JSON.parseObject(beanId,InterfaceCodeVO.class);
            return (ProtocolUtilService)ctx.getBean(vo.getBeanid());
        } catch (Exception e) {
            logger.error("ProtocolServiceFactory 异常: {}",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
