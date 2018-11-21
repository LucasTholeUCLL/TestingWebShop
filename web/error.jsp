<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="Header.jsp">
        <jsp:param name="title" value="Error"></jsp:param>
    </jsp:include>
    <main>
    <article>
        <p>You caused a ${pageContext.exception} on the server!</p>
        <p>
            <a href="Controller">Home</a>
        </p>
    </article>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
