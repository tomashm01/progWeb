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
import es.uco.pw.display.javabean.CustomerBean;
import es.uco.pw.display.javabean.ResponseBean;

/**
 * Servlet implementation class modifyReserveServlet
 */
@WebServlet(name="modifyReserve", urlPatterns="/modifyReserve")
public class modifyReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyReserveServlet() {
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
		
		/*Entra al formulario, aparece una  lista con todas sus reservas futuras
		 * Elige una
		 *  Elige los parametros a modificar, no tienen porque ser todos
		 * Con esa información sintactica y servletiana, en el servlet proceso los getParameter,
		 * los que esten a null se aplican con los datos anteriores de la reserva.
		 * Si esa reserva estaba en un bono, idPista debe de ser la misma que tenia en su bd
		 * Se rellena la reserva, se comprueba, y se modifica con el handler
		 *  
		 *  */ 
		String idUser = User.getEmail();
		String Sfecha = request.getParameter("date");
		String Sduracion = request.getParameter("time");
		String SnumAdults = request.getParameter("numAdults");
		String SnumChilds = request.getParameter("numChilds");
		String Stipo = request.getParameter("tipo");
		String SidPista = request.getParameter("idPista");
		String SidReserva = request.getParameter("idReserva");
		boolean resultado = false;

		if(Sfecha == null && Sduracion == null && SnumAdults == null && SnumChilds == null && Stipo == null && SidPista == null ){
			request.setAttribute("arrayReservas",ReservaHandler.getInstance().getFutureReservesByUSer(idUser));
			request.getRequestDispatcher(getServletContext().getInitParameter("modifyReserveView")).forward(request, response);
			return;
		}
	
		try {
	
			if (SidPista == null) {
				Integer idReserva = Integer.parseInt(SidReserva);
				if(ReservaHandler.getInstance().getReserveByID(idReserva)== null) {
					request.setAttribute("response","fail");
					request.getRequestDispatcher(getServletContext().getInitParameter("modifyReserveView")).forward(request, response);
					return;
				}

				DificultadPista dif = DificultadPista.valueOf(Stipo);
				LocalDateTime fecha = LocalDateTime.parse(Sfecha,DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
				Integer duracion = Integer.parseInt(Sduracion);
				
				Integer numAdults = (SnumAdults == null)? 0: Integer.parseInt(SnumAdults);
				Integer numChilds = (SnumChilds == null)? 0: Integer.parseInt(SnumChilds);
				if(numAdults<0 || numChilds<0 || numAdults+numChilds <0) {
					request.setAttribute("response","fail");
					request.getRequestDispatcher(getServletContext().getInitParameter("modifyReserveView")).forward(request, response);
				}
				LocalDateTime fechaFin = fecha.plusMinutes(duracion);
				
				request.setAttribute("arrayPistas",CircuitHandler.getInstance().getFreePistas(dif,fecha,fechaFin,numAdults,numChilds));
				ResponseBean form2Data = new ResponseBean(dif,fecha,duracion,numAdults,numChilds,fechaFin);
				form2Data.setIdReserva(idReserva);
				session.setAttribute("form2Data",form2Data);
				request.getRequestDispatcher(getServletContext().getInitParameter("modifyReserveView")).forward(request, response);
				return;
			}else {
				ResponseBean bean= (ResponseBean)session.getAttribute("form2Data");
				if(bean == null) {
					request.setAttribute("response","fail2");
					request.getRequestDispatcher(getServletContext().getInitParameter("modifyReserveView")).forward(request, response);
					return;
				}
				
	
				Integer idPista = Integer.parseInt(SidPista);
				String respuesta="success";
				if(bean.getDif().equals(DificultadPista.ADULTOS)) {
					ReservaAdultos reserva = new ReservaAdultos( idUser,  bean.getFecha(), bean.getDuracion(),  idPista,  0f, 0f, bean.getIdReserva(), bean.getNumAdults());
					respuesta = ReservaHandler.getInstance().KnowMyFault(reserva);	
					resultado =  ReservaHandler.getInstance().modifyReserve(reserva);
					
				}else if(bean.getDif().equals(DificultadPista.INFANTIL)) {
					ReservaInfantil reserva = new ReservaInfantil( idUser,  bean.getFecha(), bean.getDuracion(),  idPista,  0f, 0f,bean.getIdReserva(), bean.getNumChilds());
					respuesta = ReservaHandler.getInstance().KnowMyFault(reserva);	 
					resultado = ReservaHandler.getInstance().modifyReserve(reserva);

				}else if(bean.getDif().equals(DificultadPista.FAMILIAR)) {
					ReservaFamiliar reserva = new ReservaFamiliar( idUser,  bean.getFecha(), bean.getDuracion(),  idPista,  0f, 0f,bean.getIdReserva(), bean.getNumAdults(), bean.getNumChilds());
					respuesta = ReservaHandler.getInstance().KnowMyFault(reserva);		
					resultado = ReservaHandler.getInstance().modifyReserve(reserva);
				}
				session.setAttribute("form2Data",null);
				String salida = (resultado) ? "success" : "fail";
				request.setAttribute("response",salida);
				request.setAttribute("error",respuesta);
				request.getRequestDispatcher(getServletContext().getInitParameter("modifyReserveView")).forward(request, response);
				return;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("response","fail");
			request.getRequestDispatcher(getServletContext().getInitParameter("modifyReserveView")).forward(request, response);
			return;
		}
	}

}
