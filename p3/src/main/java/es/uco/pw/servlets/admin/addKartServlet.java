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
import es.uco.pw.business.circuit.models.Pista;
import es.uco.pw.business.enums.DificultadPista;
import es.uco.pw.business.enums.EstadoKart;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class addKartServlet
 */
@WebServlet(name="addKart", urlPatterns="/addKart")
public class addKartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addKartServlet() {
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
		String Spista = request.getParameter("idPista");
		String estadoKart = request.getParameter("estadoKart");
		String tipo = request.getParameter("tipoKart");
		String numero = request.getParameter("cantidad");
		
		if(Spista == null && estadoKart == null && tipo == null && numero == null ) {
			request.setAttribute("arrayPistas",CircuitHandler.getInstance().getAllPistas());
			request.getRequestDispatcher(getServletContext().getInitParameter("addKartView")).forward(request, response);
			return;
		}
		if(Spista == null || estadoKart == null || tipo == null || numero == null) {
			request.setAttribute("response","fail");
			request.getRequestDispatcher(getServletContext().getInitParameter("addKartView")).forward(request, response);
			return;
		}
		try {
			Integer idPista = Integer.parseInt(Spista);
			EstadoKart estado = Kart.toEstadoKart(estadoKart);
			boolean isAdult = Boolean.parseBoolean(tipo);
			Integer n =  Integer.parseInt(numero);
			
			//coincide pista con dificultad de kart
			if (idPista != (-1)){
				Pista pista = CircuitHandler.getInstance().getPistaByID(idPista);
				if(pista == null || pista.getKartsList().size() >= pista.getMaxKarts()) {
					request.setAttribute("response","fail");
					request.getRequestDispatcher(getServletContext().getInitParameter("pairKartView")).forward(request, response);
					return;
				}
				DificultadPista difPista =  pista.getDifficulty();
				if((difPista.equals(DificultadPista.ADULTOS) && ! isAdult)|| (difPista.equals(DificultadPista.INFANTIL) && isAdult) ) {
					request.setAttribute("response","fail");
					request.getRequestDispatcher(getServletContext().getInitParameter("pairKartView")).forward(request, response);
					return;
				}
			}
				
			if(n<1) {
				request.setAttribute("response","fail");
				request.getRequestDispatcher(getServletContext().getInitParameter("addKartView")).forward(request, response);
				return;
			}
			for(int i=0;i<n;i++) {
				CircuitHandler.getInstance().addKart(new Kart(isAdult,estado,idPista));				
			}
			request.setAttribute("response","success");
		}catch(Exception e) {
			request.setAttribute("response","fail");
			request.getRequestDispatcher(getServletContext().getInitParameter("addKartView")).forward(request, response);
			return;
		}

		request.getRequestDispatcher(getServletContext().getInitParameter("addKartView")).forward(request, response);
	}

}
