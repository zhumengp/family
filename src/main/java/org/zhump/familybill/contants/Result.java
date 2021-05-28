package org.zhump.familybill.contants;

/**
 *@Title Result
 *@Description: 返回实体
 *
 *@author zhump
 *@date 2021/4/10 20:58
 */
public class Result<T> {

    /**
     * 返回编码
     */
    private int code;
    /**
     * 返回消息提示吗
     */
    private String msg;

    /**
     * 返回具体数据
     */
    private T data;

    public Result(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
