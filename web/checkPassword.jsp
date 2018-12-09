<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Check Password</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div id="container">
        <jsp:include page="Header.jsp">
            <jsp:param name="title" value="Check password"></jsp:param>
        </jsp:include>
<main>
    <div class="container">
        <h3>Fill out your password</h3>
        <form method="post" action="Controller?action=CheckPassword" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="userid"></label><input type="hidden" id="userid" name="userid"
                                                         required value="${person.userid}"> </p>
            <p><label for="password">Password:</label><input type="password" id="password" name="password"
                                                               required value=""> </p>
            <p><input type="submit" id="checkPassword" value="Check"><a href="Controller?action=Overview">Cancel</a></p>
        </form>
    </div>
    <p>${result}</p>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>
