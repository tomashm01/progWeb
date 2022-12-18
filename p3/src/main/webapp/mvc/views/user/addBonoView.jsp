<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="es.uco.pw.business.circuit.models.Pista" %>
<%@ page import="java.util.ArrayList" %>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>A&ntildeadir Bono</title>
</head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
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
	<%
	String indexViewPath = application.getInitParameter("index");
	%>
		<form id="formulario1" method="get" action="/p3/addBono">
			<label>Tipo de Bono</label>
			<select class="cajaBlanca" id="tipo" name="tipo">
				<option value="FAMILIAR">Familiar</option>
				<option value="ADULTOS">Adultos</option>
				<option value="INFANTIL">Infantil</option>
			</select><br>
			<input type="submit" id="submit" value="Crear un nuevo Bono"><br><br>
		</form>
	<%
	if(request.getAttribute("response") != null){
		if(request.getAttribute("response") == "success"){
	%>
			<p class="cajaBlanca"><%=request.getAttribute("response")%></p>
		<%}else{%>
			<p class="cajaRoja"><%=request.getAttribute("response")%></p>
	<% 
		}
	} 
	%>
	</div>
 <jsp:include page="/include/footer.html"></jsp:include>
</main>
</body>
</html>