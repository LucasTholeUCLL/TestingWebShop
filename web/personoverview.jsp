<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div id="container">
        <jsp:include page="Header.jsp">
            <jsp:param name="title" value="User Overview"></jsp:param>
        </jsp:include>
    <main>
        <form method="post" action="Controller?action=SortPersons" novalidate="novalidate">
            <select name="choice">
                <option value="fname" <c:if test='${chosenSort eq "fname"}'>selected</c:if>>First Name</option>
                <option value="lname" <c:if test='${chosenSort eq "lname"}'>selected</c:if>>Last Name</option>
                <option value="email" <c:if test='${chosenSort eq "email"}'>selected</c:if>>Email</option>
            </select>
            <p><input type="submit" id="Submit" value="Submit"></p>
        </form>
        <table>
            <tr>
                <th>E-mail</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Delete</th>
                <th>Check password</th>
            </tr>
            <c:forEach var="person" items="${personDB}">
                <tr>
                    <td><c:out value='${person.email}'/></td>
                    <td><c:out value='${person.firstName}'/></td>
                    <td><c:out value='${person.lastName}'/></td>
                    <td><a href="Controller?action=DeletePersonForm&&id=${person.userid}"><img src="images/delete.png"></a></td>
                    <td><a href="Controller?action=CheckPasswordForm&&id=${person.userid}">Check</a></td>
                </tr>
            </c:forEach>

            <caption>Users Overview</caption>
        </table>
    </main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>