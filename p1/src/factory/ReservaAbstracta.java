package factory;

import java.sql.Date;
import java.util.UUID;

public abstract class ReservaAbstracta {
	
	protected UUID idUser;
	protected float precio;
	protected Date fecha;
	protected Integer minutos;
	protected UUID idPista;
	protected float descuento;
	
	public ReservaAbstracta() {
		
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
	@Override
	public String toString() {
		return "ReservaAbstracta [idUser=" + idUser + ", precio=" + precio + ", fecha=" + fecha + ", minutos=" + minutos
				+ ", idPista=" + idPista + ", descuento=" + descuento + "]";
	}

	
	
	
}


