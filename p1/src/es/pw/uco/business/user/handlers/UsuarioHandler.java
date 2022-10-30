package es.pw.uco.business.user.handlers;

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

import es.pw.uco.business.user.dto.UserDTO;

import es.pw.uco.business.user.models.Usuario;
import es.pw.uco.data.dao.UserDAO;

//Esta clase será la encargada de gestionar la información de los usuarios que hacen reservas.

/**
 * @author tomas
 *
 */
public class UsuarioHandler {
	public static String users_file;
	private static ArrayList<Usuario> usersList = new ArrayList<Usuario>();
	private static UsuarioHandler instance = null;
	private static UserDAO dao;

	public static UsuarioHandler getInstance() {
		if (UsuarioHandler.instance == null) {
			dao=new UserDAO();
			UsuarioHandler.instance = new UsuarioHandler();
		}
		return UsuarioHandler.instance;
	}

	public static ArrayList<Usuario> getUsersList() {
		return usersList;
	}

	public static void setUsersList(ArrayList<Usuario> usersList) {
		UsuarioHandler.usersList = usersList;
	}

	/**
	 * Dar de alta a un usuario, comprobando que no está registrado previamente.
	 * 
	 * @param Usuario u
	 * @return boolean
	 */
	public boolean addUser(Usuario u) {
		if (existUser(u.getId()) || existEmail(u.getEmail()))
			return false;
		return dao.insert(new UserDTO(u));
	}

	/**
	 * Busca si el correo existe en la lista actual de usuarios
	 * 
	 * @param correo
	 * @return boolean
	 */
	public boolean existEmail(String correo) {
		for (Usuario user : getAllUsers()) {
			if (user.getEmail().equals(correo))
				return true;
		}
		return false;
	}

	/**
	 * Busca si el idUsuario existe en la lista actual de usuarios
	 * 
	 * @param id
	 * @return boolean
	 */

	public boolean existUser(Integer id) {
		for (Usuario user : getAllUsers()) {
			if (user.getId().equals(id))
				return true;
		}
		return false;
	}

	/**
	 * Busca si existe el usuario por id y lo devuelve
	 * 
	 * @param id
	 * @return Usuario
	 */
	public Usuario getUserByID(Integer id) {
		for (Usuario user : getAllUsers()) {
			if (user.getId().equals(id))
				return user;
		}
		return null;
	}

	/**
	 * Devuelve el id de usuario a partir de su correo
	 * 
	 * @param email
	 * @return Integer
	 */
	public Integer getIdByEmail(String email) {
		for (Usuario user : getAllUsers()) {
			if (user.getEmail().equals(email))
				return user.getId();
		}
		return null;
	}

	/**
	 * Devuelve la lista de todos los usuarios
	 * 
	 * @return
	 */
	public ArrayList<Usuario> getAllUsers() {
		usersList = new ArrayList<Usuario>();
		for(UserDTO it :dao.getAll()) {
			usersList.add(new Usuario(it));
		}
		return usersList;
	}

	/**
	 * Elimina el usuario de la lista si existe
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean removeUser(Integer id) {
		return dao.delete(id);
	}

	/**
	 * Edita el usuario de la lista si existe
	 * 
	 * @param u
	 * @return boolean
	 */
	public boolean editUser(Usuario user) {
		return dao.update(new UserDTO(user));
	}

	/**
	 * Imprimir todos los id registrados
	 * 
	 * @param
	 * @return void
	 */

	public void printNameUsers() {
		
		int count = 0;
		for (Usuario us : getAllUsers()) {
			System.out.println(count + ") " + us.getFullName());
			count++;
		}
	}

	/**
	 * Carga fichero de usuario
	 */
	@SuppressWarnings("unchecked")
	public static void loadUserFile() {
		try {
			FileInputStream fis = new FileInputStream(users_file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			if (users_file.length() != 0) {
				usersList = (ArrayList<Usuario>) ois.readObject();
			}

			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero " + users_file + " no existe. No hay lista de usuarios cargadas.");
		} catch (EOFException e) {
			System.out.println("El fichero " + users_file + " esta vacio.");
		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Carga el path del fichero del usuario
	 */

	public static void loadFilesPath() {
		Properties prop = new Properties();
		String filename = "src/data.properties";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);

			String path = "datos/";
			users_file = path + prop.getProperty("users_file");

			// Captura de excepciones
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se ha encontrado el fichero \"" + filename + "\"");
		} catch (IOException e) {
			System.out.println("ERROR: No se ha podido leer el fichero");
		}
	}

}
