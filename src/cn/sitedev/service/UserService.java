package cn.sitedev.service;

import cn.sitedev.entity.User;

public interface UserService {

	User login(String loginName, String password);

}
