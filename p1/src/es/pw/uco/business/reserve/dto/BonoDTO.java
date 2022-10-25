package es.pw.uco.business.reserve.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BonoDTO {
	private Integer id;
	private LocalDateTime expirationDate;
	
	public BonoDTO(Integer id, LocalDateTime expirationDate) {
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

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

}
