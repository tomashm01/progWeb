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

import es.pw.uco.business.circuit.dto.PistaDTO;
import es.pw.uco.business.circuit.models.Pista;
import es.pw.uco.data.dao.PistaDAO;

//Esta clase será la encargada de gestionar la información de las pistas.

/**
 * @author mateo
 *
 */
public class PistaHandler {
	public static String pistas_file;
	private static ArrayList<Pista> pistasList = new ArrayList<Pista>();
	private static PistaHandler instance = null;
	private static PistaDAO dao;

	public static PistaHandler getInstance() {
		if (PistaHandler.instance == null) {
			dao=new PistaDAO();
			PistaHandler.instance = new PistaHandler();
		}
		return PistaHandler.instance;
	}

	public static ArrayList<Pista> getPistasList() {
		return pistasList;
	}

	public static void setPistaList(ArrayList<Pista> pistasList) {
		PistaHandler.pistasList = pistasList;
	}

	/**
	 * Dar de alta a una pista, comprobando que no está registrada previamente.
	 * 
	 * @param Pista p
	 * @return boolean
	 */
	public boolean addPista(Pista p) {
		if (existPista(p.getId()))
			return false;
		return dao.insert(new PistaDTO(p));
	}

	/**
	 * Busca si la pista existe en la lista de pistas por su id
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean existPista(Integer id) {
		for (Pista pista : getAllPistas()) {
			if (pista.getId().equals(id))
				return true;
		}
		return false;
	}


	/**
	 * Busca si existe la pista por id y lo devuelve
	 * 
	 * @param id
	 * @return Pista
	 */
	public Pista getPistabyId(Integer id) {
		for (Pista pista : getAllPistas()) {
			if (pista.getId().equals(id))
				return pista;
		}
		return null;
	}


	/**
	 * Devuelve la lista de todos las pistas
	 * 
	 * @return
	 */
	public ArrayList<Pista> getAllPistas() {
		pistasList = new ArrayList<Pista>();
		for(PistaDTO it :dao.getAll()) {
			pistasList.add(new Pista(it));
		}
		return pistasList;
	}

	/**
	 * Elimina la pista de la lista si existe
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean removePista(Integer id) {
		return dao.delete(id);
	}

	/**
	 * Edita la pista de la lista si existe
	 * 
	 * @param p
	 * @return boolean
	 */
	public boolean editPista(Pista p) {
		return dao.update(new PistaDTO(p));
	}

	/**
	 * Imprimir todos los id registrados
	 * 
	 * @param
	 * @return void
	 */

	public void printPistas() {
		
		int count = 0;
		for (Pista p : getAllPistas()) {
			System.out.println(count + ") " + p.getName());
			count++;
		}
	}

	/**
	 * Carga fichero de pistas
	 */
	@SuppressWarnings("unchecked")
	public static void loadPistaFile() {
		try {
			FileInputStream fis = new FileInputStream(pistas_file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			if (pistas_file.length() != 0) {
				pistasList = (ArrayList<Pista>) ois.readObject();
			}

			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero " + pistas_file + " no existe. No hay lista de pistas cargadas.");
		} catch (EOFException e) {
			System.out.println("El fichero " + pistas_file + " esta vacio.");
		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Carga el path del fichero de pista
	 */

	public static void loadFilesPath() {
		Properties prop = new Properties();
		String filename = "src/data.properties";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);

			String path = "datos/";
			pistas_file = path + prop.getProperty("pistas_file");

			// Captura de excepciones
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se ha encontrado el fichero \"" + filename + "\"");
		} catch (IOException e) {
			System.out.println("ERROR: No se ha podido leer el fichero");
		}
	}

}
