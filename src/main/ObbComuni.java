package main;

import java.util.Arrays;
import java.util.Random;

public class ObbComuni {
							//da usare durante la partita per capire l'ordine dei punti 2-4-6-8
	
	static int [] estratto= new int[2];
	static int i=0;
	static boolean [][] realizzati=new boolean[4][2];
	
	public static int AssegnaCarta() {
		
		int valore;
		boolean esiste=false;
		Random random= new Random();
		do {
			
			esiste=false;
			valore=random.nextInt(12)+1;
			for(int b=0; b<estratto.length; b++) { //per estrarre carte.comuni diverse
				if(valore==estratto[b]) {
					esiste=true;
				}
			}
		}while(esiste);
		Obbiettivo(valore);
		estratto[i]=valore;
		i++;
		return valore;
	}
	
	public static boolean PuntiPersonali(Tile libreria[][], int ncarta, int giocatore){ //nome rivedibile //si riesce a passare libreria ed analizzarla?
		
		boolean realizzato=false;
		boolean primacarta=true;
		int conta=0;
		if(realizzati[giocatore][0]==true && realizzati[giocatore][1]==true ) { //per evitare cicli inutili
			return realizzato;
		}
		if(estratto[0]!=ncarta) {
			primacarta=false;
		}
		if(primacarta==false && realizzati[giocatore][1]==true) {
			return realizzato;
		}
		if(primacarta==true && realizzati[giocatore][0]==true) {
			return realizzato;
		}
		
		switch (ncarta) { //alternativa allo switch?
		  case 1: //non sicuro che funzioni ma idea  //ordine delle immagini delle istruzioni 
			  
			  for(int riga=0; riga<6; riga++) { //controllo su riga
					for(int colonna=1; colonna<5; colonna++) {
						if((libreria[riga][colonna].getColor()).equals(libreria[riga][colonna-1].getColor())){ //.toString da sistemare come metodo
							conta++;
						}
					}
				}
			  for(int colonna=0; colonna<5; colonna++) { //controllo su colonna
					for(int riga=1; riga<6; riga++){
						if((libreria[riga][colonna].getColor()).equals(libreria[riga-1][colonna].getColor())){
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
					for(int colonna=2; colonna<5; colonna++) {
						if((libreria[riga][colonna].getColor().equals(libreria[riga][colonna+1].getColor())) //.toString da sistemare come metodo
								&&(libreria[riga][colonna-2].getColor().equals(libreria[riga][colonna-1].getColor()))){
							if(libreria[riga][colonna].getColor().equals(libreria[riga][colonna-2].getColor())) {
								conta++;
							}
						}
					}
				}
			  for(int colonna=0; colonna<5; colonna++) { //controllo su colonna
					for(int riga=2; riga<5; riga++){
						if((libreria[riga][colonna].getColor().equals(libreria[riga+1][colonna].getColor()))
								&&(libreria[riga-2][colonna].getColor().equals(libreria[riga-1][colonna].getColor()))){
							if(libreria[riga][colonna].getColor().equals(libreria[riga-2][colonna].getColor())) {
								conta++;
							}
						}
					}
				}
			  if(conta>3) {
				  realizzato=true;
			  }
		    break;
		  case 3:
			  if((libreria[0][0].getColor()==libreria[5][0].getColor())&&(libreria[0][4].getColor()==libreria[5][4].getColor())) {
				  realizzato=true;
			  }
		    
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
			   	for(int colonna=0; colonna<4; colonna++) {
			   		for(int riga=0; riga<5; riga++) {
			   			
			   		}
			   	}
			break;

		}
		
		if(realizzato==true && primacarta==true) {
			realizzati[giocatore][0]=true;
		}
		if(realizzato==true && primacarta==false) {
			realizzati[giocatore][1]=true;
		}
		return realizzato;
	}

	public static void Obbiettivo(int numerocarta) {
		
		switch (numerocarta) { 
		  case 1:
			  System.out.println("Six groups each containing at least\r\n"
			  		+ "2 tiles of the same type (not necessarily\r\n"
			  		+ "in the depicted shape).\r\n"
			  		+ "The tiles of one group can be different\r\n"
			  		+ "from those of another group.\r\n"
			  		+ "");
		    break;
		  case 2: 
			  System.out.println("Four groups each containing at least\r\n"
			  		+ "			  4 tiles of the same type (not necessarily\r\n"
			  		+ "			  in the depicted shape).\r\n"
			  		+ "			  The tiles of one group can be different\r\n"
			  		+ "			  from those of another group.");
		    break;
		  case 3:
			  System.out.println("Four tiles of the same type in the four\r\n"
			  		+ "corners of the bookshelf");
		    break;
		  case 4:
			  System.out.println("Two groups each containing 4 tiles of\r\n"
			  		+ "the same type in a 2x2 square. The tiles\r\n"
			  		+ "of one square can be different from\r\n"
			  		+ "those of the other square.");
		    break;
		  case 5:
			  System.out.println("Three columns each formed by 6 tiles Five tiles of the same type forming an X.\r\n"
			  		+ "of maximum three different types. One\r\n"
			  		+ "column can show the same or a different\r\n"
			  		+ "combination of another column.");
		    break;
		  case 6:
			  System.out.println("Eight tiles of the same type. There’s no\r\n"
			  		+ "restriction about the position of these\r\n"
			  		+ "tiles.");
		    break;
		  case 7:
			  System.out.println("Five tiles of the same type forming a\r\n"
			  		+ "diagonal. ");
		    break;
		  case 8:
			  System.out.println("Four lines each formed by 5 tiles of\r\n"
			  		+ "maximum three different types. One\r\n"
			  		+ "line can show the same or a different\r\n"
			  		+ "combination of another line"); 
			break;
		  case 9:
			  System.out.println("Two columns each formed by 6\r\n"
			  		+ "different types of tiles."); 
			break;
		  case 10:
			  System.out.println("Two lines each formed by 5 different\r\n"
			  		+ "types of tiles. One line can show the\r\n"
			  		+ "same or a different combination of the\r\n"
			  		+ "other line."); 
			break;
		  case 11:
			  System.out.println("Five tiles of the same type forming an X."); 
			break;
		  case 12:
			  System.out.println("Five columns of increasing or decreasing\r\n"
			  		+ "height. Starting from the first column on\r\n"
			  		+ "the left or on the right, each next column\r\n"
			  		+ "must be made of exactly one more tile.\r\n"
			  		+ "Tiles can be of any type. "); 
			break;
		}
	}
}
