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
 * Servlet implementation class addPistaServlet
 */
@WebServlet(name="addPista", urlPatterns="/addPista")
public class addPistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addPistaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String disponible = request.getParameter("isAvailible");
		String dificultad = request.getParameter("dificulty");
		String Karts = request.getParameter("maxKarts");
		

		if(name == null || disponible == null || dificultad == null || Karts == null) {
			request.getRequestDispatcher(getServletContext().getInitParameter("addPistaView")).forward(request, response);
			return;
		}

		try {
			boolean isAvailible = Boolean.parseBoolean(disponible);
			DificultadPista difficulty = DificultadPista.valueOf(dificultad);
			Integer maxKarts = Integer.parseInt(Karts);
			
			CircuitHandler.getInstance().addPista(new Pista(name,isAvailible,difficulty,maxKarts));
			request.setAttribute("response","success");
		}catch(Exception e) {
			request.setAttribute("response","fail");
		}

		request.getRequestDispatcher(getServletContext().getInitParameter("addPistaView")).forward(request, response);

	}

}
