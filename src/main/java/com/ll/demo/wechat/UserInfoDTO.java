package com.ll.demo.wechat;

import java.util.List;

/**
 * 微信获取用户信息
 * @author LL
 *
 */
public class UserInfoDTO {

	private String openid;
	
	private String nickname;
	
	private int sex;			//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	
	private String province;
	
	private String city;
	
	private String country;
	
	private String headimgurl; 
	
	private String unionid;
	
	private List<String> privilege;	//用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}
	
}
