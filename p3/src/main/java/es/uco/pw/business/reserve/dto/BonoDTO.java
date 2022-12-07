package es.uco.pw.business.reserve.dto;

import java.time.LocalDate;
import java.util.ArrayList;

public class BonoDTO {
	private Integer id;
	private LocalDate expirationDate;
	private ArrayList<Integer> reserves;
	
	public BonoDTO(Integer id, LocalDate expirationDate) {
		super();
		this.id = id;
		this.expirationDate = expirationDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public ArrayList<Integer> getReserves() {
		return reserves;
	}

	public void setReserves(ArrayList<Integer> reserves) {
		this.reserves = reserves;
	}

}
