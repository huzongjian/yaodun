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
    <div>
        <form action="login/upload" enctype="multipart/form-data" method="post">
            <input type="file" name ="filename">
            <input type="submit" value="submit">
        </form>
    </div>
</body>
<script type="text/javascript">
/* $(document).ready(function(){
	$("#add").click(function(){
		var username = $("#username").attr("value");
		var password = $("#password").attr("value");
		var jsonUser ={username:username, password:password};
		$.ajax({
			url:"login",
            type:"post",
            data:jsonUser,
            success:function(data){
            	alert(username+":"+data.username);
            	
            }
		});		
		
 	});
	
	
});
 */
   



  
</script>
</html>