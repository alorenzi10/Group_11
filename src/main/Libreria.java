package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Libreria{ 
	
	private static int colonne=5;
	private static int righe=6;
	int id;
	static List<Tile[][]> librerie= new ArrayList<Tile[][]>();
	
	public static void aggiungiTiles(Tile [] selezionate, int b) {
		
		Tile[][] libreria=librerie.get(b);
		Scanner in=new Scanner(System.in);
		int test;
		
		//stampaLibreria(b);
		for (int x=0; x<selezionate.length; x++) { //stampa tessere prese
			if(selezionate[x]!=null) {
				System.out.println((x+1)+" "+selezionate[x].getColor()+" ");
			}
		}
		if(selezionate.length>1) { //da sistemare
			for (int x=0; x<selezionate.length-1; x++) {
				if(selezionate[x]!=null) {
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
			}
			for (int x=0; x<selezionate.length; x++) { //stampa tessere una volta invertite, aggiungi conferma?
				if(selezionate[x]!=null) {
					System.out.println((x+1)+" "+selezionate[x].getColor()+" ");
				}
			}
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
		//AGGIUNGI CONTROLLO PIENEZZA, DA SISTEMARE ANCHE SELEZIONE POSSIBILE ALLORA CON NRIMANENTI E +1PUNTO PER FINE
		//AGGIUNGI CONTROLLO CARTE COMUNI
		stampaLibreria(b);	
	}
	
	public static void stampaLibreria(int b) { 
		
		Tile[][] libreria=librerie.get(b);
		for(int riga=righe-1; riga>=0; riga--) { //stampa invertita
			System.out.println("");
			for(int colonna=0; colonna<colonne; colonna++) {
				if(libreria[riga][colonna]==null) {
					System.out.print("|        |");
				}else {
				System.out.print("|"+libreria[riga][colonna].getColor()+"|");
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
