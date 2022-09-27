package entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import factory.ReservaAbstracta;

public class Usuario {
	
	public static final int MAX_RANDOM = 999999999;
	private Integer id;
	private String fullName;
	private LocalDate birthdayDate;	
	private LocalDate inscriptionDate;
	private String email;
	private ArrayList<ReservaAbstracta> reservesList=new ArrayList<ReservaAbstracta>(); 
	
	public Usuario() {}
	
	public Usuario(Integer id) {
		this.id = id;
	}
		
	public Usuario(Integer id, String fullName, LocalDate birthdayDate, LocalDate inscriptionDate, String email) {
		this.id = (int) (Math.random()*MAX_RANDOM);
		this.fullName = fullName;
		this.birthdayDate = birthdayDate;
		this.inscriptionDate = inscriptionDate;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	/**
	 * @return the reservas
	 */
	public ArrayList<ReservaAbstracta> getReserves() {
		return reservesList;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", fullName=" + fullName + ", birthdayDate=" + birthdayDate + ", inscriptionDate="
				+ inscriptionDate + ", email=" + email + ", reservesList=" + reservesList + "]";
	}

	/*
		Calcula los anios que lleva registrado el usuario
	*/
	public long antiquity(){
		Period period = Period.between(inscriptionDate, LocalDate.now());
		return period.getYears();
	}
	
	/**
	 * Calcula si el usuario es mayor de edad*/
	public boolean isMayorEdad(){
		Period period = Period.between(inscriptionDate,LocalDate.now());
		return period.getYears() > 17;
	}

}
