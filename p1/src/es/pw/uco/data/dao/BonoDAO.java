package es.pw.uco.data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.ResultSet;

import es.pw.uco.business.reserve.dto.BonoDTO;
import es.pw.uco.data.common.Conexion;

public class BonoDAO implements DAO<BonoDTO, Integer> {

	@Override
	public boolean insert(BonoDTO a) throws SQLException {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("INSERT_BONO");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setDate(1, Date.valueOf(a.getExpirationDate()));
			return st.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Integer> getIDReservesBono(Integer idBono) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_RESERVES_BY_IDBONO");

		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1, idBono);
			ResultSet rs = st.executeQuery();
			ArrayList<Integer> bonos = new ArrayList<Integer>();
			while (rs.next()) {
				bonos.add(rs.getInt("idReserva"));
			}
			return bonos;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean update(BonoDTO a) throws SQLException {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("UPDATE_BONO");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setDate(1, Date.valueOf(a.getExpirationDate()));
			st.setInt(2, a.getId());

			return st.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("DELETE_BONO");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1, id);
			return st.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<BonoDTO> getAll() throws SQLException {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_ALL_BONO");

		try {
			PreparedStatement st = conex.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			ArrayList<BonoDTO> bonos = new ArrayList<BonoDTO>();
			while (rs.next()) {
				bonos.add(new BonoDTO(rs.getInt("id"), rs.getDate("fechaExpiracion").toLocalDate()));
			}
			return bonos;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public BonoDTO get(Integer id) throws SQLException {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_ID_BONO");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery(query);
			if (rs.next())
				return new BonoDTO(rs.getInt("id"), rs.getDate("fechaExpiracion").toLocalDate());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
