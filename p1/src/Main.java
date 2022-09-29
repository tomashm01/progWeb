import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Scanner;

import entities.Usuario;
import factory.ReservaAdultos;
import factory.ReservaInfantil;
import handlers.CircuitHandler;
import handlers.ReservaHandler;
import handlers.UsuarioHandler;


public class Main {

	public static void main(String[] args) {
//		CircuitHandler.getInstance().addKart(new Kart())
//		UsuarioHandler.getInstance().addUser(new Usuario ("Juan",LocalDate.of(2003, 1, 1),LocalDate.of(2022, 1, 1),"emailrandom@gmail.com"));
//		ReservaHandler.getInstance().addReservaIndividual(new  );

		Period period = Period.between(LocalDate.of(2022, 9, 28),LocalDate.now());
		System.out.println(LocalDateTime.now()); 

		//int opcion=0;
		/*
		Scanner s=new Scanner(System.in);
		
		UsuarioHandler.getInstance().addUser(usuario1);
		System.out.println(UsuarioHandler.getInstance().getIdByEmail("emailrandom@gmail.com"));
		System.out.println(UsuarioHandler.getInstance().getUserByID(UsuarioHandler.getInstance().getIdByEmail("emailrandom@gmail.com")));
		*/
//		do{
//			System.out.println("Introduce una opcion");
//			opcion=s.nextInt();
//			s.nextLine();	
//			switch(opcion){
//				case 0: //Salir
//					break;
//				case 1:
//					break;
//				case 2:
//					break;
//				default:
//					break;	
//			}
//				
//		}while(opcion!=0);
		
		//s.close();
		
		
	}
	
}
