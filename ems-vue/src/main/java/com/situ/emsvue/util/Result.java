package com.situ.emsvue.util;
// 请求是成功还是失败：ok error
// 后台希望前台弹出消息：msg
// 后台返回前台数据：Data
// JSON格式的通用响应对象，封装的就是后台返回给前台的所有信息
public class Result {
	public static final int ERROR = 1;
	public static final int OK = 0;
	public static final int NO_PERMISSTION = 2;

	// 当前状态（程序员判断状态）:成功、失败、未登录、没有权限
	// 当前登录是成功还是失败要告诉前台，前台才能知道弹出的提示框用errorMsg、okMsg
	private Integer code;
	// 描述信息（主要是给用户看的提示信息）
	private String msg;
	// 后台返回给前端的数据 Object， User、List<User>
	private Object data;

	// boolean: ok
	//private Boolean ok;

	public boolean isOk() {
		return code == OK;
	}

	//{"code":0,"msg":"删除成功","data":null,"address":"beijing","ok":true}
	/*public String getAddress() {
		return "beijing";
	}*/

	public Result() {
	}
	
	public Result(Integer code) {
		this.code = code;
	}

	public Result(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public Result(Integer code, Object data) {
		this.code = code;
		this.data = data;
	}

	public Result(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	// 告诉前台成功：code
	public static Result ok() {
		return new Result(OK);
	}
	
	// 告诉前台成功：code、msg
	public static Result ok(String msg) {
		return new Result(OK, msg);
	}
	
	// 告诉前台成功：code、data
	public static Result ok(Object data) {
		return new Result(OK, data);
	}
	
	// 告诉前台成功：code、msg、data
	public static Result ok(String msg, Object data) {
		return new Result(OK, msg, data);
	}
	

	// 告诉前台成功：code
	public static Result error() {
		return new Result(ERROR);
	}
	
	// 告诉前台成功：code、msg
	public static Result error(String msg) {
		return new Result(ERROR, msg);
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
