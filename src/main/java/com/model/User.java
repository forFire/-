package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="f_user")
public class User extends IdEntity{
	
	private static final long serialVersionUID = 813149513582487086L;

	@Column(name = "f_user_name")
	private String userName;
	@Column(name = "f_password")
	private String password;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	
}
