package cn.sitedev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.swing.StringUIClientPropertyKey;

import cn.sitedev.entity.ProcDef;
import cn.sitedev.service.ProcDefService;

import com.xiaoleilu.hutool.util.StrUtil;

@Controller
@RequestMapping("/proc/")
public class ProcDefController extends CommController {

	@Autowired
	private ProcDefService procDefService;
	private static Logger lg = Logger.getLogger(ProcDefController.class);

	/**
	 * 查询最新的流程定义列表
	 * 
	 * @return
	 */
	@RequestMapping("queryLatestProcDef")
	@ResponseBody
	public Map<String, Object> queryProcDef(HttpSession session) {
		lg.info("查询最新的流程定义列表开始");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			boolean isLogin = this.isLogin(session);
			if (!isLogin) {
				return this.getInvalidSessionMap();

			}
			List<ProcDef> procDefList = this.procDefService
					.queryLatestProcDef();
			map = this.getReturnMap(true, procDefList, "查询最新的流程定义成功");
		} catch (Exception e) {
			map = this.getFailureMap("查询最新的流程定义失败，错误信息：" + e.getMessage());
		}
		lg.info("查询最新的流程定义列表结束");
		return map;
	}

	/**
	 * 根据key删除流程定义
	 * 
	 * @param key
	 * @return
	 */
	@RequestMapping("delProcDefByKey")
	@ResponseBody
	public Map<String, Object> delProcDefByKey(HttpSession session, String key) {
		Map<String, Object> map = new HashMap<>();
		if (StrUtil.isNotEmpty(key)) {
			try {
				boolean isLogin = this.isLogin(session);
				if (!isLogin) {
					return this.getInvalidSessionMap();
				}
				this.procDefService.delProcDefByKey(key);
				map = getSuccessMap("根据key删除流程定义成功");
			} catch (Exception e) {
				map = getFailureMap("根据key删除流程定义失败，错误信息：" + e.getMessage());
			}
		} else {
			map = getFailureMap("传入参数不能为空");
		}
		return map;

	}

	/**
	 * 部署流程定义
	 * 
	 * @return
	 */
	@RequestMapping("deployProcDef")
	@ResponseBody
	public Map<String, Object> deployProcDef(HttpSession session) {
		lg.info("部署流程定义开始");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			boolean isLogin = this.isLogin(session);
			if (!isLogin) {
				return this.getInvalidSessionMap();

			}
			this.procDefService.deployProcDef();
			map = this.getSuccessMap("部署流程定义成功");
		} catch (Exception e) {
			map = this.getSuccessMap("部署流程定义失败，错误信息：" + e.getMessage());
		}
		lg.info("部署流程定义结束");
		return map;

	}

	/**
	 * 根据zip文件部署流程定义
	 * 
	 * @param session
	 * @param request
	 * @param zipFile
	 * @return
	 */
	@RequestMapping("deployProcDefFromZipFile")
	@ResponseBody
	public Map<String, Object> deployProcDefFromZipFile(HttpSession session,
			HttpServletRequest request, String zipFile) {
		Map<String, Object> map = new HashMap<>();
		if (StrUtil.isNotEmpty(zipFile)) {
			try {
				boolean isLogin = this.isLogin(session);
				if (!isLogin) {
					return this.getInvalidSessionMap();
				}
				this.procDefService.deployProcDefFromZipFile(request, zipFile);
				map = this.getSuccessMap("根据zip文件部署流程定义成功");
			} catch (Exception e) {
				map = this.getSuccessMap("根据zip文件部署流程定义失败，错误信息："
						+ e.getMessage());
			}
		} else {
			map = getFailureMap("传入参数不能为空");
		}
		return map;
	}

}
