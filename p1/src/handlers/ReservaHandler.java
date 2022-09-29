package handlers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

import entities.Pista;
import entities.Usuario;
import factory.ReservaAbstracta;


public class ReservaHandler {
	
	private static ArrayList<ReservaAbstracta> reservesList=new ArrayList<ReservaAbstracta>();
	private static ReservaHandler instance = null;
	
	public static ReservaHandler getInstance() {
        if(ReservaHandler.instance == null) {
        	ReservaHandler.instance = new ReservaHandler();
        }
        return ReservaHandler.instance;
    }
	
	/**
	 * Las reservas deberán realizarse por un usuario
	 * registrado y en una pista que cumpla las condiciones de la reserva (número de
	 * participantes, tipo de pista, etc.). Si el usuario tiene más de 2 años de antigüedad,
	 * se le aplicará un 10% de rebaja en el precio de la reserva.
	 * @return boolean
	 */
	public boolean addReservaIndividual(ReservaAbstracta reserve) {

		Pista pista=CircuitHandler.getInstance().getPistaByID(reserve.getIdPista());
		Usuario user=UsuarioHandler.getInstance().getUserByID(reserve.getId());
		
		if( ! UsuarioHandler.getInstance().existUser(reserve.getId()) 					&&
			! pista.isAvailable() 														&& 
			! pista.getDifficulty().equals(reserve.type())								&& 
			! (pista.getMaxKarts() >= reserve.getPlayers().size())						&&
			! (reserve.getPlayers().size() <= pista.consultarKartsDisponibles().size())	&&
			! reserve.validate() 														&& 
			reserve.getPlayers().size()==0
		) return false;
		
		/* kart1.idUser=idUser1;
		
		for(int i=0;i < reserve.getPlayers().size();i++){
			Integer id = reserve.getPlayers().get(i).getId();
			
			
			
		}
		*/
		reserve.setDiscount( (user.antiquity() > 2) ?  0.10f : 0f);
		reservesList.add(reserve);

		return true;
        
	}
	
	/**
	 * Todas las sesiones del bono serán del mismo 
	 * tipo (infantil, familiar o adultos), aunque no necesariamente con el mismo número
	 * de participantes ni duración siempre. Todas las reservas del bono se asocian a un
	 * mismo usuario, y contarán con un precio reducido (5% menos que el precio original
	 * según la duración) respecto al de la reserva individual. No se considerará la
	 * aplicación de la rebaja por antigüedad en ninguna de las reservas asociadas al bono.
	 * Cada bono tendrá 5 sesiones y una fecha de caducidad de un año desde la primera
	 * reserva. 
	 */
	
	public boolean addReservaBono() {
		return true;
	}
	
	/**
	 * El precio de una reserva se establece en función de la duración de esta: 60 minutos
	 * (20€), 90 minutos (30€), 120 minutos (40€).
	 * @param time
	 * @return
	 */
	
	public int calculatePrice(int time) {
		return time/3;
	}
	
	/**
	 * Las reservas pueden realizarse, modificarse y/o cancelarse hasta 24h antes de la
	 * hora de inicio
	 * @return
	 */
	public boolean modifyReserve(int idReserva,int opcion) {
		ReservaAbstracta reserve= getReserveByID(idReserva);
		LocalDate date=LocalDateTime.now().toLocalDate();
		Period period = Period.between(reserve.getDate(),date);
		
		return true;
	}
	
	/**
	 * El gestor debe permitir consultar el número de reservas futuras, esto es, para
	 * cualquier fecha posterior a la actual.
	 * @return
	 */
	public ArrayList<ReservaAbstracta> getFutureReserves(){
		
		ArrayList<ReservaAbstracta> reservesFiltered=new ArrayList<ReservaAbstracta>();
		for(ReservaAbstracta reserva:ReservaHandler.reservesList) {
			if(reserva.getDate().isAfter(LocalDate.now())) reservesFiltered.add(reserva);
		}
		
		return reservesFiltered;
	}
	
	
	/**
	 * El gestor debe permitir consultar las reservas que existen para un día y una pista
	 * concretos
	 * @param idPista
	 * @param day
	 * @return
	 */
	public ArrayList<ReservaAbstracta> getReserveByPistaDay(Integer idPista,LocalDate fecha){
		ArrayList<ReservaAbstracta> reservesFiltered=new ArrayList<ReservaAbstracta>();
		
		for(ReservaAbstracta reserva:ReservaHandler.reservesList) {
			if(reserva.getDate().equals(fecha) && reserva.getIdPista().equals(idPista)) reservesFiltered.add(reserva);
		}
		
		return reservesFiltered;
	}
	
	public ReservaAbstracta getReserveByID(Integer id) {
		for(ReservaAbstracta reserve:reservesList) {
			if(reserve.getId().equals(id)) return reserve;
		}
		return null;
	}
	
	public int getIndex(Integer id) {
		int contador=0;
		for(ReservaAbstracta reserve:reservesList) {
			contador++;
			if(reserve.getId().equals(id)) return contador;
		}
		return -1;
	}

	public ArrayList<ReservaAbstracta> getReserveByPista(Integer idPista){
		ArrayList<ReservaAbstracta> reserversFiltered= new ArrayList<ReservaAbstracta>();
		for(ReservaAbstracta reserve:reservesList) {
			if(reserve.getIdPista().equals(idPista)) reserversFiltered.add(reserve);
		}
		return reserversFiltered;
	}
	
	public ArrayList<ReservaAbstracta> getReserveByUser(Integer idUser){
		ArrayList<ReservaAbstracta> reserversFiltered =new ArrayList<ReservaAbstracta>();
		for(ReservaAbstracta reserve:reservesList) {
			if(reserve.getIdUser().equals(idUser))  reserversFiltered.add(reserve);
		}
		return null;
	}
}