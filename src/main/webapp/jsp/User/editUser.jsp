<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../layout.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body>
	 <div class="box-body bg-white">
	 <table align="center" style="margin-top: 100px">
	<form name="userForm" action="/yaodun/User/updateUser" method="post">
		<input type="hidden" name="id" value="${user.id }"> 
		<h4>编辑用户</h4>
		<tr><td>用户名：</td><td><input type="text" name="userName" value="${user.userName }"></td></tr>
		
		<tr><td>密码：</td><td><input type="text" name="password" value="${user.password }"></td></tr>
		
		<tr><td>Email：</td><td><input type="text" name="email" value="${user.email }"> </td></tr>
		
		<tr><td colspan="2"> <input class="btn btn-block btn-large btn-primary"	type="submit" value="确认"></td></tr>

	</form>
	</table>
</div>


</body>
</html>