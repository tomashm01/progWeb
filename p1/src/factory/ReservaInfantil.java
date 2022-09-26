package factory;

import java.sql.Date;
import java.time.LocalDate;

import entities.Usuario;

public class ReservaInfantil extends ReservaAbstracta{
	public ReservaInfantil() {
		super();
	}

	public ReservaInfantil(Integer idUser, float precio, LocalDate fecha, Integer minutos, Integer idPista, float descuento) {
		super(idUser, precio, fecha, minutos, idPista, descuento);
		try {
		    usuarios.forEach((e) -> {
		     if(e.getId() != null && e.getId().equals(idUser) && e.isMayorEdad()) {
		    	 System.out.println("HE ENTRADO AL IF");
		     }
		      });
			
		} catch (Exception e) {
			System.out.println(e + "El usuario no es mayor de edad");
		}
	}
	

	
	

}