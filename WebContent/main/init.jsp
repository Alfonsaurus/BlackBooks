<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <img id="initLogo" class="img-responsive" src="img/logo.svg" alt="Black Books Logo">

 <div id="initForm">
 	<button class="btn btn-primary btn-lg btn-responsive" data-toggle="modal" data-target="#login">LOGIN</button>
 	<button class="btn btn-primary btn-lg btn-responsive" data-toggle="modal" data-target="#register">REGISTER</button>
 </div>
 
 <% if(request.getSession().getAttribute("initMessage")!=null){%>
 	<span class="errorMessage"><c:out value="${sessionScope.initMessage }"/></span>
 <%} %>
 
 <!-- Login Modal -->
  <div class="modal fade" id="login">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <!-- Modal body -->
        <div class="modal-body">
         	<form id="formLogin" class="modalForm" action="user" method="get">
				<input class="form-control" type="text" name="userName" placeholder="User"/>
				<input class="form-control" type="password" name="userPass" placeholder="Password"/>
			</form>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
        	<button id="modalBtn" class="btn btn-primary btn-lg btn-responsive" form="formLogin" type="submit" name="action" value="login">LOGIN</button>
          	<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  
  <!-- Register Modal -->
  <div class="modal fade" id="register">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
         	<form id="formRegister" class="modalForm" action="user" method="get">
         		<input class="form-control" type="text" name="newFirstName" placeholder="First Name" pattern="[A-Za-z]{1,15}" required>
         		<input class="form-control" type="text" name="newLastName" placeholder="Last Name" pattern="[A-Za-z]{1,15}" required>
				<input class="form-control" type="text" name="newUserName" placeholder="User Name" required/>
				<i id="validator" class="glyphicon glyphicon-ok"></i>
				<input id="newPass" class="form-control" type="password" name="newPass" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" onkeyup='check();'
					title="Eight characters with at least one number, one lowercase and one uppercase" required/>
				<i id="validator" class="glyphicon glyphicon-ok"></i>
				<input id="newRepeatPass" class="form-control" type="password" name="newRepeatPass" placeholder="Repeat Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" onkeyup='check();'
					title="Eight characters with at least one number, one lowercase and one uppercase" required/>
				<input class="form-control" type="email" name="newEmail" placeholder="Email"/>
			</form>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
        	<button id="modalClearBtn" class="btn btn-secondary" type="reset" form="formRegister">CLEAR</button>
        	<button id="modalBtn" class="btn btn-primary btn-lg btn-responsive" form="formRegister" type="submit" name="action" value="register">REGISTER</button>
        </div>              
      </div>
    </div>
  </div>