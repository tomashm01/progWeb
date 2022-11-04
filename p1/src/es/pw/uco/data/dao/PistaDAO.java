package es.pw.uco.data.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import es.pw.uco.business.circuit.dto.*;
import es.pw.uco.data.common.Conexion;

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


}
