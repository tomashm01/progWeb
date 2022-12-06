package es.uco.pw.business.reserve.models;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import es.uco.pw.business.reserve.dto.BonoDTO;

public class Bono implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> bonoList=new ArrayList<Integer>();
	private int id;
	private static int numBono=0;
	private LocalDate expirationDate; 
	
	public Bono(Integer idReserve) {
		numBono++;
		this.id = numBono;
		expirationDate= LocalDate.now().plus(1, ChronoUnit.YEARS);
		bonoList.add(idReserve);
	}
	
	public Bono(BonoDTO bono) {
		this.id = bono.getId();
		this.expirationDate = bono.getExpirationDate();
		this.setBonoList(bono.getReserves());
	}
	
	public ArrayList<Integer> getBonoList() {
		return bonoList;
	}

	public void setBonoList(ArrayList<Integer> bonoList) {
		this.bonoList = bonoList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "Bono [bonoList=" + bonoList + ", id=" + id + ", expirationDate=" + expirationDate + "]";
	}
	
}
