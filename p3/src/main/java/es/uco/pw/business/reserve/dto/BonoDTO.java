package es.uco.pw.business.reserve.dto;

import java.time.LocalDate;
import java.util.ArrayList;

public class BonoDTO {
	private Integer id;
	private LocalDate expirationDate;
	private ArrayList<Integer> reserves;
	private String idUser;
	
	public BonoDTO(Integer id, LocalDate expirationDate,String idUser) {
		super();
		this.id = id;
		this.expirationDate = expirationDate;
		this.idUser = idUser;
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

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
	

}
