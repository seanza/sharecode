<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="bathPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<div>
		<form action="${bathPath}/RigesterServlet" method="post">
			<div>
				<label for="username" class="label">用&nbsp;户&nbsp;名:</label> <input
					type="text" name="userName" /> <label id="chage1"></label><br /> <label
					for="username">*正确填写用户名,6-12位之间请用英文小写、下划线、数字。</label><br /> <label
					for="pwd1">用户密码:</label> <input type="password" name="userPwd" /><br />
				<label for="pwd1">*正确填写密码,6-12位之间请用英文小写、下划线、数字。</label><br /> <label
					for="pwd2">确认密码:</label> <input type="password" name="pwd2" /><br />
				<label for="pwd2">*两次密码要一致,请用英文小写、下划线、数字。</label><br />
			</div>
			<div>
				<label><input type="submit" value="註冊" /></label> <label><input
					type="reset" value="取消" /></label>
			</div>
		</form>
	</div>
</body>
</html>
