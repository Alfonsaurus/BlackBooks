<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="footerSearchContainer" action="book" method="get">
	<div id="footerSearchButtons"
		class="btn-group btn-group-toggle btn-group-justified"
		data-toggle="buttons">
		<label class="btn btn-primary"> <input type="radio" form="footerSearchContainer"
			name="searchType" value="isbn" autocomplete="off" required checked>ISBN
		</label> <label class="btn btn-primary"> <input type="radio" form="footerSearchContainer"
			name="searchType" value="author" autocomplete="off" required>AUTHOR
		</label> <label class="btn btn-primary"> <input type="radio" form="footerSearchContainer"
			name="searchType" value="title" autocomplete="off" required>TITLE
		</label>
	</div>
	<div class="input-group">
		<input class="form-control" type="text" placeholder="Search"
			name="searchParam" required>
		<div class="input-group-append">
			<button class="btn btn-primary" type="submit" form="footerSearchContainer"
				name="action" value="search">SEARCH</button>
		</div>
	</div>
</form>
