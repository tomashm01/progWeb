
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar  Usuario</title>
</head>
<!-- ACL -->
<%
	String returnPath = application.getInitParameter("index");

	if(User.getRol() == null ){
		%>
		<jsp:forward page="<%= returnPath %>">
		  <jsp:param name="ACL" value="Not allowed to go there" />
		</jsp:forward>
		<%
	}
	%>
<!-- ACL -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
  <body>
  <aside>
		<%if(User.getRol().equals("USER")){ %>
	    	<jsp:include page="/include/sidebar.jsp"></jsp:include>
		<%}else{ %>
	    	<jsp:include page="/include/sidebarAdmin.jsp"></jsp:include>
		<%} %>
    </aside>
	<main>
		<%if(User.getRol().equals("USER")){ %>
 			<jsp:include page="/include/header.jsp"></jsp:include>
		<%}else{ %>
  			<jsp:include page="/include/headerAdmin.jsp"></jsp:include>
		<%} %>
	   
    <div class="form-style-6">
    <%
	if(request.getParameter("ErrorModificacion") != null) {
    %>
		<p class="cajaRoja"> Error en la validacion del formulario </p>
	<%
	}
    %>
      <form id="formulario"  method="post" action= "<%=application.getInitParameter("modifyUserController")%>">
        <div class="formulario__grupo" id="grupoUsuario">
            <input class="cajaBlanca" type="text" 	placeholder="Nombre Apellido1 Apellido2" id="nombrecompleto" name="nombrecompleto">
        </div>
        <input class="cajaBlanca" type="password" placeholder="password" id="password" name="password"><br>
        <label>Fecha de nacimiento</label>
        <input class="cajaBlanca" type="date" placeholder="LocalDate" id="date" name="date"><br>
        <input type="submit" id="submit" value="Submit"><br><br>
        <input type="reset" id="reset">
      </form> 
      
    </div>
	   <jsp:include page="/include/footer.html"></jsp:include>
  </main>
  </body>
  <script src="${pageContext.request.contextPath}/js/script.js"></script>

