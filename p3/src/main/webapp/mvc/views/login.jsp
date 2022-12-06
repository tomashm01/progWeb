<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
    <link rel="stylesheet" href="../../css/estilos.css">
  <body>
    <div class="form-style-6">
      <form id="formulario" method="post" action= "../controllers/loginController.jsp">
        <input class="cajaBlanca" type="email" placeholder="email" name="email" id="email" ><br>
        <input class="cajaBlanca" type="password" placeholder="password" name="password" id="password"><br>
        <input  type="submit" id="submit" value="Submit"><br><br>
        <input  type="reset" id="reset">
      </form> 
	<%if(request.getParameter("Errorlogin") != null) {%>
		<p class="cajaRoja"> Email o contraseña incorrectas. </p>
	<%}	%>
    </div>
    <a href="../controllers/registerController.jsp">Registrarse</a>
  </body>
  <script src="../../js/script.js"></script> 

</html>