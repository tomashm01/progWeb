import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import entities.*;
import entities.enums.*;
import factory.ModalidadBono;
import factory.ModalidadIndividual;
import handlers.CircuitHandler;
import handlers.ReservaHandler;
import handlers.UsuarioHandler;

public class Main {
	
	public static void main(String[] args) {
		
		UsuarioHandler.getInstance().getAllUsers();
		CircuitHandler.getInstance().getAllKarts();
		ReservaHandler.getInstance().getAllReserves();
				
		CircuitHandler.getInstance().addPista(new Pista(1,"Alvaro",true,DificultadPista.FAMILIAR,10));
		CircuitHandler.getInstance().addPista(new Pista(2,"Tom·s",true,DificultadPista.ADULTOS,10));
		CircuitHandler.getInstance().addPista(new Pista(5,"Juan",true,DificultadPista.INFANTIL,10));		
		for(int i=0;i<20;i++) {
			Kart aux = new Kart((i%2==0),EstadoKart.DISPONIBLE);
			CircuitHandler.getInstance().addKart(aux);
			CircuitHandler.getInstance().getPistaByID(5).asociarKartAPista(aux); 
		}
		for(int i=35;i<40;i++) {
			Kart aux = new Kart(true,EstadoKart.DISPONIBLE);
			CircuitHandler.getInstance().addKart(aux);
			CircuitHandler.getInstance().getPistaByID(2).asociarKartAPista(aux); 
		}
		for(int i=35;i<40;i++) {
			Kart aux = new Kart(true,EstadoKart.DISPONIBLE);
			CircuitHandler.getInstance().addKart(aux);
			CircuitHandler.getInstance().getPistaByID(1).asociarKartAPista(aux); 
		}
		
		UsuarioHandler.getInstance().addUser(new Usuario(2,"Alvaro",LocalDate.of(2000,12,31),"alvaro@gmail.com",LocalDate.of(2019, 1, 1)));
		UsuarioHandler.getInstance().addUser(new Usuario(1,"Juan",LocalDate.of(2000,1,1),"juan@gmail.com",LocalDate.of(2022, 12, 31)));
		

//		ReservaAdultos ra	= new ModalidadBono().createReservaAdultos(1, LocalDateTime.now().plus(1000,ChronoUnit.MINUTES), 100, 2, 0, 1, 1, 3);
//		ReservaFamiliar mf	= new ModalidadBono().createReservaFamiliar(1, LocalDateTime.now().plus(100,ChronoUnit.MINUTES), 50, 1, 0, 1, 2, 1, 4);
//		ReservaInfantil mi 	= new ModalidadIndividual().createReservaInfantil(2,LocalDateTime.now().plus(2,ChronoUnit.MINUTES),2000,5,10f,0f,1,10);

	
		String fullName, email;
		boolean valid = false;
		int mainSelect = 0;
		int subMainSelect = 0;
		LocalDateTime date = LocalDateTime.now();
		

		Scanner input = new Scanner(System.in);
		do {
			mainMenu();
			mainSelect = input.nextInt();
			input.nextLine();
			switch (mainSelect) {
			case 0: // Salir
				// TO DO
				//writeFile();
				System.out.println("Saliendo del sistema");
				break;

			case 1:
				do{
					userMenu();
					subMainSelect = input.nextInt();
					input.nextLine();

					// A—ADIR USUARIO
				
					if (subMainSelect == 1) {
						valid=false;
						fullName="";
						email="";
						LocalDate date2=LocalDate.now();
	
						System.out.println("Ha seleccionado la opcion aÒadir usuario, introduzca los siguientes datos:");
	
						System.out.println("Nombre completo:");
						fullName = input.nextLine();
	
						System.out.println("Fecha de nacimiento (formato(dd-mm-yyyy):");
						while (!valid) {
							try {
								date2 = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
								valid = true;
							} catch (Exception e) {
								System.out.println("Formato de fecha no v·lido,vuelva a intentarlo:");
							}
						}
	
						System.out.println("Correo electronico:");
						email = input.nextLine();
	
						System.out.println("AÒadiendo usuario...");
						UsuarioHandler.getInstance().addUser(new Usuario(fullName, date2, email));
						System.out.println("El usuario ha sido aÒadido correctamente.");
					}
	
					// MODIFICAR USUARIO
					else if (subMainSelect == 2) {
						valid=false;
						fullName="";
						email="";
						LocalDate date1 = LocalDate.now();
						
						System.out.println("Selecciona el id del usuario que quieres modificar.");
						Usuario userMod = UsuarioHandler.getInstance().getUserByID( input.nextInt());
						input.nextLine();
								
						System.out.println("Nombre completo:");
						fullName = input.nextLine();
		
				
						System.out.println("Fecha de nacimiento (formato(dd-mm-yyyy):");
						while (!valid) {
							try {
								date1 = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
								valid = true;
							} catch (Exception e) {
								System.out.println("Formato de fecha no v·lido,vuelva a intentarlo:");
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
						valid=false;
						System.out.println("Selecciona el nombre del usuario que quieres borrar.");
						while (!valid) {
							try {
								int index =input.nextInt();
								input.nextLine();
								if(UsuarioHandler.getInstance().getAllUsers().size()> index){
									UsuarioHandler.getInstance().removeUser(UsuarioHandler.getInstance().getAllUsers().get(index).getId());
									valid=true;
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
						return;
					}
				}while(subMainSelect != 6);
			break;

			case 2:

				circuitMenu();
				subMainSelect = input.nextInt();

				if (subMainSelect == 1) {
					int adult = 1;
					System.out.println("Introduce los datos para a√±adir un kart");
					System.out.println("Si el kart a a√±adir es para adultos pulse 1, sino pulse 0");
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
					System.out.println("Si el kart a a√±adir es para adultos pulse 1, sino pulse 0");
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
				input.nextLine();

				if (subMainSelect == 1) { //AÒadir reserva
					
					UsuarioHandler.getInstance().printNameUsers();
					System.out.println("Introduce el ID del usuario encargado de la reserva");
					int indexIdUser=input.nextInt();
					input.nextLine();
					int idUser=UsuarioHandler.getInstance().getAllUsers().get(indexIdUser).getId();
					
					CircuitHandler.getInstance().printAllPistas();
					System.out.println("Introduce el ID de la pista para crear");
					int indexIdPista=input.nextInt();
					input.nextLine();
					int idPista=CircuitHandler.getInstance().getAllPistas().get(indexIdPista).getId();
					
					System.out.println("Introduce el tiempo que quieras estar");
					int time=input.nextInt();
					input.nextLine();
					
					int price=ReservaHandler.getInstance().calculatePrice(time);
					
					int tipoReserva=0,modalidadReserva=0,discount=0;
					
					do {
						System.out.println("Que tipo de reserva quieres?");
						System.out.println("1.Tipo familiar");
						System.out.println("2.Tipo adultos");
						System.out.println("3.Tipo infantil");
						tipoReserva=input.nextInt();
						input.nextLine();
					}while(tipoReserva>3 || tipoReserva<0);
					
					System.out.println("Cuantos adultos desean ir?");
					int numAdults=input.nextInt();
					input.nextLine();
					
					System.out.println("Cuantos niÒos desean ir?");
					int numChilds=input.nextInt();
					input.nextLine();
					
					do {
						System.out.println("Que modalidad de reserva quieres?");
						System.out.println("1.Modalidad individual");
						System.out.println("2.Modalidad bono");
						modalidadReserva=input.nextInt();
						input.nextLine();
					}while(modalidadReserva>2 || modalidadReserva<0);
					
					System.out.println("Fecha de reserva (formato(mm-HH-dd-MM-yyyy)(minutos,horas,dia,mes,aÒo):");
					while (!valid) {
						try {
							date = LocalDateTime.parse(input.nextLine(), DateTimeFormatter.ofPattern("mm-HH-dd-MM-yyyy"));
							valid = true;
						} catch (Exception e) {
							System.out.println("Formato de fecha no v·lido,vuelva a intentarlo:");
						}
					}
					
					if(modalidadReserva==1) { //Modalidad individual
						if(tipoReserva==1){ //Tipo familiar
							if( new ModalidadIndividual().createReservaFamiliar(idUser, date, time, idPista, price, discount, numAdults, numChilds)!=null){
								System.out.println("Reserva creada correctamente");
							}else{
								System.out.println("Reserva no ha sido creada correctamente");
							}
						}else if(tipoReserva==2){ //Tipo adultos
							if(new ModalidadIndividual().createReservaAdultos(idUser, date, time, idPista, price, discount, numAdults)!=null){
								System.out.println("Reserva creada correctamente");
							}else{
								System.out.println("Reserva no ha sido creada correctamente");
							}
						}else{ //Tipo infantil
							if(new ModalidadIndividual().createReservaInfantil(idUser, date, time, idPista, price, discount, numChilds)!=null){
								System.out.println("Reserva creada correctamente");
							}else{
								System.out.println("Reserva no ha sido creada correctamente");
							}
						}
					}else{ //Modalidad bono
						if(tipoReserva==1){ //Tipo familiar
							if( new ModalidadBono().createReservaFamiliar(idUser, date, time, idPista, price, discount, numAdults, numChilds)!=null){
								System.out.println("Reserva creada correctamente");
							}else{
								System.out.println("Reserva no ha sido creada correctamente");
							}
						}else if(tipoReserva==2){ //Tipo adultos
							if(new ModalidadBono().createReservaAdultos(idUser, date, time, idPista, price, discount, numAdults)!=null){
								System.out.println("Reserva creada correctamente");
							}else{
								System.out.println("Reserva no ha sido creada correctamente");
							}
						}else{ //Tipo infantil
							if(new ModalidadBono().createReservaInfantil(idUser, date, time, idPista, price, discount, numChilds)!=null){
								System.out.println("Reserva creada correctamente");
							}else{
								System.out.println("Reserva no ha sido creada correctamente");
							}
						}
					}
					
					System.out.println();
				}else if(subMainSelect==2){ //Modificar reserva
				
				int modalidad=0;
					
					do{
						System.out.println("Deseas eliminar una reserva bono o individual? ");
						System.out.println("1.Eliminar bono");
						System.out.println("2.Eliminar reserva individual");
						modalidad=input.nextInt();
						input.nextLine();
					}while(modalidad<0 || modalidad>2);
					
					if(modalidad==1){
						
						System.out.println("---RESERVAS BONO---");
						ReservaHandler.getInstance().printAllBonosList();
						System.out.println();
						
						System.out.println("Introduce el ID del bono a eliminar: ");
						int idBono=input.nextInt();
						input.nextLine();
					
						
					}else{
						System.out.println("---RESERVAS INDIVIDUALES---");
						ReservaHandler.getInstance().printAllReservesList();
						System.out.println();
						
						System.out.println("Introduce el ID de la reserva a eliminar: ");
						int idReserva=input.nextInt();
						input.nextLine();
					
						
					}
					
				}else if(subMainSelect==3){ //Eliminar reserva
					int modalidad=0;
					
					do{
						System.out.println("Deseas eliminar una reserva bono o individual? ");
						System.out.println("1.Eliminar bono");
						System.out.println("2.Eliminar reserva individual");
						modalidad=input.nextInt();
						input.nextLine();
					}while(modalidad<0 || modalidad>2);
					
					if(modalidad==1){
						
						System.out.println("---RESERVAS BONO---");
						ReservaHandler.getInstance().printAllBonosList();
						System.out.println();
						
						System.out.println("Introduce el ID del bono a eliminar: ");
						int idBono=input.nextInt();
						input.nextLine();
					
						if(ReservaHandler.getInstance().removeBono(idBono)){
							System.out.println("Bono eliminado correctamente");
						}else{
							System.out.println("Bono no eliminado correctamente");
						}
					}else{
						System.out.println("---RESERVAS INDIVIDUALES---");
						ReservaHandler.getInstance().printAllReservesList();
						System.out.println();
						
						System.out.println("Introduce el ID de la reserva a eliminar: ");
						int idReserva=input.nextInt();
						input.nextLine();
					
						if(ReservaHandler.getInstance().removeReserve(idReserva)){
							System.out.println("Reserva eliminada correctamente");
						}else{
							System.out.println("Reserva no eliminada correctamente");
						}
						
					}
					
				}else if(subMainSelect==4){//Ver todas las reservas
				
					System.out.println("---RESERVAS INDIVIDUALES---");
					ReservaHandler.getInstance().printAllReservesList();
					System.out.println();
					
					System.out.println("---RESERVAS BONO---");
					ReservaHandler.getInstance().printAllBonosList();
					System.out.println();
					
				}else if(subMainSelect==5){//Atr·s
					
				}else if(subMainSelect==6){//Salir de la app
					
				}else{//Fallo de opciÛn
					System.out.println("Fallo de opcion. Elige una de las opciones mostradas");
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
		System.out.println("1) Men˙ Usuario");
		System.out.println("2) Men˙ Circuito");
		System.out.println("3) Men˙ Reserva\n");
		System.out.println("0) Salir\n");
		System.out.println("-> Introduce una opcion: ");
	}

	public static void userMenu() {
		System.out.println("\n\n================================");
		System.out.println("  GESTI”N DE USUARIOS");
		System.out.println("================================");
		System.out.println("1) Alta de usuario no registrado");
		System.out.println("2) Modificar usuario");
		System.out.println("3) Listar todos los usuarios");
		System.out.println("4) Eliminar usuario");
		System.out.println("5) Ver datos de un usuario");
		System.out.println("6) Atr·s");
		System.out.println("0) Salir");
		System.out.println("-> Introduce una opcion: ");
	}

	public static void circuitMenu() {
		System.out.println("\n\n================================");
		System.out.println("  GESTI”N DE CIRCUITOS");
		System.out.println("================================");
		System.out.println("1) AÒadir kart");
		System.out.println("2) Editar kart");
		System.out.println("3) Eliminar kart");
		System.out.println("4) Ver kart de usuario");
		System.out.println("5) Ver todos los karts");
		System.out.println("6) AÒadir pista");
		System.out.println("7) Editar pista");
		System.out.println("8) Eliminar pista");
		System.out.println("9) Ver pista por nombre");
		System.out.println("10) Ver todas las pistas");
		System.out.println("11) Atr·s");
		System.out.println("0) Salir");
		System.out.println("-> Introduce una opcion: ");
	}

	public static void reserveMenu() {
		System.out.println("\n\n================================");
		System.out.println("  GESTI”N DE RESERVAS");
		System.out.println("================================");
		System.out.println("1) AÒadir reserva");
		System.out.println("2) Modificar reserva");
		System.out.println("3) Eliminar reserva por usuario");
		System.out.println("4) Ver todas las reservas");
		System.out.println("5) Atr·s");
		System.out.println("6) Salir");
		System.out.println("-> Introduce una opcion: ");
	}

	public static void writeFile() {
		String pathUser="datos/usuarios.txt";
		String pathReserve="datos/reservas.txt";
		String pathKart="datos/karts.txt";
		String pathPista="datos/pistas.txt";
		String pathBono="datos/bonos.txt";
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
