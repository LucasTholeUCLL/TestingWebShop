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
            <jsp:param name="title" value="Cart Overview"></jsp:param>
        </jsp:include>
    <main>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th></th>
            </tr>
            <c:choose>
                <c:when test="${empty productDB}">
                    <p>There are no products in your shopping cart.</p>
                </c:when>
                <c:otherwise>
                    <c:forEach var="product" items="${productDB}">
                        <tr>
                            <td><a href="Controller?action=UpdateProductForm&&id=${product.productId}">${product.name}</a></td>
                            <td><c:out value='${product.description}'/></td>
                            <td><c:out value='${product.price}'/></td>
                            <!--<td><a href="Controller?action=AddToCart&&id=${product.productId}">Add to cart!</td>--->
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <caption>Products in shopping cart</caption>
        </table>
    </main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>