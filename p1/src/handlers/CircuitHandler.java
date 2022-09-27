package handlers;

import java.util.ArrayList;

import entities.Kart;
import entities.Pista;
import entities.enums.DificultadPista;

//Esta clase será la que gestione las instalaciones y material necesario para dar servicio.

public class CircuitHandler {
	
	private static ArrayList<Pista> pistaList=new ArrayList<Pista>();
	private static ArrayList<Kart> kartList=new ArrayList<Kart>();
	private static CircuitHandler instance = null;
	
	public static CircuitHandler getInstance() {
        if(CircuitHandler.instance == null) {
        	CircuitHandler.instance = new CircuitHandler();
        }
        return CircuitHandler.instance;
    }
	
	
	/**
	 * Dar de alta a un kart, comprobando que no está registrado previamente.
	 * @param Kart neKart
	 */
	public boolean addKart(Kart newKart) {
		if(existKart(newKart.getId())) return false;
		kartList.add(newKart);
		return true;
	}
	
	/**
	 * Busca si el idKart existe en la lista actual de karts
	 * @param id
	 * @return boolean
	 */
	public boolean existKart(Integer id) {
		for(Kart kart:kartList) {
			if(kart.getId().equals(id)) return true;
		}
		return false;
	}
	
	/**
	 * Obtiene un kart dado un id
	 * @param id
	 * @return
	 */
	public Kart getKartByID(Integer id) {
		for(Kart it:kartList) {
			if(it.getId().equals(id)) return it;
		}
		return null;
	}
	
	/**
	 * Devuelve el id del kart a traves del idUser
	 * @param idUser
	 * @return
	 */
	public Integer getIdKartByIDUser(Integer idUser) {
		for(Kart kart: kartList){
			if(kart.getIdUser().equals(idUser)){
				return kart.getId();
			}
		}
		return null;
	}
	
	/**
	 * Elimina el kart dado su id si existe
	 * @param id
	 * @return
	 */
	public boolean removeKart(Integer id){
		for(int i=0;i<kartList.size();i++){
			if(kartList.get(i).getId().equals(id)){
				kartList.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Edita un kart si existe
	 * @param kart
	 * @return
	 */
	public boolean editKart(Kart kart){
		for(int i=0;i<kartList.size();i++){
			if(kartList.get(i).getId().equals(kart.getId())){
				kartList.set(i, kart);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Devuelve la lista de todos los karts
	 * @return
	 */
	public ArrayList<Kart> getAllKarts(){
		return kartList;
	}
	

	
	
	
	
	/**
	 * Dar de alta a un pista, comprobando que no está registrado previamente.
	 * @param newPista
	 */
	public boolean addPista(Pista newPista) {
		if(existPista(newPista.getId())) return false;
		pistaList.add(newPista);
		return true;
	}
	
	/**
	 * Busca si el idPista existe en la lista actual de pista
	 * @param id
	 * @return boolean
	 */
	public boolean existPista(Integer id) {
		for(Pista pista:pistaList) {
			if(pista.getId().equals(id)) return true;
		}
		return false;
	}
	/**
	 * Devuelve la pista sabiendo su id
	 * @param id
	 * @return
	 */
	public Pista getPistaByID(Integer id) {
		for(Pista it:pistaList) {
			if(it.getId().equals(id)) return it;
		}
		return null;
	}

	/**
	 * Devuelve el id de la pista sabiendo su nombre
	 * @param name
	 * @return
	 */
	public Integer getIDPistaByName(String name) {
		for(Pista pista: pistaList){
			if(pista.getName().equals(name)){
				return pista.getId();
			}
		}
		return null;
	}
	
	public boolean removePista(Integer id){
		for(int i=0;i<pistaList.size();i++){
			if(pistaList.get(i).getId().equals(id)){
				pistaList.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean editPista(Pista pista){
		for(int i=0;i<pistaList.size();i++){
			if(pistaList.get(i).getId().equals(pista.getId())){
				pistaList.set(i, pista);
				return true;
			}
		}
		return false;
	}


	/**
	 * Obtiene la dificultad sabiendo el idPista
	 * @param idPista
	 * @return
	 */
	public DificultadPista getDifficultyByID(Integer idPista) {
		return getPistaByID(idPista).getDifficulty();
	}
	
	/**
	 * Devuelve las pistas en mantenimiento
	 * @return
	 */
	public ArrayList<Pista> getMantenimientoPistas(){
		ArrayList<Pista> mantenPista = new ArrayList<Pista>();
		for(Pista pista: pistaList){
			if( ! pista.isAvailable()){
				mantenPista.add(pista);
			}
		}
		return mantenPista;
	}

	/**
	 * Dado un número de karts y tipo de pista, devolver el conjunto de pistas que estén
	 * libres (no reservadas ni en mantenimiento) y tengan al menos ese número de karts
	 * asociados.
	 * @param numKarts
	 * @param difficulty
	 * @return
	 */
	public ArrayList<Pista> getFreePistas(Integer numKarts,DificultadPista difficulty){
		ArrayList<Pista> pistasFree=new ArrayList<Pista>();
		for(int i=0;i<pistaList.size();i++) {
			if(pistaList.get(i).isAvailable() && 
				pistaList.get(i).getDifficulty().equals(difficulty) &&
				(pistaList.get(i).getMaxKarts()-pistaList.get(i).getKartsList().size())>=numKarts
			){
				pistasFree.add(pistaList.get(i));
			}
		}
		return pistasFree;
	}


	/**
	 * Devuelve los karts asociados a una pista
	 * @param idPista
	 * @return
	 */
	public ArrayList<Kart> getKartsByIDPista(Integer idPista) {
		ArrayList<Kart> kartsPista=new ArrayList<Kart>();
//		
//		for(int i=0;i<kartList.size();i++){
//			Kart kart = kartList.get(i);
//			if(kart.getIdPista().equals(idPista) && kart.getState()!=EstadoKart.MANTENIMIENTO){
//				if(maxKarts<=kartsList.size()) return false;
//				kartsPista.add(kart);
//				
//			}
//		}
		return kartsPista;
	}	
}
