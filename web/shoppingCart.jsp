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
        <c:if test="${not empty message}" >
            <div class="alert-success">
                <p>${message}</p>
            </div>
        </c:if>
        <c:if test="${not empty error}" >
            <div class="alert-danger">
                <p>${error}</p>
            </div>
        </c:if>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Amount</th>
                <th></th>
            </tr>
            <c:choose>
                <c:when test="${empty productDB}">
                    <p>There are no products in your shopping cart.</p>
            <caption>Products in shopping cart</caption>
        </table>
                </c:when>
                <c:otherwise>
                    <c:forEach var="product" items="${productDB}">
                        <tr>
                            <td><c:out value='${product.get(0).name}'/></td>
                            <td><c:out value='${product.get(0).description}'/></td>
                            <td><c:out value='${product.get(0).price}'/></td>
                            <td><c:out value='${product.get(1)}'/></td>
                            <td><a href="Controller?action=DeleteProductCart&&id=${product.get(0).productId}"><img src="images/delete.png" alt="remove from cart"></a></td>

                        </tr>
                    </c:forEach>
                    <caption>Products in shopping cart</caption>
                    </table>

                    <p class="subcontainer">Total Amount: ${total}€</p>

                    <div id="checkout">
                        <c:choose>
                            <c:when test='${not empty loggedIn}'>
                                <a href="Controller?action=OrderProducts" class="w3-button w3-green w3-round-large">Order Products</a>
                            </c:when>
                            <c:otherwise>
                                <p>You need to be logged in to buy the products in your cart!</p><br>
                                <a href="Controller?action=LogInForm" class="w3-button w3-green w3-round-large">Log In</a>
                                <a href="Controller?action=SignUp" class="w3-button w3-green w3-round-large">Sign up</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:otherwise>
            </c:choose>
    </main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>