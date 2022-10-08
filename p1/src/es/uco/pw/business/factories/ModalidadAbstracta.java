package es.uco.pw.business.factories;

import java.time.LocalDateTime;


public abstract class ModalidadAbstracta {

	public abstract ReservaInfantil createReservaInfantil(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numChilds);
	
	public abstract ReservaFamiliar createReservaFamiliar(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numAdults,Integer numChilds);
	
	public abstract ReservaAdultos createReservaAdultos(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numAdults);
	
	public abstract ReservaInfantil createReservaInfantil(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount,Integer numChilds);
	
	public abstract ReservaFamiliar createReservaFamiliar(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount,Integer numAdults,Integer numChilds);
	
	public abstract ReservaAdultos createReservaAdultos(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount,Integer numAdults);
	
}	
