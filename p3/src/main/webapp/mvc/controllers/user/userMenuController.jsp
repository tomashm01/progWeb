<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.LocalDateTime" %>
<%@page import="es.uco.pw.business.reserve.handlers.ReservaHandler" %>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cargando...</title>
</head>
<body>

<%
String userMenuViewPath = application.getInitParameter("userMenuView");
LocalDateTime Lreserva = ReservaHandler.getInstance().getNextReserveByUser(User.getEmail()).getDate();

DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/YYYY");
String todayDate= formatter1.format(LocalDate.now()).toString();
DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm");
String reserva = formatter2.format(Lreserva).toString();
%>
<jsp:forward page="<%=userMenuViewPath%>">
  <jsp:param name="reserva" value="<%=reserva%>" />
  <jsp:param name="fechaActual" value="<%=todayDate%>" />
</jsp:forward>

</body>
</html>