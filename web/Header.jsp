<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thole_000
  Date: 27/09/2018
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <h1>
        <span>Web shop<br>
        <c:choose>
            <c:when test='${not empty loggedIn}'>
                Logged in as: '${loggedIn.firstName}' <a href="Controller?action=LogOut">Log out</a>
            </c:when>
            <c:otherwise>
                <a href="Controller?action=LogInForm">Log In</a>
            </c:otherwise>
        </c:choose>
        </span>
    </h1>
    <nav>
        <ul>
            <li id="actual"><a href="Controller">Home</a></li>
            <li><a href="Controller?action=Overview">Users</a></li>
            <li><a href="Controller?action=OverviewProds">Products</a></li>
            <c:if test="${loggedIn.role == 'ADMIN'}">
                <li><a href="Controller?action=AddProductPage">Add Product</a></li>
            </c:if>
            <li><a href="Controller?action=SignUp">Sign up</a></li>
            <li><a href="Controller?action=GetShoppingCart">Cart (${cartSize})</a></li>
        </ul>
    </nav>
    <h2>${param.title}</h2>

</header>
