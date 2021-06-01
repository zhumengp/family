package org.zhump.familybill.contants;

import lombok.Data;

/**
 *@Title ResultWrap
 *@Description: 返回包装器
 *
 *@author zhump
 *@date 2021/4/10 20:58
 */
@Data
public class ResultWrap<T>{

	/**
	 * 编码
	 */
	private Integer code;

	/**
	 * 返回提示语
	 */
	private String msg;

	/**
	 * 具体数据
	 */
	private T data;

	/**
	 * 无参构造
	 */
	public ResultWrap(){

	}


	public ResultWrap(Integer code,String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResultWrap(Integer code,String msg,T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResultWrap(Constants.Status status) {
		this(status.getCode(),status.getMsg());
	}

	public ResultWrap(Constants.Status status,T data){
		this(status.getCode(),status.getMsg(),data);
	}

	/**
	 * 自定义返回提示语
	 * @param status
	 * @param msg
	 */
	public ResultWrap(Constants.Status status,String msg){
		this(status.getCode(),msg);
	}
}
