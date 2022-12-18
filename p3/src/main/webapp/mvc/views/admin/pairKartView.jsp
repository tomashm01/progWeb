<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.circuit.models.Pista" %>
<%@ page import="es.uco.pw.business.circuit.models.Kart" %>
<%@ page import="java.util.ArrayList" %>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Asociar un kart</title>
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
	ArrayList<Kart> karts = new ArrayList<Kart>();
	if(request.getAttribute("arrayPistas") != null){
		pistas = (ArrayList<Pista>)request.getAttribute("arrayPistas");
		
	}
	if(request.getAttribute("arrayKarts") != null){
		karts = (ArrayList<Kart>)request.getAttribute("arrayKarts");
	}
	
	%>
	 <aside>
    	<jsp:include page="/include/sidebarAdmin.jsp"></jsp:include>
  	</aside>
<main>
  <jsp:include page="/include/headerAdmin.jsp"></jsp:include>
	    <div class="form-style-6">
			<form id="formulario"  method="get" action= "/p3/pairKart">
			 <label>Id</label>
			   <select class="cajaBlanca" id="id" name="id">
				<%for(Kart it : karts){ %>
			    	<option value="<%=it.getId()%>"><%=it%></option>
			    <%} %>
			  </select><br>
			  <label>Pista asignada</label>
			  <select class="cajaBlanca" id="idPista" name="idPista">
				<%for(Pista it : pistas){ %>
			    	<option value="<%=it.getId()%>"><%=it.getName()%></option>
			    <%} %>
			    <option value="-1">Ninguna</option>
			  </select><br>
			  <input type="submit" id="submit" value="Enlazar Kart con Pista"><br><br>
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
</html>