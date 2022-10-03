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

public class Main {

	public static String users_file;
	public static String reserves_file;
	public static String circuits_file;

	public static void main(String[] args) {

		// Gestión del fichero properties
		loadFilesPath();
		
		System.out.println(users_file);

		// Prueba de escritura en ficheros

	    ArrayList<Usuario> lista = new ArrayList<Usuario>();

	     lista.add(new Usuario("Usuario1", LocalDate.of(2001, 10, 5), LocalDate.of(2022, 10, 10),
					"usuario@email.com"));
	     lista.add(new Usuario("Usuario2", LocalDate.of(2000, 10, 5), LocalDate.of(2022, 10, 10),
					"usuario2@email.com"));

		//Prueba de lectura desde ficheros
	     writeFile(lista, users_file);

	     for (Usuario usuario : loadFile(users_file)) {
	         System.out.println(usuario);
	     }   

		// Menú
		int opcion = 0;

		Scanner s = new Scanner(System.in);

		do {
			mainMenu();
			opcion = s.nextInt();
			s.nextLine();
			switch (opcion) {
			case 0: // Salir
				System.out.println("Hasta luego!");
				break;
			case 1:
				userMenu();
				break;
			case 2:
				circuitMenu();
				break;
			case 3:
				reserveMenu();
				break;
			default:
				break;
			}

		} while (opcion != 0);

		s.close();

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
