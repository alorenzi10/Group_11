package main.ElementiDiGioco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.Tessere.Tile;

public class Libreria{ 
	
	private static int colonne=5;
	private static int righe=6;
	int id;
	public static List<Tile[][]> librerie= new ArrayList<Tile[][]>(); //lista delle librerie giocatori
	
	public static void aggiungiTiles(Tile [] selezionate, int b) throws InterruptedException {
		
		Tile[][] libreria=librerie.get(b);
		Scanner in=new Scanner(System.in);
		boolean conferma=true;
		int test = 0;
		int lunghezza=0;
		int selezionatelunghezza=0;
		for(int i=0; i<selezionate.length; i++) {
			if(selezionate[i]!=null) {
				selezionatelunghezza+=1;
			}
		}
		boolean userInt;
		
		stampaLibreria(b);
		
		for (int x=0; x<selezionate.length; x++) {
			if(selezionate[x]!=null) {
				lunghezza++;
			}
		}
		if(lunghezza>1) { 
			String risposta=new String ();
			System.out.println("Vuoi cambiare l'ordine delle tessere? 'si' per cambiarle, invio per continuare");
			risposta=in.nextLine();
			if(risposta.equals("si")) {
			do{ //per scambio ordine delle tessere
			for (int x=0; x<=lunghezza-2; x++) {
				
				for (int z=0; z<selezionate.length; z++) { //stampa tessere prese
					if(selezionate[z]!=null) {
						System.out.println((z+1)+" "+selezionate[z].getColor()+" ");
					}
				}
					int n = 0;
					Tile temp;
					do { 
						
						try {
							System.out.println("Con che tessera vuoi scambiare la tessera numero "+(x+1)+ "? Premi 0 per ignorare" );
							n=Integer.parseInt(in.nextLine());
							userInt=true;
							if(n>selezionatelunghezza) {
								userInt=false;
								System.out.println("L'input deve essere tra le tessere proproste");
							}
							
						}
						catch(NumberFormatException e) {
							System.out.println("L'input non e' valido, riprova");
							userInt=false;
						}
					}while(!userInt);
					
					if(n!=0 ) {  //mostra le tessere e chiedi in che ordine vorrebbe le tessere 
						temp=selezionate[x];
						selezionate[x]=selezionate[n-1];
						selezionate[n-1]=temp;
					}
			}
			
			for (int x=0; x<selezionate.length; x++) { //stampa tessere una volta invertite
				if(selezionate[x]!=null) {
					System.out.println((x+1)+" "+selezionate[x].getColor()+" ");
				}
			}
			String prova=new String();
			conferma=true;
			System.out.println("Confermi Ordine tessere? 'si' per confermare, ogni altro carattere dire di no");
			prova=in.nextLine();
			if(prova.equals("si")) {
				conferma=false;
			}
			}while(conferma);
		}
			else {
				for (int z=0; z<selezionate.length; z++) { //stampa tessere prese
					if(selezionate[z]!=null) {
						System.out.println((z+1)+" "+selezionate[z].getColor()+" ");
					}
				}
			}
		}
		else {
			System.out.println("1 "+ selezionate[0].getColor());
		}
		int libere=0;
		int counter=0;
		
		do {
			do { 
				
				try {
					System.out.println("Seleziona colonna in cui inserire 0-4");
					test=Integer.parseInt(in.nextLine());
					userInt=true;
				}
				catch(NumberFormatException e) {
					System.out.println("L'input non e' valido, riprova");
					userInt=false;
				}
			}while(!userInt);
		libere=0;
		counter=0;
		for(int i=0; i<selezionate.length;i++) {
			if(selezionate[i]!=null) {
				counter++;
			}
		}
		for(int x=0; x<righe; x++) {
			if(libreria[x][test]==null) {
				libere++;
			}
		}
		if(counter>libere) {
			System.out.println("Seleziona un altra colonna questa non ha abbastanza spazio");
		}
		}while(counter>libere);
		
		int y=0;
		for(int x=0; x<righe; x++) {
			if(y<counter) {
				if(libreria[x][test]==null) {
					libreria[x][test]=selezionate[y];
						y++;
				}
			}
		}
		
		librerie.set(b, libreria);
		stampaLibreria(b);
		Thread.sleep(1000);
	}
	
	public static void stampaLibreria(int b) { 
		
	
		Tile[][] libreria=librerie.get(b);
		String spazio = "            ";
		String separatore="|";
		
		for(int riga=righe-1; riga>=0; riga--) { //stampa invertita
			System.out.println("");
			for(int colonna=0; colonna<colonne; colonna++) {
				
				if(libreria[riga][colonna]==null) {
					System.out.print(separatore+spazio+separatore);
				}else {
					String stringColore= libreria[riga][colonna].getColor().toString();
					int lunghezzaSpazio=spazio.length();
					int lungColore=stringColore.length();
					int calcolaSpazio=lunghezzaSpazio - lungColore;
	                String rigaTesto= separatore+stringColore+spazio.substring(0, calcolaSpazio)+separatore;
	                System.out.print(rigaTesto);
				}
				
			}
		}
		System.out.println("");
		System.out.println("|     0      |"+"|     1      |"+"|     2      |"+"|     3      |"+"|     4      |");
		
	}
	
	public static int calcolaSpazi(int giocatori) {
		
		int libere=0;
		Tile[][] libreria=librerie.get(giocatori);
		for(int x=0; x<righe; x++) {
			for(int y=0; y<colonne; y++) {
				if(libreria[x][y]==null) {
					libere++;
				}
			}
		}
		return libere;
	}
	
	public static int calcolaSpaziColonnaMax(int giocatori) {
		
		int libere=0;
		int max=0;
		Tile[][] libreria=librerie.get(giocatori);
		for(int y=0; y<colonne; y++) {
			for(int x=0; x<righe; x++) {
				if(libreria[x][y]==null) {
					libere++; 
				}
			}
			if(libere>max) {
				max=libere;
			}
			libere=0;
		}
		return max;
	}
}