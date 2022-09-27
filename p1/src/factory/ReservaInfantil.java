package factory;

import java.time.LocalDate;



public class ReservaInfantil extends ReservaAbstracta{
	public ReservaInfantil() {
		super();
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
	
	public ReservaInfantil(Integer idUser, float precio, LocalDate fecha, Integer minutos, Integer idPista, float descuento) {
		super(idUser, precio, fecha, minutos, idPista, descuento);
		try {
			
		    usuarios.forEach((e) -> {
		    	if(e.getId() != null && e.getId().equals(idUser)) {
		    		if(!e.isMayorEdad()) {
			    		
			    	}
		     	}
		    	
		     });
			
		} catch (Exception e) {
			System.out.println(e + "El usuario no es mayor de edad");
		}
	}
	

	
	

}