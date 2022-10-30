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
		Conexion conexController=Conexion.getInstance();
		Connection conex=conexController.getConnection();
		String query=conexController.getSql().getProperty("INSERT_USER");

		try{
			PreparedStatement st = conex.prepareStatement(query);
			st.setInt(1,null);
			st.setString(2,a.getEmail());
			st.setDate(3,a.getDate());
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public UserDTO get(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
