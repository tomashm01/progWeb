package es.uco.pw.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.circuit.handlers.CircuitHandler;
import es.uco.pw.business.circuit.models.Kart;
import es.uco.pw.business.enums.EstadoKart;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class modifyKartStateServlet
 */
@WebServlet(name="modifyKartState", urlPatterns="/modifyKartState")
public class modifyKartStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyKartStateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession  session = request.getSession();
		CustomerBean User = (CustomerBean)session.getAttribute("User");
		if(User == null ||User.getRol() == null||! User.getRol().equals("ADMIN")) {
			request.setAttribute("ACL","Not allowed to go there");
			request.getRequestDispatcher(getServletContext().getInitParameter("index")).forward(request, response);
			return;
		}
		String id = request.getParameter("id");
		String estadoKart = request.getParameter("estadoKart");

		
		if( estadoKart == null  && id == null) {
			request.setAttribute("arrayKarts",CircuitHandler.getInstance().getAllKarts());
			request.getRequestDispatcher(getServletContext().getInitParameter("modifyKartStateView")).forward(request, response);
			return;
		}

		try {
			Integer idKart = Integer.parseInt(id);
			Kart kart = CircuitHandler.getInstance().getKartByID(idKart);
			if(kart == null) {
				request.setAttribute("response","fail");
				request.getRequestDispatcher(getServletContext().getInitParameter("modifyKartStateView")).forward(request, response);
			}

			EstadoKart estado = Kart.toEstadoKart(estadoKart);

			//coincide pista con dificultad de kart
			kart.setState(estado);
			boolean resultado =CircuitHandler.getInstance().editKart(kart);
			String respuesta = (resultado) ? "success" : "fail";
			request.setAttribute("response",respuesta);

		}catch(Exception e) {
			request.setAttribute("response","fail");
		}

		request.getRequestDispatcher(getServletContext().getInitParameter("modifyKartStateView")).forward(request, response);
	}



}
