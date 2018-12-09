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
            <jsp:param name="title" value="Log in"></jsp:param>
        </jsp:include>
<main>
    <c:if test='${not empty errors}'>
        <div class="alert-danger">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <div class="container">
        <h3>Please enter your userid and password</h3>
        <form method="post" action="Controller?action=LogIn" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <label for="userid">Userid:</label>
            <input id="userid" name="userid" required value=""> </p>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required value=""> </p>
            <input type="submit" id="logIn" value="Log In"><a href="Controller">Cancel</a></p>

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
