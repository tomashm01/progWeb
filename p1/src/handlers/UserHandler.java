package handlers;

import java.util.ArrayList;

import entities.Usuario;

public class UserHandler {

	private static ArrayList<Usuario> usersList=new ArrayList<Usuario>();
	private static UserHandler instance = null;
	
	public static UserHandler getInstance() {
        if(UserHandler.instance == null) {
            UserHandler.instance = new UserHandler();
        }
        return UserHandler.instance;
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
	public boolean existUser(int id) {
		for(Usuario user:usersList) {
			if(id==user.getId()) return true;
		}
		return false;
	}
	
	/**
	 * Busca si existe el usuario por id y lo devuelve
	 * @param id
	 * @return
	 */
	public Usuario getUserByID(int id) {
		for(Usuario user:usersList) {
			if(id==user.getId()) return user;
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
	public boolean removeUser(int id){
		for(int i=0;i<usersList.size();i++) {
			if(usersList.get(i).getId() == id){
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
			if(usersList.get(i).getId() == user.getId()){
				usersList.set(i, user);
				return true;
			}
		}
		return false;
	}

}
