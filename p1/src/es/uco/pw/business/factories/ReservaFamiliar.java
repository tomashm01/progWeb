package es.uco.pw.business.factories;
import java.time.LocalDateTime;

import es.uco.pw.data.enums.DificultadPista;

//Es una reserva que realiza un adulto, en la que participan tanto adultos como niños en una pista de tipo de familiar

public class ReservaFamiliar extends ReservaAbstracta{
	

	private static final long serialVersionUID = 1L;

	private Integer numAdults;
	private Integer numChilds;
	
	public ReservaFamiliar() {};
	
	
	public ReservaFamiliar(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numAdults,Integer numChilds) {
		super(idUser,  date,  time,  idPista,  price, discount,  id);
		this.numAdults = numAdults;
		this.numChilds = numChilds;
	}
	
	public ReservaFamiliar(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount,Integer numAdults,Integer numChilds) {
		super(idUser,  date,  time,  idPista,  price, discount);
		this.numAdults = numAdults;
		this.numChilds = numChilds;
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
	
	public Integer getNumAdults() {
		return numAdults;
	}


	public void setNumAdults(Integer numAdults) {
		this.numAdults = numAdults;
	}


	public Integer getNumChilds() {
		return numChilds;
	}


	public void setNumChilds(Integer numChilds) {
		this.numChilds = numChilds;
	}


	@Override
	public String toString() {
		return "ReservaFamiliar [idUser=" + idUser + ", date=" + date + ", time=" + time + ", idPista=" + idPista
				+ ", price=" + price + ", discount=" + discount + ", numAdults=" + numAdults + ", numChilds="
				+ numChilds + ", id=" + id + "]";
	};


	
}


