package factory;

import java.time.LocalDate;
import java.util.ArrayList;

import entities.Usuario;
import entities.enums.DificultadPista;

public abstract class ReservaAbstracta {
	
	public static final int MAX_RANDOM = 999999999;
	protected Integer id;
	protected Integer idUser;
	protected float price;
	protected LocalDate date;
	protected Integer time;
	protected Integer idPista;
	protected float discount;
	protected ArrayList<Usuario> players=new ArrayList<Usuario>();
	
	public ReservaAbstracta() {
	
	}
	
	public ReservaAbstracta(Integer idUser, float price, LocalDate date, Integer time, Integer idPista,
			float discount) {
		super();
		this.id = (int) (Math.random()*MAX_RANDOM);
		this.idUser = idUser;
		this.price = price;
		this.date = date;
		this.time = time;
		this.idPista = idPista;
		this.discount = discount;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public Integer getIdPista() {
		return idPista;
	}

	public void setIdPista(Integer idPista) {
		this.idPista = idPista;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public ArrayList<Usuario> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Usuario> players) {
		this.players = players;
	}

	public int getNumChilds() {
		int numChilds=0;
		for(Usuario user : players){
			if( ! user.isMayorEdad()){
				numChilds++;
			}
		}
		return numChilds;
	}	
	
	public boolean existAdultInParticipant() {
		for(Usuario user : players){
			if(user.isMayorEdad()){
				return true;
			}
		}
		return false;
	}
	
	public boolean allChilds() {
		for(Usuario user : players){
			if( user.isMayorEdad()){
				return false;
			}
		}
		return true;
	}	

	public boolean allAdults() {
		for(Usuario user : players){
			if( ! user.isMayorEdad()){
				return false;
			}
		}
		return true;
	}	

	public abstract DificultadPista type();
	public abstract boolean validate();
	@Override
	public String toString() {
		return "ReservaAbstracta [idUser=" + idUser + ", precio=" + price + ", fecha=" + date + ", minutos=" + time
				+ ", idPista=" + idPista + ", descuento=" + discount + "]";
	}
	
	
	
}


