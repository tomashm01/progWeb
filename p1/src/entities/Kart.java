package entities;

import java.io.Serializable;

import entities.enums.EstadoKart;

//Clase que representa a un vehículo que utilizan los usuarios para recorrer las pistas.

public class Kart implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int numKarts=0;
	private Integer id;
	private boolean isAdult;
	private EstadoKart state;
	private Integer idUser;
	private Integer idPista;
	
	public Kart(){
		Kart.numKarts++;
		this.id = Kart.numKarts;
	}
	
	public Kart(boolean isAdult, EstadoKart state, Integer idUser, Integer idPista) {
		Kart.numKarts++;
		this.id = Kart.numKarts;
		this.isAdult = isAdult;
		this.state = state;
		this.idUser = idUser;
		this.idPista = idPista;
	}
	
	//constructor para kart sin pista ni usuario asignado
	public Kart(boolean isAdult, EstadoKart state) {
		Kart.numKarts++;
		this.id = Kart.numKarts;
		this.isAdult = isAdult;
		this.state = state;
		this.idUser = -1;
		this.idPista = -1;
	}

	public static int getNumKarts() {
		return numKarts;
	}

	public static void setNumKarts(int numKarts) {
		Kart.numKarts = numKarts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isAdult() {
		return isAdult;
	}

	public void setAdult(boolean isAdult) {
		this.isAdult = isAdult;
	}

	public EstadoKart getState() {
		return state;
	}

	public void setState(EstadoKart state) {
		this.state = state;
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

	@Override
	public String toString() {
		return "Kart [id=" + id + ", isAdult=" + isAdult + ", state=" + state + ", idUser=" + idUser + ", idPista="
				+ idPista + "]";
	}
	
	
}