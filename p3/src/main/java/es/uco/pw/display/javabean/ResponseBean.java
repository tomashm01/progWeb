package es.uco.pw.display.javabean;

import java.time.LocalDateTime;

import es.uco.pw.business.enums.DificultadPista;

public class ResponseBean {
	DificultadPista dif ;
	LocalDateTime fecha;
	Integer duracion;
	Integer numAdults;
	Integer numChilds;
	LocalDateTime fechaFin;
	
	
	
	public ResponseBean(DificultadPista dif, LocalDateTime fecha, Integer duracion, Integer numAdults,
			Integer numChilds, LocalDateTime fechaFin) {
		this.dif = dif;
		this.fecha = fecha;
		this.duracion = duracion;
		this.numAdults = numAdults;
		this.numChilds = numChilds;
		this.fechaFin = fechaFin;
	}
	public DificultadPista getDif() {
		return dif;
	}
	public void setDif(DificultadPista dif) {
		this.dif = dif;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public Integer getNumAdults() {
		return numAdults;
	}
	public void setNumAdults(Integer numAdults) {
		this.numAdults = numAdults;
	}
	public Integer getNumChilds() {
		return numChilds;
	}
	public void setNumChilds(Integer numChilds) {
		this.numChilds = numChilds;
	}
	public LocalDateTime getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
	@Override
	public String toString() {
		return "ResponseBean [dif=" + dif + ", fecha=" + fecha + ", duracion=" + duracion + ", numAdults=" + numAdults
				+ ", numChilds=" + numChilds + ", fechaFin=" + fechaFin + "]";
	}
	
	
}
