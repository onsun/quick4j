package com.eliteams.quick4j.web.model.menuEntity;
/**
 * 表示二级菜单(CLICK类型)
 * @ClassName: CommonButton
 * @author  作者 E-mail <a href="sunxiakun@163.com">sunxiakun</a> 
 * @version 1.0 创建时间：2017年2月6日下午2:12:03
 */
public class CommonButton extends Button {
	private String type;//菜单类型
	private String key;//key值
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
