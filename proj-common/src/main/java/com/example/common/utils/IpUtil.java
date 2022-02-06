package com.example.common.utils;

import com.alibaba.fastjson.JSON;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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


    /**
     * 获取访问者IP地址
     * <p>在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。</p>
     * <p>本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)。</p>
     * <p>如果还不存在则调用Request.getRemoteAddr()。</p>
     *
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            log.info("X-Real-IP--->");
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            if (index != -1) {
                log.info("X-Forwarded-For 0");
                return ip.substring(0, index);
            } else {
                log.info("X-Forwarded-For --->");
                return ip;
            }
        } else {
            log.info("remoteAddr 1--->");
            return request.getRemoteAddr();
        }
    }
}
