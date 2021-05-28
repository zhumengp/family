package org.zhump.familybill.module;

import java.io.Serializable;
import java.util.List;

/**
 *@Title PageInfo
 *@Description: 分页工具类
 *
 *@author zhump
 *@date 2021/4/10 20:58
 */
public class PageInfo<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 本页数据
	 */
	private T data;
	/**
	 * 当前第几页
	 */
	private int currentPageNo;

	/**
	 * 总页数
	 */
	private long totalPageCount;

	/**
	 * 总记录数
	 */
	private long totalCount;
	/**
	 * 每页的记录数
	 */
	private int pageSize=20;
	public PageInfo(int pageSize, int currentPageNo,long totalCount,T data){
		this.data=data;
		this.pageSize=pageSize;
		this.currentPageNo=currentPageNo;
		this.totalCount=totalCount;
		if(this.totalCount%this.pageSize==0){
			this.totalPageCount=this.totalCount/this.pageSize;
		}else{
			this.totalPageCount=this.totalCount/this.pageSize+1;
		}
	}
	public PageInfo(int currentPageNo,int totalCount,T data){
		this.data=data;
		this.currentPageNo=currentPageNo;
		this.totalCount=totalCount;
		if(this.totalCount%this.pageSize==0){
			this.totalPageCount=this.totalCount/this.pageSize;
		}else{
			this.totalPageCount=this.totalCount/this.pageSize+1;
		}
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public long getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(long totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
