package es.pw.uco.data.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import es.pw.uco.business.user.dto.UserDTO;
import es.pw.uco.data.common.Conexion;

public class UserDAO implements DAO<UserDTO,Integer>{

	@Override
	public boolean insert(UserDTO a) {
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("INSERT_USER");
		try{
			PreparedStatement st = conex.prepareStatement(query);
			st.setString(1,a.getEmail());
			st.setDate(2,Date.valueOf(a.getFecha()));
			st.setString(3,a.getNombreCompleto());
			st.setDate(4, Date.valueOf(a.getFechaIncripcion()));
			
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
			st.setString(1,a.getEmail());
			st.setDate(2,Date.valueOf(a.getFecha()));
			st.setString(3,a.getNombreCompleto());
			st.setDate(4, Date.valueOf(a.getFechaIncripcion()));
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
		String query=conexController.getSql().getProperty("DELETE_USER");
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
	public ArrayList<UserDTO> getAll(){
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("SELECT_ALL_USER");
		try {
			PreparedStatement st = conex.prepareStatement(query);
			ResultSet rs=st.executeQuery();
			ArrayList<UserDTO> users=new ArrayList<UserDTO>();
			while(rs.next()) {
				users.add(new UserDTO(rs.getInt("id"),rs.getString("email"), rs.getDate("fechaNacimiento").toLocalDate(), rs.getString("nombreCompleto"), rs.getDate("fechaInscripcion").toLocalDate()));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public UserDTO get(Integer id){
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("SELECT_ID_USER");
		try{
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1,id);
			ResultSet rs=st.executeQuery();
			if(rs.next()) return new UserDTO(rs.getInt("id"),rs.getString("email"), rs.getDate("fechaNacimiento").toLocalDate(), rs.getString("nombreCompleto"), rs.getDate("fechaInscripcion").toLocalDate());

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
