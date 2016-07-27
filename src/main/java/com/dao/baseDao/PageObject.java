package com.dao.baseDao;
import java.awt.event.ActionEvent;
import java.util.List;

public class PageObject   {
	/**
     * 当前页
     */
    private int pageCurrent = 1;

    /**
     * 每页记录数
     */
    private int pageSize = 10;

    /**
     * 总的页数
     */
    private int pageCount;

    /**
     * 总的记录数
     */
    private int rowCount;
    /**
     * 查询后的结果集
     */
    private List resultList;
    
    private int firstResult;

    private String disabledStyle = "color:ACA899;cursor:default;";

    public PageObject() {
    }


    public int getPageCount() {
		return pageCount;
	}

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageCurrent() {
		return pageCurrent;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getRowCount() {
		return rowCount;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	
	public int getFirstResult() {
		return firstResult;
	}

	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void init(int rowCount){
		/*设置总的记录数*/
		this.rowCount = rowCount;
		
		/*分页查询后的总页数*/
		if(this.rowCount % this.pageSize == 0){
			this.pageCount = this.rowCount/this.pageSize ;
		}else{
			this.pageCount = this.rowCount/this.pageSize + 1 ;
		}
		
		if(this.pageCurrent == 0 && this.pageCount > 0){
			this.pageCurrent = 1;
		}
		
		/*开始查询点*/
		firstResult = 0; 
		if(this.pageCurrent <= this.pageCount){
			firstResult = (this.pageCurrent-1)*this.pageSize; //得到查询从第几条记录开始查
		}else{
			firstResult = (this.pageCount-1)*this.pageSize;
		}
		
		//设置当前页 
		if(this.pageCurrent > this.pageCount){
			this.pageCurrent = this.pageCount;
		}
		
	}
	
	//第一页
	public void firstListener(ActionEvent event){
		firstResult = 0;
		pageCurrent = 1;
	}
	//最后一页
	public void lastListener(ActionEvent event){
		firstResult = (pageCount-1)*pageSize;
		pageCurrent = pageCount;
	}
	//下一页
	public void nextListener(ActionEvent event){
		if(pageCurrent < pageCount){
			pageCurrent += 1;
		}
		firstResult = (pageCurrent-1)*pageSize;
	}
	//上一页
	public void priorListener(ActionEvent event){
		if(pageCurrent > 1){
			pageCurrent -= 1;
		}
		firstResult = (pageCurrent-1)*pageSize;
	}
	//跳转某页
	public void gotoListener(ActionEvent event){
		if(pageCurrent > 0 && pageCurrent <= pageCount){
			firstResult = (pageCurrent-1)*pageSize;
		}
	}

    public String getFirstStyle(){
        return (pageCurrent == 1 || pageCurrent == 0) ? disabledStyle :"";
    }
    public String getPriorStyle(){
        return (pageCurrent == 1 || pageCurrent == 0) ? disabledStyle :"";
    }
    public String getNextStyle(){
        return (pageCount == 1 || pageCurrent == pageCount || pageCurrent == 0) ? disabledStyle : "";
    }
    public String getLastStyle(){
        return (pageCount == 1 || pageCurrent == pageCount || pageCurrent == 0) ? disabledStyle : "";
    }
}

