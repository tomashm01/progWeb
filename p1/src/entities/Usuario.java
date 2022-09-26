package entities;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Usuario {
	
	private UUID id;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}


	private String nombreApellidos;
	
	private Date fechaNacimiento;
	
	private Date fechaInscipcion;
	
	private String correo;
	
	public Usuario() {}
	
	public Usuario(String nombreApellidos, Date fechaNacimiento, Date fechaInscipcion, String correo) {
		this.id=UUID.randomUUID();
		this.nombreApellidos = nombreApellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaInscipcion = fechaInscipcion;
		this.correo = correo;
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
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the fechaInscipcion
	 */
	public Date getFechaInscipcion() {
		return fechaInscipcion;
	}

	/**
	 * @param fechaInscipcion the fechaInscipcion to set
	 */
	public void setFechaInscipcion(Date fechaInscipcion) {
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
		
	@Override
	public String toString() {
		return "Usuario [nombreApellidos=" + nombreApellidos + ", fechaNacimiento=" + fechaNacimiento
				+ ", fechaInscipcion=" + fechaInscipcion + ", correo=" + correo + "]";
	}


	/*
		Calcula los anios que lleva registrado el usuario
	*/
	public Integer calcularAntiguedad(){
		Date fechaActual = new Date();
		Integer diferencia = (int) (fechaActual.getTime() - fechaInscipcion.getTime());
		return (diferencia / (1000 * 60 * 60 * 24))/365;
	}

}
