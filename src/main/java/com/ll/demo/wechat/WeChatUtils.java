package com.ll.demo.wechat;

import java.net.URLEncoder;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

public class WeChatUtils {

	public static String appID = SystemConfig.getAppID();
	
	public static String secret = SystemConfig.getAppsecret();
	
	public static Date getTokenDate = null;
	
	public static String accessTokenUrl = null;
	
	public static String accessToken = null;
	
	public static final int ACCESS_TOKEN_EXPIRES = 7000;		//刷新token时间  秒
	
	static {
		StringBuilder sb = new StringBuilder();
		sb.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=");
		sb.append(SystemConfig.getAppID());
		sb.append("&secret=");
		sb.append(SystemConfig.getAppsecret());
		accessTokenUrl = sb.toString();
	}
	
	/**
	 * access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token
	 * @return
	 */
	public static synchronized String getAccessToken(){
		
		Date currentDate = new Date();
		
		if(getTokenDate == null || DateUtils.addSeconds(getTokenDate, ACCESS_TOKEN_EXPIRES).before(currentDate)){
			AccessTokenDTO accessTokenDTO = getAccessTokenDTO();
			
			if(accessTokenDTO != null){
				getTokenDate = new Date();
				accessToken = accessTokenDTO.getAccess_token();
			}
			
		}
		
		return accessToken;
	}
	
	public static AccessTokenDTO getAccessTokenDTO(){
		String responseBody = HttpClientUtils.getResponseBody(accessTokenUrl);
		AccessTokenDTO accessTokenDTO = (AccessTokenDTO) JSONObject.toBean(JSONObject.fromObject(responseBody), AccessTokenDTO.class);
		return accessTokenDTO;
	}
	
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
	
	/**
	 * 授权登录的token
	 * @param code
	 * @return
	 */
	public static AccessTokenDTO getLoginAccessTokenDTO(String code){
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
