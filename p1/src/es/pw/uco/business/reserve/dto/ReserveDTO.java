package es.pw.uco.business.reserve.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReserveDTO {
	private Integer id;
	private Integer idUser;
	private Integer idPista;
	private float price;
	private float discount;
	private LocalDateTime date;
	private Integer time;
	
	public ReserveDTO(Integer id, Integer idUser, Integer idPista, float price, float discount, LocalDateTime date,
			Integer time) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idPista = idPista;
		this.price = price;
		this.discount = discount;
		this.date = date;
		this.time = time;
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
