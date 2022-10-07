package factory;
import java.time.LocalDateTime;
import entities.enums.DificultadPista;

//Es una reserva que realiza un adulto, pero en la que solo participan niños en una pista infantil.

public class ReservaInfantil extends ReservaAbstracta {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReservaInfantil() {};
	
	public ReservaInfantil(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numChilds) {
		super(idUser,  date,  time,  idPista,  price, discount,  id, 0,numChilds);
	}
	
	public ReservaInfantil(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount,Integer numChilds) {
		super(idUser,  date,  time,  idPista,  price, discount, 0,numChilds);
	}


	public DificultadPista getType(){
		return DificultadPista.INFANTIL;
	}

	public  boolean validate(){
		return (numAdults == 0 && numChilds > 0);
	}

	@Override
	public String toString() {
		return "ReservaInfantil [idUser=" + idUser + ", date=" + date + ", time=" + time + ", idPista=" + idPista
				+ ", price=" + price + ", discount=" + discount + ", numChilds=" + numChilds + ", id=" + id + "]";
	}

}