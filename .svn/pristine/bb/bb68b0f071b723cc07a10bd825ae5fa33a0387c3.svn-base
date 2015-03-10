<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../layout.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

function del(id){
	$.get("/yaodun/User/delUser?id=" + id,function(data){
		alert(data.result);
		if("success" == data.result){
			alert("删除成功!");
			window.location.reload(); 
		}else{
			alert("删除失败!");
		}
	});
}

</script>
</head>
<body>
	
	<table border="1"  align="center" style="margin-top: 100px">
	<tr><td>${result}</td></tr>
		<tbody>
			<tr>
				<th width="50px">姓名</th>
				<th width="50px">密码</th>
				<th width="50px">Email</th>
				<th width="50px">编辑</th>
			</tr>
			<c:if test="${!empty user }">
			<c:forEach items="${user }" var="u">
			<tr>
				<td>${u.userName }</td>
				<td>${u.email }</td>
				<td>${u.password }</td>
				<td>
					<a href="/yaodun/User/getUser?id=${u.id }">编辑</a>
					<a href="javascript:del('${u.id }')">删除</a>
				</td>
			</tr>
			</c:forEach>
			</c:if>
		</tbody>
	</table>
</body>
</html>