package es.pw.uco.data.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

import es.pw.uco.business.circuit.dto.CircuitDTO;
import es.pw.uco.data.common.Conexion;

public class CircuitDAO implements DAO<CircuitDTO,Integer>{

	@Override
	public boolean insert(CircuitDTO a) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CircuitDTO a) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<CircuitDTO> getAll() throws SQLException {
		ResultSet rs;
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		return null;
	}

	@Override
	public CircuitDTO get(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
