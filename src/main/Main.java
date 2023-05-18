package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		
		Scanner input=new Scanner(System.in);
		
		//Lettura numero di giocatori
		int n;
		System.out.println("In quanti volete giocare? da 2 a 4");
		do {
		n=input.nextInt();
		}while(n<2 && n>4);
		
		//Vengono creati i profili dei giocatori
		List<Player> giocatori= new ArrayList<Player>();
		for(int i=0; i<n; i++) {
			String nome=new String();
			int cartaobb;
			System.out.println("Inserisci nome del giocatore "+(1+i)+": ");
			nome=input.next();
			cartaobb=ObbPersonale.AssegnaCarta();
			ObbPersonale.Obbiettivo(cartaobb);
			Player inserimento=new Player(nome, cartaobb);
			giocatori.add(inserimento);
		}
		
		int cartacomune1, cartacomune2; 
		boolean primo=false;
		cartacomune1=ObbComuni.AssegnaCarta();
		cartacomune2=ObbComuni.AssegnaCarta();
		int [] punticarta1= new int[4];
		int [] punticarta2= new int[4];
		punticarta1[3]=8;
		punticarta2[3]=8;
		if(n!=2) { //aggiungi token per ogni carta (ngiocatori) 4-8 4-6-8 2-4-6-8
			punticarta1[2]=6;
			punticarta1[2]=6;
			punticarta1[1]=4;
			punticarta1[1]=4;
			if(n==4) {
				punticarta1[0]=2;
				punticarta1[0]=2;
			}
		}else {
			punticarta1[2]=4;
			punticarta1[2]=4;
		}
		
		Collections.shuffle(giocatori);
		for(int i=0; i<n; i++) {
			Tile[][] creazione= new Tile[6][5];
			Libreria.librerie.add(creazione);
		}
		System.out.println("L'ordine di gioco sarà: ");
		for(Player prova2: giocatori) {
			System.out.println(prova2.nome+" "+ prova2.numeroobb);
		}
		//System.out.println(cartacomune1+" "+ cartacomune2); 
		//ObbComuni.Obbiettivi(cartacomune1);
		//ObbComuni.Obbiettivi(cartacomune2);
		
		/*List<Libreria> librerie= new ArrayList<Libreria>();
		for(int i=0; i<n; i++) {
			Libreria prova=new Libreria(i); //eliminabile
			librerie.add(prova); //stesso ordine dei giocatori (primo giocatore, prima libreria)
		}*/ 
		
		int turno=0;
		boolean finegioco=true;
		int contaCComuni1=4;
		int contaCComuni2=4;
		Board.Board(n); //set up Board;
		while(finegioco) {
			turno++;
			System.out.println("E' iniziato il "+turno+"° turno");
			for(int i=0; i<n; i++){
				System.out.println("Tocca al giocatore di nome "+giocatori.get(i).nome);
				//ciclo for/ do while e aggiunta finite le tessere su board, finito il gioco e countdown se vince non l'ultimo del giro 
				Tile[] provatessere=Board.SceltaUtente(i);
				Libreria.aggiungiTiles(provatessere, i);
				if(ObbComuni.PuntiPersonali(Libreria.librerie.get(i), cartacomune1, i)) {
					giocatori.get(i).punti=punticarta1[contaCComuni1];
					contaCComuni1--;
				}
				if(ObbComuni.PuntiPersonali(Libreria.librerie.get(i), cartacomune2, i)) {
					giocatori.get(i).punti=punticarta2[contaCComuni2];
					contaCComuni2--;
				}
				//libreriapiena->finegioco
		}
			//to do list 
			//tessere adiacenti
			//cartecomuni 
			//guida giocatore 
			//rendere simmetricooutput
			//gestisci input che non CRASH
			//dividi in pacchetti
//			
		for(int i=0; i<n; i++){
			giocatori.get(i).punti+=ObbPersonale.PuntiPersonali(Libreria.librerie.get(i), giocatori.get(i).numeroobb);
		}
		//controllo su personal goal
			//e tessere adiacenti
	}
	}
}
