package com.util;

/**
 * RESTful 返回结果封装类
 */

public class Response<T> {

	private int ret;
	private String desc;
	private T data;

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [ret=" + ret + ", desc=" + desc
				+ ", data=" + data + "]";
	}

}
