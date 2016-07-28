package com.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
import com.service.UserService;
import com.util.ResponseUtil;

@Controller
@RequestMapping("/register")
public class RegisterCtrl {
	private static final Logger logger = LoggerFactory
			.getLogger(RegisterCtrl.class);

	@Autowired
	UserService userService;
	
//	@RequestMapping(value = "/registerJump", method = RequestMethod.POST)
//	public ModelAndView registerJump() {
//		logger.info("");
//		ModelAndView modelAndView = new ModelAndView("/register");
//		modelAndView.addObject("name", "xxx");
//		ModelAndView modelAndView = new ModelAndView();  
//		 modelAndView.setViewName("/register");  
//		return modelAndView;
//	}
	
	@ResponseBody
	@RequestMapping(value = "/registerJump", method = RequestMethod.POST)
	public  Map<String, Object> registerJump(){
		return ResponseUtil.success("跳转成功！");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public  Map<String, Object> register(User user, HttpServletRequest request){
//		System.out.println(user.getUserName()+"=password="+user.getPassword());
		//保存到数据库中
		userService.saveUser(user);
		HttpSession session = request.getSession(true);
		session.setAttribute("user", user);
		session.setAttribute("userId", user.getId());
		return ResponseUtil.success("注册成功！");
	}
	
	

}
