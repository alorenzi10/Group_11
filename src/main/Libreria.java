package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Libreria{ //da legare con giocatore
	
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
			for (int x=0; x<selezionate.length; x++) {
				if(selezionate[x]!=null) {
					int n;
					Tile temp;
					System.out.println("Con che tessera vuoi scambiare la tessera numero "+(x+1)+ " 9 per ignorare" );
					n=in.nextInt();	//meccanismo di scambio da sistemare
					if(n!=9) {
						temp=selezionate[x];
						selezionate[x]=selezionate[n-1];
						selezionate[n-1]=temp;
					}
				}
			}
			for (int x=0; x<selezionate.length; x++) { //stampa tessere una volta invertite
				if(selezionate[x]!=null) {
					System.out.println((x+1)+" "+selezionate[x].getColor()+" ");
				}
			}
		}
		System.out.println("Seleziona colonna in cui inserire 0-4");
		test=in.nextInt(); //aggiungi ciclo controllo scelta e se colonna piena o si riempie e avanza qualche tessera
		int y=0;
		for(int x=0; x<selezionate.length; x++) {
			if(selezionate[x]!=null) { //temporaneo
			libreria[y][test]=selezionate[x];
			y++;
			}
		}
		
		librerie.set(b, libreria);
		stampaLibreria(b);
		
	}
	
	public static void stampaLibreria(int b) { //da inquadrare meglio output per farlo simmetrico
		
		Tile[][] libreria=librerie.get(b);
		for(int riga=0; riga<righe; riga++) { //da invertire stampa
			System.out.println("");
			for(int colonna=0; colonna<colonne; colonna++) {
				if(libreria[riga][colonna]==null) {
					System.out.print("|        |");
				}else {
				System.out.print("|"+libreria[riga][colonna].getColor()+"|");
				}
			}
		}
	}
	
	
	
	
	
}
