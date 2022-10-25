package es.pw.uco.data.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

import es.pw.uco.business.reserve.dto.BonoDTO;
import es.pw.uco.data.common.Conexion;

public class BonoDAO implements DAO<BonoDTO,Integer> {
	

	@Override
	public boolean insert(BonoDTO a) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(BonoDTO a) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<BonoDTO> getAll() throws SQLException {
		ResultSet rs;
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		return null;
	}

	@Override
	public BonoDTO get(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
