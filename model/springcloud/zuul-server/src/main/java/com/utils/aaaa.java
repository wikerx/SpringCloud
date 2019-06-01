package com.utils;

import com.model.InterfaceBeanVO;
import com.model.InterfaceCodeVO;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

public class aaaa {
    private static final Logger log = LoggerFactory.getLogger(EOPinterfaceFirstkeyManager.class);

    private File m_file = new File("interfaceBeans.xml");
    private File m_file2 = new File("interfaceFirstKey.xml");
    private HashMap<String, InterfaceCodeVO> map = new HashMap<String, InterfaceCodeVO>();
    private HashMap<String, InterfaceCodeVO> map2 = new HashMap<String, InterfaceCodeVO>();

    public HashMap<String, InterfaceCodeVO> loadxml() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(java.net.URLDecoder.decode(m_file.getAbsolutePath(), "UTF-8"));
        Element root = doc.getRootElement();
        Iterator iter = root.elementIterator();
        InterfaceCodeVO vo = null;
        while (iter != null && iter.hasNext()) {
            Element element = (Element) iter.next();
            String beanid = element.element("beanid").getStringValue();
            String beanclass = element.element("beanclass").getStringValue();
            String interfacecode = element.element("interfacecode").getStringValue();
            vo = new InterfaceCodeVO();
            vo.setBeanclass(beanclass);
            vo.setBeanid(beanid);
            vo.setInterfacecode(interfacecode);
            map.put(interfacecode, vo);
        }
        return map;
    }

    public HashMap<String, InterfaceCodeVO> loadxml2() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(java.net.URLDecoder.decode(m_file2.getAbsolutePath(), "UTF-8"));
        Element root = doc.getRootElement();
        Iterator iter = root.elementIterator();
        InterfaceCodeVO vo = null;
        while (iter != null && iter.hasNext()) {
            Element element = (Element) iter.next();
            String code = element.element("code").getStringValue();
            String keys = element.element("keys").getStringValue();
            vo = new InterfaceCodeVO();
            vo.setKeys(keys);
            vo.setInterfacecode(code);
            map2.put(code, vo);
        }
        return map2;
    }

    public static void main(String[] args)  throws Exception{
        aaaa a = new aaaa();
        HashMap<String, InterfaceCodeVO> loadxml = a.loadxml();
        HashMap<String, InterfaceCodeVO> loadxml2 = a.loadxml2();
        List<InterfaceCodeVO> list =  new ArrayList<>();

        log.info("{}",loadxml.size());
        log.info("{}",loadxml2.size());
        Iterator<Map.Entry<String, InterfaceCodeVO>> iterator = loadxml.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, InterfaceCodeVO> next = iterator.next();
            String interfaceCode = (String)next.getKey();
            InterfaceCodeVO interfaceCodeVO = next.getValue();
            if(loadxml2.get(interfaceCode) != null){
                InterfaceCodeVO interfaceCodeVO1 = loadxml2.get(interfaceCode);
                interfaceCodeVO.setKeys(interfaceCodeVO1.getKeys());
            }
            list.add(interfaceCodeVO);
        }

        String sql = "INSERT INTO `interfacecode` (`beanid`, `beanclass`, `interfacecode`, `keys`,`application`,  `label`) VALUES ";
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        for(InterfaceCodeVO ic :list){
            sb.append(sql+"('"+ic.getBeanid()+"', '"+ic.getBeanclass()+"', '"+ic.getInterfacecode()+"','"+ic.getKeys()+"' , 'zuul-server', 'master');");
            sb.append("\n");
        }
        log.info(sb.toString());
    }
}
