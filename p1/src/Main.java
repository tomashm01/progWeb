import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import factory.*;

public class Main {

	public static void main(String[] args) {
		int opcion=0;
		Scanner s=new Scanner(System.in);
		ReservaInfantil r = new ReservaInfantil(111111, 10, LocalDate.of(1992, 1, 1), null, null, opcion);
		/*do{
			System.out.println("Introduce una opcion");
			opcion=s.nextInt();
			s.nextLine();	
			switch(opcion){
				case 0: //Salir
					break;
				case 1:
					break;
				case 2:
					break;
				default:
					break;	
			}
				
		}while(opcion!=0);
*/
	}

}
