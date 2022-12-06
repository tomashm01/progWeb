package es.uco.pw.business.user.models;


import java.time.LocalDate;
import java.time.Period;

import es.uco.pw.business.user.dto.UserDTO;

//Esta clase representa a una persona usuaria de las instalaciones de la empresa de karts

public class Usuario{
	private String fullName;
	private LocalDate birthdayDate;	
	private LocalDate inscriptionDate;
	private String email;
	private String password;
	private String rol;

	public Usuario(String fullName, LocalDate birthdayDate, LocalDate inscriptionDate, String email, String password,
			String rol){
		super();
		this.fullName = fullName;
		this.birthdayDate = birthdayDate;
		this.inscriptionDate = inscriptionDate;
		this.email = email;
		this.password = password;
		this.rol = rol;
	}

	public Usuario() {
		this.email="";
		this.inscriptionDate = LocalDate.now();
		this.password="";
		this.rol="USER";
	}

	public Usuario(UserDTO user) {
		this.fullName = user.getNombreCompleto();
		this.birthdayDate = user.getFecha();
		this.inscriptionDate =user.getFechaIncripcion();
		this.email = user.getEmail();
		this.rol=user.getRol();
		this.password=user.getPassword();
	}


	public Usuario( String email) {
		this.email = email;
	}
		
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(LocalDate birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public LocalDate getInscriptionDate() {
		return inscriptionDate;
	}

	public void setInscriptionDate(LocalDate inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [fullName=" + fullName + ", birthdayDate=" + birthdayDate + ", inscriptionDate="
				+ inscriptionDate + ", email=" + email + ", password=" + password + ", rol=" + rol + "]";
	}

	/*
		Calcula los a�os que lleva registrado el usuario
	*/
	public int antiquity(){
		Period period = Period.between(inscriptionDate, LocalDate.now());
		return (int)period.getYears();
	}
	
	/**
	 * Calcula si el usuario es mayor de edad*/
	public boolean isMayorEdad(){
		Period period = Period.between(birthdayDate,LocalDate.now());
		return period.getYears() > 17;
	}

}
