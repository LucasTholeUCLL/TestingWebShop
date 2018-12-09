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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <div id="container">
        <jsp:include page="Header.jsp">
            <jsp:param name="title" value="Order Complete"></jsp:param>
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
        <div id="myProgress">
            <div id="myBar"></div>
        </div>
            <h2>Order Completed!</h2>
            <div class="row">
                <div class="col-50">
                    <div class="subcontainer">
                        <table>
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Amount</th>
                            </tr>
                            <c:forEach var="product" items="${productDB}">
                                <tr>
                                    <td><c:out value='${product.get(0).name}'/></td>
                                    <td><c:out value='${product.get(0).description}'/></td>
                                    <td><c:out value='${product.get(0).price}'/></td>
                                    <td><c:out value='${product.get(1)}'/></td>
                                </tr>
                            </c:forEach>
                        </table>

                        <p class="subcontainer">Total Amount: ${total}€</p>

                    </div><br>

                    <label><i class="fa fa-address-card-o"></i> Address: </label>
                    <p class="adrRead filledField">${adr}</p><br>
                        <label><i class="fa fa-institution"></i> City: </label>
                    <p class="cityRead filledField">${city}</p><br>

                    <div class="row">
                        <div class="col-50">
                            <label>Province: </label>
                            <p class="stateRead filledField">${state}</p>
                        </div>
                        <div class="col-50">
                            <label>Postcode: </label>
                            <p class="zipRead filledField">${zip}</p>
                        </div>
                    </div>

                    <a href="Controller" class="w3-button w3-green w3-round-large">Home</a>
                </div>
            </div>
    </div>
    <script type="text/javascript" src="js/orderComplete.js"></script>

</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>
