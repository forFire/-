/**
 * Copyright(c) 2010-2015 by XiangShang360 Inc.
 * All Rights Reserved
 */
package com.excelUtil;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author chenrg
 * @date 2016年3月16日
 */
public class BaseController {

	@Resource(name = "messageSource")
	protected AbstractMessageSource messageSource;

	/**
	 * request
	 */
	private ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();

	/**
	 * response
	 */
	private ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request.set(request);
		this.response.set(response);
	}

	/**
	 * 获取request
	 * 
	 * @return
	 * @author chenrg
	 * @date 2016年3月16日
	 */
	protected final HttpServletRequest getRequest() {
		return request.get();
	}

	/**
	 * 获取response
	 * 
	 * @return HttpServletResponse
	 * @author chenrg
	 * @Date 2016年3月16日
	 */
	protected final HttpServletResponse getResponse() {
		return response.get();
	}

	/**
	 * 返回消息
	 * 
	 * @param entity
	 * @param code
	 * @return
	 * @author chenrg
	 * @date 2016年3月16日
	 */
//	protected final <T> ResponseEntity responseEntity(final T entity, final int code) {
//		String message = "";
//		try {
//			message = messageSource.getMessage(String.valueOf(code), null, Locale.SIMPLIFIED_CHINESE);
//		} catch (NoSuchMessageException e) {
//			message = "错误码：" + code;
//		}
//		return new ResponseEntity(code, message, entity);
//	}
//
//	/**
//	 * 返回消息
//	 * 
//	 * @param entity
//	 * @param code
//	 * @return
//	 * @author chenrg
//	 * @date 2016年3月16日
//	 */
//	protected final <T> ResponseEntity responseEntity(final T entity, final int code, String msg) {
//		String message = "";
//		try {
//			message = messageSource.getMessage(String.valueOf(code), null, Locale.SIMPLIFIED_CHINESE);
//			if (StringUtils.isNotEmpty(msg)) {
//				message = msg;
//			}
//		} catch (Exception e) {
//			message = "错误码：" + code;
//		}
//		return new ResponseEntity(code, message, entity);
//	}
//
//	/**
//	 * 处理请求参数异常
//	 * 
//	 * @param exception
//	 * @return
//	 * @author chenrg
//	 * @date 2016年3月15日
//	 */
//	@ExceptionHandler(InvalidParamException.class)
//	public @ResponseBody ResponseEntity handleException(InvalidParamException exception) {
//		if (exception.getExceptionMessage() != null) {
//			return new ResponseEntity(exception.getExceptionMessage().getCode(), String.valueOf(exception.getExceptionMessage().getMessage()));
//		}
//		return new ResponseEntity(ExceptionCode.INVALID_PARAM, exception.getMessage());
//	}
//
//	/**
//	 * 处理业务逻辑异常
//	 * 
//	 * @param exception
//	 * @return
//	 * @author chenrg
//	 * @date 2016年3月15日
//	 */
//	@ExceptionHandler(InvalidOperationException.class)
//	public @ResponseBody ResponseEntity handleException(InvalidOperationException exception) {
//		if (exception.getExceptionMessage() != null) {
//			return new ResponseEntity(exception.getExceptionMessage().getCode(), String.valueOf(exception.getExceptionMessage().getMessage()));
//		}
//		return new ResponseEntity(ExceptionCode.INVALID_OPERATION, exception.getMessage());
//	}
//
//	/**
//	 * 处理认证异常
//	 * 
//	 * @param exception
//	 * @return
//	 * @author chenrg
//	 * @date 2016年3月15日
//	 */
//	@ExceptionHandler(InvalidAuthException.class)
//	public @ResponseBody ResponseEntity handleException(InvalidAuthException exception) {
//		if (exception.getExceptionMessage() != null) {
//			return new ResponseEntity(exception.getExceptionMessage().getCode(), String.valueOf(exception.getExceptionMessage().getMessage()));
//		}
//		return new ResponseEntity(ExceptionCode.INVALID_AUTH, exception.getMessage());
//	}
//
//	/**
//	 * 处理远程服务调用异常
//	 * 
//	 * @param exception
//	 * @return
//	 * @author chenrg
//	 * @date 2016年3月15日
//	 */
//	@ExceptionHandler(RemoteServiceException.class)
//	public @ResponseBody ResponseEntity handleException(RemoteServiceException exception) {
//		if (exception.getExceptionMessage() != null) {
//			return new ResponseEntity(exception.getExceptionMessage().getCode(), String.valueOf(exception.getExceptionMessage().getMessage()));
//		}
//		return new ResponseEntity(ExceptionCode.REMOTE_SERVICE_UNAVAILABLE, exception.getMessage());
//	}

}