package cn.sitedev.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class CommController {

	/**
	 * 返回信息
	 * 
	 * @param success
	 *            是否成功
	 * @param data
	 *            返回数据
	 * @param msg
	 *            信息
	 * @return
	 */
	protected Map<String, Object> getReturnMap(boolean success, Object data,
			String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", success);
		if (data != null) {
			map.put("data", data);
		}
		map.put("msg", msg);
		return map;
	}

	/**
	 * 返回失败信息
	 * 
	 * @param msg
	 *            失败信息
	 * @return
	 */
	protected Map<String, Object> getFailureMap(String msg) {
		return this.getReturnMap(false, null, msg);
	}

	/**
	 * 返回成功信息
	 * 
	 * @param msg
	 *            失败信息
	 * @return
	 */
	protected Map<String, Object> getSuccessMap(String msg) {
		return this.getReturnMap(true, null, msg);
	}

	/**
	 * 返回session过期信息
	 * 
	 * @return
	 */
	protected Map<String, Object> getInvalidSessionMap() {
		Map<String, Object> map = new HashMap<>();
		map =  this.getFailureMap("未登录，或者登陆过期，请重新登陆");
		map.put("flag", "invalid session");
		return map;
	}

	/**
	 * 是否登陆
	 * 
	 * @param session
	 * @return
	 */
	protected boolean isLogin(HttpSession session) {
		Object loginUser = session.getAttribute("loginUser");
		if (loginUser != null) {
			return true;
		} else {
			return false;
		}
	}
}
