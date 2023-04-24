package main;

public class ObbComuni {  //extendera qualcosa? //da legare con la "board"(?)
							//da usare durante la partita per capire l'ordine dei punti 2-4-6-8
	
	
public static boolean PuntiPersonali(Libreria libreria[][], int ncarta){ //nome rivedibile //si riesce a passare libreria ed analizzarla?
		
		boolean realizzato=false;
		int conta=0;
		
		switch (ncarta) { //alternativa allo switch?
		  case 1: //non sicuro che funzioni ma idea  //ordine delle immagini delle istruzioni 
			  
			  for(int riga=0; riga<6; riga++) { //controllo su riga
					for(int colonna=1; colonna<5; colonna++) {
						if(libreria[riga][colonna].equals(libreria[riga][colonna-1].toString())){
							conta++;
						}
					}
				}
			  for(int colonna=0; colonna<5; colonna++) { //controllo su colonna
					for(int riga=1; riga<6; riga++){
						if(libreria[riga][colonna].toString().equals(libreria[riga-1][colonna].toString())){
							conta++;
						}
					}
				}
			  if(conta>5) {
				  realizzato=true;
			  }
			  
		    break;
		  case 2: //non sicuro che funzioni ma idea
			  
			  for(int riga=0; riga<6; riga++) { //controllo su riga
					for(int colonna=2; colonna<4; colonna++) {
						if((libreria[riga][colonna].toString().equals(libreria[riga][colonna+1].toString()))
								&&(libreria[riga][colonna-2].toString().equals(libreria[riga][colonna-1].toString()))){
							conta++;
						}
					}
				}
			  for(int colonna=0; colonna<5; colonna++) { //controllo su colonna
					for(int riga=2; riga<5; riga++){
						if((libreria[riga][colonna].toString().equals(libreria[riga+1][colonna].toString()))
								&&(libreria[riga-2][colonna].toString().equals(libreria[riga-1][colonna].toString()))){
							conta++;
						}
					}
				}
			  if(conta>3) {
				  realizzato=true;
			  }
		    break;
		  case 3:
		    
		    break;
		  case 4:
		    
		    break;
		  case 5:
		    
		    break;
		  case 6:
		    
		    break;
		  case 7:
		    
		    break;
		  case 8:
			    
			break;
		  case 9:
			    
			break;
		  case 10:
			    
			break;
		  case 11:
			    
			break;
		  case 12:
			    
			break;

		}
		return realizzato;
	}
}
