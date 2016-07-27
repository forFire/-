package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.ResumeDao;
import com.dao.baseDao.PageObject;
import com.model.Resume;
import com.service.ResumeService;
import com.util.Page;

@Repository
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	ResumeDao resumeDao;
	
	@Override
	public Page findRecruitList(Page page) {
		StringBuffer sb = new StringBuffer("from Resume where 1=1 ");
		return	resumeDao.findRecruitList(sb.toString(),page);
	}

	@Override
	public void importExcelTest(List<String[]> list) {
		
	}

	@Override
	public void save(Resume resume) {
		resumeDao.save(resume);
	}

	@Override
	public void delete(Resume resume) {
		resumeDao.del_(resume);
	}

}
