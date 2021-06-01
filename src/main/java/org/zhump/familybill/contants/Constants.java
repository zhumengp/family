package org.zhump.familybill.contants;

/**
 *@Title Constants
 *@Description: TODO 描述一下这个类是干嘛的
 *
 *@author zhump
 *@date 2021/4/10 20:30
 */
public class Constants {
	
	
	
	public static enum Status{
		/**
		 * 返回成功
		 */
		SUCCESS(200,"操作成功"),
		/**
		 * 操作失败
		 */
		FAIL(400,"操作失败"),
		/**
		 * 网络，系统异常
		 */
		Error(500,"系统异常"),
		/**
		 * 用户名重复
		 */
		ACCOUNT_NAME(100001,"用户名重复")
		;

		/**
		 * 返回编码
		 */
		private Integer code;

		/**
		 * 返回消息
		 */
		private String msg;

		private Status(Integer code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

}
