package es.pw.uco.data.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

import es.pw.uco.business.user.dto.UserDTO;
import es.pw.uco.data.common.Conexion;

public class UserDAO implements DAO<UserDTO,Integer>{

	@Override
	public boolean insert(UserDTO a) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(UserDTO a) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<UserDTO> getAll() throws SQLException {
		ResultSet rs;
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		return null;
	}

	@Override
	public UserDTO get(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
