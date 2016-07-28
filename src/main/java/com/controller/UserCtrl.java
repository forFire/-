package com.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.User;
import com.service.UserService;
import com.util.ResponseUtil;

@Controller
@RequestMapping("/user")
public class UserCtrl {
	private static final Logger logger = LoggerFactory.getLogger(UserCtrl.class);

	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public  Map<String, Object> updatePassword(String newPassword, HttpServletRequest request){
		
		User user =(User)request.getSession().getAttribute("user");
		if(user != null){
		user.setPassword(newPassword);
		
		//保存到数据库中
		userService.update(user);
		return ResponseUtil.success("修改成功！");
		}else{
			return ResponseUtil.error("修改失败");
		}
	}
	
	

}
