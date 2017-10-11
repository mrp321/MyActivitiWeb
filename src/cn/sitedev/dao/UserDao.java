package cn.sitedev.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import cn.sitedev.entity.User;
import cn.sitedev.mapper.UserDynaSqlProvider;

@Repository
public interface UserDao {

	@SelectProvider(type = UserDynaSqlProvider.class, method = "login")
	// @Select("SELECT loginName, name FROM user WHERE loginName = #{loginName} AND password = #{password}")
	User login(@Param("loginName") String loginName,
			@Param("password") String password);

}
