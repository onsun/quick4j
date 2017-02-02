package com.eliteams.quick4j.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @ClassName: WebHttpController
 * @author  作者 E-mail <a href="sunxiakun@163.com">sunxiakun</a> 
 * @version 1.0 创建时间：2017年2月2日下午1:16:45
 */

@Controller
@RequestMapping("webtest")
public class WebHttpController {
	/**
	 * 
	* TODO(http接口test) 
	* @param request
	* @return  String
	 */
	@ResponseBody
    @RequestMapping("first")
    public JSONObject first(HttpServletRequest request) {
		JSONObject result=new JSONObject();
		result.put("msg", "this is result");
        return result;
    }

}