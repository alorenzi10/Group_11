package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Libreria{ //da legare con giocatore
	
	private static int colonne=5;
	private static int righe=6;
	int id;
	Tile[][] libreria=new Tile[righe][colonne];
	
	public Libreria(int numero) { //setta/resetta a null
		
		this.id=numero;
		for(int riga=0; riga<righe; riga++) {
			for(int colonna=0; colonna<colonne; colonna++) {
				libreria[riga][colonna]=null;
			}
		}
		
	}
	
	public void aggiungiTiles(Tile [] selezionate) {
		
		Scanner in=new Scanner(System.in);
		int test;
		int y=0;
		
		stampaLibreria();
		
		if(selezionate.length>1) {
			for (int x=0; x<selezionate.length; x++) {
				System.out.print(selezionate[x].getColor()+" ");
			}
			System.out.println(" ");
			for (int x=0; x<selezionate.length; x++) {
				int n;
				Tile temp;
				System.out.println("Seleziona ordine tessera "+x );
				n=in.nextInt();
				temp=selezionate[x];
				selezionate[x]=selezionate[n];
			}
			for (int x=0; x<selezionate.length; x++) {
				System.out.print(selezionate[x].getColor()+" ");
			}
		}
		System.out.println("Seleziona colonna in cui inserire");
		test=in.nextInt(); //aggiungi ciclo controllo scelta e se colonna piena
		for(int x=0; x<selezionate.length; x++) {
			libreria[y][test]=selezionate[x];
			y++;
		}
		stampaLibreria();
		
	}
	
	public void stampaLibreria() { //da inquadrare meglio output per farlo simmetrico
		
		for(int riga=righe-1; riga>=0; riga--) { //per stampare da alto verso il basso
			System.out.println("");
			for(int colonna=colonne-1; colonna>=0; colonna--) {
				if(libreria[riga][colonna]==null) {
					System.out.print("        ");
				}else {
				System.out.println(libreria[riga][colonna].getColor());
				}
			}
		}
	}
	
	
	
	
	
}
