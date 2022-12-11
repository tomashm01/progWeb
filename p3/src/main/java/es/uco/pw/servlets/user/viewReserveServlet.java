package es.uco.pw.servlets.user;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.reserve.handlers.ReservaHandler;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fechaInicio = request.getParameter("fechaInicio");
		String fechaFin = request.getParameter("fechaFin");
		String idUser = request.getParameter("email");
		if( fechaInicio == null && fechaFin == null && idUser == null) {
			request.getRequestDispatcher(getServletContext().getInitParameter("reserveView")).forward(request, response);
			return;
		}
		LocalDate fechaInicioDate = LocalDate.parse(fechaInicio);
		LocalDate fechaFinDate = LocalDate.parse(fechaFin);
		
		request.setAttribute("arrayReserves",ReservaHandler.getInstance().geReservesByUserByDates(fechaInicioDate,fechaFinDate,idUser));
		request.setAttribute("response","success");
		request.getRequestDispatcher(getServletContext().getInitParameter("reserveView")).forward(request, response);
		
	}

}
