package es.uco.pw.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.circuit.handlers.CircuitHandler;
import es.uco.pw.business.circuit.models.Pista;
import es.uco.pw.business.enums.DificultadPista;

/**
 * Servlet implementation class modifyPistaServlet
 */
@WebServlet(name="modifyPista", urlPatterns="/modifyPista")
public class modifyPistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyPistaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String disponible = request.getParameter("isAvailible");
		String dificultad = request.getParameter("dificulty");
		String Karts = request.getParameter("maxKarts");
		

		if( id==null || name == null || disponible == null || dificultad == null || Karts == null) {
			request.getRequestDispatcher(getServletContext().getInitParameter("modifyPistaView")).forward(request, response);
			return;
		}

		try {
			Integer idPista = Integer.parseInt(id);
			boolean isAvailible = Boolean.parseBoolean(disponible);
			DificultadPista difficulty = DificultadPista.valueOf(dificultad);
			Integer maxKarts = Integer.parseInt(Karts);
			CircuitHandler.getInstance().editPista(new Pista(idPista,name,isAvailible,difficulty,maxKarts));
			request.setAttribute("response","success");
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("response","fail");
		}

		request.getRequestDispatcher(getServletContext().getInitParameter("modifyPistaView")).forward(request, response);

	}

}
