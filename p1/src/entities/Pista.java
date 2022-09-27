package entities;

import java.util.ArrayList;
import java.util.UUID;

enum DificultadPista {
	INFANTIL,
	FAMILIAR,
	ADULTOS;
}

public class Pista {
	private UUID id;
	private String nombre;
	private boolean isAvailable;
	private DificultadPista dificultad;
	private Integer maxKarts;
	private ArrayList<Kart> listaKarts;
	
	public Pista() {
	}
	public Pista(String nombre, boolean isAvailable, DificultadPista dificultad, Integer maxKarts) {
		this.id=UUID.randomUUID();
		this.nombre = nombre;
		this.isAvailable = isAvailable;
		this.dificultad = dificultad;
		this.maxKarts = maxKarts;
		this.listaKarts = null;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	public boolean isAvailable() {
		return isAvailable;
	}
	/**
	 * @param isReserved the isReserved to set
	 */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
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
		return "Pista [nombre=" + nombre + ", isReserved=" + isAvailable + ", dificultad=" + dificultad + ", maxKarts="
				+ maxKarts + ", listaKarts=" + listaKarts + "]";
	}
	
	public ArrayList<Kart> consultarKartsDisponibles() {
		
		ArrayList<Kart> kartsDisponibles= new ArrayList<Kart>();
		
		for(int i=0;i<this.listaKarts.size();i++) {
			if( this.listaKarts.get(i).getEstado() == EstadoKart.DISPONIBLE ) {
				kartsDisponibles.add(listaKarts.get(i));
			}
		}
		
		return kartsDisponibles;
	}
	
	public boolean asociarKartAPista(Kart kart) {
		if(kart.getIsAdult()) {
			if(this.getDificultad() == DificultadPista.FAMILIAR || this.getDificultad() == DificultadPista.ADULTOS ) {
				this.listaKarts.add(kart);
				return true;
			}
		}else {
			if(this.getDificultad() == DificultadPista.FAMILIAR  || this.getDificultad() == DificultadPista.INFANTIL ) {
				this.listaKarts.add(kart);
				return true;
			}
		}
		return false;
	}
	
}

