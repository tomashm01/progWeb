package es.uco.pw.servlets.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.enums.DificultadPista;
import es.uco.pw.display.javabean.CustomerBean;

import es.uco.pw.business.reserve.handlers.ReservaHandler;
/**
 * Servlet implementation class addBonoServlet
 */
@WebServlet(name="addBono", urlPatterns="/addBono")
public class addBonoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addBonoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession  session = request.getSession();
		CustomerBean User = (CustomerBean)session.getAttribute("User");
		if(User == null ||User.getRol() == null||! User.getRol().equals("USER")) {
			request.setAttribute("ACL","Not allowed to go there");
			request.getRequestDispatcher(getServletContext().getInitParameter("index")).forward(request, response);
			return;
		}
		
		String idUser = User.getEmail();
		String tipo = request.getParameter("tipo");
		
		if(tipo == null) {
			request.getRequestDispatcher(getServletContext().getInitParameter("addBonoView")).forward(request, response);
			return;
		}

		try {
			DificultadPista dif = DificultadPista.valueOf(tipo);
			boolean resultado = ReservaHandler.getInstance().asociarBono(idUser,dif);
			String salida = (resultado) ? "success" : "fail";
			request.setAttribute("response",salida);
		}catch (Exception e) {
			request.setAttribute("response","fail");
			request.getRequestDispatcher(getServletContext().getInitParameter("addBonoView")).forward(request, response);
			return;
		}
		request.getRequestDispatcher(getServletContext().getInitParameter("addBonoView")).forward(request, response);
		return;
	}

}
