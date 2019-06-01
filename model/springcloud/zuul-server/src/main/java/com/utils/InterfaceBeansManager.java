package com.utils;

import com.model.InterfaceBeanVO;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class InterfaceBeansManager {

    private static final Logger logger = LoggerFactory.getLogger(InterfaceBeansManager.class);
    private static String PFILE = "";
    private File m_file = new File("interfaceBeans.xml");
    private long m_lastModifiedTime = 0;
    InputStream inputStream = null;
    private HashMap<String, InterfaceBeanVO> map = new HashMap<String, InterfaceBeanVO>();
    private volatile static InterfaceBeansManager m_instance;

    @SuppressWarnings("static-access")
    private InterfaceBeansManager() {
        try {
            Resource resource = new ClassPathResource("interfaceBeans.xml");
            //只能在运行的本地环境获取到，不能在jar包里面获取
//            m_file = new File("interfaceBeans.xml");
            FileUtils.inputstreamToFile(resource.getInputStream(),m_file);
//            m_file = ResourceUtils.getFile("classpath:interfaceBeans.xml");
            PFILE = m_file.getAbsolutePath();
            logger.info("\n加载请求接口资源文件路径:" + java.net.URLDecoder.decode(this.PFILE, "UTF-8"));

            m_lastModifiedTime = m_file.lastModified();
            if (m_lastModifiedTime == 0) {
                logger.info(PFILE + " 文件不存在!");
            }
            loadxml();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /**
     * 单例模式的双重校验锁
     *
     * @return
     */
    public static InterfaceBeansManager getInstance() {
        if (m_instance == null) {
            synchronized (InterfaceBeansManager.class) {
                if (m_instance == null) {
                    m_instance = new InterfaceBeansManager();
                }
            }
        }
        return m_instance;
    }

    /**
     * 得到spring管理bean
     *
     * @param interfacecode
     * @param defaultVal
     * @return
     */
    final public String getBeanConfigItem(String interfacecode, String defaultVal) {
        long newTime = m_file.lastModified();

        if (newTime == 0) {
            if (m_lastModifiedTime == 0) {
                logger.info(PFILE + " 文件不存在!");
            } else {
                logger.info(PFILE + " 文件已删除!!");
            }
            return defaultVal;
        } else if (newTime > m_lastModifiedTime) {
            try {
                loadxml();    // 重新加载xml
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        m_lastModifiedTime = newTime;

        InterfaceBeanVO val = map.get(interfacecode);
        if (val == null) {
            return defaultVal;
        } else {
            if (val.getBeanId() == null) {
                return defaultVal;
            } else {
                return val.getBeanId();
            }

        }
    }

    @SuppressWarnings("unchecked")
    public void loadxml() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(java.net.URLDecoder.decode(PFILE, "UTF-8"));
        Element root = doc.getRootElement();
        Iterator iter = root.elementIterator();
        map.clear();
        InterfaceBeanVO vo = null;
        while (iter != null && iter.hasNext()) {
            Element element = (Element) iter.next();
            String beanid = element.element("beanid").getStringValue();
            String beanclass = element.element("beanclass").getStringValue();
            String interfacecode = element.element("interfacecode").getStringValue();
            vo = new InterfaceBeanVO(beanid, beanclass, interfacecode);
            map.put(interfacecode, vo);
        }
    }
}
