package com.nieyue.util;


import org.apache.commons.codec.digest.DigestUtils;


/**
 * 
 *加密
 *解密
 * @author yy
 *
 */
public class MyDESutil {
	private static final String SHA_SLAT="sadfe4f#23%.;'+sdfssdf43543534";
	private static final String MD5_SLAT="sa2334f#23%.;'+sdfs233543dssdf4";
	/**
	 * 获取SHA
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String getSHA(Object obj){
	 String sha1 = DigestUtils.sha1Hex(obj+SHA_SLAT);
		return sha1;
	}
	/**
	 * 获取MD5
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String getMD5(Object obj){
		String md5 = DigestUtils.md5Hex(obj+MD5_SLAT);
		return md5;
	}
	
	public static void main(String[] args) throws Exception {
		String sha1 =getSHA("sdfadsf");
		System.out.println(sha1);
		
		String md5=getMD5("123456");
		System.out.println(md5);
		 
	}
}
