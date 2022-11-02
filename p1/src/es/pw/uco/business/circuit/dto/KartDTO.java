package es.pw.uco.business.circuit.dto;

import es.pw.uco.business.circuit.models.Kart;

public class KartDTO {
	private Integer id;
	private Integer isAdult;
	private String state;
	
	public KartDTO(Integer id, Integer isAdult, String state) {
		super();
		this.id = id;
		this.isAdult = isAdult;
		this.state = state;
	}

	public KartDTO(Kart k) {
		this.id = k.getId();
		this.isAdult = k.isAdult() ? 1 : 0;
		this.state = k.getState().toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsAdult() {
		return isAdult;
	}

	public void setIsAdult(Integer isAdult) {
		this.isAdult = isAdult;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	

}

