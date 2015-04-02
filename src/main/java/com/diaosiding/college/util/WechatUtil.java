package com.diaosiding.college.util;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class WechatUtil {

	/*	
	public static Map<String, String> parseXml(HttpServletRequest request) {
		Map<String, String> dict = new HashMap<String, String>();
		
		try {
			InputStream inputStream = request.getInputStream();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dict;
	}
	*/
	
	public static String sha1(String key) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(key.getBytes());
			String pwd = new BigInteger(1, md.digest()).toString(16);
			
			return pwd;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return key;
		}
		
	}
}
