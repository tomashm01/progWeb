package es.uco.pw.business.reserve.models.factory;
import java.time.LocalDateTime;

import es.uco.pw.business.enums.DificultadPista;
import es.uco.pw.business.reserve.dto.ReserveDTO;

//Es una reserva que realiza un adulto, en la que solo participan adultos en una pista de ese tipo

public class ReservaAdultos extends ReservaAbstracta{
	

	private static final long serialVersionUID = 1L;

	private Integer numAdults;
	
	public ReservaAdultos() {};
	
	public ReservaAdultos(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numAdults) {
		super(idUser,  date,  time,  idPista,  price, discount,  id);
		this.numAdults = numAdults;
	}
	
	public ReservaAdultos(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount,Integer numAdults) {
		super(idUser,  date,  time,  idPista,  price, discount);
		this.numAdults = numAdults;
	}
	
	public ReservaAdultos(ReserveDTO dto) {
		this.idUser = dto.getIdUser();
		this.date = dto.getDate();
		this.time = dto.getTime();
		this.idPista = dto.getIdPista();
		this.price = dto.getPrice();
		this.discount = dto.getDiscount();
		this.id = dto.getId();
		this.numAdults = dto.getNumAdultos();
	}


	public DificultadPista getType(){
		return DificultadPista.ADULTOS;
	}

	public  boolean validate(){
		return (numAdults > 0);
	}

	public Integer getNumPlayers() {
		return numAdults;
	}
	@Override
	public Integer getNumAdults() {
		return numAdults;
	}

	public void setNumAdults(Integer numAdults) {
		this.numAdults = numAdults;
	}

	@Override
	public String toString() {
		return "ReservaAdultos [idUser=" + idUser + ", date= " + date + ", time=" + time + ", idPista=" + idPista
				+ ", price=" + price + ", discount=" + discount + ", numAdults=" + numAdults + ", id=" + id + "]";
	};


}