package handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Properties;

import entities.Kart;
import entities.Pista;
import entities.enums.DificultadPista;

//Esta clase será la que gestione las instalaciones y material necesario para dar servicio.

public class CircuitHandler {
	public static String karts_file;
	public static String pistas_file;
	private static ArrayList<Pista> pistaList=new ArrayList<Pista>();
	private static ArrayList<Kart> kartList=new ArrayList<Kart>();
	private static CircuitHandler instance = null;
	
	public static CircuitHandler getInstance() {
        if(CircuitHandler.instance == null) {
        	loadFilesPath();
        	pistaList = loadPistaFile();
        	kartList = loadKartFile();
        	CircuitHandler.instance = new CircuitHandler();
        }
        return CircuitHandler.instance;
    }
	
	
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

    public static ArrayList<Pista> loadPistaFile() {
		ArrayList<Pista> lista = new ArrayList<Pista>();
		try {
			FileInputStream fis = new FileInputStream(pistas_file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			lista = (ArrayList<Pista>) ois.readObject();

			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
    //TODO funcion de load
    
	public static ArrayList<Kart> loadKartFile() {
		ArrayList<Kart> lista = new ArrayList<Kart>();
		try {
			FileInputStream fis = new FileInputStream(karts_file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			if(lista instanceof ArrayList<?>) {
				lista = (ArrayList<Kart>) ois.readObject();	
			}
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
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
	
	public ArrayList<Pista> getAllPistas(){
		return pistaList;
	}	
}
