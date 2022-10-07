package handlers;

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

import entities.Bono;
import entities.Pista;
import entities.Usuario;
import factory.ReservaAbstracta;

public class ReservaHandler {
	public static String reserves_file;
	public static String bono_file;
	private static ArrayList<Bono> bonoList = new ArrayList<Bono>();
	private static ArrayList<ReservaAbstracta> reservesList = new ArrayList<ReservaAbstracta>();
	private static ReservaHandler instance = null;

	public static ReservaHandler getInstance() {
		if (ReservaHandler.instance == null) {
			ReservaHandler.instance = new ReservaHandler();
			loadFilesPath();
			loadReserveFile();
			loadReserveBonoFile();
		}
		return ReservaHandler.instance;
	}


	/**
	 * Las reservas deber√°n realizarse por un usuario registrado y en una pista que
	 * cumpla las condiciones de la reserva (n√∫mero de participantes, tipo de
	 * pista, etc.). Si el usuario tiene m√°s de 2 a√±os de antig√ºedad, se le
	 * aplicar√° un 10% de rebaja en el precio de la reserva.
	 * 
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

		if (!UsuarioHandler.getInstance().getUserByID(reserve.getIdUser()).isMayorEdad()) {
			System.out.println("El responsble de la reserva no es mayor de edad.");
			return false;
		}
		if (!pista.isAvailable()) {
			System.out.println("La pista no est· disponible para reservas.");
			return false;
		}
		if (!pista.getDifficulty().equals(reserve.getType())) {
			System.out.println("La dificultad de la pista no coincide con la dificultad de la reserva.");
			return false;
		}
		if (!(pista.getMaxKarts() >= reserve.getNumPlayers())) {
			System.out.println("Hay m·s personas que el numero m·ximo permitido en la pista.");
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
			System.out.println("Los integrantes de la reserva no est·n permitidos en el tipo de pista seleccionado.");
			return false;
		}

		if (reserve.getDate().isBefore(LocalDateTime.now())) {
			System.out.println("No se puede hacer una reserva en el pasado");
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
		reservesList.add(reserve);

		return true;

	}

	/**
	 * Hacer reservas dentro de un bono: Todas las sesiones del bono ser·n del mismo
	 * tipo (infantil, familiar o adultos), aunque no necesariamente con el mismo
	 * n˙mero de participantes ni duraciÛn siempre. Todas las reservas del bono se
	 * asocian a un mismo usuario, y contar·n con un precio reducido (5% menos que
	 * el precio original seg˙n la duraciÛn) respecto al de la reserva individual.
	 * No se considerar· la aplicaciÛn de la rebaja por antig¸edad en ninguna de las
	 * reservas asociadas al bono. Cada bono tendr· 5 sesiones y una fecha de
	 * caducidad de un aÒo desde la primera reserva.
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
		Usuario user = UsuarioHandler.getInstance().getUserByID(reserve.getIdUser());

		if (!UsuarioHandler.getInstance().getUserByID(reserve.getIdUser()).isMayorEdad()) {
			System.out.println("El responsble de la reserva no es mayor de edad.");
			return false;
		}
		if (!pista.isAvailable()) {
			System.out.println("La pista no est· disponible para reservas.");
			return false;
		}
		if (!pista.getDifficulty().equals(reserve.getType())) {
			System.out.println("La dificultad de la pista no coincide con la dificultad de la reserva.");
			return false;
		}
		if (!(pista.getMaxKarts() >= reserve.getNumPlayers())) {
			System.out.println("Hay m·s personas que el numero m·ximo permitido en la pista.");
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
			System.out.println("Los integrantes de la reserva no est·n permitidos en el tipo de pista seleccionado.");
			return false;
		}

		if (reserve.getDate().isBefore(LocalDateTime.now())) {
			System.out.println("No se puede hacer una reserva en el pasado");
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
		
		//idUser mismo en todos
		for(int i=0;i<bonoList.size();i++) {
			//la id de reserva de la primera reserva del bono
			Integer idIt = ReservaHandler.getInstance().getAllBonos().get(i).getBonoList().get(0);
			LocalDate expirationDateBono=ReservaHandler.getInstance().getAllBonos().get(i).getExpirationDate();
			
			if(	(	ReservaHandler.getInstance().getReserveByID(idIt).getIdUser().equals(reserve.getIdUser()) 	) && 	//comprueba que el bono es del usuario
				(	ReservaHandler.getInstance().getReserveByID(idIt).getType().equals(reserve.getType())		) &&		//comprueba que el bono es del tipo igual que la reserva
				(	ReservaHandler.getInstance().getAllBonos().get(i).getBonoList().size() < 5					) &&		//comprueba que el bono tiene hueco
				(	expirationDateBono.isBefore(LocalDate.now())												)
			){				
						
				ReservaHandler.getInstance().getAllBonos().get(i).getBonoList().add(reserve.getId());
				return true;
			}
		}
		
		Bono bono=new Bono(reserve.getId());
		bonoList.add(bono);
		reservesList.add(reserve);
		return true;
	}

	/**
	 * El precio de una reserva se establece en funciÛn de la duraciÛn de esta: 60
	 * minutos (20eur), 90 minutos (30eur), 120 minutos (40eur).
	 * 
	 * @param time
	 * @return
	 */

	public int calculatePrice(int time) {
		return time / 3;
	}

	/**
	 * Las reservas pueden realizarse, modificarse y/o cancelarse hasta 24h antes de
	 * la hora de inicio
	 * 
	 * @return
	 */

	public boolean modifyReserve(int idReserva, int opcion) {
		// TODO modificar reserva

		return true;
	}

	/**
	 * El gestor debe permitir consultar el n√∫mero de reservas futuras, esto es,
	 * para cualquier fecha posterior a la actual.
	 * 
	 * @return
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
	 * El gestor debe permitir consultar las reservas que existen para un d√≠a y una
	 * pista concretos
	 * 
	 * @param idPista
	 * @param day
	 * @return
	 */
	public ArrayList<ReservaAbstracta> getReserveByPistaDay(Integer idPista, LocalDate fecha) {
		ArrayList<ReservaAbstracta> reservesFiltered = new ArrayList<ReservaAbstracta>();

		for (ReservaAbstracta reserva : ReservaHandler.reservesList) {
			if (reserva.getDate().toLocalDate().equals(fecha) && reserva.getIdPista().equals(idPista))
				reservesFiltered.add(reserva);
		}

		return reservesFiltered;
	}

	public ReservaAbstracta getReserveByID(Integer id) {
		for (ReservaAbstracta reserve : reservesList) {
			if (reserve.getId().equals(id))
				return reserve;
		}
		return null;
	}

	public int getIndex(Integer id) {
		int contador = 0;
		for (ReservaAbstracta reserve : reservesList) {
			contador++;
			if (reserve.getId().equals(id))
				return contador;
		}
		return -1;
	}

	public ArrayList<ReservaAbstracta> getReserveByPista(Integer idPista) {
		ArrayList<ReservaAbstracta> reserversFiltered = new ArrayList<ReservaAbstracta>();
		for (ReservaAbstracta reserve : reservesList) {
			if (reserve.getIdPista().equals(idPista))
				reserversFiltered.add(reserve);
		}
		return reserversFiltered;
	}

	public ArrayList<ReservaAbstracta> getReserveByUser(Integer idUser) {
		ArrayList<ReservaAbstracta> reserversFiltered = new ArrayList<ReservaAbstracta>();
		for (ReservaAbstracta reserve : reservesList) {
			if (reserve.getIdUser().equals(idUser))
				reserversFiltered.add(reserve);
		}
		return null;
	}

	public ArrayList<ReservaAbstracta> getAllReserves() {
		return reservesList;
	}
	public ArrayList<Bono> getAllBonos(){
		return bonoList;
	}

	@SuppressWarnings("unchecked")
	public static void loadReserveFile() {
		try {
			FileInputStream fis = new FileInputStream(reserves_file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			if(reserves_file.length()!=0) {
				reservesList = (ArrayList<ReservaAbstracta>) ois.readObject();
			}


			ois.close();
			fis.close();
		} catch(FileNotFoundException e){
			System.out.println("El fichero "+reserves_file+" no existe. No hay lista de reservas cargadas.");
		}catch(EOFException e){
			System.out.println("El fichero "+reserves_file+" esta vacio.");
		}catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void loadReserveBonoFile() {
		try {
			FileInputStream fis = new FileInputStream(bono_file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			if(bono_file.length()!=0) {
				bonoList = (ArrayList<Bono>) ois.readObject();
			}


			ois.close();
			fis.close();
		} catch(FileNotFoundException e){
			System.out.println("El fichero "+bono_file+" no existe. No hay lista de reservas cargadas.");
		}catch(EOFException e){
			System.out.println("El fichero "+bono_file+" esta vacio.");
		}catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


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