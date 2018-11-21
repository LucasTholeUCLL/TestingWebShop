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
            <jsp:param name="title" value="Product Overview"></jsp:param>
        </jsp:include>
    <main>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="product" items="${productDB}">
                <tr>
                    <td><a href="Controller?action=UpdateProductForm&&id=${product.productId}">${product.name}</a></td>
                    <td><c:out value='${product.description}'/></td>
                    <td><c:out value='${product.price}'/></td>
                    <td><a href="Controller?action=DeleteProductForm&&id=${product.productId}"><img src="images/delete.png"></a></td>
                    <td><a href="Controller?action=AddToCart&&id=${product.productId}">Add to cart!</td>
                    <!--
                     <form method="post" action="Controller?action=AddToCart" novalidate="novalidate">
                        <input type="number" id="amount" name="amount"
                               required value="0">
                        <input hidden="hidden" type="text" id="id" name="id" value="${product.productId}" required>
                        <input type="submit" id="submit" value="Add to cart">
                        <a href="Controller?action=AddToCart&&id=${product.productId}">Add to cart!</a>
                    </form>
                    --->
                </tr>
            </c:forEach>

            <caption>Products Overview</caption>
        </table>
    </main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>