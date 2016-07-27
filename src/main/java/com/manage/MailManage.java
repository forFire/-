package com.manage;

import javax.annotation.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class MailManage {

	@Resource
	private JavaMailSender javaMailSender;

	@Resource
	private MailConf mailConf;

	public void sendMail(String email, String text) throws Exception {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("fei.zhang@chinalbs.org");
		mail.setTo("sillyboyfei@qq.com");
		mail.setSubject("hi_job邮箱测试！！");
		mail.setText("邮箱测试！！！！！");
		try {
		    javaMailSender.send(mail);
            
        }
        catch (Exception e) {
        	e.printStackTrace();
//            log.info ("sola++++++++++++++++++++++++++",e);
        }
	}

	public void sendMailText(String userName, String email, String pwdToken) throws Exception {
		StringBuffer sb = new StringBuffer();
		String url = mailConf.getUrl();
		sb.append("请点击下面的链接:\r\n");
		sb.append(url + "?");
		sb.append("userName=" + userName + "&pwdToken=" + pwdToken);
		sb.append("\r\n如果上述链接不能点击,请手动复制到浏览器地址!");
		sendMail(email, sb.toString());
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}
}
