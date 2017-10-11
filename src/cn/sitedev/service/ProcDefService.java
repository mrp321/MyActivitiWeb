package cn.sitedev.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.sitedev.entity.ProcDef;

public interface ProcDefService {

	/**
	 * 查询最新的流程定义
	 * 
	 * @return
	 */
	List<ProcDef> queryLatestProcDef();

	/**
	 * 部署流程定义
	 */
	void deployProcDef();

	/**
	 * 根据key删除流程定义
	 * 
	 * @param key
	 * @return
	 */
	void delProcDefByKey(String key);

	/**
	 * 根据zip文件部署流程定义
	 * 
	 * @param request
	 * @param zipFile
	 * @return
	 */
	void deployProcDefFromZipFile(HttpServletRequest request, String zipFile);

}
