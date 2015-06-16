<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
	<form action="servlet/addTask" method="post">
		please input uri : <input name="uriName"> <input type="submit"
			value="Add"></input>
	</form>
	<form action="servlet/currentTask">
		<input type="submit" value="Check downloading link">
	</form>
</body>
</html>