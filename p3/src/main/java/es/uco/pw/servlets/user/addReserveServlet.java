package es.uco.pw.servlets.user;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import es.uco.pw.business.circuit.handlers.CircuitHandler;
import es.uco.pw.business.enums.DificultadPista;
import es.uco.pw.business.reserve.handlers.ReservaHandler;
import es.uco.pw.business.reserve.models.factory.ReservaAdultos;
import es.uco.pw.business.reserve.models.factory.ReservaFamiliar;
import es.uco.pw.business.reserve.models.factory.ReservaInfantil;
import es.uco.pw.display.javabean.ResponseBean;
import es.uco.pw.display.javabean.CustomerBean;
/**
 * Servlet implementation class addReserveServlet
 */
@WebServlet(name="addReserve", urlPatterns="/addReserve")
public class addReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addReserveServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String idUser = User.getEmail();
		String Sfecha = request.getParameter("date");
		String Sduracion = request.getParameter("time");
		String SnumAdults = request.getParameter("numAdults");
		String SnumChilds = request.getParameter("numChilds");
		String Stipo = request.getParameter("tipo");
		String SidPista = request.getParameter("idPista");
		String Sbono = request.getParameter("isBono");
		
		boolean resultado = false;

		if(Sfecha == null && Sduracion == null && SnumAdults == null && SnumChilds == null && Stipo == null && SidPista == null && Sbono == null){
			request.getRequestDispatcher(getServletContext().getInitParameter("addReserveView")).forward(request, response);
			return;
		}
	
		try {
	
			if (SidPista == null) {
				
				DificultadPista dif = DificultadPista.valueOf(Stipo);
				LocalDateTime fecha = LocalDateTime.parse(Sfecha,DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
				Integer duracion = Integer.parseInt(Sduracion);
				
				Integer numAdults = (SnumAdults == null)? 0: Integer.parseInt(SnumAdults);
				Integer numChilds = (SnumChilds == null)? 0: Integer.parseInt(SnumChilds);
				if(numAdults<0 || numChilds<0 || numAdults+numChilds <0) {
					request.setAttribute("response","fail");
					request.getRequestDispatcher(getServletContext().getInitParameter("addReserveView")).forward(request, response);
				}
				LocalDateTime fechaFin = fecha.plusMinutes(duracion);
				
				request.setAttribute("arrayPistas",CircuitHandler.getInstance().getFreePistas(dif,fecha,fechaFin,numAdults,numChilds));
				ResponseBean form1Data = new ResponseBean(dif,fecha,duracion,numAdults,numChilds,fechaFin);
				session.setAttribute("form1Data",form1Data);
				request.getRequestDispatcher(getServletContext().getInitParameter("addReserveView")).forward(request, response);
				return;
			}else {
				ResponseBean bean= (ResponseBean)session.getAttribute("form1Data");
				if(bean == null) {
					request.setAttribute("response","fail2");
					request.getRequestDispatcher(getServletContext().getInitParameter("addReserveView")).forward(request, response);
					return;
				}
				
				Integer idPista = Integer.parseInt(SidPista);
				String respuesta="success";
				if(bean.getDif().equals(DificultadPista.ADULTOS)) {
					ReservaAdultos reserva = new ReservaAdultos( idUser,  bean.getFecha(), bean.getDuracion(),  idPista,  0f, 0f, -1, bean.getNumAdults());
					if(Sbono == null) {
						respuesta = ReservaHandler.getInstance().KnowMyFault(reserva);
						 resultado = ReservaHandler.getInstance().addReservaIndividual(reserva);
					}else {
						respuesta = ReservaHandler.getInstance().KnowMyFault(reserva);
						resultado =  ReservaHandler.getInstance().addReservaBono(reserva);
					}
				}else if(bean.getDif().equals(DificultadPista.INFANTIL)) {
					ReservaInfantil reserva = new ReservaInfantil( idUser,  bean.getFecha(), bean.getDuracion(),  idPista,  0f, 0f, -1, bean.getNumChilds());
					if(Sbono == null) {
						respuesta = ReservaHandler.getInstance().KnowMyFault(reserva);
						 resultado = ReservaHandler.getInstance().addReservaIndividual(reserva);
					}else {
						respuesta = ReservaHandler.getInstance().KnowMyFault(reserva);
						resultado =  ReservaHandler.getInstance().addReservaBono(reserva);
					}
				}else if(bean.getDif().equals(DificultadPista.FAMILIAR)) {
					ReservaFamiliar reserva = new ReservaFamiliar( idUser,  bean.getFecha(), bean.getDuracion(),  idPista,  0f, 0f, -1, bean.getNumAdults(), bean.getNumChilds());
					if(Sbono == null) {
						respuesta = ReservaHandler.getInstance().KnowMyFault(reserva);
						 resultado = ReservaHandler.getInstance().addReservaIndividual(reserva);
					}else {
						respuesta = ReservaHandler.getInstance().KnowMyFault(reserva);
						resultado =  ReservaHandler.getInstance().addReservaBono(reserva);
					}
				}

				String salida = (resultado) ? "success" : "fail";
				request.setAttribute("response",salida);
				request.setAttribute("error",respuesta);
				request.getRequestDispatcher(getServletContext().getInitParameter("addReserveView")).forward(request, response);
				return;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("response","fail");
			request.getRequestDispatcher(getServletContext().getInitParameter("addReserveView")).forward(request, response);
			return;
		}
	}
}
