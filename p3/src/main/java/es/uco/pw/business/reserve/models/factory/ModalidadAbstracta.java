package es.uco.pw.business.reserve.models.factory;

import java.time.LocalDateTime;


public abstract class ModalidadAbstracta {

	public abstract ReservaInfantil createReservaInfantil(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numChilds);
	
	public abstract ReservaFamiliar createReservaFamiliar(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numAdults,Integer numChilds);
	
	public abstract ReservaAdultos createReservaAdultos(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numAdults);
	
}	
