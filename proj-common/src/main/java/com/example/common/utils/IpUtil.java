package com.example.common.utils;

import com.alibaba.fastjson.JSON;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuchao
 * @date 2022/2/5 11:40 下午
 */
@Slf4j
public class IpUtil {

    private static String getLocalIp() throws UnknownHostException {
        String sysType = System.getProperties().getProperty("os.name");
        if (sysType.equals("win")) {
            InetAddress ia = InetAddress.getLocalHost();
            String localip = ia.getHostAddress();
            return localip;
        } else if (sysType.equalsIgnoreCase("Linux")) {
            return getIpByEthNum("eth0");
        } else if (sysType.equals("Mac OS X")) {
            return getIpByEthNum("en0");
        }
        throw new RuntimeException("不支持的操作系统:" + sysType);
    }

    /**
     * 根据网络接口获取IP地址
     *
     * @param ethNum 网络接口名，Linux下是eth0
     * @return
     */
    private static String getIpByEthNum(String ethNum) {
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (ethNum.equals(netInterface.getName())) {
                    Enumeration addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = (InetAddress) addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            log.error(e.getMessage(), e);
        }
        return "获取服务器IP错误";
    }
}
