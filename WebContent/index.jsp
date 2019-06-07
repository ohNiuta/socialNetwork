<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in here</title>
</head>
<body>
	<h1>Log In</h1>
	<s:form action="login">
		<s:textfield key="user.userName" label="User Name" />
		<s:password key="user.password" label="Password"/>
		<s:submit />
		
	</s:form>
	<form action="/SocialNetwork/register.jsp">
		<input type="submit" value="Register">
	</form>

</body>
</html>