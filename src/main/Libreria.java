package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.Tessere.Tile;

public class Libreria{ 
	
	private static int colonne=5;
	private static int righe=6;
	int id;
	static List<Tile[][]> librerie= new ArrayList<Tile[][]>();
	
	public static void aggiungiTiles(Tile [] selezionate, int b) {
		
		Tile[][] libreria=librerie.get(b);
		Scanner in=new Scanner(System.in);
		boolean conferma=true;
		int test;
		int lunghezza=0;
		
		stampaLibreria(b);
		
		for (int x=0; x<selezionate.length; x++) {
			if(selezionate[x]!=null) {
				lunghezza++;
			}
		}
		if(lunghezza>1) { 
			do{//da sistemare
			for (int x=0; x<=lunghezza-2; x++) {
				
				for (int z=0; z<selezionate.length; z++) { //stampa tessere prese
					if(selezionate[z]!=null) {
						System.out.println((z+1)+" "+selezionate[z].getColor()+" ");
					}
				}
					int n;
					Tile temp;
					System.out.println("Con che tessera vuoi scambiare la tessera numero "+(x+1)+ " 9 per ignorare" );
					n=in.nextInt();	//meccanismo di scambio da sistemare (es non spostare l'ultima e farlo più passo a passo
					if(n!=9) {  //mostra le tessere e chiedi in che ordine vorrebbe le tessere (migliore)
						temp=selezionate[x];
						selezionate[x]=selezionate[n-1];
						selezionate[n-1]=temp;
					}
			}
			
			for (int x=0; x<selezionate.length; x++) { //stampa tessere una volta invertite, aggiungi conferma?
				if(selezionate[x]!=null) {
					System.out.println((x+1)+" "+selezionate[x].getColor()+" ");
				}
			}
			String prova=new String();
			conferma=true;
			System.out.println("Confermi Ordine tessere? si per confermare");
			prova=in.next();
			if(prova.equals("si")) {
				conferma=false;
			}
			}while(conferma);
		}
		int libere=0;
		int counter=0;
		do {
		System.out.println("Seleziona colonna in cui inserire 0-4"); //aggiungi commento in caso di errore colonna
		test=in.nextInt(); 
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
}
