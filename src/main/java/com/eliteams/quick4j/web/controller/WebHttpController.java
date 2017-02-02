package com.eliteams.quick4j.web.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
@RequestMapping("weixin")
public class WebHttpController {
	 private Logger log =Logger.getLogger(this.getClass().getName());  
	 private   String Token="myowen1314"; 
	 private   String echostr;  
	/**
	 * 
	* TODO(http接口test) 
	* @param request
	* @return  String
	 */
	@ResponseBody
    @RequestMapping("accessing")
    public String weixin(HttpServletRequest request) {
		String signature = request.getParameter("signature");  
        String timestamp = request.getParameter("timestamp");  
        String nonce = request.getParameter("nonce");  
        String echostr = request.getParameter("echostr");  
        if(isEmpty(signature)){  
            return "signature can't be null"; 
        }  
        if(isEmpty(timestamp)){  
            return "timestamp can't be null";
        }  
        if(isEmpty(nonce)){  
            return "nonce can't be null";
        }  
        if(isEmpty(echostr)){  
            return "echostr can't be null";
        }  
        String[] ArrTmp = { Token, timestamp, nonce };  
        Arrays.sort(ArrTmp);  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < ArrTmp.length; i++) {  
            sb.append(ArrTmp[i]);  
        }  
        String pwd = Encrypt(sb.toString());  
           
        log.info("signature:"+signature+"timestamp:"+timestamp+"nonce:"+nonce+"pwd:"+pwd+"echostr:"+echostr);  
          
        if(trim(pwd).equals(trim(signature))){  
            this.echostr =echostr;  
            return echostr;
        }else{  
            return "fail";
        } 
    }
	 private String Encrypt(String strSrc) {  
	        MessageDigest md = null;  
	        String strDes = null;  
	  
	        byte[] bt = strSrc.getBytes();  
	        try {  
	            md = MessageDigest.getInstance("SHA-1");  
	            md.update(bt);  
	            strDes = bytes2Hex(md.digest()); //to HexString  
	        } catch (NoSuchAlgorithmException e) {  
	            System.out.println("Invalid algorithm.");  
	            return null;  
	        }  
	        return strDes;  
	    }  
	  
    public String bytes2Hex(byte[] bts) {  
        String des = "";  
        String tmp = null;  
        for (int i = 0; i < bts.length; i++) {  
            tmp = (Integer.toHexString(bts[i] & 0xFF));  
            if (tmp.length() == 1) {  
                des += "0";  
            }  
            des += tmp;  
        }  
        return des;  
    }  
    private boolean isEmpty(String str){  
        return null ==str || "".equals(str) ? true :false;  
    }  
    private String trim(String str){  
        return null !=str  ? str.trim() : str;  
    } 

}