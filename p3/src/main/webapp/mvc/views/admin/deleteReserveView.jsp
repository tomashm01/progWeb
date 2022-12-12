<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="es.uco.pw.business.reserve.models.factory.*" %>
<%@ page import="java.util.ArrayList" %>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Remove reserve</title>
</head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
<body>
	<!-- ACL -->
	<%String aclAdmin = application.getInitParameter("aclAdmin"); %>
	<jsp:include page="<%=aclAdmin%>"></jsp:include>
	<!-- ACL -->

	<%
	String indexViewPath = application.getInitParameter("index");
	ArrayList<ReservaAbstracta> pistas = new ArrayList<ReservaAbstracta>(); 
	if(request.getAttribute("arrayReserves") != null){
		pistas = (ArrayList<ReservaAbstracta>)request.getAttribute("arrayReserves");
	}
	%>
	    <div class="form-style-6">
			<form id="formulario"  method="get" action= "/p3/deleteReserve">
			<label>Elija la reserva a borrar</label>
			  <select class="cajaBlanca" id="id" name="id">
				<%for(ReservaAbstracta it : pistas){ %>
			    	<option value="<%=it.getId()%>">User:<%=it.getIdUser()%> date:<%=it.getDate()%></option>
			    <%} %>
			  </select><br>
			  <input type="submit" id="submit" value="Submit"><br><br>
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
		<p><a href="${pageContext.request.contextPath}<%=indexViewPath%>">Inicio</a></p>
</body>
</html>