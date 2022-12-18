package es.uco.pw.data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import es.uco.pw.business.reserve.dto.ReserveDTO;
import es.uco.pw.data.common.Conexion;

public class ReserveDAO implements DAO<ReserveDTO, Integer> {

	@Override
	public boolean insert(ReserveDTO a) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("INSERT_RESERVE");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1, a.getIdUser());
			st.setInt(2, a.getIdPista());
			st.setFloat(3, a.getPrice());
			st.setFloat(4, a.getDiscount());
			st.setString(5,(a.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))+":00"));
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
				reserves.add(new ReserveDTO(rs.getString("idUser"),LocalDateTime.parse(rs.getString("fecha"),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00.0")),
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
			st.setString(1, a.getIdUser());
			st.setInt(2, a.getIdPista());
			st.setFloat(3, a.getPrice());
			st.setFloat(4, a.getDiscount());
			st.setString(5,a.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))+":00");
			st.setInt(6, a.getTime());
			st.setString(7, a.getTipo());
			st.setInt(8, a.getNumAdultos());
			st.setInt(9, a.getNumMenores());
			st.setInt(10, a.getId());
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
				return new ReserveDTO(rs.getString("idUser"),LocalDateTime.parse(rs.getString("fecha"),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00.0")), rs.getInt("duracion"),
						rs.getInt("idPista"), rs.getFloat("precio"), rs.getFloat("descuento"), rs.getInt("id"),
						rs.getString("tipo"), rs.getInt("numAdultos"), rs.getInt("numMenores"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean hasBono(Integer id) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_RESERVE_IF_BONO");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Integer getNextReserveByUser(String email) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_NEXT_IDRESERVE_BY_USER");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1,email);
			st.setString(2,email);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				return  rs.getInt("id");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Integer completedReservationsByUser(String email) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_NUM_RESERVES_BY_USER");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1,email);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				return  rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getByPistaDate(Integer idPista,LocalDateTime fecha) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_IDRESERVE_BY_PISTA_DATE");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1, idPista);
			st.setString(2,fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))+":00");
			ResultSet rs = st.executeQuery();
			if(rs.next())return  rs.getInt("id");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	
	
	public ArrayList<ReserveDTO> getAllReservesByUser(String idUser) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_RESERVE_BY_USER");
		ArrayList<ReserveDTO> array = new ArrayList<ReserveDTO>();
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1, idUser);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				array.add(new ReserveDTO(rs.getString("idUser"),LocalDateTime.parse(rs.getString("fecha"),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00.0")), rs.getInt("duracion"),
						rs.getInt("idPista"), rs.getFloat("precio"), rs.getFloat("descuento"), rs.getInt("id"),
						rs.getString("tipo"), rs.getInt("numAdultos"), rs.getInt("numMenores")));

			}
			return array;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<ReserveDTO> geReservesByUserByDates(LocalDate fechaInicio,LocalDate fechaFin,String idUser) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_RESERVE_BY_USER_BY_DATE");
		ArrayList<ReserveDTO> array = new ArrayList<ReserveDTO>();
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1, idUser);
			st.setDate(2, Date.valueOf(fechaInicio));
			st.setDate(3, Date.valueOf(fechaFin));
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				
				array.add(new ReserveDTO(rs.getString("idUser"),LocalDateTime.parse(rs.getString("fecha"),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00.0")), rs.getInt("duracion"),
						rs.getInt("idPista"), rs.getFloat("precio"), rs.getFloat("descuento"), rs.getInt("id"),
						rs.getString("tipo"), rs.getInt("numAdultos"), rs.getInt("numMenores")));
			}
			return array;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<ReserveDTO> geFutureReservesByUser(String idUser) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_FUTURE_RESERVE_BY_USER");
		ArrayList<ReserveDTO> array = new ArrayList<ReserveDTO>();
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1, idUser);
			st.setDate(2, Date.valueOf(LocalDate.now().plusDays(1)));
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				
				array.add(new ReserveDTO(rs.getString("idUser"),LocalDateTime.parse(rs.getString("fecha"),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00.0")), rs.getInt("duracion"),
						rs.getInt("idPista"), rs.getFloat("precio"), rs.getFloat("descuento"), rs.getInt("id"),
						rs.getString("tipo"), rs.getInt("numAdultos"), rs.getInt("numMenores")));
			}
			return array;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<ReserveDTO> getAllReservesByPista(Integer idPista) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("SELECT_RESERVE_BY_PISTA");
		ArrayList<ReserveDTO> array = new ArrayList<ReserveDTO>();
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1, idPista);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				
				array.add(new ReserveDTO(rs.getString("idUser"),LocalDateTime.parse(rs.getString("fecha"),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00.0")), rs.getInt("duracion"),
						rs.getInt("idPista"), rs.getFloat("precio"), rs.getFloat("descuento"), rs.getInt("id"),
						rs.getString("tipo"), rs.getInt("numAdultos"), rs.getInt("numMenores")));
			}
			return array;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean pairKartReserve(Integer idReserva,Integer idKart) {
		Conexion conexController = Conexion.getInstance();
		Connection conex = conexController.getConnection();
		String query = conexController.getSql().getProperty("INSERT_RESERVE_KART");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(2,idReserva);
			st.setInt(1, idKart);
			return st.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
