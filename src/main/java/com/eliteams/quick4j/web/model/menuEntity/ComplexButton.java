package com.eliteams.quick4j.web.model.menuEntity;
/**
 * 表示一级菜单
 * @ClassName: ComplexButton
 * @author  作者 E-mail <a href="sunxiakun@163.com">sunxiakun</a> 
 * @version 1.0 创建时间：2017年2月6日下午2:11:22
 */
public class ComplexButton extends Button {
	private Button[] sub_button;//子级菜单，因为一个一级菜单可以有多个二级菜单，所以这儿使用二级 菜单类型的集合
	public Button[] getSub_button() {
		return sub_button;
	}
	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
}
