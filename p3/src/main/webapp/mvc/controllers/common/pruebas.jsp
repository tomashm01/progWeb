<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PruebasControlador</title>
</head>
<body>
<%
String path = application.getInitParameter("pruebasView");
%>
<jsp:forward page="<%=path%>" />
<!-- 
<jsp:forward page="${request.getContextPath()}${application.getInitParameter('pruebasView')}"/>
give me a similar jsp for Add Pista, class that has the following parameters:
-String name
-boolean isAvailible (implement with radio button)
-Enum dificulty (implement with select opion between 'familiar','adultos' and 'infantil')
-Int maxKarts
 -->

</body>
</html>