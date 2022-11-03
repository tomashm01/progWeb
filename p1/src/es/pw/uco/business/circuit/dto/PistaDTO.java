package es.pw.uco.business.circuit.dto;

import es.pw.uco.business.circuit.models.Pista;

public class PistaDTO {
	private Integer id;
	private String nombre;
	private String dificultad;
	private int maxKarts;

	public PistaDTO(Integer id, String nombre, String dificultad, int maxKarts) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dificultad = dificultad;
		this.maxKarts = maxKarts;
	}

	public PistaDTO(Pista p) {
		this.id = p.getId();
		this.nombre = p.getName();
		this.dificultad = p.getDifficulty().toString();
		this.maxKarts = p.getMaxKarts();
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

}
