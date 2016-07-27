package com.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.baseDao.GenericDao;
import com.dao.baseDao.PageObject;
import com.model.Resume;
import com.util.Page;

@Repository
public class ResumeDao extends GenericDao<Resume, Serializable>{

	public Page findRecruitList(String hql,Page page){
		List<Resume> list = new ArrayList<Resume>();
		return  queryResultByPage(hql,page);
	}
	

}
