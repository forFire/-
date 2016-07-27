package com.util;

import java.util.HashMap;
import java.util.Map;


public class ResponseHelper {

    public static <T> Response<T> createSuccessResponse() {
        return createResponse(ReturnCode.SUCCESS, null);
    }

    public static <T> Response<T> createSuccessResponse(T data) {
        Response<T> response = createResponse(ReturnCode.SUCCESS, null);
        response.setData(data);
        return response;
    }
    public static <T> Map<String,Object> createSuccessResponse(Page<T> page) {
        Map<String,Object> rs = new HashMap<String,Object>();
        rs.put("total", page.getTotal());
		rs.put("rows", page.getResult());
        return rs;
    }
    public static <T> Response<T> createResponse() {
        return createResponse(ReturnCode.NEVER_USED_CODE, null);
    }

    public static <T> Response<T> createResponse(int ret, String desc) {
        Response<T> response = new Response<T>();
        if (ret != ReturnCode.NEVER_USED_CODE) response.setRet(ret);
        if (desc != null) response.setDesc(desc);
        return response;
    }
    
    public static <T> Response<T> createBusinessErrorResponse(String description) {
        return createResponse(ReturnCode.BUSINESS_ERROR, description);
    }
    
    public static <T> Response<T> createExceptionResponse(Exception e) {
        return createResponse(ReturnCode.EXCEPTION, "系统异常，请联系管理人员处理！");
    }

}
