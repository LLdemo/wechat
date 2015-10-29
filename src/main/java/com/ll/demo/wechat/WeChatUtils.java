package com.ll.demo.wechat;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

public class WeChatUtils {

	public static String appID = SystemConfig.getAppID();
	
	public static String secret = SystemConfig.getAppsecret();
	
	public static String getMobileCodeUrl(){
		
		StringBuilder sb = new StringBuilder();
		try {

			sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
			sb.append(SystemConfig.getAppID());
			sb.append("&redirect_uri=");
			sb.append(URLEncoder.encode(SystemConfig.getRedirectUri(), "utf-8"));
			sb.append("&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public static String getPcCodeUrl(){

		StringBuilder sb = new StringBuilder();
		try {

			sb.append("https://open.weixin.qq.com/connect/qrconnect?appid=");
			sb.append(SystemConfig.getAppID());
			sb.append("&redirect_uri=");
			sb.append(URLEncoder.encode(SystemConfig.getRedirectUri(), "utf-8"));
			sb.append("&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public static AccessTokenDTO getAccessTokenDTO(String code){
		String uri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appID+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		String responseBody = HttpClientUtils.getResponseBody(uri);
		if(StringUtils.isNotBlank(responseBody)){
			AccessTokenDTO accessTokenDTO = (AccessTokenDTO) JSONObject.toBean(JSONObject.fromObject(responseBody), AccessTokenDTO.class);
			return accessTokenDTO;
		}
		return null;
	}
	
	public static UserInfoDTO getUserInfoDTO(String openId, String accessToken){
		String uri = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
		String responseBody = HttpClientUtils.getResponseBody(uri);
		System.out.println("responseBody:" + responseBody);
		if(StringUtils.isNotBlank(uri)){
			
			UserInfoDTO userInfoDTO = (UserInfoDTO) JSONObject.toBean(JSONObject.fromObject(responseBody), UserInfoDTO.class);
			
			return userInfoDTO;
		}
		
		return null;
	}
	
}
