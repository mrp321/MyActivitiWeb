// 跳转至登陆
function jumpToLoginIfNoSession(flag) {
	if (flag != "undefined" && flag != "" && flag != null
			&& flag == "invalid session") {
		top.location.href = "../System_User/loginUI.html";
	}
}