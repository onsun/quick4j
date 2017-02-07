package com.eliteams.quick4j.core.util.xmlUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.sword.wechat4j.common.Config;
import org.sword.wechat4j.token.AccessToken;
import org.sword.wechat4j.token.TokenProxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eliteams.quick4j.web.model.menuEntity.Button;
import com.eliteams.quick4j.web.model.menuEntity.CommonButton;
import com.eliteams.quick4j.web.model.menuEntity.ComplexButton;
import com.eliteams.quick4j.web.model.menuEntity.Menu;
import com.eliteams.quick4j.web.model.menuEntity.ViewButton;

public class MenuUtil {
	/**
	 * 封装菜单数据
	 * */
	public static Menu getMenu(){
	//首先创建二级菜单
	CommonButton cb_1 = new CommonButton();
	cb_1.setKey("key1");
	cb_1.setName("子菜单1");
	cb_1.setType("click");
	
	
	CommonButton cb_2 = new CommonButton();
	cb_2.setKey("key2");
	cb_2.setName("子菜单2");
	cb_2.setType("click");
	
	//创建第一个一级菜单
	ComplexButton cx_1 = new ComplexButton();
	cx_1.setName("一级菜单");
	cx_1.setSub_button(new Button[]{cb_1,cb_2});
	
	
	//继续创建二级菜单
	CommonButton cb_3 = new CommonButton();
	cb_3.setKey("key3");
	cb_3.setName("子菜单3");
	cb_3.setType("click");
	
	
	ViewButton cb_4 = new ViewButton();
	cb_4.setName("访问网页");
	cb_4.setType("view");
	//需要使用网页授权获取微信用户的信息
	String url="http%3a%2f%2fluckspring.vicp.cc%2frest%2fweixin%2fconnectUserInfo";
	String appid=Config.instance().getAppid();
	cb_4.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+url+"&response_type=code&scope=snsapi_userinfo&state=121#wechat_redirect");
	
	//创建第二个一级菜单
	ComplexButton cx_2 = new ComplexButton();
	cx_2.setName("一级菜单");
	cx_2.setSub_button(new Button[]{cb_3,cb_4});
	
	//封装菜单数据
	Menu menu=new Menu();
	menu.setButton(new ComplexButton[]{cx_1,cx_2});
	return menu;
	}
	/**
	 * 创建自定义菜单(每天限制1000次)
	 * */
	public static int createMenu(Menu menu) {
		String jsonMenu = JSON.toJSONString(menu);

		int status = 0;
		//String accesstoken=AccessTokenProxy.token();
		System.out.println(Config.class.getProtectionDomain().getCodeSource());
		String accesstoken = TokenProxy.accessToken(); //1.2 、1.3
		System.out.println("菜单：" + jsonMenu);
		String path = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accesstoken;
		try {
			URL url = new URL(path);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setDoOutput(true);
			http.setDoInput(true);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(jsonMenu.getBytes("UTF-8"));
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] bt = new byte[size];
			is.read(bt);
			String message = new String(bt, "UTF-8");
			JSONObject jsonMsg = JSON.parseObject(message);
			status = Integer.parseInt(jsonMsg.getString("errcode"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}
}
