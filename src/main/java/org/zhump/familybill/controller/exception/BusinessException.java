package org.zhump.familybill.controller.exception;


/**
 *@Title BusinessException
 *@Description: 业务异常类
 *
 *@author zhump
 *@date 2021/4/10 20:49
 */
public class BusinessException extends Exception{


	/**
	 *业务异常
	 *
	 * @param mes
	 * @author zhump
	 * @return
	 * @date 2021/4/10 20:50
	 * @throws
	 */
	public BusinessException(String mes){
		super(mes);
	}
}
