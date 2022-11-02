package es.pw.uco.business.reserve.handlers;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import es.pw.uco.business.circuit.handlers.CircuitHandler;
import es.pw.uco.business.circuit.models.Pista;
import es.pw.uco.business.enums.DificultadPista;
import es.pw.uco.business.reserve.dto.ReserveDTO;
import es.pw.uco.business.reserve.models.Bono;
import es.pw.uco.business.reserve.models.factory.ReservaAbstracta;
import es.pw.uco.business.reserve.models.factory.ReservaAdultos;
import es.pw.uco.business.reserve.models.factory.ReservaFamiliar;
import es.pw.uco.business.reserve.models.factory.ReservaInfantil;
import es.pw.uco.business.user.dto.UserDTO;
import es.pw.uco.business.user.handlers.UsuarioHandler;
import es.pw.uco.business.user.models.Usuario;
import es.pw.uco.data.dao.ReserveDAO;

public class ReservaHandler {
	public static String reserves_file;
	public static String bono_file;
	private static ArrayList<Bono> bonoList = new ArrayList<Bono>();
	private static ArrayList<ReservaAbstracta> reservesList = new ArrayList<ReservaAbstracta>();
	private static ReservaHandler instance = null;
	private static ReserveDAO dao;

	public static ReservaHandler getInstance() {
		if (ReservaHandler.instance == null) {
			ReservaHandler.instance = new ReservaHandler();
			dao = new ReserveDAO();
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
		if (!UsuarioHandler.getInstance().existUser(reserve.getIdUser())) {
			System.out.println("El usuario que va a reservar no existe en la bd");
			return false;
		}

		Pista pista = CircuitHandler.getInstance().getPistaByID(reserve.getIdPista());
		Usuario user = UsuarioHandler.getInstance().getUserByID(reserve.getIdUser());

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
		// reservesList.add(reserve);
		dao.insert(new ReserveDTO(reserve));
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
		if (!UsuarioHandler.getInstance().existUser(reserve.getIdUser())) {
			System.out.println("El usuario que va a reservar no existe en la bd");
			return false;
		}

		Pista pista = CircuitHandler.getInstance().getPistaByID(reserve.getIdPista());

		if (!UsuarioHandler.getInstance().getUserByID(reserve.getIdUser()).isMayorEdad()) {
			System.out.println("El responsble de la reserva no es mayor de edad.");
			return false;
		}
		if (!pista.isAvailable()) {
			System.out.println("La pista no está disponible para reservas.");
			return false;
		}
		if (!pista.getDifficulty().equals(reserve.getType())) {
			System.out.println("La dificultad de la pista no coincide con la dificultad de la reserva.");
			return false;
		}
		if (!(pista.getMaxKarts() >= reserve.getNumPlayers())) {
			System.out.println("Hay más personas que el numero máximo permitido en la pista.");
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

		reserve.setDiscount(0.05f);
		reserve.setPrice(calculatePrice(reserve.getTime()));

		// idUser mismo en todos
		for (int i = 0; i < bonoList.size(); i++) {
			// la id de reserva de la primera reserva del bono
			Integer idIt = ReservaHandler.getInstance().getAllBonos().get(i).getBonoList().get(0);
			LocalDate expirationDateBono = ReservaHandler.getInstance().getAllBonos().get(i).getExpirationDate();

			if ((ReservaHandler.getInstance().getReserveByID(idIt).getIdUser().equals(reserve.getIdUser())) && 
					(ReservaHandler.getInstance().getReserveByID(idIt).getType().equals(reserve.getType())) && 
					(ReservaHandler.getInstance().getAllBonos().get(i).getBonoList().size() < 5) && 
					(expirationDateBono.isBefore(LocalDate.now()))) {

				ReservaHandler.getInstance().getAllBonos().get(i).getBonoList().add(reserve.getId());
				return true;
			}
		}

		Bono bono = new Bono(reserve.getId());
		bonoList.add(bono);
		// reservesList.add(reserve);
		dao.insert(new ReserveDTO(reserve));
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
		ArrayList<Bono> bono = new ArrayList<Bono>();
		// TODO:
		for (int i = 0; i < bonoList.size(); i++) {
			if (ReservaHandler.getInstance().getReserveByID(bonoList.get(i).getBonoList().get(0)).getIdUser()
					.equals(idUser)) {
				bono.add(new Bono(
						ReservaHandler.getInstance().getReserveByID(bonoList.get(i).getBonoList().get(0)).getId()));
			}
		}
		return bono;
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
		ReserveDTO aux = dao.get(reserve.getId());
		if(aux == null) {
			System.out.println("No se ha encontrado la reserva a modificar (Id no encontrado)");
			return false;
		}
		dao.update(new ReserveDTO(reserve));
		return true;
			
	}

	/**
	 * Elimina bono de la lista de bonos
	 * 
	 * @param idBono
	 * @return boolean
	 */
	public boolean removeBono(Integer idBono) {
		int i = ReservaHandler.getInstance().getIndexBono(idBono);
		return (bonoList.remove(i) != null);
	}

	/**
	 * Elimina reserva de la lista
	 * 
	 * @param idReserve
	 * @return boolean
	 */
	public boolean removeReserve(Integer idReserve) {
		return dao.delete(idReserve);
	}

	/**
	 * El gestor debe permitir consultar el número de reservas futuras, esto es,
	 * para cualquier fecha posterior a la actual.
	 * 
	 * @return ArrayList<ReservaAbstracta>
	 */
	public ArrayList<ReservaAbstracta> getFutureReserves() {

		ArrayList<ReservaAbstracta> reservesFiltered = new ArrayList<ReservaAbstracta>();
		for (ReservaAbstracta reserva : ReservaHandler.reservesList) {
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
		ReserveDTO aux = dao.get(id);
		if(aux==null) {
			return null;
		}
		else if(aux.getTipo().equals("FAMILIAR")) {
			return new ReservaFamiliar(aux);
		}else if(aux.getTipo().equals("ADULTOS")) {
			return new ReservaAdultos(aux);
		}
		else {
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
		for (Bono bono : bonoList) {
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
		for (ReservaAbstracta reserve : reservesList) {
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
		for (ReservaAbstracta reserve : reservesList) {
			if (reserve.getIdPista().equals(idPista))
				reserversFiltered.add(reserve);
		}
		return reserversFiltered;
	}

	/**
	 * Devuelve reserva buscando por idUser
	 * 
	 * @param id
	 * @return ArrayList<ReservaAbstracta>
	 */
	public ArrayList<ReservaAbstracta> getReserveByUser(Integer idUser) {
		ArrayList<ReservaAbstracta> reserversFiltered = new ArrayList<ReservaAbstracta>();
		for (ReservaAbstracta reserve : ReservaHandler.getInstance().getAllReserves()) {
			if (reserve.getIdUser().equals(idUser))
				reserversFiltered.add(reserve);
		}
		return reserversFiltered;
	}

	/**
	 * Devuelve arrayList de reservasAbstractas
	 * 
	 * @return ArrayList<ReservaAbstracta>
	 */
	public ArrayList<ReservaAbstracta> getAllReserves() {
		reservesList = new ArrayList<ReservaAbstracta>();
		for(ReserveDTO it : dao.getAll()) {
			if(it.getTipo().equals("FAMILIAR")) {
				reservesList.add(new ReservaFamiliar(it));
			}else if(it.getTipo().equals("ADULTOS")) {
				reservesList.add(new ReservaAdultos(it));
			}
			else {
				reservesList.add(new ReservaInfantil(it));
			}
		}
		return reservesList;
	}

	/**
	 * Devuelve arrayList de bonos
	 * 
	 * @return ArrayList<Bono>
	 */
	public ArrayList<Bono> getAllBonos() {
		return bonoList;
	}

	/**
	 * Carga fichero de reservas
	 */
	@SuppressWarnings("unchecked")
	public static void loadReserveFile() {
		try {
			FileInputStream fis = new FileInputStream(reserves_file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			if (reserves_file.length() != 0) {
				reservesList = (ArrayList<ReservaAbstracta>) ois.readObject();
			}
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero " + reserves_file + " no existe. No hay lista de reservas cargadas.");
		} catch (EOFException e) {
			System.out.println("El fichero " + reserves_file + " esta vacio.");
		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga fichero de bonos
	 */
	@SuppressWarnings("unchecked")
	public static void loadReserveBonoFile() {
		try {
			FileInputStream fis = new FileInputStream(bono_file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			if (bono_file.length() != 0) {
				bonoList = (ArrayList<Bono>) ois.readObject();
			}
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero " + bono_file + " no existe. No hay lista de reservas cargadas.");
		} catch (EOFException e) {
			System.out.println("El fichero " + bono_file + " esta vacio.");
		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Imprime las reservas
	 */
	public void printAllReservesList() {
		for (ReservaAbstracta res : reservesList) {
			System.out.println(res.toString());
		}
	}

	/**
	 * Imprime los bonos
	 */
	public void printAllBonosList() {
		for (Bono bono : bonoList) {
			System.out.println(bono.toString());
		}
	}

	/**
	 * Carga los paths de reservas y bonos
	 */

	public static void loadFilesPath() {
		Properties prop = new Properties();
		String filename = "src/data.properties";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);

			String path = "datos/";
			reserves_file = path + prop.getProperty("reserves_file");
			bono_file = path + prop.getProperty("bono_file");

			// Captura de excepciones
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se ha encontrado el fichero \"" + filename + "\"");
		} catch (IOException e) {
			System.out.println("ERROR: No se ha podido leer el fichero");
		}
	}
}