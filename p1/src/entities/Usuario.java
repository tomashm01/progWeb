package entities;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import factory.ReservaAbstracta;

public class Usuario {
	
	private Integer id;
	private String nombreApellidos;
	private LocalDate fechaNacimiento;	
	private LocalDate fechaInscipcion;
	private String correo;
	private ArrayList<ReservaAbstracta> reservas; 
	
	public Usuario() {}
	
	public Usuario(Integer id) {
		this.id = id;
		
	}
	
	public Usuario(String nombreApellidos, LocalDate fechaNacimiento, LocalDate fechaInscipcion, String correo) {
		//this.id=UUID.randomUUID();
		this.id = 111111;
		this.nombreApellidos = nombreApellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaInscipcion = fechaInscipcion;
		this.correo = correo;
		this.reservas = null;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return the nombreApellidos
	 */
	public String getNombreApellidos() {
		return nombreApellidos;
	}

	/**
	 * @param nombreApellidos the nombreApellidos to set
	 */
	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the fechaInscipcion
	 */
	public LocalDate getFechaInscipcion() {
		return fechaInscipcion;
	}

	/**
	 * @param fechaInscipcion the fechaInscipcion to set
	 */
	public void setFechaInscipcion(LocalDate fechaInscipcion) {
		this.fechaInscipcion = fechaInscipcion;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
		
	/**
	 * @return the reservas
	 */
	public ArrayList<ReservaAbstracta> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	public void setReservas(ArrayList<ReservaAbstracta> reservas) {
		this.reservas = reservas;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreApellidos=" + nombreApellidos + ", fechaNacimiento=" + fechaNacimiento
				+ ", fechaInscipcion=" + fechaInscipcion + ", correo=" + correo + ", reservas=" + reservas + "]";
	}

	/*
		Calcula los anios que lleva registrado el usuario
	*/
	public long calcularAntiguedad(){
		//Date fechaActual = new Date();
		//long diferencia = fechaActual.getTime() - fechaInscipcion.getTime();
		//return (diferencia / (1000 * 60 * 60 * 24))/365;
		return 0;
	}
	
	/**
	 * Calcula si el usuario es mayor de edad*/
	public boolean isMayorEdad(){
		LocalDate fechaActual = LocalDate.now();
		int anioActual = fechaActual.getYear();
		int anioUsuario = fechaNacimiento.getYear();
		long res = (anioActual - anioUsuario);
		System.out.println(res);
		return (res)>17;
		
		
	}

}
