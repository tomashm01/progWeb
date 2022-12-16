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
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class modifyPistaStateServlet
 */
@WebServlet(name="modifyPistaState", urlPatterns="/modifyPistaState")
public class modifyPistaStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyPistaStateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession  session = request.getSession();
		CustomerBean User = (CustomerBean)session.getAttribute("User");
		if(User == null||User.getRol() == null || ! User.getRol().equals("ADMIN")) {
			request.setAttribute("ACL","Not allowed to go there");
			request.getRequestDispatcher(getServletContext().getInitParameter("index")).forward(request, response);
			return;
		}
		String id = request.getParameter("id");
		String disponible = request.getParameter("isAvailible");
		

		if( id==null &&  disponible == null ) {
			request.setAttribute("arrayPistas",CircuitHandler.getInstance().getAllPistas());
			request.getRequestDispatcher(getServletContext().getInitParameter("modifyPistaStateView")).forward(request, response);
			return;
		}

		try {
			
			Integer idPista = Integer.parseInt(id);
			Pista pista = CircuitHandler.getInstance().getPistaByID(idPista);
			if(pista== null) {
				request.setAttribute("response","fail");
				request.getRequestDispatcher(getServletContext().getInitParameter("modifyPistaStateView")).forward(request, response);
			}
			boolean isAvailible = Boolean.parseBoolean(disponible);
			pista.setAvailable(isAvailible);
			
			boolean resultado = CircuitHandler.getInstance().editPista(pista);
			String respuesta = (resultado) ? "success" : "fail";
			request.setAttribute("response",respuesta);
	
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("response","fail");
		}

		request.getRequestDispatcher(getServletContext().getInitParameter("modifyPistaStateView")).forward(request, response);

	}

}
