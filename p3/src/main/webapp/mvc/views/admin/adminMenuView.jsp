<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="es.uco.pw.business.user.models.Usuario" %>
<%@ page import="java.util.ArrayList"%>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menú de administrador</title>
</head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilos.css">
  <body>
  
  <aside>
    	<jsp:include page="/include/sidebarAdmin.jsp"></jsp:include>
  	</aside>
<main>
  <jsp:include page="/include/headerAdmin.jsp"></jsp:include>
  
  	<div class="form-style-6">
	<%	if(request.getParameter("ACL")!= null){ %>
				<p class="cajaRoja">Acceso denegado</p>	
	<%	}else if (request.getAttribute("ACL")!=null){ %>
				<p class="cajaRoja">Acceso denegado</p>
	<%	} %>
	

<% 	if(request.getAttribute("users") != null && request.getAttribute("reservas") != null){
	ArrayList<Usuario> users = (ArrayList<Usuario>)request.getAttribute("users");
	ArrayList<Integer> reservas = (ArrayList<Integer>)request.getAttribute("reservas");

%>
		<table>
		  <thead>
		    <tr>
		      <th>Full Name</th>
		      <th>Antiquity</th>
		      <th>Completed Reservations</th>
		    </tr>
		  </thead>
		  <tbody>
		    <% for(int i=0;i<users.size();i++){
		     %>
		          <tr>
		            <td><%=users.get(i).getFullName()%></td>
		            <td><%=users.get(i).antiquity()%></td>
		            <td><%=reservas.get(i)%></td>
		          </tr>
		    <% 
		      } %>
		  </tbody>
		</table>
<%	} %>
	
  	</div>
  	<jsp:include page="/include/footer.html"></jsp:include>
	</main>
  </body>

</html>