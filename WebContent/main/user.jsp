<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">
	<div class="row">
		<div id="userArea" class="col-sm-3 col-md-6 col-lg-4">
			<p>first name</p>
			<span><c:out value="${sessionScope.user.getFirstName()}" /></span>
			<p>last name</p>
			<span><c:out value="${sessionScope.user.getLastName()}" /></span>
			<p>user name</p>
			<span><c:out value="${sessionScope.user.getUserName()}" /></span>
			<p>email</p>
			<span><c:out value="${sessionScope.user.getEmail()}" /></span>

			<button id="deletionBtn" class="btn btn-primary"
				onclick="window.location.href='user?action=deleteUser'">REQUEST
				ACCOUNT DELETION</button>
			<br id="bottom">
		</div>
		<div id="bookArea" class="col-sm-9 col-md-6 col-lg-8">
			<nav class="bookNav">
				<a href="book?action=loanList">LOANS</a> <a href="book?action=lastSearch">SEARCH</a>
			</nav>
			<div id="resultArea">
				<%
					if (session.getAttribute("resultContainer") != null) {
						String resultContainer = (String) session.getAttribute("resultContainer");
				%>
				<jsp:include page="<%=resultContainer%>" />
				<%
					}
				%>
			</div>
		</div>
	</div>
</div>
