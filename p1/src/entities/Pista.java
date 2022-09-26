package entities;

import java.util.ArrayList;
import java.util.UUID;

enum DificultadPista {
	INFANTIL,
	FAMILIAR,
	ADULTOS
}

public class Pista {
	private UUID id;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}

	private String nombre;
	private boolean isReserved;
	private DificultadPista dificultad;
	private Integer maxKarts;
	private ArrayList<Kart> listaKarts = new ArrayList<Kart>();
	public Pista() {
	}
	public Pista(String nombre, boolean isReserved, DificultadPista dificultad, Integer maxKarts) {
		
		this.nombre = nombre;
		this.isReserved = isReserved;
		this.dificultad = dificultad;
		this.maxKarts = maxKarts;
		this.listaKarts = null;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the isReserved
	 */
	public boolean isReserved() {
		return isReserved;
	}
	/**
	 * @param isReserved the isReserved to set
	 */
	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
	/**
	 * @return the dificultad
	 */
	public DificultadPista getDificultad() {
		return dificultad;
	}
	/**
	 * @param dificultad the dificultad to set
	 */
	public void setDificultad(DificultadPista dificultad) {
		this.dificultad = dificultad;
	}
	/**
	 * @return the maxKarts
	 */
	public Integer getMaxKarts() {
		return maxKarts;
	}
	/**
	 * @param maxKarts the maxKarts to set
	 */
	public void setMaxKarts(Integer maxKarts) {
		this.maxKarts = maxKarts;
	}
	/**
	 * @return the listaKarts
	 */
	public ArrayList<Kart> getListaKarts() {
		return listaKarts;
	}
	/**
	 * @param listaKarts the listaKarts to set
	 */
	public void setListaKarts(ArrayList<Kart> listaKarts) {
		this.listaKarts = listaKarts;
	}
	@Override
	public String toString() {
		return "Pista [nombre=" + nombre + ", isReserved=" + isReserved + ", dificultad=" + dificultad + ", maxKarts="
				+ maxKarts + ", listaKarts=" + listaKarts + "]";
	}
	
	public ArrayList<Kart> consultarKartsDisponibles() {
		
		ArrayList<Kart> kartsDisponibles= new ArrayList<Kart>();
		
		for(int i=0;i<this.listaKarts.size();i++) {
			if(this.listaKarts.get(i).getEstado().equals("DISPONIBLE")) {
				kartsDisponibles.add(listaKarts.get(i));
			}
		}
		
		return kartsDisponibles;
	}
	
	public boolean asociarKartAPista(Kart kart) {
		if(kart.getIsAdult()) {
			if(this.getDificultad().equals("FAMILIAR") || this.getDificultad().equals("ADULTOS")) {
				this.listaKarts.add(kart);
				return true;
			}
		}else {
			if(this.getDificultad().equals("FAMILIAR") || this.getDificultad().equals("INFANTIL")) {
				this.listaKarts.add(kart);
				return true;
			}
		}
		return false;
	}
	
}

