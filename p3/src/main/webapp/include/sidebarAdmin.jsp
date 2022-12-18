<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="/p3/css/sidebarAdmin.css">
  </head>
  <body><div class="area"></div><nav class="main-menu">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/addKart">
                        <i class="fa fa-tachometer"></i>
                        <span class="nav-text">
                            Dar de alta Karts
                        </span>
                    </a>
                  
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/addPista">
                        <i class="fa fa-road"></i>
                        <span class="nav-text">
                            Dar de alta Pista
                        </span>
                    </a>
                    
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/modifyKart">
                       <i class="fa fa-pencil"></i>
                        <span class="nav-text">
                            Modificar Karts
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/modifyKartState">
                       <i class="fa fa-wrench"></i>
                        <span class="nav-text">
                            Modificar estado de Kart
                        </span>
                    </a>
                   
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/pairKart">
                        <i class="fa fa-arrows-h"></i>
                        <span class="nav-text">
                            Asociar Karts a pistas
                        </span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/modifyPista">
                        <i class="fa fa-pencil-square"></i>
                        <span class="nav-text">
                           Modificar Pista
                        </span>
                    </a>
                </li>
                <li>
                   <a href="${pageContext.request.contextPath}/modifyPistaState">
                       <i class="fa fa-cog"></i>
                        <span class="nav-text">
                            Modificar estado de Pista
                        </span>
                    </a>
                </li>
                <li>
                <li>
                   <a href="${pageContext.request.contextPath}/deleteReserve">
                        <i class="fa fa-trash-o"></i>
                        <span class="nav-text">
                            Borrar Reserva
                        </span>
                    </a>
                </li>
            </ul>

            <ul class="logout">
                <li>
                   <a href="${pageContext.request.contextPath}<%=application.getInitParameter("logoutController")%>">
                         <i class="fa fa-power-off fa-2x"></i>
                        <span class="nav-text">
                            Cerrar Sesión
                        </span>
                    </a>
                </li>  
            </ul>
        </nav>
  </body>
 </html>