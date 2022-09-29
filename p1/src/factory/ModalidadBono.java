package factory;

import java.util.ArrayList;

public class ModalidadBono extends ModalidadAbstracta{

	private ArrayList<ReservaAbstracta> bonoList=new ArrayList<ReservaAbstracta>();
	
	@Override
	public ReservaInfantil createReservaInfantil() {
		ReservaInfantil modalidad = new ReservaInfantil();
		bonoList.add(modalidad);
		return modalidad;
	}
	
	
	@Override
	public ReservaFamiliar createReservaFamiliar() {
		ReservaFamiliar modalidad = new ReservaFamiliar();
		bonoList.add(modalidad);
		return modalidad;
	}
	
	@Override
	public ReservaAdultos createReservaAdultos() {
		ReservaAdultos modalidad = new ReservaAdultos();
		bonoList.add(modalidad);
		return modalidad;
	}



}
