package com.ll.demo.wechat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;    
import org.slf4j.Logger;    
import org.slf4j.LoggerFactory;

public class InitWeChatServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;    
    private static Logger log = LoggerFactory.getLogger(InitWeChatServlet.class);    
    
    public void init() throws ServletException {   
    
    	log.info("init webchat servet start");
        // 启动定时获取access_token的线程    
        new Thread(new TokenThread()).start();
        
    	log.info("init webchat servet end");
    }   
	
}
