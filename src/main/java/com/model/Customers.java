package com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "f_customers")
public class Customers extends IdEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//客户名称
	@Column(name = "f_name")
	private String name;
	
	@Column(name = "f_address")
	private String address;
	
	@Column(name = "f_phone")
	private String phone;
	
	@Column(name = "f_email")
	private String email;
	
	@Column(name = "f_qq")
	private String qq;
	
	@Column(name = "f_sex")
	private Integer sex;

	/**
	 * 备注
	 */
	@Column(name = "f_memo")
	private String memo;
	/**
	 * 状态：1启用，0禁用
	 */
	@Column(name = "f_status")
	private Integer status;
	/**
	 * 创建者
	 */
	@Column(name = "f_creator")
	private Integer creator;
	/**
	 * 创建时间
	 */
	@Column(name = "f_create_time")
	private Date createTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	
	
}
