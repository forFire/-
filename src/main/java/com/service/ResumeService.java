package com.service;

import java.util.List;

import com.dao.baseDao.PageObject;
import com.model.Resume;
import com.util.Page;

public interface ResumeService {
	public Page findRecruitList(Page page);
	public void save( Resume resume);
	void delete(Resume resume);
	
	public void importExcelTest(List<String[]> list);
	
	
	
}
