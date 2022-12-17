<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="/p3/css/sidebar.css">
  </head>
  <body><div class="area"></div><nav class="main-menu">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/addReserve">
                        <i class="fa fa-home fa-2x"></i>
                        <span class="nav-text">
                           Nueva reserva
                        </span>
                    </a>
                  
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/addBono">
                        <i class="fa fa-laptop fa-2x"></i>
                        <span class="nav-text">
                            Nuevo Bono
                        </span>
                    </a>
                    
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/modifyReserve">
                       <i class="fa fa-list fa-2x"></i>
                        <span class="nav-text">
                            Modificar reserva
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/viewReserve">
                       <i class="fa fa-folder-open fa-2x"></i>
                        <span class="nav-text">
                            Ver reservas
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