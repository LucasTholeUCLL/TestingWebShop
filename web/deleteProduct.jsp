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
    <div class="container">
        <h3>Are you sure you want to delete this product?</h3>
        <form method="post" action="Controller?action=DeleteProduct" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="name">Product Name</label><input type="hidden" id="name" name="name"
             required value="${product.name}"> ${product.name}</p>
            <p><label for="price">Price</label><input type="hidden" id="price" name="price"
             required value="${product.price}"> ${product.price}</p>
            <p><label for="description">Description</label><input type="hidden" id="description" name="description"
             required value="${product.description}"> ${product.description}</p>
            <p><label for="productId">Product Id</label><input type="hidden" id="productId" name="productId"
             required value="${product.productId}"> ${product.productId}</p>
            <p><input type="submit" id="deleteProduct" value="Delete Product"><a href="Controller?action=OverviewProds">Cancel</a></p>

        </form>
    </div>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>
