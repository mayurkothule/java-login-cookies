<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${cookie.loginerror.value }

<form action="http://localhost:8080/myCookies/login" method="post">
Enter id:<input type="text" name="uid"/><br/>
Password:<input type="password" name="pwd"/><br/>
<input type="submit" value="LOGIN"/> 
</form>
</body>
</html>