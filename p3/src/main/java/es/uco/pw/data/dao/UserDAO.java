package es.uco.pw.data.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import es.uco.pw.business.user.dto.UserDTO;
import es.uco.pw.data.common.Conexion;

public class UserDAO implements DAO<UserDTO,Integer>{

	@Override
	public boolean insert(UserDTO a) {
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("INSERT_USER");
		try{
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1,a.getEmail());
			st.setString(2,a.getPassword());
			st.setString(3,a.getRol());
			st.setDate(4,Date.valueOf(a.getFecha()));
			st.setString(5,a.getNombreCompleto());
			st.setDate(6, Date.valueOf(a.getFechaIncripcion()));
			
			return st.executeUpdate()==1;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(UserDTO a) {
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("UPDATE_USER");
		try{
			PreparedStatement st = conex.prepareStatement(query);
			
			st.setString(1,a.getPassword());
			st.setString(2,a.getRol());
			st.setDate(3,Date.valueOf(a.getFecha()));
			st.setString(4,a.getNombreCompleto());
			st.setDate(5, Date.valueOf(a.getFechaIncripcion()));
			st.setString(6,a.getEmail());
			return st.executeUpdate()==1;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(String email){
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("DELETE_USER");
		try{
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1,email);
			
			return st.executeUpdate()==1;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id){
		return false;
	}

	@Override
	public ArrayList<UserDTO> getAll(){
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("SELECT_ALL_USER");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			ResultSet rs=st.executeQuery();
			ArrayList<UserDTO> users=new ArrayList<UserDTO>();
			while(rs.next()) {
				users.add(new UserDTO(rs.getString("email"),rs.getString("password"),rs.getString("rol"), rs.getDate("fechaNacimiento").toLocalDate(), rs.getString("nombreCompleto"), rs.getDate("fechaInscripcion").toLocalDate()));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public UserDTO get(String email){
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("SELECT_EMAIL_USER");
		try{
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1,email);
			ResultSet rs=st.executeQuery();
			if(rs.next()) return new UserDTO(rs.getString("email"),rs.getString("password"),rs.getString("rol"), rs.getDate("fechaNacimiento").toLocalDate(), rs.getString("nombreCompleto"), rs.getDate("fechaInscripcion").toLocalDate());

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDTO get(Integer id){
		return null;
	}


}
