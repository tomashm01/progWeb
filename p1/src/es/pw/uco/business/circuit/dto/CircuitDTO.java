package es.pw.uco.business.circuit.dto;

public class CircuitDTO {
	private Integer id;
	private String name;
	private String difficulty;
	private Integer maxKarts;
	
	public CircuitDTO(Integer id, String name, String difficulty, Integer maxKarts) {
		super();
		this.id = id;
		this.name = name;
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

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Integer getMaxKarts() {
		return maxKarts;
	}

	public void setMaxKarts(Integer maxKarts) {
		this.maxKarts = maxKarts;
	}

	
	
}
