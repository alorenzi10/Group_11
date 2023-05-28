package main.Carte;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Tessere.Tile;

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
	
	public static int PuntiPersonali(Tile[][] libreria1) {
		int punti=0;
		int contag=1;
		int [][] raggruppamenti= new int [6][5];
		
		//controllo per righe 
				for(int i=0; i<5; i++) {
					for(int y=0; y<5; y++) {
						if(libreria1[i][y]!=null) {
							if(libreria1[i+1][y]!=null) {
								if(libreria1[i+1][y].getColor().equals(libreria1[i][y].getColor())) {
									if(raggruppamenti[i][y]==0) {
									raggruppamenti[i][y]=contag;
									raggruppamenti[i+1][y]=contag;
									contag+=1;
									}
									else {
										int temp=raggruppamenti[i][y];
										raggruppamenti[i+1][y]=temp;
									}
								}
							}
						}
					}	
				}
				/*for(int i=0; i<6; i++) {
					for(int y=0; y<5; y++) {
						System.out.print(raggruppamenti[i][y]+"|");
					}
					System.out.println("");
				}
				System.out.println("");*/
				
				for(int y=0; y<4; y++) {
					for(int i=0; i<6; i++) {
						if(libreria1[i][y]!=null) {
							if(libreria1[i][y+1]!=null) {
								if(libreria1[i][y+1].getColor().equals(libreria1[i][y].getColor())) {
									if(raggruppamenti[i][y]==0) {
										if(raggruppamenti[i][y+1]==0) {
										raggruppamenti[i][y]=contag;
										raggruppamenti[i][y+1]=contag;
										contag+=1;
										}
										else {
											int temp=raggruppamenti[i][y+1];
											raggruppamenti[i][y]=temp;
										}
									}
									else {
										
											int temp=raggruppamenti[i][y];
											raggruppamenti[i][y+1]=temp;
										
									}
								}
							}
						}
					}	
				}
				/*for(int i=0; i<6; i++) {
					for(int y=0; y<5; y++) {
						System.out.print(raggruppamenti[i][y]+"|");
					}
					System.out.println("");
				}
				System.out.println("");*/
				for(int i=0; i<5; i++) {
					for(int y=0; y<5; y++) {
						if(libreria1[i][y]!=null) {
							if(libreria1[i+1][y]!=null) {
								if(libreria1[i+1][y].getColor().equals(libreria1[i][y].getColor())) {
										int temp=raggruppamenti[i+1][y];
										raggruppamenti[i][y]=temp;
								
								}
							}
						}
					}	
				}
				/*for(int i=0; i<6; i++) {
					for(int y=0; y<5; y++) {
						System.out.print(raggruppamenti[i][y]+"|");
					}
					System.out.println("");
				}*/
				int [][] valori=new int [11][2];
				int conta=0;
				int conta2=0;
				for(int i=0; i<6; i++) {
					for(int y=0; y<5; y++) {
						for(int z=0; z<11; z++) {
							if(raggruppamenti[i][y]!=valori[z][0]) {
								conta2+=1;
								
							}
							else {
								valori[z][1]+=1;
							}
						}
						if(conta2==valori.length) {
							valori[conta][0]=raggruppamenti[i][y];
							valori[conta][1]=1;
							conta++;
							}
						conta2=0;
					}
				}
				
				for(int z=0; z<11; z++) {
					if(valori[z][0]!=0) {
					if(valori[z][1]==3) {
						System.out.println("Hai raggruppato 3 tessere uguali, +2 punti");
						punti+=2;
					}
					if(valori[z][1]==4) {
						System.out.println("Hai raggruppato 4 tessere uguali, +3 punti");
						punti+=3;
					}
					if(valori[z][1]==5) {
						System.out.println("Hai raggruppato 5 tessere uguali, +5 punti");
						punti+=5;
					}
					if(valori[z][1]>5) {
						System.out.println("Hai raggruppato almeno6  tessere uguali, +8 punti");
						punti+=8;
					}
					}
				}
				/*System.out.println("");
				for(int z=0; z<11; z++) {
					System.out.println(valori[z][0]+" "+valori[z][1]);
				}
				System.out.println(punti);*/
				
				return punti;
			}
		
	
	
	public static int PuntiPersonali(Tile[][] libreria, int ncarta){ //confronto tra
		
		int punti=0;
		int n=0;
		if(ncarta==estratto[0]) { 
			n=0;
		}
		if(ncarta==estratto[1]) {
			n=1;
		}
		if(ncarta==estratto[2]) {
			n=2;
		}
		if(ncarta==estratto[3]) {
			n=3;
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
			}
		}
		if(punti==3) {
			System.out.println("Hai messo le tessere in 3 posti richiesti, realizzando 4 punti");
			punti=4;
		}
		else if(punti==4) {
			System.out.println("Hai messo le tessere in 4 posti richiesti, realizzando 6 punti");
			punti=6;
		}
		else if(punti==5) {
			System.out.println("Hai messo le tessere in 5 posti richiesti, realizzando 9 punti");
			punti=9;
		}
		else if(punti==6) {
			System.out.println("Hai messo le tessere in 6 posti richiesti, realizzando 12 punti");
			punti=12;
		}
		return punti;
	}
	
	public static void Obbiettivo(int numerocarta, int turno) { //permette di visualizzare la propria cara obbiettivo
		
		Tile[][] libreria=new Tile[6][5];
		Tile.Color[] colors=Tile.Color.values(); //Green, White, Yellow, Blue, Pink, LightBlue;
		Tile.Color green=colors[0];
		Tile.Color white=colors[1];
		Tile.Color yellow=colors[2];
		Tile.Color blu=colors[3];
		Tile.Color pink=colors[4];
		Tile.Color light=colors[5];
		
		
		switch (numerocarta) { 
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
		if(turno==1) {
			confronti.add(libreria);
		}

		String spazio = "            ";
		String separatore="|";
			for(int riga=6-1; riga>=0; riga--) { //stampa invertita della libreria
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
