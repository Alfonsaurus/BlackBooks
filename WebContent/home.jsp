<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" >

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans|Roboto">

<link rel="stylesheet" type="text/css" href="css/styles.css">

<title>BLACK BOOKS</title>
</head>

<body>
	<header>
		<div id="headerContainer">
			<%
				if (session.getAttribute("headerContainer") != null) {
					String headerContainer = (String) session.getAttribute("headerContainer");
			%>
			<jsp:include page="<%=headerContainer%>" />
			<%
				} else {
			%>
			<h1>Black Books</h1>
			<%
				}
			%>
		</div>
	</header>

	<section id="mainContainer">
		<%
			if (session.getAttribute("mainContainer") != null) {
				String mainContainer = (String) session.getAttribute("mainContainer");
		%>
		<jsp:include page="<%=mainContainer%>" />
		<%
			}
		%>
	</section>

	<footer id="footerContainer">
		<%
			if (session.getAttribute("footerContainer") != null) {
				String footerContainer = (String) session.getAttribute("footerContainer");
		%>
		<jsp:include page="<%=footerContainer%>" />
		<%
			}
		%>
	</footer>
</body>
</html>