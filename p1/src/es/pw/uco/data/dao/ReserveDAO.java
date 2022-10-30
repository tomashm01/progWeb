package es.pw.uco.data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.pw.uco.business.reserve.dto.ReserveDTO;
import es.pw.uco.data.common.Conexion;

public class ReserveDAO implements DAO<ReserveDTO, Integer> {

	@Override
	public boolean insert(ReserveDTO a) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("INSERT_RESERVE");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1, a.getIdUser());
			st.setInt(2, a.getIdPista());
			st.setFloat(3, a.getPrice());
			st.setFloat(4, a.getDiscount());
			st.setDate(5, Date.valueOf(a.getDate()));
			st.setInt(6, a.getTime());
			st.setString(7, a.getTipo());
			st.setInt(8, a.getNumAdultos());
			st.setInt(9, a.getNumMenores());
			return st.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("DELETE_RESERVE");
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
	public ArrayList<ReserveDTO> getAll() {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_ALL_RESERVE");

		try {
			PreparedStatement st = conex.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			ArrayList<ReserveDTO> reserves = new ArrayList<ReserveDTO>();
			while (rs.next()) {
				reserves.add(new ReserveDTO(rs.getInt("idUser"), rs.getDate("fecha").toLocalDate(),
						rs.getInt("duracion"), rs.getInt("idPista"), rs.getFloat("precio"), rs.getFloat("descuento"),
						rs.getInt("id"), rs.getString("tipo"), rs.getInt("numAdultos"), rs.getInt("numMenores")));
			}
			return reserves;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean update(ReserveDTO a) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("UPDATE_RESERVE");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1, a.getIdUser());
			st.setInt(2, a.getIdPista());
			st.setFloat(3, a.getPrice());
			st.setDate(4, Date.valueOf(a.getDate()));
			st.setInt(5, a.getTime());
			st.setString(6, a.getTipo());
			st.setInt(7, a.getNumAdultos());
			st.setInt(8, a.getNumMenores());
			st.setInt(9, a.getId());

			return st.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ReserveDTO get(Integer id) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_ID_RESERVE");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				return new ReserveDTO(rs.getInt("idUser"), rs.getDate("fecha").toLocalDate(), rs.getInt("duracion"),
						rs.getInt("idPista"), rs.getFloat("precio"), rs.getFloat("descuento"), rs.getInt("id"),
						rs.getString("tipo"), rs.getInt("numAdultos"), rs.getInt("numMenores"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
