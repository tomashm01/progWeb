package handlers;

import java.util.ArrayList;

import entities.Usuario;

//Esta clase será la encargada de gestionar la información de los usuarios que hacen reservas.

public class UsuarioHandler {

	private static ArrayList<Usuario> usersList=new ArrayList<Usuario>();
	private static UsuarioHandler instance = null;
	
	public static UsuarioHandler getInstance() {
        if(UsuarioHandler.instance == null) {
            UsuarioHandler.instance = new UsuarioHandler();
        }
        return UsuarioHandler.instance;
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
	
	//TODO: implementar metodo para leer de fchero csv, escribir en fichero csv y añadir csv

}
