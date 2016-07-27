package com.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

	public static Map<String, Object> success(String desc) {
		Map<String, Object> result = new HashMap<>(10);
		result.put("code", 1);
		result.put("desc", desc);
		return result;
	}

	public static Map<String, Object> error(String errormsg) {
		Map<String, Object> result = new HashMap<>(10);
		result.put("code", 0);
		result.put("desc", errormsg);
		return result;
	}
}
