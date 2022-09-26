package factory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import entities.Usuario;

public abstract class ReservaAbstracta {
	
	public static int MAX_RANDOM = 999999;
	protected Integer id;
	protected Integer idUser;
	protected float precio;
	protected LocalDate fecha;
	protected Integer minutos;
	protected Integer idPista;
	protected float descuento;
	protected ArrayList<Usuario> participantes;
	protected static ArrayList<Usuario> usuarios;
	
	public ReservaAbstracta() {
	
	}
	
	public ReservaAbstracta(Integer idUser, float precio, LocalDate fecha, Integer minutos, Integer idPista,
			float descuento) {
		super();
		this.id = (int) Math.random();
		this.idUser = idUser;
		this.precio = precio;
		this.fecha = fecha;
		this.minutos = minutos;
		this.idPista = idPista;
		this.descuento = descuento;
		this.participantes = null;
		this.usuarios = obtenerUsuarios();
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
	 * @return the idUser
	 */
	public Integer getIdUser() {
		return idUser;
	}
	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(Integer idUser) {
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
	public LocalDate getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
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
	public Integer getIdPista() {
		return idPista;
	}
	/**
	 * @param idPista the idPista to set
	 */
	public void setIdPista(Integer idPista) {
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
	
	static ArrayList<Usuario> obtenerUsuarios() {
		// AQUI VA LA FUENTE DE DATOS
		Usuario usuario1 = new Usuario("Juan Higuera", LocalDate.of(2004, 1, 1), null, "juan@uco.es");
		Usuario usuario2 = new Usuario();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario1);
		usuarios.add(usuario2);
		return usuarios;
	}

	@Override
	public String toString() {
		return "ReservaAbstracta [idUser=" + idUser + ", precio=" + precio + ", fecha=" + fecha + ", minutos=" + minutos
				+ ", idPista=" + idPista + ", descuento=" + descuento + "]";
	}

	
	
	
}


