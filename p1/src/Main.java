import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import entities.Usuario;
import handlers.CircuitHandler;
import handlers.ReservaHandler;
import handlers.UsuarioHandler;

public class Main {

	public static String users_file;
	public static String reserves_file;
	public static String circuits_file;

	public static void main(String[] args) {

		// Gestión del fichero properties
		loadFilesPath();
		
		System.out.println(users_file);
		
		//Declaración de handlers
		UsuarioHandler userHandler = new UsuarioHandler();
		ReservaHandler reserveHandler = new ReservaHandler();
		CircuitHandler circuitHandler = new CircuitHandler();

		// Prueba de escritura en ficheros

	    ArrayList<Usuario> lista = new ArrayList<Usuario>();

	     lista.add(new Usuario("Usuario1", LocalDate.of(2022, 10, 10),"usuario@email.com"));
	     lista.add(new Usuario("Usuario2", LocalDate.of(2022, 10, 10),"usuario2@email.com"));

		//Prueba de lectura desde ficheros
	     writeFile(lista, users_file);

	     for (Usuario usuario : loadFile(users_file)) {
	         System.out.println(usuario);
	     }   
	     
		// Menú
		int mainSelect = 0;
		int subMainSelect = 0;

		Scanner input = new Scanner(System.in);
		do {
			mainMenu();
			mainSelect = input.nextInt();
			
			switch (mainSelect) {
				case 0: // Salir
					
					System.out.println("Saliendo del sistema");
				break;
				
				case 1:
					
					userMenu();
					subMainSelect = input.nextInt();
					input.nextLine();
					
					
					if (subMainSelect == 1) {
						//Declaracion de variables
						Usuario user = new Usuario();
						int year, month, day;
						
						
						System.out.println("Ha seleccionado la opcion anyadir usuario, introduzca los siguientes datos:");
						System.out.println("Nombre de usuario:");
						
						user.setFullName(input.nextLine());
						
						
						System.out.println("Fecha de nacimiento:");
						System.out.println("Año(YYYY):");
						year = input.nextInt();
						input.nextLine();
						
						
						
						do {
							System.out.println("Mes(MM):");
							month = input.nextInt();
							input.nextLine();
						}
						while(month>12 || month < 1);
						
						
						do {
							System.out.println("Dia(DD):");
							day = input.nextInt();
							input.nextLine();
						}
						while(day > 31 || day < 1);
						
						
						
						user.setBirthdayDate(LocalDate.of(year, month, day));
						
						
						System.out.println("Correo electronico");
						user.setEmail(input.nextLine());
						
						
						System.out.println("Anyadiendo usuario...");
						userHandler.addUser(user);
						System.out.println("El usuario ha sido anyadido correctamente.");
						System.out.println("Mostrando usuario...");
						for( Usuario us: userHandler.getAllUsers()){
							System.out.println(us);
						}
					}
					
				break;
					
				case 2:
				
					circuitMenu();
					subMainSelect = input.nextInt();
					
					if (subMainSelect == 2) {
						
					}
					
				break;
					
				case 3:
					
					
					reserveMenu();
					subMainSelect = input.nextInt();
					
					if (subMainSelect == 3) {
						
					}
					
				break;
				
			}
			
		} while (mainSelect != 0);

		input.close();

	}

	public static void mainMenu() {
		System.out.println("\n\n================================");
		System.out.println("  SISTEMA DE RESERVA DE KARTS");
		System.out.println("================================");
		System.out.println("1) Menú Usuario");
		System.out.println("2) Menú Circuito");
		System.out.println("3) Menú Reserva\n");
		System.out.println("0) Salir\n");
		System.out.println("-> Introduce una opcion: ");
	}

	public static void userMenu() {
		System.out.println("\n\n================================");
		System.out.println("  GESTIÓN DE USUARIOS");
		System.out.println("================================");
		System.out.println("1) Alta de usuario no registrado");
		System.out.println("2) Modificar usuario");
		System.out.println("3) Listar todos los usuarios");
		System.out.println("4) Eliminar usuario");
		System.out.println("5) Ver datos de un usuario");
		System.out.println("6) Atrás");
		System.out.println("0) Salir");
		System.out.println("-> Introduce una opcion: ");
	}

	public static void circuitMenu() {
		System.out.println("\n\n================================");
		System.out.println("  GESTIÓN DE CIRCUITOS");
		System.out.println("================================");
		System.out.println("1) Añadir kart");
		System.out.println("2) Editar kart");
		System.out.println("3) Eliminar kart");
		System.out.println("4) Ver kart de usuario");
		System.out.println("5) Ver todos los karts");
		System.out.println("6) Añadir pista");
		System.out.println("7) Editar pista");
		System.out.println("8) Eliminar pista");
		System.out.println("9) Ver pista por nombre");
		System.out.println("10) Ver todas las pistas");
		System.out.println("11) Atrás");
		System.out.println("0) Salir");
		System.out.println("-> Introduce una opcion: ");
	}

	public static void reserveMenu() {
		System.out.println("\n\n================================");
		System.out.println("  GESTIÓN DE RESERVAS");
		System.out.println("================================");
		System.out.println("1) Añadir reserva");
		System.out.println("2) Modificar reserva");
		System.out.println("3) Ver reserva por usuario");
		System.out.println("4) Ver reserva por pista");
		System.out.println("5) Eliminar reserva");
		System.out.println("6) Atrás");
		System.out.println("0) Salir");
		System.out.println("-> Introduce una opcion: ");
	}

	public static void writeFile(ArrayList<Usuario> list, String path) {

		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(list);
			objectOut.close();
			System.out.println("El objeto se ha guardado correctamente en el fichero");

		} catch (FileNotFoundException ex) {
			System.out.println("ERROR: No se ha encontrado el fichero \"" + path + "\"");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static  ArrayList<Usuario> loadFile(String path) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try
		 {
		     FileInputStream fis = new FileInputStream(path);
		     ObjectInputStream ois = new ObjectInputStream(fis);

		     lista = (ArrayList<Usuario>) ois.readObject();

		     ois.close();
		     fis.close();
		 } 
		 catch (IOException ioe) 
		 {
		     ioe.printStackTrace();

		 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;

	}
	
	
	
	public static void loadFilesPath(){
		Properties prop = new Properties();
		String filename = "src/data.properties";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);

			String path = "datos/";
			users_file = path + prop.getProperty("users_file");
			reserves_file = path + prop.getProperty("reserves_file");
			circuits_file = path + prop.getProperty("circuits_file");
			

		// Captura de excepciones
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se ha encontrado el fichero \"" + filename + "\"");
		} catch (IOException e) {
			System.out.println("ERROR: No se ha podido leer el fichero");
		}
	}

}
