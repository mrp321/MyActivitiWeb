package cn.sitedev.service.impl;

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sitedev.dao.UserDao;
import cn.sitedev.entity.User;
import cn.sitedev.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RepositoryService repositoryService;

	@Override
	public User login(String loginName, String password) {
		return this.userDao.login(loginName, password);
	}

}
