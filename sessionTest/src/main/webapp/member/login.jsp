<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>로그인 폼</h3>
<form action="/member/login" method="post">
<table border ="1">
<tr>
    <th>ID</th>
    <td><input type="text" name="id"></td>
</tr>
<tr>
    <th>PWD</th>
    <td><input type="password" name="pwd"></td>
</tr>
<tr>
    <th colspan="2"><input type="submit" value="로그인"></th>
</tr>
</table>
</form>
</body>
</html>