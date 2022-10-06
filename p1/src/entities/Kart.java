package entities;

import java.io.Serializable;

import entities.enums.EstadoKart;

//Clase que representa a un vehhículo que utilizan los usuarios para recorrer las pistas.

public class Kart implements Serializable{
	
	//Variables indicadas en la práctica
	private Integer id;
	private boolean isAdult;
	private EstadoKart state;
	
	private Integer idPista;
	
	private static final long serialVersionUID = 1L;
	public static int numKarts=0;
	
	public Kart(){
		Kart.numKarts++;
		this.id = Kart.numKarts;
	}
	
	public Kart(Integer id,boolean isAdult, EstadoKart state, Integer idPista) {
		Kart.numKarts++;
		this.id = id;
		this.isAdult = isAdult;
		this.state = state;
		this.idPista = idPista;
	}
	
	public Kart(boolean isAdult, EstadoKart state, Integer idPista) {
		Kart.numKarts++;
		this.id = Kart.numKarts;
		this.isAdult = isAdult;
		this.state = state;
		this.idPista = idPista;
	}
	
	public Kart(boolean isAdult, EstadoKart state) {
		Kart.numKarts++;
		this.id = Kart.numKarts;
		this.isAdult = isAdult;
		this.state = state;
		this.idPista = -1;
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

	public Integer getIdPista() {
		return idPista;
	}

	public void setIdPista(Integer idPista) {
		this.idPista = idPista;
	}

	public static int getNumKarts() {
		return numKarts;
	}

	public static void setNumKarts(int numKarts) {
		Kart.numKarts = numKarts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Kart [id=" + id + ", isAdult=" + isAdult + ", state=" + state + ", idPista="
				+ idPista + "]";
	}

	

	
}