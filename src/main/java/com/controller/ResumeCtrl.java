package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.model.Customers;
import com.model.Resume;
import com.service.ResumeService;
import com.util.ImportExcelUtil;
import com.util.Page;
import com.util.Response;
import com.util.ResponseHelper;

/**
 * 招聘信息
 */
@Controller
@RequestMapping(value = "/resume")
public class ResumeCtrl {

	@Autowired
	ResumeService resumeService;

	@ResponseBody
	@RequestMapping("findResumeList")
	public Map<String, Object> findRecruitList(Page page) {
		page = resumeService.findRecruitList(page);
		return ResponseHelper.createSuccessResponse(page);
	}

	/**
	 *增加 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Response<?> save(HttpServletRequest request, Resume resume) {
		resumeService.save(resume);
		return ResponseHelper.createSuccessResponse();
	}
	
	/**
	 *删除 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Response<?> delete(Resume resume){
		resumeService.delete(resume);
		return ResponseHelper.createSuccessResponse();
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value= "importExcelTest" ,method = RequestMethod.POST)
	public void importExcelTest(HttpServletRequest request,
			@RequestParam("upfile") MultipartFile[] upfile) {
		ImportExcelUtil im = new ImportExcelUtil();
		List<String[]> list = new ArrayList<String[]>();
		File file = null;
		if (upfile != null && upfile.length > 0) {
			String basePath = request.getSession().getServletContext().getRealPath("/");
			for (int i = 0; i < upfile.length; i++) {
				//将文件放入指定目录
				MultipartFile mfile = upfile[i];
				String originalFilename = mfile.getOriginalFilename();
				try {
//					mfile.transferTo(dirFile);
					System.out.println(originalFilename);
					list = im.exportListFromExcel(mfile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
//		List<Device> deviceList = new ArrayList<>();
//		Device device = new Device();
//		
		for (int i =0;i<list.size();i++) {
			
			String[] s = list.get(i);
			
			for(int j = 0;j < s.length;j++){
				
				//企业名称
//				device.setName(s[2]);
				
				
				
				
				
			}
			
			
		}
		
		
	}
	
	
	
}
