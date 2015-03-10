<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<jsp:include page="../layout.jsp"></jsp:include>
</head>
<body>
 <div class="box-body bg-white">
		<table align="center" style="margin-top: 100px" >
			<form action="/yaodun/User/add" method="post">
			<h4>添加用户</h>
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="loginName" name="userName"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" id="password" name="password"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="text" id="email" name="email"></td>
				</tr>
				<tr>
					<td colspan="2"><input class="btn btn-block btn-large btn-primary" type="submit" value="提交"></td>
				</tr>
			</form>
		</table>
</div>
</body>
</html>