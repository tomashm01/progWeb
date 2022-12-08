package es.uco.pw.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.reserve.handlers.ReservaHandler;

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
		String id = request.getParameter("id");

		
		if(id == null) {
			request.setAttribute("arrayReserves",ReservaHandler.getInstance().getFutureReserves());
			request.getRequestDispatcher(getServletContext().getInitParameter("deleteReserveView")).forward(request, response);
			return;
		}
		try {
			Integer idReserve = Integer.parseInt(id);
			ReservaHandler.getInstance().removeReserve(idReserve);
			request.setAttribute("response","success");
		}catch(Exception e) {
			request.setAttribute("response","fail");
		}

		request.getRequestDispatcher(getServletContext().getInitParameter("deleteReserveView")).forward(request, response);
	}

}
