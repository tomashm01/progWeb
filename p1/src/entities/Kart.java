package entities;

import java.util.UUID;

enum EstadoKart {
	DISPONIBLE,
	RESERVADO,
	MANTENIMIENTO
}

public class Kart {
	
	private Integer id;
	private boolean isAdult;
	private EstadoKart estado;
	private UUID idUser;
	private UUID idPista;
	
	public Kart() {}
	
	public Kart(Integer id, boolean isAdult, EstadoKart estado, UUID idUser, UUID idPista) {
		this.id = id;
		this.isAdult = isAdult;
		this.estado = estado;
		this.idUser = idUser;
		this.idPista = idPista;
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the tipo
	 */
	public boolean getIsAdult() {
		return isAdult;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(boolean tipo) {
		this.isAdult = tipo;
	}
	/**
	 * @return the estado
	 */
	public EstadoKart getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoKart estado) {
		this.estado = estado;
	}
	
	
	/**
	 * @return the idUser
	 */
	public UUID getIdUser() {
		return idUser;
	}
	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(UUID idUser) {
		this.idUser = idUser;
	}

	/**
	 * @param isAdult the isAdult to set
	 */
	public void setAdult(boolean isAdult) {
		this.isAdult = isAdult;
	}
	
	/**
	 * @return the idPista
	 */
	public UUID getIdPista() {
		return idPista;
	}
	/**
	 * @param idPista the idPista to set
	 */
	public void setIdPista(UUID idPista) {
		this.idPista = idPista;
	}
	@Override
	public String toString() {
		return "Kart [id=" + id + ", tipo=" + isAdult + ", estado=" + estado + "]";
	}
}
