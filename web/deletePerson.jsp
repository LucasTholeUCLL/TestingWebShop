<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <jsp:param name="title" value="Delete confirmation"></jsp:param>
        </jsp:include>
<main>
    <h3>Are you sure you want to delete this person?</h3>
    <form method="post" action="Controller?action=DeletePerson" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="userid">User id</label><input type="hidden" id="userid" name="userid"
                                                     required value="${person.userid}">${person.userid} </p>
        <p><label for="firstName">First Name</label><input type="hidden" id="firstName" name="firstName"
                                                           required value="${person.firstName}">${person.firstName} </p>
        <p><label for="lastName">Last Name</label><input type="hidden" id="lastName" name="lastName"
                                                         required value="${person.lastName}"> ${person.lastName}</p>
        <p><label for="email">Email</label><input type="hidden" id="email" name="email" required value="${person.email}">${person.email}</p>
        <p><input type="submit" id="deletePerson" value="Delete Person"><a href="Controller?action=Overview">Cancel</a></p>
        
    </form>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>
