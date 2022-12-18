package es.uco.pw.display.javabean;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String email;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIncripcion;
	private String nombreCompleto;
	private String rol;
	private String password;
	private int antiguedad;
	private boolean mayorEdad;
	
	public CustomerBean() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaIncripcion() {
		return fechaIncripcion;
	}

	public void setFechaIncripcion(LocalDate fechaIncripcion) {
		this.fechaIncripcion = fechaIncripcion;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public boolean isMayorEdad() {
		return mayorEdad;
	}

	public void setMayorEdad(boolean mayorEdad) {
		this.mayorEdad = mayorEdad;
	}
	

	@Override
	public String toString() {
		return "CustomerBean [email=" + email + ", fechaNacimiento=" + fechaNacimiento + ", fechaIncripcion="
				+ fechaIncripcion + ", nombreCompleto=" + nombreCompleto + ", rol=" + rol + ", password=" + password
				+ ", antiguedad=" + antiguedad + ", MayorEdad=" + mayorEdad + "]";
	}
	
}
