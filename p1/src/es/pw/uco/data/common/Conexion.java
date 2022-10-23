package es.pw.uco.data.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    private Conexion(){
        config = new Properties();
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	BufferedReader reader = new BufferedReader(new FileReader(new File("src/config.properties")));
            config.load( reader );
            this.url=config.getProperty("URL");
            this.user=config.getProperty("USER");
            this.passwd=config.getProperty("PASSWORD");
        } catch (IOException e ) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
            System.out.println("Connection with the database established");
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
}