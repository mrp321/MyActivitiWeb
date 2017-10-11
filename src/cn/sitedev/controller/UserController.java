package cn.sitedev.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sitedev.entity.User;
import cn.sitedev.service.UserService;

import com.xiaoleilu.hutool.util.StrUtil;

@Controller
@RequestMapping("/user/")
public class UserController extends CommController {

	@Autowired
	private UserService userService;
	private static Logger lg = Logger.getLogger(UserController.class);

	@RequestMapping("login")
	@ResponseBody
	public Map<String, Object> login(HttpSession session, String loginName,
			String password) {
		lg.info("用户" + loginName + "登录开始");
		Map<String, Object> map = new HashMap<>();
		if (StrUtil.isNotEmpty(loginName) && StrUtil.isNotEmpty(password)) {
			try {
				User user = this.userService.login(loginName, password);
				if (user != null) {
					session.setAttribute("loginUser", user);
					map = this.getReturnMap(true, user, "登录成功");
				} else {
					map = this.getFailureMap("该用户不存在，请检查用户名和密码");
				}
			} catch (Exception e) {
				map = getFailureMap("登录失败，失败信息：" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			map = getFailureMap("传入参数不能为空");
		}
		lg.info("用户" + loginName + "登录结束");
		return map;
	}

	/**
	 * 退出
	 * 
	 * @param session
	 * @param loginName
	 * @return
	 */
	@RequestMapping("logout")
	@ResponseBody
	public Map<String, Object> logout(HttpSession session, String loginName) {
		lg.info("用户" + loginName + "退出开始");
		Map<String, Object> map = new HashMap<>();
		if (StrUtil.isNotEmpty(loginName)) {
			Object loginUser = session.getAttribute("loginUser");
			if (loginUser != null) {
				session.invalidate();
			}
			map = this.getSuccessMap("退出成功");
		} else {
			map = this.getFailureMap("传入参数不能为空");
		}
		lg.info("用户" + loginName + "退出结束");
		return map;
	}
}
