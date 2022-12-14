package es.pw.uco.business.reserve.models.factory;

import java.time.LocalDateTime;

import es.pw.uco.business.reserve.handlers.ReservaHandler;

public class ModalidadIndividual extends ModalidadAbstracta{
	
	@Override
	public ReservaInfantil createReservaInfantil(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numChilds) {
		ReservaInfantil modalidad = new ReservaInfantil( idUser,  date,  time,  idPista,  price, discount,  id, numChilds);
		if(!ReservaHandler.getInstance().addReservaIndividual(modalidad)) modalidad=null;
		return modalidad;
	}

	@Override
	public ReservaFamiliar createReservaFamiliar(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numAdults,Integer numChilds) {
		ReservaFamiliar modalidad = new ReservaFamiliar( idUser,  date,  time,  idPista,  price, discount,  id, numAdults, numChilds);
		if(!ReservaHandler.getInstance().addReservaIndividual(modalidad)) modalidad=null;
		return modalidad;
	}

	@Override
	public ReservaAdultos createReservaAdultos(String idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numAdults) {
		ReservaAdultos modalidad = new ReservaAdultos( idUser,  date,  time,  idPista,  price, discount,  id, numAdults);
		if(!ReservaHandler.getInstance().addReservaIndividual(modalidad)) modalidad=null;
		return modalidad;
	}

}
