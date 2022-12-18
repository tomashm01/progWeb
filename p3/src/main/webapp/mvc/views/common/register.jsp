<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
</head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
  <body>
<!-- ACL -->
<%String aclNew = application.getInitParameter("aclNew"); %>
<jsp:include page="<%=aclNew%>"></jsp:include>
<!-- ACL -->
    
    <%
	if(request.getParameter("ErrorRegister") != null) {
    %>
		<p class="cajaRoja"> Error en la validacion del formulario, email ya registrado </p>
	<%
	}
    %>
    <div class="login-box">
	  <h2>Register</h2>
	   <form id="formulario"  method="post" action= "${pageContext.request.contextPath}<%=application.getInitParameter("registerController")%>">
	       <div class="user-box">
	     	<input type="text" 	placeholder="Nombre Apellido1 Apellido2" id="nombrecompleto" name="nombrecompleto">
	      	<label>Nombre Completo</label>
	    </div>
	       
	    <div class="user-box">
	      <input type="text"  name="email" id="email" >
	      <label>Email</label>
	    </div>
	    <div class="user-box">
	      <input type="password" placeholder="password" name="password" id="password">
	      <label>Password</label>
	    </div>
	     <div class="user-box">
	      <input  type="date" placeholder="LocalDate" id="date" name="date">
	      <label>Fecha de Nacimiento</label>
	    </div>
	    <h2>
	  		<a class="sub" href="${pageContext.request.contextPath}<%= application.getInitParameter("loginController")%>">Login</a>
	  </h2>
	    <h2>
	    <div class="submit">
	      <span></span>
	      <span></span>
	      <span></span>
	      <span></span>
	      <input class="sub" type="submit" id="submit" value="Register">
		</div>
	    </h2>
	  </form>
	  
	</div>
    
  </body>
 <script src="${pageContext.request.contextPath}/js/script.js"></script> 

</html>