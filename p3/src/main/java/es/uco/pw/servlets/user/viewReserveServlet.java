package es.uco.pw.servlets.user;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.reserve.handlers.ReservaHandler;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class viewReserveServlet
 */
@WebServlet(name="viewReserve", urlPatterns="/viewReserve")
public class viewReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewReserveServlet() {
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
		}
		String fechaInicio = request.getParameter("fechaInicio");
		String fechaFin = request.getParameter("fechaFin");
		String idUser = request.getParameter("email");
		if( fechaInicio == null && fechaFin == null && idUser == null) {
			request.getRequestDispatcher(getServletContext().getInitParameter("reserveView")).forward(request, response);
			return;
		}
		if( fechaInicio == null || fechaFin == null || idUser == null) {
			request.setAttribute("response","fail");
			request.getRequestDispatcher(getServletContext().getInitParameter("reserveView")).forward(request, response);
			return;
		}
		LocalDate fechaInicioDate = LocalDate.parse(fechaInicio);
		LocalDate fechaFinDate = LocalDate.parse(fechaFin);
		if(fechaFinDate.isBefore(fechaInicioDate)) {
			request.setAttribute("response","fail");
			request.getRequestDispatcher(getServletContext().getInitParameter("reserveView")).forward(request, response);
			return;
		}
		
		request.setAttribute("arrayReservesPre",ReservaHandler.getInstance().geReservesByUserByDates(fechaInicioDate,LocalDate.now(),idUser));
		request.setAttribute("arrayReservesPost",ReservaHandler.getInstance().geReservesByUserByDates(LocalDate.now(),fechaFinDate,idUser));
		request.setAttribute("response","success");
		request.getRequestDispatcher(getServletContext().getInitParameter("reserveView")).forward(request, response);
		
	}

}
