package cn.sitedev.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.util.BuddhistCalendar;

import cn.sitedev.entity.ProcDef;
import cn.sitedev.service.ProcDefService;

@Service
@Transactional
public class ProcDefServiceImpl implements ProcDefService {
	@Autowired
	private RepositoryService repositoryService;

	/**
	 * 查询最新的流程定义列表
	 */
	@Override
	public List<ProcDef> queryLatestProcDef() {
		ProcessDefinitionQuery query = this.repositoryService
				.createProcessDefinitionQuery();
		// 按照版本升序排列
		query.orderByProcessDefinitionVersion().asc();
		List<ProcessDefinition> list = query.list();
		ProcDef procDef = null;
		Map<String, ProcDef> map = new HashMap<String, ProcDef>();
		for (ProcessDefinition processDefinition : list) {
			procDef = new ProcDef(processDefinition.getId(),
					processDefinition.getName(), processDefinition.getKey(),
					processDefinition.getVersion());
			map.put(processDefinition.getKey(), procDef);
		}
		return new ArrayList<ProcDef>(map.values());
	}

	/**
	 * 部署流程定义
	 */
	@Override
	public void deployProcDef() {
		DeploymentBuilder builder = this.repositoryService.createDeployment();
		builder.addClasspathResource("cn/sitedev/bpmn/test.bpmn");
		builder.addClasspathResource("cn/sitedev/bpmn/test.png");
		Deployment deployment = builder.deploy();
		System.out.println(deployment);
	}

	/**
	 * 根据key删除流程定义
	 */
	@Override
	public void delProcDefByKey(String key) {
		// 根据key查询流程定义
		ProcessDefinitionQuery query = this.repositoryService
				.createProcessDefinitionQuery();
		query.processDefinitionKey(key);
		List<ProcessDefinition> procDefList = query.list();
		// 遍历，级联删除
		for (ProcessDefinition processDefinition : procDefList) {
			this.repositoryService.deleteDeployment(
					processDefinition.getDeploymentId(), true);
		}
	}

	/**
	 * 根据zip文件部署流程定义
	 * 
	 * 
	 * @param request
	 * @param zipFile
	 * @return
	 */
	@Override
	public void deployProcDefFromZipFile(HttpServletRequest request,
			String zipFile) {
		DeploymentBuilder builder = this.repositoryService.createDeployment();
		ZipInputStream zipInputStream = new ZipInputStream(this.getClass()
				.getClassLoader().getResourceAsStream(zipFile));
		builder.addZipInputStream(zipInputStream);
		Deployment deployment = builder.deploy();
		System.out.println(deployment);

	}
}
