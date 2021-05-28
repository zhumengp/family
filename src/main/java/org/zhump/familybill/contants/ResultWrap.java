package org.zhump.familybill.contants;

/**
 *@Title ResultWrap
 *@Description: 返回包装器
 *
 *@author zhump
 *@date 2021/4/10 20:58
 */
public class ResultWrap<T> extends Result<T> {

	public ResultWrap(Constants.Status status, T data) {
		super(status.getCode(),status.getMsg(),data);
	}

	

}
