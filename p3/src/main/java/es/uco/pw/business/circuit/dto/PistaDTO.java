package es.uco.pw.business.circuit.dto;

import es.uco.pw.business.circuit.models.Pista;

public class PistaDTO {
	private Integer id;
	private String nombre;
	private String dificultad;
	private int maxKarts;
	private Integer isAvailable;

	public PistaDTO(Integer id, String nombre, String dificultad, int maxKarts,Integer isAvailable) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dificultad = dificultad;
		this.maxKarts = maxKarts;
		this.isAvailable = isAvailable;
	}

	public PistaDTO(Pista p) {
		this.id = p.getId();
		this.nombre = p.getName();
		this.dificultad = p.getDifficulty().toString();
		this.maxKarts = p.getMaxKarts();
		this.isAvailable = (p.isAvailable())?1:0;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public int getMaxKarts() {
		return maxKarts;
	}

	public void setMaxKarts(int maxKarts) {
		this.maxKarts = maxKarts;
	}

	public Integer isAvailable() {
		return isAvailable;
	}

	public void setAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}
}
