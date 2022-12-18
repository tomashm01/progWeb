<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="es.uco.pw.business.circuit.models.Pista" %>
<%@ page import="java.util.ArrayList" %>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>A&ntildeadir reservas</title>
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
	ArrayList<Pista> pistas = new ArrayList<Pista>(); 
	if(request.getAttribute("arrayPistas") == null){
		
	%>
		<form id="formulario1" method="get" action="/p3/addReserve">
			<label>Fecha y hora de la reserva</label>
			<input type="datetime-local" name="date" class="cajaBlanca"><br>
			<label>Duración de la reserva (en minutos)</label>
			<input type="number" name="time" class="cajaBlanca"><br>
			<label>Número de adultos</label>
			<input type="number" name="numAdults" class="cajaBlanca"><br>
			<label>Número de niños</label>
			<input type="number" name="numChilds" class="cajaBlanca"><br>
			<label>Tipo de Reserva</label>
			<select class="cajaBlanca" id="tipo" name="tipo">
				<option value="FAMILIAR">Familiar</option>
				<option value="ADULTOS">Adultos</option>
				<option value="INFANTIL">Infantil</option>
			</select><br>
			<input type="submit" id="submit" value="Reservar"><br><br>
			<input type="reset" id="reset">
		</form>
	<%}else{
		
		 pistas = (ArrayList<Pista>)request.getAttribute("arrayPistas");	
		%>	
		<label>Pistas disponibles</label>
		<form id="formulario2" method="get" action="/p3/addReserve">
		<label>Pista</label>
		<select class="cajaBlanca" id="idPista" name="idPista">
			<%for(Pista it : pistas){ %>
		    	<option value="<%=it.getId()%>"><%=it.getName()%></option>
		    <%} %>
		</select><br>
		<input type="checkbox" name="isBono">Bono
		<input type="submit" id="submit" value="Reservar"><br><br>
		</form>
	<%
	}
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