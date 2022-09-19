package entities;

//import java.time.Period;
import java.util.Date;

public class Usuario {
	
	private String nombreApellidos;
	
	private Date fechaNacimiento;
	
	private Date fechaInscipcion;
	
	private String correo;
	
	public Usuario() {}
	
	public Usuario(String nombreApellidos, Date fechaNacimiento, Date fechaInscipcion, String correo) {
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
	/**
	public Integer calcularAntiguedad(){
		return Period.between(this.fechaInscipcion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), java.time.LocalDate.now()).getYears();
	}
	*/
}
