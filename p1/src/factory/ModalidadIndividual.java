package factory;

public class ModalidadIndividual extends ModalidadAbstracta{

	@Override
	public ReservaInfantil createReservaInfantil() {
		ReservaInfantil modalidad = new ReservaInfantil();
		return modalidad;
	}
	
	
	@Override
	public ReservaFamiliar createReservaFamiliar() {
		ReservaFamiliar modalidad = new ReservaFamiliar();
		return modalidad;
	}
	
	@Override
	public ReservaAdultos createReservaAdultos() {
		ReservaAdultos modalidad = new ReservaAdultos();
		return modalidad;
	}

	
}
