package es.uco.pw.data.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import es.uco.pw.business.circuit.dto.*;
import es.uco.pw.business.enums.DificultadPista;
import es.uco.pw.data.common.Conexion;

public class PistaDAO implements DAO<PistaDTO,Integer>{

	@Override
	public boolean insert(PistaDTO a) {
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("INSERT_PISTA");
		try{
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1,a.getNombre());
			st.setString(2,a.getDificultad());
			st.setInt(3,a.getMaxKarts());
			st.setInt(4, a.isAvailable());
			
			return st.executeUpdate()==1;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(PistaDTO a) {
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("UPDATE_PISTA");
		try{
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1,a.getNombre());
			st.setString(2,a.getDificultad());
			st.setInt(3,a.getMaxKarts());
			st.setInt(4,a.isAvailable());
			st.setInt(5,a.getId());
			return st.executeUpdate()==1;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id){
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("DELETE_PISTA");
		try{
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1,id);
			
			return st.executeUpdate()==1;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<PistaDTO> getAll(){
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("SELECT_ALL_PISTA");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			ResultSet rs=st.executeQuery();
			ArrayList<PistaDTO> pistas=new ArrayList<PistaDTO>();
			while(rs.next()) {
				pistas.add(new PistaDTO(rs.getInt("id"),rs.getString("nombre"), rs.getString("dificultad"), rs.getInt("maxKarts"),rs.getInt("disponible")));
			}
			return pistas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public PistaDTO get(Integer id){
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("SELECT_ID_PISTA");
		try{
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1,id);
			ResultSet rs=st.executeQuery();
			if(rs.next()) return new PistaDTO(rs.getInt("id"),rs.getString("nombre"), rs.getString("dificultad"), rs.getInt("maxKarts"),rs.getInt("disponible"));

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*SELECT_PISTAS_BY_PARAMS = SELECT distinct Pista.* FROM Pista 
	WHERE Pista.disponible = 1
	 AND Pista.dificultad = ?  
	 AND (Pista.maxKarts >= ?) 
	  AND Pista.id not in (Select Reserva.idPista from Reserva where Reserva.idPista = Pista.id AND Reserva.fecha BETWEEN ? AND ?);
	*/
	public ArrayList<PistaDTO> getFreePistas(DificultadPista tipo,LocalDateTime fechaInicio,LocalDateTime fechaFin,Integer numAdults,Integer numChilds){
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("SELECT_PISTAS_BY_PARAMS");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1,tipo.toString());
			st.setInt(2, (numAdults+numChilds) );
			st.setString(3,(fechaInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))+":00"));
			st.setString(4,(fechaFin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))+":00"));
			ResultSet rs=st.executeQuery();
			ArrayList<PistaDTO> pistas=new ArrayList<PistaDTO>();
			while(rs.next()) {
				pistas.add(new PistaDTO(rs.getInt("id"),rs.getString("nombre"), rs.getString("dificultad"), rs.getInt("maxKarts"),rs.getInt("disponible")));
			}
			return pistas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Integer getNumKartsByPista (Integer idPista) {
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("SELECT_NUM_KARTS_BY_PISTA");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1,idPista);
			ResultSet rs=st.executeQuery();
			if(rs.next())return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
