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
  <body>
    <div class="form-style-6">
    <%
	if(request.getParameter("ErrorRegister") != null) {
    %>
		<p class="cajaRoja"> Error en la validacion del formulario, email ya registrado </p>
	<%
	}
    %>
      <form id="formulario"  method="post" action= "${pageContext.request.contextPath}<%=application.getInitParameter("registerController")%>">
        <div class="formulario__grupo" id="grupoUsuario">
            <input class="cajaBlanca" type="text" 	placeholder="Nombre Apellido1 Apellido2" id="nombrecompleto" name="nombrecompleto">
        </div>
        <input class="cajaBlanca" type="email" placeholder="email" id="email" name="email" ><br>
        <input class="cajaBlanca" type="password" placeholder="password" id="password" name="password"><br>
        <label>Fecha de nacimiento</label>
        <input class="cajaBlanca" type="date" placeholder="LocalDate" id="date" name="date"><br>
        <input type="submit" id="submit" value="Submit"><br><br>
        <input type="reset" id="reset">
      </form> 
      
    </div>
        <a href="${pageContext.request.contextPath}<%= application.getInitParameter("loginController")%>">Login</a>
  </body>
 <script src="${pageContext.request.contextPath}/js/script.js"></script> 

</html>