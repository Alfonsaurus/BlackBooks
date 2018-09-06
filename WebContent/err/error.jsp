<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>


<title>Error</title>
</head>
<body>
	<%
		if (response.getStatus() == 500) {
	%>
	<label class="errorText">Error: <%=exception.getMessage()%></label>

	<%
		} else {
	%>
	<div  class="errorText">
	<span>Connection with database lost!</span>
	<span>Don't worry, here's something you can read :</span>
	<span class="errorDetails"> Exception is: <%=exception%></span>
	</div>
	<br>

	<%
		}
	%>
	<label class="errorText">Please go to <a href="/index.html">home page</a></label>
</body>
</html>