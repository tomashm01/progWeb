package es.uco.pw.data.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



/**
 * A class to manage the MySQL connection (general methods and configuration).
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class Conexion {

    protected Connection connection = null;
    private static Conexion instance = null;
    private String url;
    private String user;
    private String passwd;
    private Properties config;
    private Properties sql;

    private Conexion(){

        config = new Properties();
        sql=new Properties();
        try {
        	Class.forName("com.mysql.jdbc.Driver");
 
        	InputStream pathConfig = getClass().getClassLoader().getResourceAsStream("/config.properties");
        	InputStream pathSQL = getClass().getClassLoader().getResourceAsStream("/sql.properties");

        	BufferedReader readerConfig = new BufferedReader(new InputStreamReader(pathConfig));
        	BufferedReader readerSQL = new BufferedReader(new InputStreamReader(pathSQL));

            config.load( readerConfig );
            sql.load( readerSQL );
           
            this.url=config.getProperty("URL");
            this.user=config.getProperty("USER");
            this.passwd=config.getProperty("PASSWORD");
        
        } catch (IOException e ) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
    }
    
    public static Conexion getInstance() {
        if ( instance == null ) {
            return new Conexion();
        }
        return instance;
    }

    private Connection connect() {
        try {
            connection = DriverManager.getConnection(this.url, this.user, this.passwd);
        } catch (SQLException e) {
            System.out.println("Connection with the database has failed!");
            e.printStackTrace();
        }
        return connection;
    }

    public Connection getConnection() {
        if( connection == null ){
            connection = connect();
        }
        return connection;
    }

    private void disconnect() {
        try{
            if ( connection != null && !connection.isClosed() ){
                this.connection.close();
                System.out.println("Connection with database closed");
            }
        }catch ( SQLException e ) {
            System.out.println("Failed to close connection");
            e.printStackTrace();
        }
    }

    public void getDisconnected() {
        if( connection == null ) return;
        disconnect();
    }

    public Properties getConfig() {
        return config;
    }
    
    public Properties getSql() {
        return sql;
    }
}