package factory;
import java.time.LocalDateTime;
import entities.enums.DificultadPista;

//Es una reserva que realiza un adulto, en la que participan tanto adultos como niños en una pista de tipo de familiar

public class ReservaFamiliar extends ReservaAbstracta{
	
	public ReservaFamiliar() {};
	
	
	public ReservaFamiliar(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numAdults,Integer numChilds) {
		super(idUser,  date,  time,  idPista,  price, discount,  id, numAdults,numChilds);
	}

	public DificultadPista getType(){
		return DificultadPista.FAMILIAR;
	}

	public  boolean validate(){
		return (numAdults > 0);
	}

	@Override
	public String toString() {
		return "ReservaFamiliar [idUser=" + idUser + ", date=" + date + ", time=" + time + ", idPista=" + idPista
				+ ", price=" + price + ", discount=" + discount + ", numAdults=" + numAdults + ", numChilds="
				+ numChilds + ", id=" + id + "]";
	};


	
}


