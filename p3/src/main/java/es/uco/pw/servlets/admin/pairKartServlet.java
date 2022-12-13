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
import es.uco.pw.business.enums.DificultadPista;
import es.uco.pw.business.enums.EstadoKart;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class pairKartServlet
 */
@WebServlet(name="pairKart", urlPatterns="/pairKart")
public class pairKartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pairKartServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession  session = request.getSession();
		CustomerBean User = (CustomerBean)session.getAttribute("User");
		if(User == null ||! User.getRol().equals("ADMIN")) {
			request.setAttribute("ACL","Not allowed to go there");
			request.getRequestDispatcher(getServletContext().getInitParameter("index")).forward(request, response);
		}
		String id = request.getParameter("id");
		String pista = request.getParameter("idPista");

		
		if(pista == null  && id == null) {
			request.setAttribute("arrayKarts",CircuitHandler.getInstance().getAllKarts());
			request.setAttribute("arrayPistas",CircuitHandler.getInstance().getAllPistas());
			request.getRequestDispatcher(getServletContext().getInitParameter("pairKartView")).forward(request, response);
			return;
		}

		try {
			Integer idKart = Integer.parseInt(id);
			Kart kart = CircuitHandler.getInstance().getKartByID(idKart);
			if(kart == null) {
				request.setAttribute("response","fail");
				request.getRequestDispatcher(getServletContext().getInitParameter("pairKartView")).forward(request, response);
			}
			Integer idPista = Integer.parseInt(pista);
			EstadoKart estado = kart.getState();
			boolean isAdult = kart.isAdult();
			//coincide pista con dificultad de kart
			if (idPista != (-1)){
				DificultadPista difPista =  CircuitHandler.getInstance().getPistaByID(idPista).getDifficulty();
				if((difPista.equals(DificultadPista.ADULTOS) && ! isAdult)|| (difPista.equals(DificultadPista.INFANTIL) && isAdult) ) {
					request.setAttribute("response","fail");
					request.getRequestDispatcher(getServletContext().getInitParameter("pairKartView")).forward(request, response);
					return;
				}
			}
			boolean resultado =CircuitHandler.getInstance().editKart(new Kart(idKart,isAdult,estado,idPista));
			String respuesta = (resultado) ? "sucess" : "fail";
			request.setAttribute("response",respuesta);

		}catch(Exception e) {
			request.setAttribute("response","fail");
		}

		request.getRequestDispatcher(getServletContext().getInitParameter("pairKartView")).forward(request, response);
	}

}
