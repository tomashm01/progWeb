<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"><title>Añadir una pista</title>
</head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
<body>
	<!-- ACL -->
	<%String aclAdmin = application.getInitParameter("aclAdmin"); %>
	<jsp:include page="<%=aclAdmin%>"></jsp:include>
	<!-- ACL -->
	<aside>
    	<jsp:include page="/include/sidebarAdmin.jsp"></jsp:include>
  	</aside>
<main>
  <jsp:include page="/include/headerAdmin.jsp"></jsp:include>
	<div class="form-style-6">
		<form id="formulario" method="get" action="/p3/addPista">
			<label>Nombre</label>
			<input type="text" name="name" class="cajaBlanca"><br>
			<label>Disponibilidad</label>
			<div class="cajaBlanca">
				<input type="radio" name="isAvailible" value="true" checked> Disponible
				<input type="radio" name="isAvailible" value="false"> No Disponible
			</div>
			<label>Dificultad</label>
			<select class="cajaBlanca" id="dificulty" name="dificulty">
				<option value="FAMILIAR">Familiar</option>
				<option value="ADULTOS">Adultos</option>
				<option value="INFANTIL">Infantil</option>
			</select><br>
			<label>Máximo de karts</label>
			<input type="number" name="maxKarts" class="cajaBlanca"><br>
			<input type="submit" id="submit" value="Dar de alta Pista"><br><br>
			<input type="reset" id="reset">
		</form>
	<%
	String indexViewPath = application.getInitParameter("index");
	
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
 <script src="${pageContext.request.contextPath}/js/script.js"></script> 
</html>