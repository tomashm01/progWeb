package es.uco.pw.data.models;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

//Esta clase representa a una persona usuaria de las instalaciones de la empresa de karts

public class Usuario implements Serializable {

	private String fullName;
	private LocalDate birthdayDate;	
	private LocalDate inscriptionDate;
	private String email;

	private static final long serialVersionUID = 1L;
	public static final int MAX_RANDOM = 999999999;
	private Integer id;
	
	public Usuario() {
		this.id=(int) (Math.random()*MAX_RANDOM);
		this.inscriptionDate = LocalDate.now();
	}
	
	public Usuario(Integer id,String fullName, LocalDate birthdayDate, String email, LocalDate inscriptionDate) {
		this.fullName = fullName;
		this.birthdayDate = birthdayDate;
		this.inscriptionDate = inscriptionDate;
		this.email = email;
		this.id = id;
	}
	
	public Usuario(String fullName, LocalDate birthdayDate, String email, LocalDate inscriptionDate) {
		this.fullName = fullName;
		this.birthdayDate = birthdayDate;
		this.inscriptionDate = inscriptionDate;
		this.email = email;
		this.id=(int) (Math.random()*MAX_RANDOM);
	}

	public Usuario(String fullName, LocalDate birthdayDate, String email) {
		this.fullName = fullName;
		this.birthdayDate = birthdayDate;
		this.inscriptionDate = LocalDate.now();
		this.email = email;
		this.id=(int) (Math.random()*MAX_RANDOM);
	}


	public Usuario(Integer id) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usuario [fullName=" + fullName + ", birthdayDate=" + birthdayDate + ", inscriptionDate="
				+ inscriptionDate + ", email=" + email + ", id=" + id + "]";
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