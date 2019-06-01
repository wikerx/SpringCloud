package com.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class EOPinterfaceFirstkeyManager {
    private static final Logger log = LoggerFactory.getLogger(EOPinterfaceFirstkeyManager.class);

    private static String PFILE = "";

    private File m_file = null;
    private long m_lastModifiedTime = 0;
    private HashMap<String, String> map = new HashMap<String, String>();
    private static EOPinterfaceFirstkeyManager m_instance = new EOPinterfaceFirstkeyManager();

    @SuppressWarnings("static-access")
    private EOPinterfaceFirstkeyManager() {
        try {
            ClassPathResource classPathResource = new ClassPathResource("interfaceFirstKey.xml");
            InputStream inputStream = classPathResource.getInputStream();
            m_file = new File("interfaceFirstKey.xml");
            FileUtils.inputstreamToFile(inputStream,m_file);
//            m_file = ResourceUtils.getFile("classpath:interfaceFirstKey.xml");
            PFILE = m_file.getAbsolutePath();
            log.info("\n加载请求接口验签配置文件路径:"+ java.net.URLDecoder.decode(this.PFILE, "UTF-8"));

            m_lastModifiedTime = m_file.lastModified();
            if (m_lastModifiedTime == 0) {
                log.info(PFILE + " 文件不存在!");
            }
            loadxml();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static EOPinterfaceFirstkeyManager getInstance() {
        if (m_instance == null) {
            m_instance = new EOPinterfaceFirstkeyManager();
        }
        return m_instance;
    }

    /**
     * 根据接口代码获取相应的参与验签的一级参数key值
     *
     * @param interfacecode
     * @param defaultVal
     * @return
     */
    public final String getInterfaceFirstKeys(String interfacecode,
                                              String defaultVal) {
        long newTime = m_file.lastModified();

        if (newTime == 0) {
            if (m_lastModifiedTime == 0) {
                log.info(PFILE + " 文件不存在!");
            } else {
                log.info(PFILE + " 文件已删除!!");
            }
            return defaultVal;
        } else if (newTime > m_lastModifiedTime) {
            try {
                loadxml(); // 重新加载xml
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        m_lastModifiedTime = newTime;

        String val = map.get(interfacecode);
        if (null == val || "".equals(val)) {
            return defaultVal;
        } else {
            return val;
        }
    }

    @SuppressWarnings("unchecked")
    public void loadxml() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(java.net.URLDecoder
                .decode(PFILE, "UTF-8"));
        Element root = doc.getRootElement();
        Iterator iter = root.elementIterator();
        HashMap<String, String> loadMap = new HashMap<String, String>();
        while (iter != null && iter.hasNext()) {
            Element element = (Element) iter.next();
            loadMap.put(element.element("code").getStringValue().replaceAll("\\s", ""), element.element("keys").getStringValue().replaceAll("\\s", ""));
        }
        //每个接口参数都加上全局参数key
        String globalFirstLevelKey = loadMap.remove("globalFirstParamKey");
        Set set = loadMap.entrySet();
        Iterator iterator = set.iterator();
        map.clear();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            map.put(entry.getKey(), globalFirstLevelKey + "," + entry.getValue());
        }
        loadMap.clear();
    }
}