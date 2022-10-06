package factory;
import java.time.LocalDateTime;
import entities.enums.DificultadPista;

//Es una reserva que realiza un adulto, en la que solo participan adultos en una pista de ese tipo

public class ReservaAdultos extends ReservaAbstracta{
	
	public ReservaAdultos() {};
	
	public ReservaAdultos(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numAdults) {
		super(idUser,  date,  time,  idPista,  price, discount,  id, numAdults,0);
	}

	public DificultadPista getType(){
		return DificultadPista.ADULTOS;
	}

	public  boolean validate(){
		return (numAdults > 0 && numChilds == 0);
	}

	@Override
	public String toString() {
		return "ReservaAdultos [idUser=" + idUser + ", date=" + date + ", time=" + time + ", idPista=" + idPista
				+ ", price=" + price + ", discount=" + discount + ", numAdults=" + numAdults + ", id=" + id + "]";
	};


}