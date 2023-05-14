package main;

import java.util.Random;

public class ObbPersonale { 
	
	static int [] estratto= new int[4];
	static int i=0;
	
	public static int AssegnaCarta() {
		
		int valore;
		boolean esiste=false;
		Random random = new Random();
		do {
			
			esiste=false;
			valore=random.nextInt(12)+1;
			for(int b=0; b<estratto.length; b++) { //per estrarre carte obb. diverse per ogni giocatore
				if(valore==estratto[b]) {
					esiste=true;
				}
			}
		}while(esiste);
		
		estratto[i]=valore;
		i++;
		return valore;
	}
	
	public static int PuntiPersonali(Libreria libreria, int ncarta){ //nome rivedibile //si riesce a passare libreria ed analizzarla?
		
		int punti=0;
		
		switch (ncarta) { //alternativa allo switch?
		  case 1: //ordine delle immagini fornite del prof
			  
		    
		    break;
		  case 2:
		    
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
		return punti;
	}
	
	public static void Obbiettivo(int numerocarta) {
		
		switch (numerocarta) { //alternativa allo switch?
		  case 1:
			  
		    break;
		  case 2: //non sicuro che funzioni ma idea
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
	}
}
