<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	window.onload = () => {
		if('${m.type}'=='구매자'){
			document.getElementById("op1").selected = true;
		} else {
			document.getElementById("op1").selected = true;
		}		
	}
</script>
</head>
<body>
<h3>내 정보</h3>
<form action="/member/edit" method="post">
<table border ="1">
<tr>
    <th>ID</th>
    <td>
    	<input type="text" name="id" value="${m.id}" readonly>
    </td>
    
</tr>
<tr>
    <th>NAME</th>
    <td><input type="text" name="name" value="${m.name}"></td>
</tr>
<tr>
    <th>EMAIL</th>
    <td><input type="text" name="email" value="${m.email}"></td>
</tr>
<tr>
    <th>TYPE</th>
    <td>
    	<select name="type">
    		<option>구매자</option>
    		<option>판매자</option>
    	</select>
    </td>
</tr>
<tr>
	<th>JOIN</th>
    <th colspan="2"><input type="submit" value="수정"></th>
</tr>
</table>
</form>
</body>
</html>