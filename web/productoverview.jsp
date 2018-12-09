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
        <c:if test="${not empty message}" >
            <div class="alert-success">
                ${message}
            </div>
        </c:if>
        <c:if test="${not empty error}" >
            <div class="alert-danger">
                <p>${error}</p>
            </div>
        </c:if>
        <input type="text" id="productFilterInput" onkeyup="filterProducts()" placeholder="Search for products..">
        <script type="text/javascript" src="js/filter.js"></script>
        <table id="products">
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="product" items="${productDB}">
                <tr>
                    <c:choose>
                        <c:when test="${loggedIn.role == 'ADMIN'}">
                            <td><a href="Controller?action=UpdateProductForm&&id=${product.productId}">${product.name}</a></td>
                        </c:when>
                        <c:otherwise>
                            <td><c:out value='${product.name}'/></td>
                        </c:otherwise>
                    </c:choose>
                    <td><c:out value='${product.description}'/></td>
                    <td><c:out value='${product.price} €'/></td>
                    <!---<td><a href="Controller?action=AddToCart&&id=${product.productId}">Add to cart!</td>--->

                     <td><form method="post" action="Controller?action=AddToCart" novalidate="novalidate">
                        <input type="number" id="amount" name="amount" min="1" max="10000" aria-required="true"
                               required value="1">
                        <input type="hidden" id="id" name="id" value="${product.productId}" required>
                        <input type="submit" id="submit" value="Add to cart">
                    </form></td>
                    <td>
                        <c:if test="${loggedIn.role == 'ADMIN'}">
                            <a href="Controller?action=DeleteProductForm&&id=${product.productId}"><img src="images/delete.png" alt="remove Product"></a>
                        </c:if>
                    </td>

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