package es.uco.pw.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.circuit.handlers.CircuitHandler;
import es.uco.pw.business.circuit.models.Kart;
import es.uco.pw.business.enums.EstadoKart;

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

		String pista = request.getParameter("idPista");
		String estadoKart = request.getParameter("estadoKart");
		String tipo = request.getParameter("tipoKart");
		
		if(pista == null && estadoKart == null && tipo == null) {
			request.setAttribute("arrayPistas",CircuitHandler.getInstance().getAllPistas());
			request.getRequestDispatcher(getServletContext().getInitParameter("addKartView")).forward(request, response);
			return;
		}
		if(pista == null || estadoKart == null || tipo == null) {
			request.setAttribute("response","fail");
			request.getRequestDispatcher(getServletContext().getInitParameter("addKartView")).forward(request, response);
			return;
		}
		try {
			Integer idPista = Integer.parseInt(pista);
			EstadoKart estado = Kart.toEstadoKart(estadoKart);
			boolean isAdult = Boolean.parseBoolean(tipo);
			CircuitHandler.getInstance().addKart(new Kart(isAdult,estado,idPista));
			request.setAttribute("response","success");
		}catch(Exception e) {
			request.setAttribute("response","fail");
		}

		request.getRequestDispatcher(getServletContext().getInitParameter("addKartView")).forward(request, response);
	}

}
