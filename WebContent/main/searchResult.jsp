<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% if (request.getSession().getAttribute("resultBookList")!=null){ %>
	<div id="searchResultContainer">
		<span class="resultTitle"><c:out value="${sessionScope.resultBookList.size() }"/> BOOKS FOUND :</span>
		<c:forEach var="book" items="${sessionScope.resultBookList}">
			<button type=button class="bookFound btn" onclick="window.location.href='book?action=details&value=${book.getIsbn()}'">
				<c:out value="${book}"/>
			</button>
		</c:forEach>
	</div>
<%} else {%>
	<span class="nothingFoundText">Sorry, nothing found :(</span>
<%}%>