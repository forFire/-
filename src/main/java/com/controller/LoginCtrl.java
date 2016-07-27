package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.User;
import com.service.UserService;
import com.util.Response;
import com.util.ResponseHelper;
import com.util.ReturnDesc;
/**
 *登录 
 */
@Controller
@RequestMapping(value = "/login")
public class LoginCtrl {
	private static final Logger logger = LoggerFactory.getLogger(LoginCtrl.class);
	
	@Autowired
	private UserService userService;
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	@Autowired
//	public CompositeSessionAuthenticationStrategy sessionStrategy;
	
	@ResponseBody
//	@RequestMapping("/login")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Response<User> login(String username, String password,HttpServletResponse response, HttpServletRequest request) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return ResponseHelper.createBusinessErrorResponse(ReturnDesc.EMPTY_USERNAME_PASSWORD);
		}
		User user = userService.findByUserName(username);
		
		if(user ==null){
			return ResponseHelper.createBusinessErrorResponse(ReturnDesc.USER_NOT_EXISTS);
		}
		if(!password.equals(user.getPassword())){
			return ResponseHelper.createBusinessErrorResponse(ReturnDesc.USER_PASSWORD_ERROR);
		}
		
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		//session
//		sessionStrategy.onAuthentication(authentication, request, response);
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		securityContext.setAuthentication(authentication);
		
		HttpSession session = request.getSession(true);
//		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		session.setAttribute("user", user);
		session.setAttribute("userId", user.getId());
		
		
		return ResponseHelper.createSuccessResponse(user);
	}

	/** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }  

	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			request.getSession().invalidate();
			response.sendRedirect(basePath+"logintop.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
