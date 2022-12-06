package es.uco.pw.business.user.dto;

import java.time.LocalDate;

import es.uco.pw.business.user.models.Usuario;

public class UserDTO {
	private String email;
	private LocalDate fecha;
	private LocalDate fechaIncripcion;
	private String nombreCompleto;
	private String rol;
	private String password;
	
	public UserDTO(String email, String password,String rol,LocalDate fecha, String nombreCompleto,LocalDate fechaIncripcion) {
		this.email = email;
		this.fecha = fecha;
		this.fechaIncripcion = fechaIncripcion;
		this.rol=rol;
		this.password=password;
		this.nombreCompleto = nombreCompleto;
	}
	
	public UserDTO(Usuario u) {
		this.email=u.getEmail();
		this.fecha=u.getBirthdayDate();
		this.fechaIncripcion=u.getInscriptionDate();
		this.nombreCompleto=u.getFullName();
		this.password=u.getPassword();
		this.rol=u.getRol();
	}

	public UserDTO() {
		this.email="";
		this.fecha=LocalDate.now();
		this.fechaIncripcion=LocalDate.now();
		this.nombreCompleto="";
		this.password="";
		this.rol="USER";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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
}