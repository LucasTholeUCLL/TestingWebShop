<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="container">
		<jsp:include page="Header.jsp">
			<jsp:param name="title" value="Home"></jsp:param>
		</jsp:include>
		<main>
			<c:if test="${not empty error}">
				<div class="alert-danger">
					<p>${error}</p>
				</div>
			</c:if>
            <c:if test="${not empty message}" >
                <div class="alert-success">
                        ${message}
                </div>
            </c:if>
		<p>
		Sed ut perspiciatis unde omnis iste natus error sit
		voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
		ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
		dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
		aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
		qui ratione voluptatem sequi nesciunt.
		</p>
		<form method="post" action="Controller?action=SetQuotePref" novalidate="novalidate">
			<p>Do you want to see a quote?</p>
			<input type="radio" name="choice" value="yes" <c:if test='${wantsQuote eq "yes"}'>checked</c:if>> Yes
			<input type="radio" name="choice" value="no" <c:if test='${wantsQuote eq "no"}'>checked</c:if>> No
			<p><input type="submit" id="Send" value="Send"></p>
		</form>
			<c:if test='${wantsQuote eq "yes"}'>Even a dead fish can go with the flow</c:if>

		</main>

		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>