package entities;

import java.io.Serializable;

import entities.enums.EstadoKart;

//Clase que representa a un vehhículo que utilizan los usuarios para recorrer las pistas.

public class Kart implements Serializable{
	
	private Integer id;
	private boolean isAdult;
	private EstadoKart state;
	
	private Integer idPista;
	
	private static final long serialVersionUID = 1L;
	public static final int MAX_RANDOM = 999999999;
	
	public Kart(){
		this.id = (int) (Math.random()*MAX_RANDOM);
	}
	
	public Kart(Integer id,boolean isAdult, EstadoKart state, Integer idPista) {
		this.id = id;
		this.isAdult = isAdult;
		this.state = state;
		this.idPista = idPista;
	}
	
	public Kart(boolean isAdult, EstadoKart state, Integer idPista) {
		this.id = (int) (Math.random()*MAX_RANDOM);
		this.isAdult = isAdult;
		this.state = state;
		this.idPista = idPista;
	}
	
	public Kart(boolean isAdult, EstadoKart state) {
		this.id = (int) (Math.random()*MAX_RANDOM);
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Kart [id=" + id + ", isAdult=" + isAdult + ", state=" + state + ", idPista="
				+ idPista + "]";
	}

	

	
}