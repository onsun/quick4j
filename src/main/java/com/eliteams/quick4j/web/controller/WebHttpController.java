package com.eliteams.quick4j.web.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eliteams.quick4j.core.generic.MsgType;
import com.eliteams.quick4j.core.util.xmlUtil.ParseReceiveXml;
import com.eliteams.quick4j.web.model.xmlEntity.ReceiveXmlEntity;

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
	@RequestMapping(value = "accessing", produces = {"application/json;charset=UTF-8"})
    public String weixin(HttpServletRequest request,HttpServletResponse response) {
		
		try{
			//access(request,response);
			String msg=acceptMessage(request,response);
			if(!msg.equals("")){
				return msg;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
    }
	private String acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {  
        // 处理接收消息  
        ServletInputStream in = request.getInputStream();  
        StringBuilder content = new StringBuilder();  
        byte[] b = new byte[1024];  
        int lens = -1;  
        while ((lens = in.read(b)) > 0) {  
            content.append(new String(b, 0, lens));  
        }  
        String strcont = content.toString();// 内容
        ReceiveXmlEntity xmlEntity=new ParseReceiveXml().getMsgEntity(strcont);
        if(xmlEntity.getMsgType().equals(MsgType.Text)){
        	 StringBuffer str = new StringBuffer();  
             str.append("<xml>");  
             str.append("<ToUserName><![CDATA[" + xmlEntity.getFromUserName() + "]]></ToUserName>");  
             str.append("<FromUserName><![CDATA[" + xmlEntity.getToUserName() + "]]></FromUserName>");  
             str.append("<CreateTime>" +  Calendar.getInstance().getTimeInMillis() / 1000 + "</CreateTime>");  
             str.append("<MsgType><![CDATA[" + xmlEntity.getMsgType() + "]]></MsgType>");  
             str.append("<Content><![CDATA[你说的是：" + xmlEntity.getContent() + "，吗？]]></Content>");  
             str.append("</xml>");  
             return str.toString();
        }
        return "";
    }  
	/**
	 * 
	* TODO(验证URL真实性) 
	* @param request
	* @param response
	* @throws IOException  void
	 */
	@SuppressWarnings("unused")
	private String access(HttpServletRequest request, HttpServletResponse response) throws IOException { 
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