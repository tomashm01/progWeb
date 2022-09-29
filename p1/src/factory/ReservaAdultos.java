package factory;

import java.time.LocalDate;
import java.util.ArrayList;

import entities.Usuario;

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
		super.participantes=participantes;
		numAdults=participantes.size();
	}

	public ReservaAdultos() {}

	@Override
	public String toString() {
		return "ReservaAdulto [idUser=" + idUser + ", precio=" + precio + ", fecha=" + fecha + ", minutos=" + minutos
				+ ", idPista=" + idPista + ", descuento=" + descuento + ",numAdults=" + numAdults + "]";
	}
	
}