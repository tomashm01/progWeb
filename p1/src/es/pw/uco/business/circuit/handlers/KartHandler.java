package es.pw.uco.business.circuit.handlers;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Properties;

import es.pw.uco.business.circuit.dto.KartDTO;
import es.pw.uco.business.circuit.models.Kart;
import es.pw.uco.data.dao.KartDAO;

//Esta clase será la encargada de gestionar la información de los karts.

/**
 * @author mateo
 *
 */
public class KartHandler {
	public static String karts_file;
	private static ArrayList<Kart> kartsList = new ArrayList<Kart>();
	private static KartHandler instance = null;
	private static KartDAO dao;

	public static KartHandler getInstance() {
		if (KartHandler.instance == null) {
			dao=new KartDAO();
			KartHandler.instance = new KartHandler();
		}
		return KartHandler.instance;
	}

	public static ArrayList<Kart> getKartsList() {
		return kartsList;
	}

	public static void setKartList(ArrayList<Kart> kartsList) {
		KartHandler.kartsList = kartsList;
	}

	/**
	 * Dar de alta a un kart, comprobando que no está registrado previamente.
	 * 
	 * @param Kart k
	 * @return boolean
	 */
	public boolean addKart(Kart k) {
		if (existKart(k.getId()))
			return false;
		return dao.insert(new KartDTO(k));
	}

	/**
	 * Busca si el kart existe en la lista de karts por su id
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean existKart(Integer id) {
		for (Kart kart : getAllKarts()) {
			if (kart.getId().equals(id))
				return true;
		}
		return false;
	}


	/**
	 * Busca si existe el kart por id y lo devuelve
	 * 
	 * @param id
	 * @return Kart
	 */
	public Kart getKartbyId(Integer id) {
		for (Kart kart : getAllKarts()) {
			if (kart.getId().equals(id))
				return kart;
		}
		return null;
	}


	/**
	 * Devuelve la lista de todos los karts
	 * 
	 * @return
	 */
	public ArrayList<Kart> getAllKarts() {
		kartsList = new ArrayList<Kart>();
		for(KartDTO it :dao.getAll()) {
			kartsList.add(new Kart(it));
		}
		return kartsList;
	}

	/**
	 * Elimina el kart de la lista si existe
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean removeKart(Integer id) {
		return dao.delete(id);
	}

	/**
	 * Edita el kart de la lista si existe
	 * 
	 * @param k
	 * @return boolean
	 */
	public boolean editKart(Kart k) {
		return dao.update(new KartDTO(k));
	}

	/**
	 * Imprimir todos los id registrados
	 * 
	 * @param
	 * @return void
	 */

	public void printKarts() {
		
		int count = 0;
		for (Kart k : getAllKarts()) {
			System.out.println(count + ") " + k.getId());
			count++;
		}
	}

	/**
	 * Carga fichero de karts
	 */
	@SuppressWarnings("unchecked")
	public static void loadKartFile() {
		try {
			FileInputStream fis = new FileInputStream(karts_file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			if (karts_file.length() != 0) {
				kartsList = (ArrayList<Kart>) ois.readObject();
			}

			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero " + karts_file + " no existe. No hay lista de karts cargadas.");
		} catch (EOFException e) {
			System.out.println("El fichero " + karts_file + " esta vacio.");
		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Carga el path del fichero del kart
	 */

	public static void loadFilesPath() {
		Properties prop = new Properties();
		String filename = "src/data.properties";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);

			String path = "datos/";
			karts_file = path + prop.getProperty("karts_file");

			// Captura de excepciones
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se ha encontrado el fichero \"" + filename + "\"");
		} catch (IOException e) {
			System.out.println("ERROR: No se ha podido leer el fichero");
		}
	}

}
