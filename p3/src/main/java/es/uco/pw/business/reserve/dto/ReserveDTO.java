package es.uco.pw.business.reserve.dto;

import java.time.LocalDateTime;

import es.uco.pw.business.reserve.models.factory.ReservaAbstracta;

public class ReserveDTO {
	private Integer id;
	private String idUser;
	private Integer idPista;
	private float price;
	private float discount;
	private LocalDateTime date;
	private Integer time;
	private String tipo;
	private Integer numAdultos;
	private Integer numMenores;
	
	public ReserveDTO( String idUser,LocalDateTime date,Integer time, Integer idPista, float price, float discount,Integer id,
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
	
	public ReserveDTO( String idUser, Integer idPista, float price, float discount, LocalDateTime date,
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
	
	public ReserveDTO(ReservaAbstracta res) {
		this.id = res.getId();
		this.idUser = res.getIdUser();
		this.idPista = res.getIdPista();
		this.price = res.getPrice();
		this.discount = res.getDiscount();
		this.date = res.getDate();
		this.time = res.getTime();
		this.tipo = res.getType().toString();
		this.numAdultos=res.getNumAdults();
		this.numMenores=res.getNumChilds();
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

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}
