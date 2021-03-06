<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Add product</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div id="container">
        <jsp:include page="Header.jsp">
            <jsp:param name="title" value="Add Product"></jsp:param>
        </jsp:include>
<main>
    <c:if test="${fn:length(errors) gt 0}">
        <div class="alert-danger">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <div class="container">
        <form method="post" action="Controller?action=AddProduct" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="name">Product Name</label><input type="text" id="name" name="name"
             required value="<c:out value='${namePrev}'/>"> </p>
            <p><label for="price">Price</label><input type="number" id="price" name="price"
             required value="<c:out value='${pricePrev}'/>"> </p>
            <p><label for="description">Description</label><input type="text" id="description" name="description"
             required value="<c:out value='${descriptionPrev}'/>"> </p>
            <p><input type="submit" id="addProduct" value="Add Product"></p>

        </form>
    </div>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>
