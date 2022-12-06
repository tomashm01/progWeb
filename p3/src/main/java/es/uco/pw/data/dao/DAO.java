package es.uco.pw.data.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T,K> {
	boolean insert(T a) throws SQLException;
	boolean update(T a)throws SQLException;
	boolean delete(K id)throws SQLException;
	ArrayList<T>getAll()throws SQLException;
	T get(K id)throws SQLException;
}