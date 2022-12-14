package es.pw.uco.business.circuit.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import es.pw.uco.business.circuit.dto.KartDTO;
import es.pw.uco.business.circuit.dto.PistaDTO;
import es.pw.uco.business.circuit.models.Kart;
import es.pw.uco.business.circuit.models.Pista;
import es.pw.uco.business.enums.DificultadPista;
import es.pw.uco.data.dao.KartDAO;
import es.pw.uco.data.dao.PistaDAO;

//Esta clase será la que gestione las instalaciones y material necesario para dar servicio.

public class CircuitHandler {
	public static String karts_file;
	public static String pistas_file;
	private static CircuitHandler instance = null;
	
	private static PistaDAO daoPista;
	private static KartDAO daoKart;
	
	public static CircuitHandler getInstance() {
        if(CircuitHandler.instance == null) {
        	daoPista=new PistaDAO();
        	daoKart= new KartDAO();
        	CircuitHandler.instance = new CircuitHandler();
        }
        return CircuitHandler.instance;
    }


	
	/**
	 * Dar de alta a un kart, comprobando que no está registrado previamente.
	 * @param newKart
	 * @return boolean
	 */
	public boolean addKart(Kart newKart) {
		if(existKart(newKart.getId())) return false;
		return daoKart.insert(new KartDTO(newKart));
	}
	
	/**
	 * Busca si el idKart existe en la lista actual de karts
	 * @param id
	 * @return boolean
	 */
	public boolean existKart(Integer id) {
		return ( daoKart.get(id) != null );
	}
	
	/**
	 * Obtiene un kart dado un id
	 * @param id
	 * @return Kart
	 */
	public Kart getKartByID(Integer id) {
		return (new Kart(daoKart.get(id)));
	}
	

	/**
	 * Elimina el kart dado su id si existe
	 * @param id
	 * @return boolean
	 */
	public boolean removeKart(Integer id){
		return daoKart.delete(id);
	}

	/**
	 * Edita un kart si existe
	 * @param kart
	 * @return boolean
	 */
	public boolean editKart(Kart kart){
		return daoKart.update(new KartDTO(kart));
	}
	
	/**
	 * Devuelve los karts asociados a una pista
	 * @param idPista
	 * @return ArrayList<Kart>
	 */
	public ArrayList<Kart> getKartsByIDPista(Integer idPista) {
		ArrayList<Kart> kartList = new ArrayList<Kart>();
		for(KartDTO it : daoKart.getKartsByPista(idPista)) {
			kartList.add(new Kart(it));
		}
		return kartList;
	}
	
	/**
	 * Devuelve la lista de todos los karts
	 * @return ArrayList<Kart>
	 */
	public ArrayList<Kart> getAllKarts(){
		ArrayList<Kart> kartList = new ArrayList<Kart>();
		for(KartDTO it : daoKart.getAll()) {
			kartList.add(new Kart(it));
		}
		return kartList;
	}
	
	/**
	 * Imprimir todos los karts registrados
	 * @param 
	 * @return void 
	 */
	
	public void printAllKarts() {
		int count = 0;
		
		for( Kart k: getAllKarts()){
			System.out.println(count + ") " + k);
			count ++;
		}
	}
	
	/**
	 * Imprime pistas sin los arrayList de karts
	 */
	public void printAllPistasWithoutKarts() {
		int count = 0;
		
		for( Pista p: getAllPistas()){
			System.out.println(count + ") " + p.toStringWithoutKarts());
			count ++;
		}
	}
	
	
	/**
	 * Dar de alta a un pista, comprobando que no está registrado previamente.
	 * @param newPista
	 * @return boolean
	 */
	public boolean addPista(Pista newPista) {
		if(existPista(newPista.getId())) return false;
		return daoPista.insert(new PistaDTO (newPista));
	}
	
	/**
	 * Busca si el idPista existe en la lista actual de pista
	 * @param id
	 * @return boolean
	 */
	public boolean existPista(Integer id) {
		return ( daoPista.get(id) != null );		
	}
	/**
	 * Devuelve la pista sabiendo su id
	 * @param id
	 * @return Pista
	 */
	public Pista getPistaByID(Integer id) {
		Pista aux = new Pista( daoPista.get(id) );
		ArrayList<Kart> Karts = new ArrayList<Kart>();
		for(KartDTO it : daoKart.getKartsByPista(id)) {
			Karts.add(new Kart(it));
		}
		aux.setKartsList(Karts);
		return aux;
	}

	/**
	 * Devuelve el id de la pista sabiendo su nombre
	 * @param name
	 * @return Integer
	 */
	public Integer getIDPistaByName(String name) {
		for(Pista pista: getAllPistas()){
			if(pista.getName().equals(name)){
				return pista.getId();
			}
		}
		return null;
	}
	
	/**
	 * Elimina pista de la lista
	 * @param id
	 * @return boolean
	 */
	public boolean removePista(Integer id){
		return daoPista.delete(id);
	}

	/**
	 * Edita datos de una pista que exista en la lista
	 * @param pista
	 * @return boolean
	 */
	public boolean editPista(Pista pista){
		return daoPista.update(new PistaDTO(pista));
	}


	/**
	 * Obtiene la dificultad sabiendo el idPista
	 * @param idPista
	 * @return DificultadPista
	 */
	public DificultadPista getDifficultyByID(Integer idPista) {
		return getPistaByID(idPista).getDifficulty();
	}
	
	/**
	 * Devuelve las pistas en mantenimiento
	 * @return ArrayList<Pista> 
	 */
	public ArrayList<Pista> getMantenimientoPistas(){
		ArrayList<Pista> mantenPista = new ArrayList<Pista>();
		for(Pista pista: getAllPistas()){
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
	 * @return ArrayList<Pista> 
	 */
	public ArrayList<Pista> getFreePistas(Integer numKarts,DificultadPista difficulty){
		ArrayList<Pista> pistasFree=new ArrayList<Pista>();
		for(Pista it : getAllPistas()) {
			if((it.isAvailable()) && 
			   (it.getDifficulty().equals(difficulty)) && 
			   (it.getMaxKarts()-it.getKartsList().size())>=numKarts
			  ) {
				pistasFree.add(it);
			}
		}
		return pistasFree;
	}
	
	/**
	 * Imprimir todos las pistas registradas
	 * @param 
	 * @return void 
	 */
	public void printAllPistas() {
		int count = 0;
		
		for( Pista p: getAllPistas()){
			System.out.println(count + ") " + p);
			count ++;
		}
	}
	
	/**
	 * Imprime pistas sin los arrayList de pistas
	 */
	
	public void printAllPistasByName() {
		int count = 0;
		
		for( Pista p: getAllPistas()){
			System.out.println(count + ") " + p.getName());
			count ++;
		}
	}
	
	/**
	 * Develve todas las pistas
	 * @return ArrayList<Pista> 
	 */
	public ArrayList<Pista> getAllPistas(){
		ArrayList<Pista> pistaList = new ArrayList<Pista>();
		for(PistaDTO it : daoPista.getAll()) {
			Pista aux = new Pista(it);
			aux.setKartsList(getKartsByIDPista(aux.getId()));
			pistaList.add(aux);
		}
		return pistaList;
	}	
	
	
	/**
	 * Carga path de karts y pistas
	 */
	public static void loadFilesPath() {
		Properties prop = new Properties();
		String filename = "src/data.properties";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);

			String path = "datos/";
			karts_file = path + prop.getProperty("karts_file");
			pistas_file = path + prop.getProperty("pistas_file");

			// Captura de excepciones
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se ha encontrado el fichero \"" + filename + "\"");
		} catch (IOException e) {
			System.out.println("ERROR: No se ha podido leer el fichero");
		}
	}

}
