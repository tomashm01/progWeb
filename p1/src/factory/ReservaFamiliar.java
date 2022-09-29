package factory;

import java.time.LocalDate;
import java.util.ArrayList;

import entities.Usuario;
import entities.enums.DificultadPista;

//Es una reserva que realiza un adulto, en la que participan tanto adultos como niños en una pista de tipo de familiar

public class ReservaFamiliar extends ReservaAbstracta{
	
	private int numChilds=0;
	private int numAdults=0;
	
	public int getNumChilds() {
		return numChilds;
	}

	public void setNumChilds(int numChilds) {
		this.numChilds = numChilds;
	}

	public int getNumAdults() {
		return numAdults;
	}

	public void setNumAdults(int numAdults) {
		this.numAdults = numAdults;
	}

	public ReservaFamiliar(Integer idUser,ArrayList<Usuario> participantes, float precio, LocalDate fecha, Integer minutos, Integer idPista, float descuento) {
		super(idUser, precio, fecha, minutos, idPista, descuento);
		super.players=participantes;
		numChilds=super.getNumChilds();
		numAdults=participantes.size()-super.getNumChilds();
	}

	public ReservaFamiliar() {}

	public DificultadPista type(){
		return DificultadPista.FAMILIAR;
	}

	public boolean validate(){
		return existAdultInParticipant();
	}

	@Override
	public String toString() {
		return "ReservaFamiliar [idUser=" + idUser + ", precio=" + price + ", fecha=" + date + ", minutos=" + time
				+ ", idPista=" + idPista + ", descuento=" + discount + ",numChilds=" + numChilds + ", numAdults=" + numAdults + "]";
	}
	
}


