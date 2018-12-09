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
        <jsp:param name="title" value="Order"></jsp:param>
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
            <form id="orderForm" method="post" action="Controller?action=ConfirmOrder">
                <div id="myProgress">
                    <div id="myBar"></div>
                </div>

                <div class="tab">
                    <h2>Personal Information</h2>
                    <div class="row">
                        <div class="col-50">
                            <label for="name"><i class="fa fa-user"></i> Full Name</label>
                            <input type="text" id="name" name="name" value="${loggedIn.firstName} ${loggedIn.lastName}" oninput="this.className = ''"><br>
                            <label for="email"><i class="fa fa-envelope"></i> Email</label>
                            <input type="text" id="email" name="email" value="${loggedIn.email}" oninput="this.className = ''"><br>
                            <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
                            <input type="text" id="adr" name="adr" placeholder="Stationstraat 123" oninput="this.className = ''"><br>
                            <label for="city"><i class="fa fa-institution"></i> City</label>
                            <input type="text" id="city" name="city" placeholder="Leuven" oninput="this.className = ''"><br>

                            <div class="row">
                                <div class="col-50">
                                    <label for="state">Province</label>
                                    <input type="text" id="state" name="state" placeholder="Vlaams-Brabant" oninput="this.className = ''">
                                </div>
                                <div class="col-50">
                                    <label for="zip">Postcode</label>
                                    <input type="text" id="zip" name="zip" placeholder="3000" class="numberInput" oninput="this.className = 'numberInput'">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="tab">
                    <h2>Confirm Order</h2>
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
                                <p class="subsubcontainer">Total Amount: ${total}€</p>

                            </div><br>

                            <label><i class="fa fa-address-card-o"></i> Address: </label><p id="adrRead" class="filledField"></p><br>
                            <label><i class="fa fa-institution"></i> City: </label><p id="cityRead" class="filledField"></p><br>

                            <div class="row">
                                <div class="col-50">
                                    <label>Province: </label><p id="stateRead" class="filledField"></p>
                                </div>
                                <div class="col-50">
                                    <label>Postcode: </label><p id="zipRead" class="filledField"></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="tab">
                    <h2>Payment details</h2>
                    <div class="row">
                        <div class="col-50">
                            <div class="icon-container">
                                Accepted Cards
                                <i class="fa fa-cc-visa" style="color:navy;"></i>
                                <i class="fa fa-cc-amex" style="color:blue;"></i>
                                <i class="fa fa-cc-mastercard" style="color:red;"></i>
                                <i class="fa fa-cc-discover" style="color:orange;"></i>
                            </div>

                            <label for="cname">Name on Card</label>
                            <input type="text" id="cname" name="cardname" placeholder="Firstname Lastname" oninput="this.className = ''"><br>
                            <label for="ccnum">Credit card number</label>
                            <input type="text" id="ccnum" class="numberInput" oninput="this.className = 'numberInput'" name="cardnumber" placeholder="1111-2222-3333-4444"><br>
                            <label for="expmonth">Exp Month</label>
                            <input type="text" id="expmonth" name="expmonth" placeholder="September" oninput="this.className = ''"><br>
                            <label for="expyear">Exp Year</label>
                            <input type="text" id="expyear" name="expyear" placeholder="2018" class="numberInput" oninput="this.className = 'numberInput'"><br>
                            <label for="cvv">CVV</label>
                            <input type="text" id="cvv" name="cvv" placeholder="352" class="numberInput" oninput="this.className = 'numberInput'"><br>
                        </div>
                    </div>
                </div>

                <div style="overflow:auto;">
                    <p id="orderFormWarning"></p>
                    <div class="buttonsForm">
                        <button type="button" id="prevBtn" class="btn" onclick="nextPrev(-1)">Previous</button>
                        <button type="button" id="nextBtn" class="btn" onclick="nextPrev(1)">Next</button>
                    </div>
                </div>
            </form>
            <script type="text/javascript" src="js/form.js"></script>
        </div>

    </main>
<footer>
    &copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>
