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
		
		//Valores basura
		

		userHandler.addUser(new Usuario("Usuario1", LocalDate.of(2022, 10, 10),"usuario@email.com"));
		userHandler.addUser(new Usuario("Usuario2", LocalDate.of(2022, 10, 10),"usuario2@email.com"));
		
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
	    // Declaracion de variables
	     
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
					
					//AÑADIR USUARIO
					if (subMainSelect == 1) {
						//Declaracion de variables
						Usuario u1 = new Usuario();
						int year, month, day;
						
						
						System.out.println("Ha seleccionado la opcion anyadir usuario, introduzca los siguientes datos:");
						
						System.out.println("Nombre completo:");
						u1.setFullName(input.nextLine());
							
						
				
						
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
						
						
						
						u1.setBirthdayDate(LocalDate.of(year, month, day));
						
						
						System.out.println("Correo electronico:");
						u1.setEmail(input.nextLine());
						
						
						System.out.println("Anyadiendo usuario...");
						userHandler.addUser(u1);
						System.out.println("El usuario ha sido anyadido correctamente.");
						System.out.println("Mostrando usuario...");
						for( Usuario us: userHandler.getAllUsers()){
							System.out.println(us);
						}
					}
					
					//MODIFICAR USUARIO
					else if (subMainSelect == 2) {
						//Declaracion de variables
						Usuario u2 = new Usuario();
						Usuario u3 = new Usuario();
						int year, month, day, id;
						
						System.out.println("Estos son los usuarios disponibles para modificar.");
						for( Usuario us: userHandler.getAllUsers()){
							System.out.println(us);
						}
						
						System.out.println("Selecciona el id del usuario que quieres modificar.");
						id = input.nextInt();
						input.nextLine();
						u2 = userHandler.getUserByID(id);
						System.out.println(u2);
						
						System.out.println("Introduce los nuevos datos del usuario:");
						System.out.println("Nombre completo:");
						u3.setFullName(input.nextLine());
						
						
							
						
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
						
						u3.setBirthdayDate(LocalDate.of(year, month, day));
						
						
						System.out.println("Correo electronico:");
						u3.setEmail(input.nextLine());
						/*
						userHandler.removeUser(u2.getId());
						u3.setId(id);
						userHandler.addUser(u3);
						*/
						//userHandler.editUser()
						System.out.println("Mostrando el nuevo usuario...");
						
						for( Usuario us: userHandler.getAllUsers()){
							System.out.println(us);
						}
					}
					
					
					else if (subMainSelect == 3) {
						
						System.out.println("Estos son los usuarios de la lista.");
						for( Usuario us: userHandler.getAllUsers()){
							System.out.println(us);
						}
						
					}
					else if (subMainSelect == 5) {
						System.out.println("Selecciona el id del usuario que quieres modificar.");
						System.out.println(userHandler.getUserByID(input.nextInt()));
						input.nextLine();
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
