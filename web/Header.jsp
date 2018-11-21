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
            <c:when test='${loggedIn eq "-1"}'>
                <a href="Controller?action=LogInForm">Log In</a>
            </c:when>
            <c:otherwise>
                Logged in as: '${loggedIn}' <a href="Controller?action=LogOut">Log out</a>
            </c:otherwise>
        </c:choose>
        </span>
    </h1>
    <nav>
        <ul>
            <li id="actual"><a href="Controller">Home</a></li>
            <li><a href="Controller?action=Overview">Users</a></li>
            <li><a href="Controller?action=OverviewProds">Products</a></li>
            <li><a href="Controller?action=AddProductPage">Add Product</a></li>
            <li><a href="Controller?action=SignUp">Sign up</a></li>
            <li><a href="Controller?action=GetShoppingCart">Cart</a></li>
        </ul>
    </nav>
    <h2>${param.title}</h2>

</header>
