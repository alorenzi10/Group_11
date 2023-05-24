package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObbPersonale { 
	
	static int [] estratto= new int[4];
	static List<Tile[][]> confronti= new ArrayList<Tile[][]>();
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
	
	public static int PuntiPersonali(Tile[][] libreria, int ncarta){ //nome rivedibile //si riesce a passare libreria ed analizzarla?
		
		int punti=0;
		int n;
		if(ncarta==estratto[0]) {
			n=0;
		}
		else {
			n=1;
		}
		Tile[][] confronto=confronti.get(n);
		for(int riga=0; riga<6; riga++){
			for(int colonna=0; colonna<5; colonna++){
				if(confronto[riga][colonna]!=null) {
					if(libreria[riga][colonna]!=null) {
					if((confronto[riga][colonna].getColor()).equals(libreria[riga][colonna].getColor())) {
						punti++;
					}
					}
				}
				
				//trova modo per confrontare con le librerie gia prodotte
			}
		}
		
		return punti*2;
	}
	
	public static void Obbiettivo(int numerocarta) {
		
		Tile[][] libreria=new Tile[6][5];
		Tile.Color[] colors=Tile.Color.values(); //Green, White, Yellow, Blue, Pink, LightBlue;
		Tile.Color green=colors[0];
		Tile.Color white=colors[1];
		Tile.Color yellow=colors[2];
		Tile.Color blu=colors[3];
		Tile.Color pink=colors[4];
		Tile.Color light=colors[5];
		
		
		switch (numerocarta) { //alternativa allo switch?
		  case 1:
			  
			  libreria[5][0]=new Tile(pink);//pink
			  libreria[2][1]=new Tile(yellow);//yellow
			  libreria[0][2]=new Tile(light);//lightblu
			  libreria[5][2]=new Tile(blu);//blu
			  libreria[3][3]=new Tile(white);//white
			  libreria[4][4]=new Tile(green);//green
					  
		    break;
		  case 2: //non sicuro che funzioni ma idea
			  libreria[3][0]=new Tile(green);//green
			  libreria[4][1]=new Tile(pink);//pink
			  libreria[3][2]=new Tile(yellow);//yellow
			  libreria[1][3]=new Tile(light);//lightblue
			  libreria[2][4]=new Tile(white);//white
			  libreria[0][4]=new Tile(blu);//blue
		    break;
		  case 3:
			  libreria[0][0]=new Tile(white);//white
			  libreria[4][0]=new Tile(blu);//blue
			  libreria[2][1]=new Tile(green);//green
			  libreria[3][2]=new Tile(pink);//pink
			  libreria[4][3]=new Tile(yellow);//yellow
			  libreria[2][4]=new Tile(light);//lightblue
		    break;
		  case 4:
			  libreria[3][0]=new Tile(light);//lightbue
			  libreria[1][1]=new Tile(white);//w
			  libreria[1][2]=new Tile(green);//gren
			  libreria[3][2]=new Tile(blu);//b
			  libreria[2][3]=new Tile(pink);//p
			  libreria[5][4]=new Tile(yellow);//y
		    break;
		  case 5:
			  libreria[0][0]=new Tile(yellow);//y
			  libreria[2][1]=new Tile(blu);//b
			  libreria[4][1]=new Tile(light);//lb
			  libreria[2][2]=new Tile(white);//w
			  libreria[0][3]=new Tile(green);//g
			  libreria[1][4]=new Tile(pink);//p
		    break;
		  case 6:
			  libreria[0][0]=new Tile(pink);//pink
			  libreria[1][1]=new Tile(yellow);//y
			  libreria[5][2]=new Tile(light);//lb
			  libreria[3][3]=new Tile(white);//w
			  libreria[1][3]=new Tile(blu);//b
			  libreria[5][4]=new Tile(green);//g
		    break;
		  case 7:
			  libreria[5][0]=new Tile(green);//g
			  libreria[2][0]=new Tile(light);//lb
			  libreria[3][1]=new Tile(pink);//pink
			  libreria[0][2]=new Tile(white);//w
			  libreria[4][3]=new Tile(blu);//b
			  libreria[1][4]=new Tile(yellow);//y
		    break;
		  case 8:
			  libreria[2][0]=new Tile(pink);//p
			  libreria[4][1]=new Tile(green);//g
			  libreria[3][2]=new Tile(light);//lb
			  libreria[1][3]=new Tile(white);//w
			  libreria[0][3]=new Tile(yellow);//y
			  libreria[5][4]=new Tile(blu);//b
			break;
		  case 9:
			  libreria[0][0]=new Tile(blu);//b
			  libreria[1][1]=new Tile(light);//lb
			  libreria[3][2]=new Tile(green);//g
			  libreria[5][3]=new Tile(yellow);//y
			  libreria[1][4]=new Tile(pink);//p
			  libreria[2][4]=new Tile(white);//w
			break;
		  case 10:
			  libreria[0][3]=new Tile(white);//w
			  libreria[1][1]=new Tile(blu);//b
			  libreria[4][1]=new Tile(yellow);//y
			  libreria[0][3]=new Tile(pink);//p
			  libreria[2][3]=new Tile(green);//g
			  libreria[5][4]=new Tile(light);//lb
			break;
		  case 11:
			  libreria[3][0]=new Tile(yellow);//y
			  libreria[4][1]=new Tile(white);//w
			  libreria[5][2]=new Tile(pink);//p
			  libreria[2][2]=new Tile(blu);//b
			  libreria[0][3]=new Tile(light);//lb
			  libreria[1][4]=new Tile(green);//g
			break;
		  case 12:
			  libreria[0][0]=new Tile(green);//g
			  libreria[4][1]=new Tile(pink);//p
			  libreria[5][2]=new Tile(white);//w
			  libreria[3][2]=new Tile(blu);//b
			  libreria[2][3]=new Tile(light);//lb
			  libreria[1][4]=new Tile(yellow);//y
			break;
			
		}
		confronti.add(libreria);

		String spazio = "            ";
		String separatore="|";
			for(int riga=6-1; riga>=0; riga--) { //stampa invertita
				System.out.println("");
				for(int colonna=0; colonna<5; colonna++) {
					
					if(libreria[riga][colonna]==null) {
						System.out.print(separatore+spazio+separatore);
					}else {
						String sLibreria = libreria[riga][colonna].getColor().toString();
						int lunghezzaSpazio =spazio.length();
						int lunghezzaLibreria = sLibreria.length();
						int spaziDopo = lunghezzaSpazio - lunghezzaLibreria;

		                String rigaTesto = separatore+sLibreria + spazio.substring(0, spaziDopo) +separatore;
		                System.out.print(rigaTesto);
					}
				}
			}
			System.out.println("");

		
	}
}
