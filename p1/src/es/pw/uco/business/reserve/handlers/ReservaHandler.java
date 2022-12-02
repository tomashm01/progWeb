package es.pw.uco.business.reserve.handlers;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.ArrayList;
import es.pw.uco.business.circuit.handlers.CircuitHandler;
import es.pw.uco.business.circuit.models.Kart;
import es.pw.uco.business.circuit.models.Pista;
import es.pw.uco.business.reserve.dto.BonoDTO;
import es.pw.uco.business.reserve.dto.ReserveDTO;
import es.pw.uco.business.reserve.models.Bono;
import es.pw.uco.business.reserve.models.factory.ReservaAbstracta;
import es.pw.uco.business.reserve.models.factory.ReservaAdultos;
import es.pw.uco.business.reserve.models.factory.ReservaFamiliar;
import es.pw.uco.business.reserve.models.factory.ReservaInfantil;
import es.pw.uco.business.user.handlers.UsuarioHandler;
import es.pw.uco.business.user.models.Usuario;
import es.pw.uco.data.dao.BonoDAO;
import es.pw.uco.data.dao.ReserveDAO;

public class ReservaHandler {
	private static ReservaHandler instance = null;
	private static ReserveDAO daoReserva;
	private static BonoDAO daoBono;

	public static ReservaHandler getInstance() {
		if (ReservaHandler.instance == null) {
			ReservaHandler.instance = new ReservaHandler();
			daoReserva = new ReserveDAO();
			daoBono = new BonoDAO();
		}
		return ReservaHandler.instance;
	}

	/**
	 * Hacer reservas individuales: Las reservas deberán realizarse por un usuario
	 * registrado y en una pista que cumpla las condiciones de la reserva (número de
	 * participantes, tipo de pista, etc.). Si el usuario tiene más de 2 años de
	 * antigüedad, se le aplicará un 10% de rebaja en el precio de la reserva.
	 * 
	 * @param reserve
	 * @return boolean
	 */
	public boolean addReservaIndividual(ReservaAbstracta reserve) {

		if (!CircuitHandler.getInstance().existPista(reserve.getIdPista())) {
			System.out.println("El id de la pista no existe");
			return false;
		}
		if (!UsuarioHandler.getInstance().existEmail(reserve.getIdUser())) {
			System.out.println("El usuario que va a reservar no existe en la bd");
			return false;
		}
		Pista pista = CircuitHandler.getInstance().getPistaByID(reserve.getIdPista());
		Usuario user = UsuarioHandler.getInstance().getUserByEmail(reserve.getIdUser());

		if (!user.isMayorEdad()) {
			System.out.println("El responsble de la reserva no es mayor de edad.");
			return false;
		}
		if (!pista.isAvailable()) {
			System.out.println("La pista no est� disponible para reservas.");
			return false;
		}
		if (!pista.getDifficulty().equals(reserve.getType())) {
			System.out.println("La dificultad de la pista no coincide con la dificultad de la reserva.");
			return false;
		}
		if (!(pista.getMaxKarts() >= reserve.getNumPlayers())) {
			System.out.println("Hay m�s personas que el numero m�ximo permitido en la pista.");
			return false;
		}
		if ((reserve.getNumPlayers() > pista.consultarKartsDisponibles().size())) {
			System.out.println("No hay suficientes karts disponibles para todos los reservantes.");
			return false;
		}
		if (reserve.getNumPlayers() == 0) {
			System.out.println("El numero de integrantes de la reserva no puede ser cero.");
			return false;
		}
		if (!reserve.validate()) {
			System.out.println("Los integrantes de la reserva no est�n permitidos en el tipo de pista seleccionado.");
			return false;
		}

		if (reserve.getDate().isBefore(LocalDateTime.now())) {
			System.out.println("No se puede hacer una reserva en el pasado");
			return false;
		}
		if (reserve.getDate().isBefore(LocalDateTime.now().minus(24, ChronoUnit.HOURS))) {
			System.out.println("No se puede reservar a 24h o antes de la fecha de la reserva");
			return false;
		}
		for (ReservaAbstracta t1 : getReserveByPistaDay(reserve.getIdPista(), reserve.getDate().toLocalDate())) {
			LocalDateTime t11 = t1.getDate();
			LocalDateTime t12 = t1.getDate().plus(t1.getTime(), ChronoUnit.MINUTES);
			LocalDateTime t21 = reserve.getDate();
			LocalDateTime t22 = reserve.getDate().plus(reserve.getTime(), ChronoUnit.MINUTES);

			if (!((t12.isBefore(t21) && t12.isBefore(t22)) || (t11.isAfter(t21) && t11.isAfter(t22)))) {
				System.out.println("Ya hay una reserva en el intervalo que se quiere reservar, en esa misma pista.");
				return false;
			}
		}

		reserve.setPrice(calculatePrice(reserve.getTime()));
		float aux = (user.antiquity() > 2) ? 0.10f : 0f;
		reserve.setDiscount(aux);

		if (!daoReserva.insert(new ReserveDTO(reserve))) {
			return false;
		}
		int idReserva = daoReserva.getByPistaDate(reserve.getIdPista(), reserve.getDate());
		for (Kart it : pista.getKartsList()) {
			if (!daoReserva.pairKartReserve(idReserva, it.getId())) {
				daoReserva.delete(idReserva);
				return false;
			}
		}
		return true;
	}

	/**
	 * Hacer reservas dentro de un bono: Todas las sesiones del bono serán del mismo
	 * tipo (infantil, familiar o adultos), aunque no necesariamente con el mismo
	 * número de participantes ni duración siempre. Todas las reservas del bono se
	 * asocian a un mismo usuario, y contarán con un precio reducido (5% menos que
	 * el precio original según la duración) respecto al de la reserva individual.
	 * No se considerará la aplicación de la rebaja por antigüedad en ninguna de las
	 * reservas asociadas al bono. Cada bono tendrá 5 sesiones y una fecha de
	 * caducidad de un año desde la primera reserva.
	 * 
	 * @param reserve
	 * @return boolen
	 */

	public boolean addReservaBono(ReservaAbstracta reserve) {

		if (!CircuitHandler.getInstance().existPista(reserve.getIdPista())) {
			System.out.println("El id de la pista no existe");
			return false;
		}
		if (!UsuarioHandler.getInstance().existEmail(reserve.getIdUser())) {
			System.out.println("El usuario que va a reservar no existe en la bd");
			return false;
		}
		Pista pista = CircuitHandler.getInstance().getPistaByID(reserve.getIdPista());
		Usuario user = UsuarioHandler.getInstance().getUserByEmail(reserve.getIdUser());

		if (!user.isMayorEdad()) {
			System.out.println("El responsble de la reserva no es mayor de edad.");
			return false;
		}
		if (!pista.isAvailable()) {
			System.out.println("La pista no est� disponible para reservas.");
			return false;
		}
		if (!pista.getDifficulty().equals(reserve.getType())) {
			System.out.println("La dificultad de la pista no coincide con la dificultad de la reserva.");
			return false;
		}
		if (!(pista.getMaxKarts() >= reserve.getNumPlayers())) {
			System.out.println("Hay m�s personas que el numero m�ximo permitido en la pista.");
			return false;
		}
		if ((reserve.getNumPlayers() > pista.consultarKartsDisponibles().size())) {
			System.out.println("No hay suficientes karts disponibles para todos los reservantes.");
			return false;
		}
		if (reserve.getNumPlayers() == 0) {
			System.out.println("El numero de integrantes de la reserva no puede ser cero.");
			return false;
		}
		if (!reserve.validate()) {
			System.out.println("Los integrantes de la reserva no est�n permitidos en el tipo de pista seleccionado.");
			return false;
		}

		if (reserve.getDate().isBefore(LocalDateTime.now())) {
			System.out.println("No se puede hacer una reserva en el pasado");
			return false;
		}
		if (reserve.getDate().isBefore(LocalDateTime.now().minus(24, ChronoUnit.HOURS))) {
			System.out.println("No se puede reservar a 24h o antes de la fecha de la reserva");
			return false;
		}
		for (ReservaAbstracta t1 : getReserveByPistaDay(reserve.getIdPista(), reserve.getDate().toLocalDate())) {
			LocalDateTime t11 = t1.getDate();
			LocalDateTime t12 = t1.getDate().plus(t1.getTime(), ChronoUnit.MINUTES);
			LocalDateTime t21 = reserve.getDate();
			LocalDateTime t22 = reserve.getDate().plus(reserve.getTime(), ChronoUnit.MINUTES);

			if (!((t12.isBefore(t21) && t12.isBefore(t22)) || (t11.isAfter(t21) && t11.isAfter(t22)))) {
				System.out.println("Ya hay una reserva en el intervalo que se quiere reservar, en esa misma pista.");
				return false;
			}
		}
		reserve.setPrice(calculatePrice(reserve.getTime()));
		float aux = (user.antiquity() > 2) ? 0.10f : 0f;
		reserve.setDiscount(aux);

		if (!daoReserva.insert(new ReserveDTO(reserve))) {
			return false;
		}
		int idReserva = daoReserva.getByPistaDate(reserve.getIdPista(), reserve.getDate());
		for (Kart it : pista.getKartsList()) {
			if (!daoReserva.pairKartReserve(idReserva, it.getId())) {
				daoReserva.delete(idReserva);
				return false;
			}
		}
		int idBono = daoBono.getFreeBono(reserve.getIdUser(), reserve.getType().toString());

		if (idBono == -1) {
			idBono = daoBono.insertGettingId(new BonoDTO(null, LocalDate.now().plus(1, ChronoUnit.YEARS)));
		}
		int posicion = daoBono.getNextPositicon(idBono);
		if(posicion == -1) {
			posicion=0;
		}
		daoBono.pairReserveBono(idBono, idReserva,posicion);
		return true;
	}

	/**
	 * El precio de una reserva se establece en funci�n de la duraci�n de esta: 60
	 * minutos (20eur), 90 minutos (30eur), 120 minutos (40eur).
	 * 
	 * @param time
	 * @return
	 */

	public int calculatePrice(int time) {
		return time / 3;
	}

	public ArrayList<Bono> getAllBonosByIDUser(Integer idUser) {
		ArrayList<Bono> bonoList = new ArrayList<Bono>();
		for (BonoDTO it : daoBono.getAllBonoByUser(idUser)) {
			it.setReserves(daoBono.getAllIdByBono(it.getId()));
			bonoList.add(new Bono(it));
		}
		return bonoList;
	}

	/**
	 * Las reservas pueden realizarse, modificarse y/o cancelarse hasta 24h antes de
	 * la hora de inicio
	 * 
	 * @param reserve
	 * @return
	 */

	public boolean modifyReserve(ReservaAbstracta reserve) {
		if (reserve.getDate().isAfter(LocalDateTime.now().minus(24, ChronoUnit.HOURS))) {
			System.out.println("No se puede modificar a 24h o antes de la fecha de la reserva");
			return false;
		}
		ReserveDTO aux = daoReserva.get(reserve.getId());
		if (aux == null) {
			System.out.println("No se ha encontrado la reserva a modificar (Id no encontrado)");
			return false;
		}
		return daoReserva.update(new ReserveDTO(reserve));
	}

	/**
	 * Elimina bono de la lista de bonos
	 * 
	 * @param idBono
	 * @return boolean
	 */
	public boolean removeBono(Integer idBono) {
		return daoBono.delete(idBono);
	}

	/**
	 * Elimina reserva de la lista
	 * 
	 * @param idReserve
	 * @return boolean
	 */
	public boolean removeReserve(Integer idReserve) {
		return daoReserva.delete(idReserve);
	}

	/**
	 * El gestor debe permitir consultar el número de reservas futuras, esto es,
	 * para cualquier fecha posterior a la actual.
	 * 
	 * @return ArrayList<ReservaAbstracta>
	 */
	public ArrayList<ReservaAbstracta> getFutureReserves() {

		ArrayList<ReservaAbstracta> reservesFiltered = new ArrayList<ReservaAbstracta>();
		for (ReservaAbstracta reserva : getAllReserves()) {
			if (reserva.getDate().toLocalDate().isAfter(LocalDate.now()))
				reservesFiltered.add(reserva);
		}

		return reservesFiltered;
	}

	/**
	 * El gestor debe permitir consultar las reservas que existen para un día y una
	 * pista concretos
	 * 
	 * @param idPista
	 * @param day
	 * @return
	 */
	public ArrayList<ReservaAbstracta> getReserveByPistaDay(Integer idPista, LocalDate fecha) {
		ArrayList<ReservaAbstracta> reservesFiltered = new ArrayList<ReservaAbstracta>();

		for (ReservaAbstracta reserva : ReservaHandler.getInstance().getAllReserves()) {
			if (reserva.getDate().toLocalDate().equals(fecha) && reserva.getIdPista().equals(idPista))
				reservesFiltered.add(reserva);
		}

		return reservesFiltered;
	}

	/**
	 * Devuelve ReservaAbstracta buscando por idReserva
	 * 
	 * @param id
	 * @return ReservaAbstracta
	 */
	public ReservaAbstracta getReserveByID(Integer id) {
		ReserveDTO aux = daoReserva.get(id);
		if (aux == null) {
			return null;
		} else if (aux.getTipo().equals("FAMILIAR")) {
			return new ReservaFamiliar(aux);
		} else if (aux.getTipo().equals("ADULTOS")) {
			return new ReservaAdultos(aux);
		} else {
			return new ReservaInfantil(aux);
		}
	}

	/**
	 * Devuelve índice del bono
	 * 
	 * @param id
	 * @return int
	 */
	public int getIndexBono(int idBono) {
		int contador = 0;
		for (Bono bono : getAllBonos()) {
			if (bono.getId() == idBono) {
				return contador;
			}
			contador++;
		}
		return -1;
	}

	/**
	 * Devuelve índice de la resereva
	 * 
	 * @param id
	 * @return int
	 */
	public int getIndexReserve(Integer id) {
		int contador = 0;
		for (ReservaAbstracta reserve : getAllReserves()) {
			contador++;
			if (reserve.getId().equals(id))
				return contador;
		}
		return -1;
	}

	/**
	 * Devuelve reserva buscando por idPista
	 * 
	 * @param id
	 * @return ArrayList<ReservaAbstracta>
	 */
	public ArrayList<ReservaAbstracta> getReserveByPista(Integer idPista) {
		ArrayList<ReservaAbstracta> reserversFiltered = new ArrayList<ReservaAbstracta>();
		for (ReserveDTO it : daoReserva.getAllReservesByPista(idPista)) {
			if (it.getTipo().equals("FAMILIAR")) {
				reserversFiltered.add(new ReservaFamiliar(it));
			} else if (it.getTipo().equals("ADULTOS")) {
				reserversFiltered.add(new ReservaAdultos(it));
			} else {
				reserversFiltered.add(new ReservaInfantil(it));
			}
		}
		return reserversFiltered;
	}

	/**
	 * Devuelve reserva buscando por idUser
	 * 
	 * @param id
	 * @return ArrayList<ReservaAbstracta>
	 */
	public ArrayList<ReservaAbstracta> getReserveByUser(String idUser) {
		ArrayList<ReservaAbstracta> reserversFiltered = new ArrayList<ReservaAbstracta>();
		for (ReserveDTO it : daoReserva.getAllReservesByUser(idUser)) {
			if (it.getTipo().equals("FAMILIAR")) {
				reserversFiltered.add(new ReservaFamiliar(it));
			} else if (it.getTipo().equals("ADULTOS")) {
				reserversFiltered.add(new ReservaAdultos(it));
			} else {
				reserversFiltered.add(new ReservaInfantil(it));
			}
		}
		return reserversFiltered;
	}

	/**
	 * Devuelve arrayList de reservasAbstractas
	 * 
	 * @return ArrayList<ReservaAbstracta>
	 */
	public ArrayList<ReservaAbstracta> getAllReserves() {
		ArrayList<ReservaAbstracta> reserversFiltered = new ArrayList<ReservaAbstracta>();
		for (ReserveDTO it : daoReserva.getAll()) {
			if (it.getTipo().equals("FAMILIAR")) {
				reserversFiltered.add(new ReservaFamiliar(it));
			} else if (it.getTipo().equals("ADULTOS")) {
				reserversFiltered.add(new ReservaAdultos(it));
			} else {
				reserversFiltered.add(new ReservaInfantil(it));
			}
		}
		return reserversFiltered;
	}

	/**
	 * Devuelve arrayList de bonos
	 * 
	 * @return ArrayList<Bono>
	 */
	public ArrayList<Bono> getAllBonos() {
		ArrayList<Bono> bonoList = new ArrayList<Bono>();
		for (BonoDTO it : daoBono.getAll()) {
			it.setReserves(daoBono.getAllIdByBono(it.getId()));
			bonoList.add(new Bono(it));
		}
		return bonoList;
	}

	/**
	 * Imprime las reservas
	 */
	public void printAllReservesList() {
		for (ReservaAbstracta res : getAllReserves()) {
			System.out.println(res.toString());
		}
	}

	/**
	 * Imprime los bonos
	 */
	public void printAllBonosList() {
		for (Bono bono : getAllBonos()) {
			System.out.println(bono.toString());
		}
	}


}