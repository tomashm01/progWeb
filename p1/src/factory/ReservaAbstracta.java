package factory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

import entities.Usuario;

public abstract class ReservaAbstracta {
	
	protected UUID id;
	protected UUID idUser;
	protected float precio;
	protected Date fecha;
	protected Integer minutos;
	protected UUID idPista;
	protected float descuento;
	protected ArrayList<Usuario> participantes;
	
	public ReservaAbstracta() {
	
	}
	
	public ReservaAbstracta(UUID idUser, float precio, Date fecha, Integer minutos, UUID idPista,
			float descuento) {
		super();
		this.id = UUID.randomUUID();
		this.idUser = idUser;
		this.precio = precio;
		this.fecha = fecha;
		this.minutos = minutos;
		this.idPista = idPista;
		this.descuento = descuento;
		this.participantes = null;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
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
	 * @return the precio
	 */
	public float getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the minutos
	 */
	public Integer getMinutos() {
		return minutos;
	}
	/**
	 * @param minutos the minutos to set
	 */
	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
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
	/**
	 * @return the descuento
	 */
	public float getDescuento() {
		return descuento;
	}
	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	/**
	 * @return the participantes
	 */
	public ArrayList<Usuario> getParticipantes() {
		return participantes;
	}

	/**
	 * @param participantes the participantes to set
	 */
	public void setParticipantes(ArrayList<Usuario> participantes) {
		this.participantes = participantes;
	}

	@Override
	public String toString() {
		return "ReservaAbstracta [idUser=" + idUser + ", precio=" + precio + ", fecha=" + fecha + ", minutos=" + minutos
				+ ", idPista=" + idPista + ", descuento=" + descuento + "]";
	}

	
	
	
}


