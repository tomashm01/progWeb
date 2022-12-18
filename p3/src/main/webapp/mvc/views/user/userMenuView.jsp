<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="es.uco.pw.business.reserve.handlers.ReservaHandler" %>
<%@page import="es.uco.pw.business.user.handlers.UsuarioHandler" %>
<%@page import="es.uco.pw.business.user.models.Usuario" %>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.LocalDateTime" %>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu de usuario</title>
</head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilos.css">
  <body>
  	<!-- ACL -->
	<%String aclUser = application.getInitParameter("aclUser"); %>
	<jsp:include page="<%=aclUser%>"></jsp:include>
	<!-- ACL -->

  	  <aside>
    <jsp:include page="/include/sidebar.jsp"></jsp:include>
  </aside>
<main>
  <jsp:include page="/include/header.jsp"></jsp:include>
  	<div class="form-style-6">

	  		<p>Bienvenido <jsp:getProperty property="nombreCompleto" name="User"/></p>
	  		<p>Hoy es <%=request.getParameter("fechaActual")%></p>
	  		<p>Tu antiguedad es: <jsp:getProperty property="antiguedad" name="User"/> años</p> 
	  	
		<% if(! request.getParameter("reserva").equals("none")){%>
  			<p>Tu siguiente reserva es el dia:<%=request.getParameter("reserva")%></p>
		<%} %>

  	<%
	if(request.getParameter("ACL")!= null){
		%>
		<p class="cajaRoja">Acceso denegado</p>
		<%
	}else if (request.getAttribute("ACL")!=null){
		%>
		<p class="cajaRoja">Acceso denegado</p>
		
		<%
	}
  	%>
  	</div>
  	 <jsp:include page="/include/footer.html"></jsp:include>
</main>
  </body>
</html>