package es.pw.uco.data.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import es.pw.uco.business.user.dto.UserDTO;

public class UserDAO implements DAO<UserDTO,Integer>{

	@Override
	public boolean insertar(UserDTO a) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(UserDTO a) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<UserDTO> obtenerTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO obtenerUno(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
