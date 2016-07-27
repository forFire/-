package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.manage.MailManage;


@Controller
@RequestMapping("/mail")
public class MailCtrlTest extends Thread {
	
	@Autowired
	private MailManage mailManage;
	
	
	@RequestMapping("/sendMail")
	public String sendMail(){
		try {
			mailManage.sendMail("","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/spring/index";
	}
	
	
	
	
	
//	private String userName;
//	private String email;
//	private String pwdToken;
//
//	public void run() {
//		try {
//			mailManage.sendMailText(userName, email, pwdToken);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
//
//	public MailCtrlTest(MailManage mailManage, String userName, String email, String pwdToken) {
//		super();
//		this.mailManage = mailManage;
//		this.userName = userName;
//		this.email = email;
//		this.pwdToken = pwdToken;
//	}
}
