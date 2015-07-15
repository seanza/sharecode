<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="bathPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<div>
		<form action="${bathPath}/StudentServlet" method="post">
			<div>
				<label>用户名：</label> <label><input type="text"
					name="userName" /></label><br /> <label>密&nbsp;&nbsp;碼：</label> <label><input
					type="password" name="userPwd" /></label>
			</div>
			<div>
				<label><input type="submit" value="登錄" /></label> <label><input
					type="button" value="註冊"
					onclick="window.location='${bathPath}/register'" /></label>
			</div>
		</form>
	</div>
</body>
</html>
