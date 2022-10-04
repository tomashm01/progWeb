import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;


import entities.*;
import entities.enums.*;
import factory.ReservaAbstracta;
import handlers.*;


public class Main {

	public static String users_file;
	public static String reserves_file;
	public static String karts_file;
	public static String pistas_file;
	Kart nuevo = new Kart();
	public static void main(String[] args) {

		// Gestión del fichero properties
		loadFilesPath();

		System.out.println(users_file);

		UsuarioHandler.getInstance().addUser(new Usuario("Usuario1", LocalDate.of(2022, 10, 10), "usuario@email.com"));
		UsuarioHandler.getInstance().addUser(new Usuario("Usuario2", LocalDate.of(2022, 10, 10), "usuario2@email.com"));

		// Prueba de escritura en ficheros
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		lista.add(new Usuario("Usuario1", LocalDate.of(2022, 10, 10), "usuario@email.com"));
		lista.add(new Usuario("Usuario2", LocalDate.of(2022, 10, 10), "usuario2@email.com"));

		// Prueba de lectura desde ficheros
		writeFile();

		for (Usuario usuario : loadUserFile()) {
			System.out.println(usuario);
		}

		// Menú
		// Declaracion de variables
		String fullName, email;
		boolean valid = false;
		int mainSelect = 0;
		int subMainSelect = 0;
		LocalDate date = LocalDate.now();

		Scanner input = new Scanner(System.in);
		do {
			mainMenu();
			mainSelect = input.nextInt();
			input.nextLine();
			switch (mainSelect) {
			case 0: // Salir

				System.out.println("Saliendo del sistema");
				break;

			case 1:
				userMenu();
				subMainSelect = input.nextInt();
				input.nextLine();

				// AÑADIR USUARIO
				if (subMainSelect == 1) {
					valid=false;
					fullName="";
					email="";
					date=LocalDate.now();

					System.out.println("Ha seleccionado la opcion anyadir usuario, introduzca los siguientes datos:");

					System.out.println("Nombre completo:");
					fullName = input.nextLine();

					System.out.println("Fecha de nacimiento (formato(dd-mm-yyyy):");
					while (!valid) {
						try {
							date = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
							valid = true;
						} catch (Exception e) {
							System.out.println("Formato de fecha no válido,vuelva a intentarlo:");
						}
					}

					System.out.println("Correo electronico:");
					email = input.nextLine();

					System.out.println("Añadiendo usuario...");
					UsuarioHandler.getInstance().addUser(new Usuario(fullName, date, email));
					System.out.println("El usuario ha sido añadido correctamente.");
				}

				// MODIFICAR USUARIO
				else if (subMainSelect == 2) {
					valid=false;
					fullName="";
					email="";
					date=LocalDate.now();
					
					System.out.println("Selecciona el id del usuario que quieres modificar.");
					Usuario userMod = UsuarioHandler.getInstance().getUserByID( input.nextInt());
					input.nextLine();
							
					System.out.println("Nombre completo:");
					fullName = input.nextLine();
	
			
					System.out.println("Fecha de nacimiento (formato(dd-mm-yyyy):");
					while (!valid) {
						try {
							date = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
							valid = true;
						} catch (Exception e) {
							System.out.println("Formato de fecha no válido,vuelva a intentarlo:");
						}
					}

					System.out.println("Correo electronico:");
					email = input.nextLine();

					userMod.setBirthdayDate(date);
					userMod.setEmail(email);
					userMod.setFullName(fullName);
					UsuarioHandler.getInstance().editUser(userMod);
					System.out.println("Mostrando el nuevo usuario...");
					System.out.println(userMod);
				}

				else if (subMainSelect == 3) {

					System.out.println("Estos son los usuarios de la lista.");
					for (Usuario us : UsuarioHandler.getInstance().getAllUsers()) {
						System.out.println(us);
					}

				} else if (subMainSelect == 4) {
					System.out.println("Estos son los nombres de los usuarios de la lista.");
					UsuarioHandler.getInstance().printNameUsers();

					System.out.println("Selecciona el nombre del usuario que quieres borrar.");
					UsuarioHandler.getInstance()
							.removeUser(UsuarioHandler.getInstance().getAllUsers().get(input.nextInt()).getId());
					input.nextLine();

				} else if (subMainSelect == 5) {

					System.out.println("Estos son los nombres de los usuarios de la lista.");
					UsuarioHandler.getInstance().printNameUsers();

					System.out.println("Selecciona el nombre del usuario que quieres ver.");
					System.out.println(UsuarioHandler.getInstance().getAllUsers().get(input.nextInt()));
					input.nextLine();
				} else if (subMainSelect == 6) {
					mainMenu();
					mainSelect = input.nextInt();
				} else if (subMainSelect == 0) {
					input.close();
					return;
				}

				break;

			case 2:

				circuitMenu();
				subMainSelect = input.nextInt();

				if (subMainSelect == 1) {
					int adult = 1;
					System.out.println("Introduce los datos para añadir un kart");
					System.out.println("Si el kart a añadir es para adultos pulse 1, sino pulse 0");
					adult = input.nextInt();
					input.nextLine();
					CircuitHandler.getInstance().addKart(new Kart((adult == 1), EstadoKart.DISPONIBLE));
				}
				
				if(subMainSelect == 2) {
					int adult, id;
					Kart k;
					ArrayList<EstadoKart> state = new ArrayList<EstadoKart>();
					state.add(EstadoKart.DISPONIBLE);
					state.add(EstadoKart.MANTENIMIENTO);
					state.add(EstadoKart.RESERVADO);
					
					System.out.println("Selecciona el id del kart que quieres modificar.");
					CircuitHandler.getInstance().printAllKarts();
					
					id = input.nextInt();
					input.nextLine();
					k = CircuitHandler.getInstance().getKartByID(id);
					
					System.out.println("Introduce los nuevos datos del usuario:");
					System.out.println("Si el kart a añadir es para adultos pulse 1, sino pulse 0");
					adult = input.nextInt();
					input.nextLine();
					
					k.setAdult((adult == 1));
					
					System.out.println("Introduce el estado del kart:");
					System.out.println("1 --> Disponible");
					System.out.println("2 --> Reservado");
					System.out.println("3 --> Mantenimiento");
					
					
					k.setState(state.get(input.nextInt()-1));
					input.nextLine();
					
					System.out.println("Mostrando de nuevo la lista de karts...");

					for (Kart k2 : CircuitHandler.getInstance().getAllKarts()) {
						System.out.println(k2);
					}
					
					
				}

				if (subMainSelect == 5) {
					
					System.out.println("Estos son los nombres de los karts de la lista.");

					CircuitHandler.getInstance().printAllKarts();
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

	public static void writeFile() {
		String pathUser="datos/usuarios.txt";
		String pathReserve="datos/reservas.txt";
		String pathKart="datos/karts.txt";
		String pathPista="datos/pista.txt";
		try {
			
			FileOutputStream fileOut = new FileOutputStream(pathUser);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(UsuarioHandler.getInstance().getAllUsers());
			
			fileOut = new FileOutputStream(pathReserve);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(ReservaHandler.getInstance().getAllReserves());
			
			fileOut = new FileOutputStream(pathPista);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(CircuitHandler.getInstance().getAllPistas());
			
			fileOut = new FileOutputStream(pathKart);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(CircuitHandler.getInstance().getAllKarts());
			
			objectOut.close();
			System.out.println("El objeto se ha guardado correctamente en el fichero");

		} catch (FileNotFoundException ex) {
			System.out.println("ERROR: No se ha encontrado el fichero");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Usuario> loadUserFile() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {
			FileInputStream fis = new FileInputStream(users_file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			lista = (ArrayList<Usuario>) ois.readObject();

			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static ArrayList<ReservaAbstracta> loadReserveFile() {
		ArrayList<ReservaAbstracta> lista = new ArrayList<ReservaAbstracta>();
		try {
			FileInputStream fis = new FileInputStream(reserves_file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			lista = (ArrayList<ReservaAbstracta>) ois.readObject();

			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
    public static ArrayList<Pista> loadPistaFile() {
		ArrayList<Pista> lista = new ArrayList<Pista>();
		try {
			FileInputStream fis = new FileInputStream(pistas_file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			lista = (ArrayList<Pista>) ois.readObject();

			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public static ArrayList<Kart> loadKartFile() {
		ArrayList<Kart> lista = new ArrayList<Kart>();
		try {
			FileInputStream fis = new FileInputStream(karts_file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			lista = (ArrayList<Kart>) ois.readObject();

			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}


	public static void loadFilesPath() {
		Properties prop = new Properties();
		String filename = "src/data.properties";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);

			String path = "datos/";
			users_file = path + prop.getProperty("users_file");
			reserves_file = path + prop.getProperty("reserves_file");
			karts_file = path + prop.getProperty("karts_file");
			pistas_file = path + prop.getProperty("pistas_file");

			// Captura de excepciones
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se ha encontrado el fichero \"" + filename + "\"");
		} catch (IOException e) {
			System.out.println("ERROR: No se ha podido leer el fichero");
		}
	}
}
