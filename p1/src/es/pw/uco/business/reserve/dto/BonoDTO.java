package es.pw.uco.business.reserve.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BonoDTO {
	private Integer id;
	private LocalDate expirationDate;
	
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

}
