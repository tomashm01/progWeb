<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
  <body>
  
<!-- ACL -->
<%String aclNew = application.getInitParameter("aclNew"); %>
<jsp:include page="<%=aclNew%>"></jsp:include>
<!-- ACL -->
    <div class="form-style-6">
      <form id="formulario" method="post" action= "${pageContext.request.contextPath}<%=application.getInitParameter("loginController")%>">
        <input class="cajaBlanca" type="email" placeholder="email" name="email" id="email" ><br>
        <input class="cajaBlanca" type="password" placeholder="password" name="password" id="password"><br>
        <input  type="submit" id="submit" value="Submit"><br><br>
        <input  type="reset" id="reset">
      </form> 
	<%if(request.getParameter("Errorlogin") != null) {%>
		<p class="cajaRoja"> Email o contraseña incorrectas. </p>
	<%}	%>
    </div>
    <a href="${pageContext.request.contextPath}<%= application.getInitParameter("registerController")%>">Registrarse</a>
  </body>
  <script src="${pageContext.request.contextPath}/js/script.js"></script> 
</html>