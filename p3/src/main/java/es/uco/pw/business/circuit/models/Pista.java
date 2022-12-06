package es.uco.pw.business.circuit.models;

import java.io.Serializable;
import java.util.ArrayList;

import es.uco.pw.business.enums.DificultadPista;
import es.uco.pw.business.enums.EstadoKart;
import es.uco.pw.business.circuit.dto.PistaDTO;

//Esta clase representa a una persona usuaria de las instalaciones de la empresa de karts

public class Pista implements Serializable{
	
	private String name;
	private boolean isAvailable;
	private DificultadPista difficulty;
	private Integer maxKarts;
	private ArrayList<Kart> kartsList=new ArrayList<Kart>();

	
	private static final long serialVersionUID = 1L;
	public static final int MAX_RANDOM = 999999999;
	private Integer id;
	
	public Pista() {
		this.id = (int) (Math.random()*MAX_RANDOM);
	}

	public Pista(String name, boolean isAvailable, DificultadPista difficulty, Integer maxKarts) {
		this.id = (int) (Math.random()*MAX_RANDOM);
		this.name = name;
		this.isAvailable = isAvailable;
		this.difficulty = difficulty;
		this.maxKarts = maxKarts;
	}
	
	public Pista(int id,String name, boolean isAvailable, DificultadPista difficulty, Integer maxKarts) {
		this.id = id;
		this.name = name;
		this.isAvailable = isAvailable;
		this.difficulty = difficulty;
		this.maxKarts = maxKarts;
	}

	public Pista(PistaDTO pista) {
		this.id = pista.getId();
		this.name = pista.getNombre();
		this.difficulty = toDificultadPista(pista.getDificultad());
		this.maxKarts = pista.getMaxKarts();
		this.isAvailable = pista.isAvailable()==1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public DificultadPista getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(DificultadPista difficulty) {
		this.difficulty = difficulty;
	}

	public Integer getMaxKarts() {
		return maxKarts;
	}

	public void setMaxKarts(Integer maxKarts) {
		this.maxKarts = maxKarts;
	}

	public ArrayList<Kart> getKartsList() {
		return kartsList;
	}

	public void setKartsList(ArrayList<Kart> kartsList) {
		this.kartsList = kartsList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	@Override
	public String toString() {
		return "Pista [name=" + name + ", isAvailable=" + isAvailable + ", difficulty=" + difficulty + ", maxKarts="
				+ maxKarts + ", kartsList=" + kartsList + ", id=" + id + "]";
	}

	public String toStringWithoutKarts() {
		return "Pista [name=" + name + ", isAvailable=" + isAvailable + ", difficulty=" + difficulty + ", maxKarts="
				+ maxKarts +", id=" + id + "]";
	}

	public ArrayList<Kart> consultarKartsDisponibles() {
		ArrayList<Kart> kartsDisponibles= new ArrayList<Kart>();
		for(Kart it : kartsList) {
			if( it.getState() == EstadoKart.DISPONIBLE ) {
				kartsDisponibles.add(it);
			}
		}
		return kartsDisponibles;
	}
	
	public boolean asociarKartAPista(Kart kart) {
		if(kart.isAdult()) {
			if(this.getDifficulty() == DificultadPista.FAMILIAR || this.getDifficulty() == DificultadPista.ADULTOS ) {
				kart.setIdPista(id);
				this.kartsList.add(kart);
				return true;
			}
		}else {
			if(this.getDifficulty() == DificultadPista.FAMILIAR  || this.getDifficulty() == DificultadPista.INFANTIL ) {
				kart.setIdPista(id);
				this.kartsList.add(kart);
				return true;
			}
		}
		return false;
	}
	
	public static DificultadPista toDificultadPista(String e) {
		switch (e.toUpperCase()) {
		case "ADULTOS":
			return DificultadPista.ADULTOS;
		case "FAMILIAR":
			return DificultadPista.FAMILIAR;
		case "INFANTIL":
			return DificultadPista.INFANTIL;

		default:
			throw new IllegalArgumentException("Unexpected value: " + e);
		}

	}
	
}