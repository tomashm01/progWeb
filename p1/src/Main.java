import java.business.factories.ModalidadBono;
import java.business.factories.ModalidadIndividual;
import java.business.factories.ReservaAbstracta;
import java.business.factories.ReservaAdultos;
import java.business.factories.ReservaFamiliar;
import java.business.factories.ReservaInfantil;
import java.business.handlers.CircuitHandler;
import java.business.handlers.ReservaHandler;
import java.business.handlers.UsuarioHandler;
import java.data.enums.DificultadPista;
import java.data.enums.EstadoKart;
import java.data.models.Kart;
import java.data.models.Pista;
import java.data.models.Usuario;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//auxiliar variables
		String fullName, email;
		boolean valid = false;
		int mainSelect = 0;
		int subMainSelect = 0;
		LocalDateTime date = LocalDateTime.now();
		Scanner input = new Scanner(System.in);

		//mainMenu
		do {
			mainMenu();
			mainSelect = input.nextInt();
			input.nextLine();
			switch (mainSelect) {
			case 0: // Salir
				
				System.out.println("Saliendo del sistema");
				break;

			case 1:
				do {
					userMenu();
					subMainSelect = input.nextInt();
					input.nextLine();

					// AÑADIR USUARIO

					if (subMainSelect == 1) {
						valid = false;
						fullName = "";
						email = "";
						LocalDate date2 = LocalDate.now();

						System.out
								.println("Ha seleccionado la opcion añadir usuario, introduzca los siguientes datos:");

						System.out.println("Nombre completo:");
						fullName = input.nextLine();

						System.out.println("Fecha de nacimiento (formato(dd-mm-yyyy):");
						while (!valid) {
							try {
								date2 = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
								valid = true;
							} catch (Exception e) {
								System.out.println("Formato de fecha no válido,vuelva a intentarlo:");
							}
						}

						System.out.println("Correo electronico:");
						email = input.nextLine();

						System.out.println("Añadiendo usuario...");
						UsuarioHandler.getInstance().addUser(new Usuario(fullName, date2, email));
						System.out.println("El usuario ha sido añadido correctamente.");
					}

					// MODIFICAR USUARIO
					else if (subMainSelect == 2) {
						valid = false;
						fullName = "";
						email = "";
						LocalDate date1 = LocalDate.now();

						System.out.println("Selecciona el id del usuario que quieres modificar.");
						Usuario userMod = UsuarioHandler.getInstance().getUserByID(input.nextInt());
						input.nextLine();

						System.out.println("Nombre completo:");
						fullName = input.nextLine();

						System.out.println("Fecha de nacimiento (formato(dd-mm-yyyy):");
						while (!valid) {
							try {
								date1 = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
								valid = true;
							} catch (Exception e) {
								System.out.println("Formato de fecha no válido,vuelva a intentarlo:");
							}
						}

						System.out.println("Correo electronico:");
						email = input.nextLine();

						userMod.setBirthdayDate(date1);
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
						valid = false;
						System.out.println("Selecciona el nombre del usuario que quieres borrar.");
						while (!valid) {
							try {
								int index = input.nextInt();
								input.nextLine();
								if (UsuarioHandler.getInstance().getAllUsers().size() > index) {
									UsuarioHandler.getInstance()
											.removeUser(UsuarioHandler.getInstance().getAllUsers().get(index).getId());
									valid = true;
								}
							} catch (Exception e) {
								System.out.println("Formato de id no correcto o id no existente");
							}
						}

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
						writeFile();
						return;
					} else {// Fallo de opción
						System.out.println("Fallo de opcion. Elige una de las opciones mostradas");
					}
				} while (subMainSelect != 6);
				break;

			case 2:
				do {
					circuitMenu();
					subMainSelect = input.nextInt();
					if( subMainSelect == 0) {
						writeFile();
						System.out.println("saliendo del menu");
						return;
					}
					else if (subMainSelect == 1) {
						int adult = 1;
						int idPista=0;
						int numKarts=0;
						System.out.println("Introduce los datos para añadir un kart");
						System.out.println("Indique el tipo de Kart:");
						System.out.println("1)Adultos");
						System.out.println("2)Niños");
						adult = input.nextInt();
						input.nextLine();
						
						CircuitHandler.getInstance().printAllPistasWithoutKarts();
						
						do {
							System.out.println("Indique el id de la pista a la que se va a asignar el idPista el kart, -1 si no quiere");
							idPista = input.nextInt();
							input.nextLine();
						}while(!CircuitHandler.getInstance().existPista(idPista) && idPista != -1);
						
						System.out.println("Cuantos karts con estas caracteristicas desea añadir");
						numKarts = input.nextInt();
						input.nextLine();
						
						for(int i=0;i<numKarts;i++)
							CircuitHandler.getInstance().addKart(new Kart((adult == 1), EstadoKart.DISPONIBLE,idPista));
					}
					else if (subMainSelect == 2) {
						int adult, idKart;
						Kart kart;
						ArrayList<EstadoKart> state = new ArrayList<EstadoKart>();
						state.add(EstadoKart.DISPONIBLE);
						state.add(EstadoKart.MANTENIMIENTO);
						state.add(EstadoKart.RESERVADO);
	
						
						do {
							System.out.println("Selecciona el id del kart que quiera modificar.");
							idKart = input.nextInt();
							input.nextLine();
						}while(CircuitHandler.getInstance().existKart(idKart));
						kart = CircuitHandler.getInstance().getKartByID(idKart);
	
						System.out.println("Indique el tipo de Kart:");
						System.out.println("1)Adultos");
						System.out.println("2)Niños");
						adult = input.nextInt();
						input.nextLine();
	
						kart.setAdult((adult == 1));
	
						System.out.println("Introduzca el estado del kart:");
						System.out.println("1 --> Disponible");
						System.out.println("2 --> Reservado");
						System.out.println("3 --> Mantenimiento");
	
						kart.setState(state.get(input.nextInt() - 1));
						input.nextLine();
						
						System.out.println("Kart modificado");
						System.out.println(CircuitHandler.getInstance().getKartByID(idKart).toString());

					}
					else if (subMainSelect == 3) {
						System.out.println("Estos son los karts de la lista.");
						CircuitHandler.getInstance().printAllKarts();
						System.out.println("Selecciona el id del kart que quiera eliminar.");
						CircuitHandler.getInstance().printAllKarts();
						int id = input.nextInt();
						if(CircuitHandler.getInstance().existKart(id)) {
							CircuitHandler.getInstance().removeKart(id);
							System.out.println("Kart eliminado");
						}
						input.nextLine();
					}
					else if (subMainSelect == 4) {
	
						System.out.println("Estos son los karts de la lista.");
	
						CircuitHandler.getInstance().printAllKarts();
					}
					else if( subMainSelect == 5) { //String name, boolean isAvailable, DificultadPista difficulty, Integer maxKart
						int tipoPista = 10;
						DificultadPista dif = DificultadPista.ADULTOS;
						System.out.println("Ha seleccionado crear una pista nueva:");
						System.out.println("Introduzca el nombre de la pista");
						fullName=input.nextLine();
						System.out.println("Introduzca si está disponible (1) o no (0)");
						int disp = input.nextInt();
						input.nextLine();
						do {
							System.out.println("Introduzca el tipo de pista que quiere");
							System.out.println("1.Tipo familiar");
							System.out.println("2.Tipo adultos");
							System.out.println("3.Tipo infantil");
							tipoPista = input.nextInt();
							input.nextLine();
						} while (tipoPista > 3 || tipoPista < 0);
						if(tipoPista == 1)
							dif = DificultadPista.FAMILIAR;
						else if(tipoPista == 2)
							dif = DificultadPista.ADULTOS;
						if(tipoPista == 3)
							dif = DificultadPista.INFANTIL;
						System.out.println("Introduzca el numero de karts maximos en esta pista");
						int numMax = input.nextInt();
						input.nextLine();
						
						CircuitHandler.getInstance().addPista(new Pista(fullName,disp == 1,dif,numMax));
					}
					else if( subMainSelect == 6) {
						int tipoPista = 10;
						DificultadPista dif = DificultadPista.ADULTOS;
						System.out.println("Ha seleccionado modificar una pista:");
						System.out.println("Las pistas disponibles son:");
						CircuitHandler.getInstance().printAllPistas();
						int index = input.nextInt();
						input.nextLine();
						
						System.out.println("Introduzca el nombre de la pista");
						fullName=input.nextLine();
						System.out.println("Introduzca si está disponible (1) o no (0)");
						int disp = input.nextInt();
						input.nextLine();
						do {
							System.out.println("Introduzca el tipo de pista que quiere");
							System.out.println("1.Tipo familiar");
							System.out.println("2.Tipo adultos");
							System.out.println("3.Tipo infantil");
							tipoPista = input.nextInt();
							input.nextLine();
						} while (tipoPista > 3 || tipoPista < 0);
						if(tipoPista == 1)
							dif = DificultadPista.FAMILIAR;
						else if(tipoPista == 2)
							dif = DificultadPista.ADULTOS;
						if(tipoPista == 3)
							dif = DificultadPista.INFANTIL;
						System.out.println("Introduzca el numero de karts maximos en esta pista");
						int numMax = input.nextInt();
						input.nextLine();
						if (CircuitHandler.getInstance().editPista(new Pista(CircuitHandler.getInstance().getAllPistas().get(index).getId(),fullName,disp == 1,dif,numMax))) {
							System.out.println("Pista modificada con exito");
						}
					}
					else if( subMainSelect == 7) {
						System.out.println("Seleccione la pista a eliminar");
						System.out.println(CircuitHandler.getInstance().getAllPistas());
						int id = input.nextInt();
						input.nextLine();
						CircuitHandler.getInstance().removePista(id);
						System.out.println("pista eliminada");
					}
					else if( subMainSelect == 8) {
						CircuitHandler.getInstance().printAllPistasByName();
					}
					else if( subMainSelect == 9) {
						CircuitHandler.getInstance().printAllPistasWithoutKarts();
					}else if(subMainSelect==10){
						
					}else {// Fallo de opción
						System.out.println("Fallo de opcion. Elige una de las opciones mostradas");
					}
					
				}while(subMainSelect != 10);
			break;

			case 3:
			do{
				reserveMenu();

				subMainSelect = input.nextInt();
				input.nextLine();

				if (subMainSelect == 1) { // Añadir reserva

					UsuarioHandler.getInstance().printNameUsers();
					System.out.println("Introduce el ID del usuario encargado de la reserva");
					int indexIdUser = input.nextInt();
					input.nextLine();
					int idUser = UsuarioHandler.getInstance().getAllUsers().get(indexIdUser).getId();

					CircuitHandler.getInstance().printAllPistas();
					System.out.println("Introduce el ID de la pista para crear");
					int indexIdPista = input.nextInt();
					input.nextLine();
					int idPista = CircuitHandler.getInstance().getAllPistas().get(indexIdPista).getId();

					System.out.println("Introduce el tiempo que quieras estar");
					int time = input.nextInt();
					input.nextLine();

					int price = ReservaHandler.getInstance().calculatePrice(time);

					int tipoReserva = 0, modalidadReserva = 0, discount = 0;

					do {
						System.out.println("Que tipo de reserva quieres?");
						System.out.println("1.Tipo familiar");
						System.out.println("2.Tipo adultos");
						System.out.println("3.Tipo infantil");
						tipoReserva = input.nextInt();
						input.nextLine();
					} while (tipoReserva > 3 || tipoReserva < 0);

					System.out.println("Cuantos adultos desean ir?");
					int numAdults = input.nextInt();
					input.nextLine();

					System.out.println("Cuantos niños desean ir?");
					int numChilds = input.nextInt();
					input.nextLine();

					do {
						System.out.println("Que modalidad de reserva quieres?");
						System.out.println("1.Modalidad individual");
						System.out.println("2.Modalidad bono");
						modalidadReserva = input.nextInt();
						input.nextLine();
					} while (modalidadReserva > 2 || modalidadReserva < 0);

					System.out.println("Fecha de reserva (formato(mm-HH-dd-MM-yyyy)(minutos,horas,dia,mes,año):");
					while (!valid) {
						try {
							date = LocalDateTime.parse(input.nextLine(),
									DateTimeFormatter.ofPattern("mm-HH-dd-MM-yyyy"));
							valid = true;
						} catch (Exception e) {
							System.out.println("Formato de fecha no válido,vuelva a intentarlo:");
						}
					}

					if (modalidadReserva == 1) { // Modalidad individual
						if (tipoReserva == 1) { // Tipo familiar
							if (new ModalidadIndividual().createReservaFamiliar(idUser, date, time, idPista, price,
									discount, numAdults, numChilds) != null) {
								System.out.println("Reserva creada correctamente");
							} else {
								System.out.println("Reserva no ha sido creada correctamente");
							}
						} else if (tipoReserva == 2) { // Tipo adultos
							if (new ModalidadIndividual().createReservaAdultos(idUser, date, time, idPista, price,
									discount, numAdults) != null) {
								System.out.println("Reserva creada correctamente");
							} else {
								System.out.println("Reserva no ha sido creada correctamente");
							}
						} else { // Tipo infantil
							if (new ModalidadIndividual().createReservaInfantil(idUser, date, time, idPista, price,
									discount, numChilds) != null) {
								System.out.println("Reserva creada correctamente");
							} else {
								System.out.println("Reserva no ha sido creada correctamente");
							}
						}
					} else { // Modalidad bono
						if (tipoReserva == 1) { // Tipo familiar
							if (new ModalidadBono().createReservaFamiliar(idUser, date, time, idPista, price, discount,
									numAdults, numChilds) != null) {
								System.out.println("Reserva creada correctamente");
							} else {
								System.out.println("Reserva no ha sido creada correctamente");
							}
						} else if (tipoReserva == 2) { // Tipo adultos
							if (new ModalidadBono().createReservaAdultos(idUser, date, time, idPista, price, discount,
									numAdults) != null) {
								System.out.println("Reserva creada correctamente");
							} else {
								System.out.println("Reserva no ha sido creada correctamente");
							}
						} else { // Tipo infantil
							if (new ModalidadBono().createReservaInfantil(idUser, date, time, idPista, price, discount,
									numChilds) != null) {
								System.out.println("Reserva creada correctamente");
							} else {
								System.out.println("Reserva no ha sido creada correctamente");
							}
						}
					}

					System.out.println();
				} else if (subMainSelect == 2) { // Modificar reserva

					valid = false;
					
					System.out.println("---RESERVAS INDIVIDUALES---");
					ReservaHandler.getInstance().printAllReservesList();
					System.out.println();

					System.out.println("Introduce el ID de la reserva a modificar: ");
					int idReserva = input.nextInt();
					input.nextLine();
					System.out.println("Introduzca la fecha de la reserva dia-mes-año-hora-minuto (todo 2 digitos, menos año)");
					while (!valid) {
						try {
							date = LocalDateTime.parse(input.nextLine(),
									DateTimeFormatter.ofPattern("dd-MM-yyyy-mm-HH"));
							valid = true;
						} catch (Exception e) {
							System.out.println("Formato de fecha no válido,vuelva a intentarlo:");
						}
					}
					
					System.out.println("Introduce el tiempo que quieras estar");
					int time = input.nextInt();
					input.nextLine();
					
					ReservaAbstracta reserve=ReservaHandler.getInstance().getReserveByID(idReserva);
					reserve.setDate(date);
					reserve.setTime(time);
					reserve.setPrice(ReservaHandler.getInstance().calculatePrice(time));
					
					
					if(ReservaHandler.getInstance().modifyReserve(reserve)){
						System.out.println("Reserva modificada correctamente");
					}else{
						System.out.println("Reserva no modificada correctamente");
					}

				} else if (subMainSelect == 3) { // Eliminar reserva
					int modalidad = 0;

					do {
						System.out.println("Deseas eliminar una reserva bono o individual? ");
						System.out.println("1.Eliminar bono");
						System.out.println("2.Eliminar reserva individual");
						modalidad = input.nextInt();
						input.nextLine();
					} while (modalidad < 0 || modalidad > 2);

					if (modalidad == 1) {

						System.out.println("---RESERVAS BONO---");
						ReservaHandler.getInstance().printAllBonosList();
						System.out.println();

						System.out.println("Introduce el ID del bono a eliminar: ");
						int idBono = input.nextInt();
						input.nextLine();

						if (ReservaHandler.getInstance().removeBono(idBono)) {
							System.out.println("Bono eliminado correctamente");
						} else {
							System.out.println("Bono no eliminado correctamente");
						}
					} else {
						System.out.println("---RESERVAS INDIVIDUALES---");
						ReservaHandler.getInstance().printAllReservesList();
						System.out.println();

						System.out.println("Introduce el ID de la reserva a eliminar: ");
						int idReserva = input.nextInt();
						input.nextLine();

						if (ReservaHandler.getInstance().removeReserve(idReserva)) {
							System.out.println("Reserva eliminada correctamente");
						} else {
							System.out.println("Reserva no eliminada correctamente");
						}

					}

				} else if (subMainSelect == 4) {// Ver todas las reservas

					System.out.println("---RESERVAS INDIVIDUALES---");
					ReservaHandler.getInstance().printAllReservesList();
					System.out.println();

					System.out.println("---RESERVAS BONO---");
					ReservaHandler.getInstance().printAllBonosList();
					System.out.println();

				} else if (subMainSelect == 5) {// Atrás
					

				} else if (subMainSelect == 6) {// Salir de la app
					writeFile();
					return;

				} else {// Fallo de opción
					System.out.println("Fallo de opcion. Elige una de las opciones mostradas");
				}
			}while(subMainSelect!=5);

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
		System.out.println("4) Ver todos los karts");
		System.out.println("5) Añadir pista");
		System.out.println("6) Editar pista");
		System.out.println("7) Eliminar pista");
		System.out.println("8) Ver pista por nombre");
		System.out.println("9) Ver todas las pistas");
		System.out.println("10) Atrás");
		System.out.println("0) Salir");
		System.out.println("-> Introduce una opcion: ");
	}

	public static void reserveMenu() {
		System.out.println("\n\n================================");
		System.out.println("  GESTIÓN DE RESERVAS");
		System.out.println("================================");
		System.out.println("1) Añadir reserva");
		System.out.println("2) Modificar reserva");
		System.out.println("3) Eliminar reserva por usuario");
		System.out.println("4) Ver todas las reservas");
		System.out.println("5) Atrás");
		System.out.println("6) Salir");
		System.out.println("-> Introduce una opcion: ");
	}

	public static void writeFile() {
		String pathUser = "datos/usuarios.txt";
		String pathReserve = "datos/reservas.txt";
		String pathKart = "datos/karts.txt";
		String pathPista = "datos/pistas.txt";
		String pathBono = "datos/bonos.txt";
		try {

			FileOutputStream fileOut = new FileOutputStream(pathUser);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(UsuarioHandler.getInstance().getAllUsers());
			fileOut.close();

			fileOut = new FileOutputStream(pathReserve);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(ReservaHandler.getInstance().getAllReserves());
			fileOut.close();

			fileOut = new FileOutputStream(pathBono);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(ReservaHandler.getInstance().getAllBonos());
			fileOut.close();

			fileOut = new FileOutputStream(pathPista);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(CircuitHandler.getInstance().getAllPistas());
			fileOut.close();

			fileOut = new FileOutputStream(pathKart);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(CircuitHandler.getInstance().getAllKarts());
			fileOut.close();

			objectOut.close();
			System.out.println("OK: Se han guardados los cambios");

		} catch (FileNotFoundException ex) {
			System.out.println("ERROR: No se ha encontrado el fichero");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
