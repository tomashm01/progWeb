package es.uco.pw.business.reserve.models.factory;

import java.io.Serializable;
import java.time.LocalDateTime;

import es.uco.pw.business.enums.DificultadPista;
import es.uco.pw.business.reserve.dto.ReserveDTO;

public abstract class ReservaAbstracta implements Serializable {

	protected static final long serialVersionUID = 1L;
	protected String idUser;
	protected LocalDateTime date;
	protected Integer time;
	protected Integer idPista;
	protected float price;
	protected float discount;

	protected Integer id;
	public static final int MAX_RANDOM = 999999999;

	public ReservaAbstracta() {
	}

	public ReservaAbstracta(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,
			float discount, Integer id) {
		this.idUser = idUser;
		this.date = date;
		this.time = time;
		this.idPista = idPista;
		this.price = price;
		this.discount = discount;
		this.id = id;
	}

	public ReservaAbstracta(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,
			float discount) {
		this.idUser = idUser;
		this.date = date;
		this.time = time;
		this.idPista = idPista;
		this.price = price;
		this.discount = discount;
		this.id = (int) (Math.random() * MAX_RANDOM);
	}

	public ReservaAbstracta(ReserveDTO dto) {
		this.idUser = dto.getIdUser();
		this.date = dto.getDate();
		this.time = dto.getTime();
		this.idPista = dto.getIdPista();
		this.price = dto.getPrice();
		this.discount = dto.getDiscount();
		this.id = dto.getId();
	}

	public abstract DificultadPista getType();

	public abstract boolean validate();

	public abstract Integer getNumPlayers();

	public Integer getNumAdults() {
		return 0;
	}

	public Integer getNumChilds() {
		return 0;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ReservaAbstracta [idUser=" + idUser + ", date=" + date + ", time=" + time + ", idPista=" + idPista
				+ ", price=" + price + ", discount=" + discount + ", id=" + id + "]";
	}
}
