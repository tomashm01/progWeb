package es.pw.uco.business.reserve.models.factory;
import java.io.Serializable;
import java.time.LocalDateTime;

import es.pw.uco.business.enums.DificultadPista;

public abstract class ReservaAbstracta implements Serializable{
	
	protected static final long serialVersionUID = 1L;
	protected Integer idUser;
	protected LocalDateTime date;
	protected Integer time;
	protected Integer idPista;
	protected float price;
	protected float discount;
	
	protected Integer id;
	public static final int MAX_RANDOM = 999999999;

	public ReservaAbstracta() {}

	public ReservaAbstracta(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id) {
		this.idUser = idUser;
		this.date = date;
		this.time = time;
		this.idPista = idPista;
		this.price = price;
		this.discount = discount;
		this.id = id;
	}
	
	public ReservaAbstracta(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount) {
		this.idUser = idUser;
		this.date = date;
		this.time = time;
		this.idPista = idPista;
		this.price = price;
		this.discount = discount;
		this.id = (int) (Math.random()*MAX_RANDOM);;
	}
	
	public abstract DificultadPista getType();
	
	public abstract boolean validate();
	
	public abstract Integer getNumPlayers();

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
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

