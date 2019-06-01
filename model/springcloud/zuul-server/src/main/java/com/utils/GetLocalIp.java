package com.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * 获取本地IP地址
 *
 */
public class GetLocalIp {

    public static List<String> getIpAddress(String networkInterfaceName) throws Exception {
        Collection<InetAddress> colInetAddress = getAllHostAddress(networkInterfaceName);
        List<String> ipList = new ArrayList<String>();
        for (InetAddress address : colInetAddress) {
            if (!address.isLoopbackAddress()){
                ipList.add(address.getHostAddress());
            }
        }
        return ipList;
    }

    public static Collection<InetAddress> getAllHostAddress(String networkInterfaceName) throws Exception {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        Collection<InetAddress> addresses = new ArrayList<InetAddress>();
        networkInterfaceName = String.valueOf(networkInterfaceName).trim();

        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            if(networkInterfaceName.equals(networkInterface.getName())){
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if(inetAddress.getAddress().length == 4){
                        addresses.add(inetAddress);
                    }
                }
            }
        }
        return addresses;
    }
}
