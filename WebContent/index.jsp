<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<s:form action="login">
		<s:textfield key="user.userName" label="User Name" />
		<s:textfield key="user.password" label="Passowrd" />
		<s:submit />
		
	</s:form>

</body>
</html>