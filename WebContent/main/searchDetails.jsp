<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="searchDetails" class="grid-container">
	
	<span id="detailsIsbn">ISBN <c:out value="${sessionScope.book.getIsbn() }"/></span>
	<div class="grid-item">
		<span id="detailsAuthor"><c:out value="${sessionScope.book.getAuthor() }"/></span>
		<span id="detailsTitle"><c:out value="${sessionScope.book.getTitle().toUpperCase() }"/></span>
	</div>
	<div class="grid-item">
		<span id="detailsPageNum"><c:out value="${sessionScope.book.getPageNum() }"/> pages</span>
		<span id="detailsGenre"><c:out value="${sessionScope.book.getGenre() }"/></span>
	</div>
	<div class="grid-item">
		<span id="detailsSynopsis"><c:out value="${sessionScope.book.getSynopsis() }"/></span>
		<img src="">
	</div>
		<span id="loanButton">
			<button id="loanReturnButton" type="button" 
				onclick="window.location.href='book?action=loanStatus'" >
				<c:out value="${sessionScope.book.user.equals(sessionScope.user.getUserName())?'RETURN BOOK':'GET BOOK'}"/>
			</button>
	</span>	
</div>