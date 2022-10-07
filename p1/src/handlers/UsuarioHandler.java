package handlers;

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
import entities.Usuario;

//Esta clase será la encargada de gestionar la información de los usuarios que hacen reservas.

public class UsuarioHandler {
	public static String users_file;
	private static ArrayList<Usuario> usersList=new ArrayList<Usuario>();
	private static UsuarioHandler instance = null;
	
	
	
	public static UsuarioHandler getInstance() {
        if(UsuarioHandler.instance == null) {
            UsuarioHandler.instance = new UsuarioHandler();
            loadFilesPath();
			loadUserFile();
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
	 * @param Usuario u
	 */
	public boolean addUser(Usuario u) {
		if(existUser(u.getId())) return false;
		usersList.add(u);
		return true;
	}
	
	/**
	 * Busca si el idUsuario existe en la lista actual de usuarios
	 * @param newUser
	 * @return boolean
	 */
	public boolean existUser(String correo) {
		for(Usuario user:usersList) {
			if(user.getEmail().equals(correo)) return true;
		}
		return false;
	}
	
	public boolean existUser(Integer id) {
		for(Usuario user:usersList) {
			if(user.getId().equals(id)) return true;
		}
		return false;
	}
	
	/**
	 * Busca si existe el usuario por id y lo devuelve
	 * @param id
	 * @return
	 */
	public Usuario getUserByID(Integer id) {
		for(Usuario user:usersList) {
			if(user.getId().equals(id)) return user;
		}
		return null;
	}
	
	/**
	 * Devuelve el id de usuario a partir de su correo
	 * @param email
	 * @return
	 */
	public Integer getIdByEmail(String email) {
		for(Usuario user:usersList) {
			if(user.getEmail().equals(email)) return user.getId();
		}
		return null;
	}
	
	/**
	 * Devuelve la lista de todos los usuarios
	 * @return
	 */
	public ArrayList<Usuario> getAllUsers(){
		return usersList;
	}
	
	/**
	 * Elimina el usuario de la lista si existe
	 * @param id
	 * @return
	 */
	public boolean removeUser(Integer id){
		for(int i=0;i<usersList.size();i++) {
			if(usersList.get(i).getId().equals(id)){
				usersList.remove(i);
				return true;
			} 
		}
		return false;
	}

	/**
	 * Edita el usuario de la lista si existe
	 * @param u
	 * @return
	 */
	public boolean editUser(Usuario user){
		for(int i=0;i<usersList.size();i++){
			if(usersList.get(i).getId().equals(user.getId())){
				usersList.set(i, user);
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Imprimir todos los id registrados
	 * @param 
	 * @return void 
	 */
	
	public void printNameUsers() {
		int count = 0;
		
		for( Usuario us: getAllUsers()){
			System.out.println(count + ") " + us.getFullName());
			count ++;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadUserFile() {
		try {
			FileInputStream fis = new FileInputStream(users_file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			if(users_file.length()!=0){
				usersList = (ArrayList<Usuario>) ois.readObject();	
			}
			
			ois.close();
			fis.close();
		}catch(FileNotFoundException e){
			System.out.println("El fichero "+users_file+" no existe. No hay lista de usuarios cargadas.");
		}catch(EOFException e){
			System.out.println("El fichero "+users_file+" esta vacio.");
		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
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
		} 
		catch (IOException e) {
			System.out.println("ERROR: No se ha podido leer el fichero");
		}
	}

}
