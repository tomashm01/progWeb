<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="/p3/css/sidebar.css">
  </head>
  <body><div class="area"></div><nav class="main-menu">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/addKart">
                        <i class="fa fa-home fa-2x"></i>
                        <span class="nav-text">
                            Dar de alta Karts
                        </span>
                    </a>
                  
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/addPista">
                        <i class="fa fa-laptop fa-2x"></i>
                        <span class="nav-text">
                            Dar de alta Pista
                        </span>
                    </a>
                    
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/modifyKart">
                       <i class="fa fa-list fa-2x"></i>
                        <span class="nav-text">
                            Modificar Karts
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/modifyKartState">
                       <i class="fa fa-folder-open fa-2x"></i>
                        <span class="nav-text">
                            Modificar estado de Kart
                        </span>
                    </a>
                   
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/pairKart">
                        <i class="fa fa-bar-chart-o fa-2x"></i>
                        <span class="nav-text">
                            Asociar Karts a pistas
                        </span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/modifyPista">
                        <i class="fa fa-font fa-2x"></i>
                        <span class="nav-text">
                           Modificar Pista
                        </span>
                    </a>
                </li>
                <li>
                   <a href="${pageContext.request.contextPath}/modifyPistaState">
                       <i class="fa fa-table fa-2x"></i>
                        <span class="nav-text">
                            Modificar estado de Pista
                        </span>
                    </a>
                </li>
                <li>
                   <a href="${pageContext.request.contextPath}/deleteReserve">
                        <i class="fa fa-map-marker fa-2x"></i>
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
                            Logout
                        </span>
                    </a>
                </li>  
            </ul>
        </nav>
  </body>
 </html>