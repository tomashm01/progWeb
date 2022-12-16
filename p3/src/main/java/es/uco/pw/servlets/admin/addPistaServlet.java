package es.uco.pw.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.circuit.handlers.CircuitHandler;
import es.uco.pw.business.circuit.models.Pista;
import es.uco.pw.business.enums.DificultadPista;
import es.uco.pw.display.javabean.CustomerBean;

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
		HttpSession  session = request.getSession();
		CustomerBean User = (CustomerBean)session.getAttribute("User");
		if(User == null ||User.getRol() == null||! User.getRol().equals("ADMIN")) {
			request.setAttribute("ACL","Not allowed to go there");
			request.getRequestDispatcher(getServletContext().getInitParameter("index")).forward(request, response);
			return;
		}
		String name = request.getParameter("name");
		String disponible = request.getParameter("isAvailible");
		String dificultad = request.getParameter("dificulty");
		String Karts = request.getParameter("maxKarts");
		

		if(name == null && disponible == null && dificultad == null && Karts == null) {
			request.getRequestDispatcher(getServletContext().getInitParameter("addPistaView")).forward(request, response);
			return;
		}
		
		if(name == null || disponible == null || dificultad == null || Karts == null) {
			request.setAttribute("response","fail");
			request.getRequestDispatcher(getServletContext().getInitParameter("addPistaView")).forward(request, response);
			return;
		}

		try {
			boolean isAvailible = Boolean.parseBoolean(disponible);
			DificultadPista difficulty = DificultadPista.valueOf(dificultad);
			Integer maxKarts = Integer.parseInt(Karts);
			
			boolean resultado =CircuitHandler.getInstance().addPista(new Pista(name,isAvailible,difficulty,maxKarts));
			String respuesta = (resultado) ? "sucess" : "fail";
			request.setAttribute("response",respuesta);
		}catch(Exception e) {
			request.setAttribute("response","fail");
			request.getRequestDispatcher(getServletContext().getInitParameter("addKartView")).forward(request, response);
			return;
		}

		request.getRequestDispatcher(getServletContext().getInitParameter("addPistaView")).forward(request, response);

	}

}
