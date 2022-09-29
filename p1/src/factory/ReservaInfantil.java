package factory;

import java.time.LocalDate;
import java.util.ArrayList;

import entities.Usuario;
import entities.enums.DificultadPista;

//Es una reserva que realiza un adulto, pero en la que solo participan niños en una pista infantil.

public class ReservaInfantil extends ReservaAbstracta{
	
	private int numChilds=0;
	
	@Override
	public String toString() {
		return "ReservaInfantil [idUser=" + idUser + ", precio=" + price + ", fecha=" + date + ", minutos=" + time
				+ ", idPista=" + idPista + ", descuento=" + discount + ",numChilds=" + numChilds + "]";
	}
	

	public ReservaInfantil() {
		super();
	}

	public int getNumChilds() {
		return numChilds;
	}

	public void setNumChilds(int numChilds) {
		this.numChilds = numChilds;
	}

	/**
	 * Es una reserva que realiza un adulto, pero en la que solo participan
	 * niños en una pista infantil. Se deberá registrar el número de niños que participan.
	 * @param idUser
	 * @param precio
	 * @param fecha
	 * @param minutos
	 * @param idPista
	 * @param descuento
	 */
	
	public ReservaInfantil(Integer idUser,ArrayList<Usuario> participantes, float precio, LocalDate fecha, Integer minutos, Integer idPista, float descuento) {
		super(idUser, precio, fecha, minutos, idPista, descuento);
		super.players=participantes;
		numChilds=participantes.size();
	}

	public DificultadPista type(){
		return DificultadPista.INFANTIL;
	}

	public  boolean validate(){
		return allChilds();
	};

}