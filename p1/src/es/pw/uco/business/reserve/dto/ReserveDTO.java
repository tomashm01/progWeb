package es.pw.uco.business.reserve.dto;

import java.time.LocalDate;

import es.pw.uco.business.reserve.models.factory.ReservaAbstracta;
import es.pw.uco.business.reserve.models.factory.ReservaAdultos;
import es.pw.uco.business.reserve.models.factory.ReservaFamiliar;
import es.pw.uco.business.reserve.models.factory.ReservaInfantil;

public class ReserveDTO {
	private Integer id;
	private Integer idUser;
	private Integer idPista;
	private float price;
	private float discount;
	private LocalDate date;
	private Integer time;
	private String tipo;
	private Integer numAdultos;
	private Integer numMenores;
	
	public ReserveDTO( Integer idUser, LocalDate date,Integer time, Integer idPista, float price, float discount,Integer id,
			 String tipo,Integer numAdultos,Integer numMenores) {
		this.id = id;
		this.idUser = idUser;
		this.idPista = idPista;
		this.price = price;
		this.discount = discount;
		this.date = date;
		this.time = time;
		this.tipo = tipo;
		this.numAdultos=numAdultos;
		this.numMenores=numMenores;
	}
	
	public ReserveDTO( Integer idUser, Integer idPista, float price, float discount, LocalDate date,
			Integer time, String tipo,Integer numAdultos,Integer numMenores) {
		this.id = null;
		this.idUser = idUser;
		this.idPista = idPista;
		this.price = price;
		this.discount = discount;
		this.date = date;
		this.time = time;
		this.tipo = tipo;
		this.numAdultos=numAdultos;
		this.numMenores=numMenores;
	}

	public Integer getNumAdultos() {
		return numAdultos;
	}
	public void setNumAdultos(Integer numAdultos) {
		this.numAdultos = numAdultos;
	}
	public Integer getNumMenores() {
		return numMenores;
	}
	public void setNumMenores(Integer numMenores) {
		this.numMenores = numMenores;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdPista() {
		return idPista;
	}

	public void setIdPista(Integer idPista) {
		this.idPista = idPista;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}
