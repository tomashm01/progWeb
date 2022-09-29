package factory;

import java.time.LocalDate;
import java.util.ArrayList;

import entities.Usuario;
import entities.enums.DificultadPista;

//Es una reserva que realiza un adulto, en la que solo participan adultos en una pista de ese tipo

public class ReservaAdultos extends ReservaAbstracta{
	
	private int numAdults=0;
	
	public int getNumAdults() {
		return numAdults;
	}

	public void setNumAdults(int numAdults) {
		this.numAdults = numAdults;
	}
	
	public ReservaAdultos(Integer idUser,ArrayList<Usuario> participantes, float precio, LocalDate fecha, Integer minutos, Integer idPista, float descuento) {
		super(idUser, precio, fecha, minutos, idPista, descuento);
		super.players=participantes;
		numAdults=participantes.size();
	}

	public ReservaAdultos() {}

	public DificultadPista type(){
		return DificultadPista.ADULTOS;
	}

	public  boolean validate(){
		return allAdults();
	};

	@Override
	public String toString() {
		return "ReservaAdulto [idUser=" + idUser + ", precio=" + price + ", fecha=" + date + ", minutos=" + time
				+ ", idPista=" + idPista + ", descuento=" + discount + ",numAdults=" + numAdults + "]";
	}
	
}