<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="es.uco.pw.business.circuit.models.Pista" %>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>A&ntildeadir un kart</title>
</head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
<body>
	<!-- ACL -->
	<%String aclAdmin = application.getInitParameter("aclAdmin"); %>
	<jsp:include page="<%=aclAdmin%>"></jsp:include>
	<!-- ACL -->
	<%
	String indexViewPath = application.getInitParameter("index");
	ArrayList<Pista> pistas = new ArrayList<Pista>(); 
	if(request.getAttribute("arrayPistas") != null){
		pistas = (ArrayList<Pista>)request.getAttribute("arrayPistas");
	}
	%>
	 <aside>
    	<jsp:include page="/include/sidebarAdmin.jsp"></jsp:include>
  	</aside>
<main>
  <jsp:include page="/include/headerAdmin.jsp"></jsp:include>
	
	    <div class="form-style-6">
			<form id="formulario"  method="get" action= "/p3/addKart">
			  <div class="formulario__grupo" id="grupoKart">
			  	<label>Tipo</label>
			  	<div class="cajaBlanca">
			      <input type="radio" name="tipoKart" value="true" checked> Adulto
			      <input type="radio" name="tipoKart" value="false"> Infantil
			 	 </div>
			  </div>
			  <label>Estado</label>
			  <select class="cajaBlanca" id="estadoKart" name="estadoKart">
			    <option value="DISPONIBLE">Disponible</option>
			    <option value="RESERVADO">Reservado</option>
			    <option value="MANTENIMIENTO">Mantenimiento</option>
			  </select><br>
			  <label>Pista asignada</label>
			  <select class="cajaBlanca" id="idPista" name="idPista">
				<%for(Pista it : pistas){ %>
			    	<option value="<%=it.getId()%>"><%=it.getName()%></option>
			    <%} %>
			    <option value="-1">Ninguna</option>
			  </select><br>
			  <label>Número de Karts con estas caracteristicas a crear</label>
			  <input class="cajaBlanca" type="number" id="cantidad" name="cantidad">
			  <input type="submit" id="submit" value="Dar de alta Kart"><br><br>
			  <input type="reset" id="reset">
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
 <script src="${pageContext.request.contextPath}/js/script.js"></script> 
</html>