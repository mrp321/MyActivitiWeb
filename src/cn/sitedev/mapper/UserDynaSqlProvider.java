package cn.sitedev.mapper;

import org.apache.ibatis.jdbc.SQL;

/**
 * 动态sql
 * 
 * @author qchen
 * 
 */
public class UserDynaSqlProvider {
	/**
	 * 登录动态sql
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public String login() {
		return new SQL() {
			{
				SELECT("name, loginName");
				FROM("user");
				WHERE("loginName = #{loginName}");
				AND();
				WHERE("password = #{password}");
			}

		}.toString();

	}
}
