package com.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapUtils {

    /**
     * 讲request.getParameterMap 转为普通的Map
     * @param reqMap
     * @return
     */
    public static Map<String,String> toMap(Map<String,String[]> reqMap) {
        Map<String, String> tempMap = new HashMap<String, String>();
        Set<Map.Entry<String, String[]>> set = reqMap.entrySet();
        Iterator<Map.Entry<String, String[]>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String[]> entry = it.next();
            for (String str : entry.getValue()) {
                tempMap.put(entry.getKey(), str);
            }
        }
        return tempMap;
    }
}
