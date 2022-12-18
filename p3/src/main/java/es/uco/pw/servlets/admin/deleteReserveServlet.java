package es.uco.pw.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.reserve.handlers.ReservaHandler;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class deleteReserveServlet
 */
@WebServlet(name="deleteReserve", urlPatterns="/deleteReserve")
public class deleteReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteReserveServlet() {
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

		
		if(id == null) {
			request.setAttribute("arrayReserves",ReservaHandler.getInstance().getFutureReserves());
			request.getRequestDispatcher(getServletContext().getInitParameter("deleteReserveView")).forward(request, response);
			return;
		}
		try {
			Integer idReserve = Integer.parseInt(id);
			boolean resultado =ReservaHandler.getInstance().removeReserve(idReserve);
			String respuesta = (resultado) ? "sucess" : "fail";
			request.setAttribute("response",respuesta);
		}catch(Exception e) {
			request.setAttribute("response","fail");
			request.getRequestDispatcher(getServletContext().getInitParameter("addKartView")).forward(request, response);
			return;
		}

		request.getRequestDispatcher(getServletContext().getInitParameter("deleteReserveView")).forward(request, response);
	}

}
