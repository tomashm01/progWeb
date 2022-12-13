<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="es.uco.pw.business.user.models.Usuario" %>
<%@ page import="java.util.ArrayList"%>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminMenu</title>
</head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilos.css">
  <body>
  	<div class="form-style-6">
	<%	if(request.getParameter("ACL")!= null){ %>
				<p class="cajaRoja"><%=request.getParameter("ACL")%></p>	
	<%	}else if (request.getAttribute("ACL")!=null){ %>
				<p class="cajaRoja"><%=request.getAttribute("ACL")%></p>
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
	
		

			<p><a href="${pageContext.request.contextPath}/addKart">Dar de alta Karts</a></p>
			<p><a href="${pageContext.request.contextPath}/addPista">Dar de alta Pista</a></p>
			<p><a href="${pageContext.request.contextPath}/modifyKart">Modificar Karts</a></p>
			<p><a href="${pageContext.request.contextPath}/pairKart">Asociar Karts a pistas</a></p>
			<p><a href="${pageContext.request.contextPath}/modifyPista">Modificar Pista</a></p>
			<p><a href="${pageContext.request.contextPath}/deleteReserve">Eliminar reservas</a></p>
			<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("logoutController")%>">logout</a></p>
	  		<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("modifyUserController")%>">ModificarUsuario</a></p>
  	</div>
  </body>

</html>