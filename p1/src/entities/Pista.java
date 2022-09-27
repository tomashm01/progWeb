package entities;

import java.util.ArrayList;

import entities.enums.DificultadPista;
import entities.enums.EstadoKart;

//Esta clase representa a una persona usuaria de las instalaciones de la empresa de karts

public class Pista {
	
	public static final int MAX_RANDOM = 999999999;
	private Integer id;
	private String name;
	private boolean isAvailable;
	private DificultadPista difficulty;
	private Integer maxKarts;
	private ArrayList<Kart> kartsList=new ArrayList<Kart>();
	
	public Pista() {
		this.id=(int) (Math.random()*MAX_RANDOM);
	}

	public Pista(String name, boolean isAvailable, DificultadPista difficulty, Integer maxKarts) {
		this.id = (int) (Math.random()*MAX_RANDOM);
		this.name = name;
		this.isAvailable = isAvailable;
		this.difficulty = difficulty;
		this.maxKarts = maxKarts;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public void setKartsList(ArrayList<Kart> newList) {
		kartsList=newList;
	}
	
	@Override
	public String toString() {
		return "Pista [id=" + id + ", name=" + name + ", isAvailable=" + isAvailable + ", difficulty=" + difficulty
				+ ", maxKarts=" + maxKarts + "]";
	}
	public ArrayList<Kart> consultarKartsDisponibles() {
		
		ArrayList<Kart> kartsDisponibles= new ArrayList<Kart>();
		
		for(int i=0;i<this.kartsList.size();i++) {
			if( this.kartsList.get(i).getState() == EstadoKart.DISPONIBLE ) {
				kartsDisponibles.add(kartsList.get(i));
			}
		}
		
		return kartsDisponibles;
	}
	
	public boolean asociarKartAPista(Kart kart) {
		if(kart.isAdult()) {
			if(this.getDifficulty() == DificultadPista.FAMILIAR || this.getDifficulty() == DificultadPista.ADULTOS ) {
				this.kartsList.add(kart);
				return true;
			}
		}else {
			if(this.getDifficulty() == DificultadPista.FAMILIAR  || this.getDifficulty() == DificultadPista.INFANTIL ) {
				this.kartsList.add(kart);
				return true;
			}
		}
		return false;
	}
	
}

