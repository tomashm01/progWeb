import java.time.LocalDate;
import java.util.Scanner;

import entities.Usuario;
import handlers.UsuarioHandler;


public class Main {

	public static void main(String[] args) {
		//int opcion=0;
		Scanner s=new Scanner(System.in);
		Usuario usuario1 = new Usuario ("Juan",LocalDate.of(2003, 1, 1),LocalDate.of(2022, 1, 1),"emailrandom@gmail.com");
		UsuarioHandler.getInstance().addUser(usuario1);
		System.out.println(UsuarioHandler.getInstance().getIdByEmail("emailrandom@gmail.com"));
		System.out.println(UsuarioHandler.getInstance().getUserByID(UsuarioHandler.getInstance().getIdByEmail("emailrandom@gmail.com")));
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

		s.close();
	}
	
}
