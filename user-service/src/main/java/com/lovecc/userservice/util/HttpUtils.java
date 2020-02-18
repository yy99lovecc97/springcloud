package com.lovecc.userservice.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpUtils {
    /**
     *尝试获取HttpServletRequest（非bean方式）
     * @return
     */
    public static HttpServletRequest getHttpServletRequest(){
        try{
            return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        }catch (Exception e){
            return null;
        }
    }
    public static Map<String,String> getHeaders(HttpServletRequest request){
        Map<String,String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key,value);
        }
        return map;
    }

    /**
     * 获取请求客户端的真实ip地址
     * @param request
     * @return ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {

        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        /**
         * X-Forwarded-For 是一个扩展头。HTTP/1.1（RFC 2616）协议并没有对它的定义，它最开始是由 Squid 这个缓存代理软件引入
         * ，用来表示 HTTP 请求端真实 IP，现在已经成为事实上的标准，被各大 HTTP 代理、负载均衡等转发服务广泛使用
         * ，并被写入 RFC 7239（Forwarded HTTP Extension）标准之中.
         * X-Forwarded-For请求头格式:
         * X-Forwarded-For:client, proxy1, proxy2
         */
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
    /**
     * 获取请求客户端的真实ip地址
     *
     * @param
     * @return ip地址
     */
    public static String getIpAddress() {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        return getIpAddress(getHttpServletRequest());
    }

}
