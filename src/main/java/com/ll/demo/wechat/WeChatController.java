package com.ll.demo.wechat;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeChatController {

	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model){
		
		String mobileCodeUrl = WeChatUtils.getMobileCodeUrl();
		model.addAttribute("mobileCodeUrl", mobileCodeUrl);
		
		String pcCodeUrl = WeChatUtils.getPcCodeUrl();
		model.addAttribute("pcCodeUrl", pcCodeUrl);

		//测试access_token
		//第一种 直接获取 通过synchronized 控制并发
//		System.out.println("access_token---> " + WeChatUtils.getAccessToken());
		
		//第二种 写一个线程自动刷新
		if(TokenThread.accessToken != null){
			System.out.println("access token : " + TokenThread.accessToken.getAccess_token());
		}else{
			System.out.println("access token is null");
		}
		
		return "/index";
	}

	
	@RequestMapping("/init")
	@ResponseBody
	public String init(HttpServletRequest request){
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			return echostr;
		}
		
		return null;
	}
	
	@RequestMapping("/userInfo")
	public String userInfo(HttpServletRequest request, Model model){
		
		String code = request.getParameter("code");
		
		AccessTokenDTO accessTokenDTO = WeChatUtils.getLoginAccessTokenDTO(code);
		
		if(accessTokenDTO != null){
			UserInfoDTO userInfoDTO = WeChatUtils.getUserInfoDTO(accessTokenDTO.getOpenid(), accessTokenDTO.getAccess_token());
			model.addAttribute("userInfoDTO", userInfoDTO);
			System.out.println(JSONObject.valueToString(userInfoDTO));
		}
		
		return "/user_info";
	}
	
}
