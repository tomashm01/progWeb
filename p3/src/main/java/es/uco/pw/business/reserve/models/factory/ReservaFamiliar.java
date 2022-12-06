package es.uco.pw.business.reserve.models.factory;
import java.time.LocalDateTime;

import es.uco.pw.business.enums.DificultadPista;
import es.uco.pw.business.reserve.dto.ReserveDTO;

//Es una reserva que realiza un adulto, en la que participan tanto adultos como niños en una pista de tipo de familiar

public class ReservaFamiliar extends ReservaAbstracta{
	

	private static final long serialVersionUID = 1L;

	private Integer numAdults;
	private Integer numChilds;
	
	public ReservaFamiliar() {};
	
public ReservaFamiliar(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numAdults,Integer numChilds) {
		super(idUser,  date,  time,  idPista,  price, discount,  id);
		this.numAdults = numAdults;
		this.numChilds = numChilds;
	}
	
	public ReservaFamiliar(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount,Integer numAdults,Integer numChilds) {
		super(idUser,  date,  time,  idPista,  price, discount);
		this.numAdults = numAdults;
		this.numChilds = numChilds;
	}

	public ReservaFamiliar(ReserveDTO dto) {
		this.idUser = dto.getIdUser();
		this.date = dto.getDate();
		this.time = dto.getTime();
		this.idPista = dto.getIdPista();
		this.price = dto.getPrice();
		this.discount = dto.getDiscount();
		this.id = dto.getId();
		this.numAdults = dto.getNumAdultos();
		this.numChilds = dto.getNumMenores();
	}
	
	public DificultadPista getType(){
		return DificultadPista.FAMILIAR;
	}

	public  boolean validate(){
		return (numAdults > 0);
	}

	public Integer getNumPlayers() {
		return numAdults+numChilds;
	}
	@Override
	public Integer getNumAdults() {
		return numAdults;
	}


	public void setNumAdults(Integer numAdults) {
		this.numAdults = numAdults;
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
		return "ReservaFamiliar [idUser=" + idUser + ", date= " + date + ", time=" + time + ", idPista=" + idPista
				+ ", price=" + price + ", discount=" + discount + ", numAdults=" + numAdults + ", numChilds="
				+ numChilds + ", id=" + id + "]";
	};


	
}


