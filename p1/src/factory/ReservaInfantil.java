package factory;
import java.time.LocalDateTime;
import entities.enums.DificultadPista;

//Es una reserva que realiza un adulto, pero en la que solo participan niños en una pista infantil.

public class ReservaInfantil extends ReservaAbstracta{
	
	public ReservaInfantil() {};
	
	//TODO (en todas las reservas) id y discount creo que no deberia de ir en el constructor, pero no me deja llamar a funciones sin poner super arriba del todo
	public ReservaInfantil(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numChilds) {
		super(idUser,  date,  time,  idPista,  price, discount,  id, 0,numChilds);
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