package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 招聘信息
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "f_resume")
public class Resume extends IdEntity implements Serializable {

	// 招聘题目
	@Column(name = "f_resumeName")
	private String resumeName;
	// 类别
	@Column(name = "f_type")
	private String type;
	// 发布人
	@Column(name = "f_publisher")
	private String publisher;
	// 发布时间
	@Column(name = "f_publisher_time")
	private String publisherTime;
	// 发布状态
	@Column(name = "f_publisher_status")
	private String publisherStatus;

	
	public Resume() {

	}


	public String getResumeName() {
		return resumeName;
	}


	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}


	public String getPublisherTime() {
		return publisherTime;
	}


	public void setPublisherTime(String publisherTime) {
		this.publisherTime = publisherTime;
	}


	public String getPublisherStatus() {
		return publisherStatus;
	}


	public void setPublisherStatus(String publisherStatus) {
		this.publisherStatus = publisherStatus;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

}
