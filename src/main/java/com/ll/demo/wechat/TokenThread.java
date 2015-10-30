package com.ll.demo.wechat;

import org.slf4j.Logger;    
import org.slf4j.LoggerFactory; 

/** 
 * 定时获取微信access_token的线程 
 * 
 */  
public class TokenThread implements Runnable {
	
	private static Logger log = LoggerFactory.getLogger(TokenThread.class);   
	
	public static AccessTokenDTO accessToken = null;
	
    public void run() {  
        while (true) {  
            try {  
            	accessToken = WeChatUtils.getAccessTokenDTO();  
                if (null != accessToken) {
                    log.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpires_in(), accessToken.getAccess_token());  
                    // 休眠7000秒  
                    Thread.sleep((accessToken.getExpires_in() - 200) * 1000);  
                } else {  
                    // 如果access_token为null，60秒后再获取  
                    Thread.sleep(60 * 1000);  
                }  
            } catch (InterruptedException e) {  
                try {  
                    Thread.sleep(60 * 1000);  
                } catch (InterruptedException e1) {  
                    log.error("{}", e1);  
                }  
                log.error("{}", e);  
            }  
        }  
    }  
}  