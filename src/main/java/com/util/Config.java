package com.util;

public class Config {

	public static String appid = "wx360cf184079f502c";
	public static String secret = "ed19726798e297390e44d6076bcf05d9";
	public static String tockenurl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=#{appid}&secret=#{secret}";
	public static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=#{access_token}&type=jsapi";

}
