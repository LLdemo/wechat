package com.ll.demo.wechat;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 
 * @author LL
 *
 */
public class SystemConfig {

	private static Properties properties = null;
	
	static{
		try {
			properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("systemConfig.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getToken(){
		return properties.getProperty("token");
	}
	
	public static String getAppID(){
		return properties.getProperty("appID");
	}
	
	public static String getAppsecret(){
		return properties.getProperty("appsecret");
	}
	
	public static String getRedirectUri(){
		return properties.getProperty("redirectUri");
	}
	
}
