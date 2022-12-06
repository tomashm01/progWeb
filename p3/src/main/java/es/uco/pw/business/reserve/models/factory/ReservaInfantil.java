package es.uco.pw.business.reserve.models.factory;
import java.time.LocalDateTime;

import es.uco.pw.business.enums.DificultadPista;
import es.uco.pw.business.reserve.dto.ReserveDTO;

//Es una reserva que realiza un adulto, pero en la que solo participan niños en una pista infantil.

public class ReservaInfantil extends ReservaAbstracta {
	

	private static final long serialVersionUID = 1L;

	private Integer numChilds;
	
	public ReservaInfantil() {};
	
	public ReservaInfantil(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numChilds) {
		super(idUser,  date,  time,  idPista,  price, discount,  id);
		this.numChilds = numChilds;
	}
	
	public ReservaInfantil(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount,Integer numChilds) {
		super(idUser,  date,  time,  idPista,  price, discount);
		this.numChilds = numChilds;
	}

	public ReservaInfantil(ReserveDTO dto) {
		this.idUser = dto.getIdUser();
		this.date = dto.getDate();
		this.time = dto.getTime();
		this.idPista = dto.getIdPista();
		this.price = dto.getPrice();
		this.discount = dto.getDiscount();
		this.id = dto.getId();
		this.numChilds = dto.getNumMenores();
	}

	public DificultadPista getType(){
		return DificultadPista.INFANTIL;
	}

	public  boolean validate(){
		return ( numChilds > 0);
	}

	public Integer getNumPlayers() {
		return numChilds;
	}
	@Override
	public Integer getNumChilds() {
		return numChilds;
	}

	public void setNumChilds(Integer numChilds) {
		this.numChilds = numChilds;
	}

	@Override
	public String toString() {
		return "ReservaInfantil [idUser=" + idUser + ", date= " + date + ", time=" + time + ", idPista=" + idPista
				+ ", price=" + price + ", discount=" + discount + ", numChilds=" + numChilds + ", id=" + id + "]";
	}

}