package es.pw.uco.business.user.dto;

import java.time.LocalDate;

import es.pw.uco.business.user.models.Usuario;

public class UserDTO {
	private Integer id;
	private String email;
	private LocalDate fecha;
	private LocalDate fechaIncripcion;
	private String nombreCompleto;
	
	public UserDTO(int id, String email, LocalDate fecha, String nombreCompleto,LocalDate fechaIncripcion) {
		super();
		this.id = id;
		this.email = email;
		this.fecha = fecha;
		this.fechaIncripcion = fechaIncripcion;
		this.nombreCompleto = nombreCompleto;
	}
	
	public UserDTO(String email,LocalDate fecha, String nombreCompleto,LocalDate fechaIncripcion) {
		this.id=null;
		this.email = email;
		this.fecha = fecha;
		this.fechaIncripcion = fechaIncripcion;
		this.nombreCompleto = nombreCompleto;
	}
	
	public UserDTO(Usuario u) {
		this.id=u.getId();
		this.email=u.getEmail();
		this.fecha=u.getBirthdayDate();
		this.fechaIncripcion=u.getInscriptionDate();
		this.nombreCompleto=u.getFullName();
	}

	public UserDTO() {
		this.id=null;
		this.email="";
		this.fecha=LocalDate.now();
		this.fechaIncripcion=LocalDate.now();
		this.nombreCompleto="";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
	
}
