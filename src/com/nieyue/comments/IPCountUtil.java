package com.nieyue.comments;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


/**
 * ip统计
 * @author yy
 *
 */
public class IPCountUtil {
	static int getIPByDay( ){
		return 0;
	}
	
	/**
	* 获取访问者IP
	* 
	* 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	* 
	* 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	* 如果还不存在则调用Request .getRemoteAddr()。
	* 
	* @param request
	* @return
	*/
	public static String getIpAddr(HttpServletRequest request){
	String ip = request.getHeader("X-Real-IP");
	if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
	return ip;
	}
	ip = request.getHeader("X-Forwarded-For");
	if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
	// 多次反向代理后会有多个IP值，第一个为真实IP。
	int index = ip.indexOf(',');
	if (index != -1) {
	return ip.substring(0, index);
	} else {
	return ip;
	}
	} else {
	return request.getRemoteAddr();
	}
	}
	/**
	 * 获取当前网络ip
	 * @param request
	 * @return
	 */
	public String getIpAddr2(HttpServletRequest request){
		String ipAddress = request.getHeader("x-forwarded-for");
			if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
					//根据网卡取本机配置的IP
					InetAddress inet=null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ipAddress= inet.getHostAddress();
				}
			}
			//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
				if(ipAddress.indexOf(",")>0){
					ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
				}
			}
			return ipAddress; 
	}
	
	
}
