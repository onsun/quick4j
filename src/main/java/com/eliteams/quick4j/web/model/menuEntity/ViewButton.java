package com.eliteams.quick4j.web.model.menuEntity;
/**
 * 表示二级菜单（VIEW类型）
 * @ClassName: ViewButton
 * @author  作者 E-mail <a href="sunxiakun@163.com">sunxiakun</a> 
 * @version 1.0 创建时间：2017年2月6日下午2:13:16
 */
public class ViewButton extends Button {
	private String type;//菜单类型
	private String url;//view路径值
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
