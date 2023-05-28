package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import main.Carte.ObbComuni;
import main.Carte.ObbPersonale;
import main.ElementiDiGioco.Board;
import main.ElementiDiGioco.Libreria;
import main.Giocatore.Player;
import main.Tessere.Tile;

public class Main {
	public static void main(String[] args){
		
		Scanner input=new Scanner(System.in);
		
		//Lettura numero di giocatori
		boolean userInt=false;
		int n=0;
		do { //aggiunto dopo
			System.out.println("In quanti volete giocare? da 2 a 4");
			try {
				n=Integer.parseInt(input.nextLine());
				userInt=true;
			}
			catch(NumberFormatException e) {
				System.out.println("L'input non e' valido, riprova");
				userInt=false;
			}
		}while(n<2 || n>4 || !userInt);
		
		//Vengono creati i profili dei giocatori
		List<Player> giocatori= new ArrayList<Player>();
		for(int i=0; i<n; i++) {
			String nome=new String();
			int cartaobb;
			do {
				userInt=true;
			System.out.println("Inserisci nome del giocatore "+(1+i)+": ");
			nome=input.nextLine();
			for(int y=0; y<giocatori.size(); y++) {
				if(giocatori.get(y).nome!=null) {
					if(giocatori.get(y).nome.equals(nome)) {
						userInt=false;
				}
			}
			}
			if(!userInt) {
				System.out.println("Si prega di non inserire nomi uguali");
				}
			}while(!userInt);
			cartaobb=ObbPersonale.AssegnaCarta();
			Player inserimento=new Player(nome, cartaobb);
			giocatori.add(inserimento);
		}
		
		int cartacomune1, cartacomune2; 
		boolean primo=false;
		System.out.println("");
		cartacomune1=ObbComuni.AssegnaCarta();
		cartacomune2=ObbComuni.AssegnaCarta();
		int [] punticarta1= new int[4];
		int [] punticarta2= new int[4];
		punticarta1[3]=8;
		punticarta2[3]=8;
		if(n!=2) { //aggiungi token per ogni carta (ngiocatori) 4-8, 4-6-8, 2-4-6-8
			punticarta1[2]=6;
			punticarta1[2]=6;
			punticarta1[1]=4;
			punticarta1[1]=4;
			if(n==4) {
				punticarta1[0]=2;
				punticarta1[0]=2;
			}
		}
		if(n==2){
			punticarta1[2]=4;
			punticarta1[2]=4;
		}
		
		Collections.shuffle(giocatori); //mischia giocatori
		for(int i=0; i<n; i++) {
			Tile[][] creazione= new Tile[6][5];
			Libreria.librerie.add(creazione); //crea librerie con stesso ordine giocatori
		}
		System.out.println("L'ordine di gioco sara': ");
		for(Player prova2: giocatori) {
			System.out.println(prova2.nome);
		} 
		
		int turno=0;	
		String risposta=new String();
		int contaCComuni1=3; 
		int contaCComuni2=3;
		Board.Board(n); //set up Board;
		
		while(!primo) {
			turno++;
			System.out.println("E' iniziato il "+turno+" turno");
			for(int i=0; i<n; i++){
				
				System.out.println("Tocca al giocatore di nome "+giocatori.get(i).nome);
				
				if(turno==1) {
					ObbPersonale.Obbiettivo(giocatori.get(i).numeroobb, turno);
				}
				else if(turno>1){
				System.out.println("Vuoi rivedere gli obbiettivi comuni ? 'si' per rivederli");
				risposta=input.nextLine();
				if(risposta.equals("si")) {
					
					ObbComuni.Obbiettivo(cartacomune1);
					ObbComuni.Obbiettivo(cartacomune2);
				}
				System.out.println("Vuoi rivedere l'obbiettivo personale? 'si' per rivederli");
				risposta=input.nextLine();
				if(risposta.equals("si")) {
					ObbPersonale.Obbiettivo(giocatori.get(i).numeroobb, turno);
				}
				System.out.println("Vuoi la tua libreria? 'si' per rivederla");
				risposta=input.nextLine();
				if(risposta.equals("si")) {
					Libreria.stampaLibreria(i);
					}
				}
				Tile[] provatessere=Board.SceltaUtente(i);
				Libreria.aggiungiTiles(provatessere, i);
				if(ObbComuni.PuntiPersonali(Libreria.librerie.get(i), cartacomune1, i)) {
					System.out.println("Hai realizzato per "+(4-contaCComuni1)+" il primo obbiettivo comune");
					System.out.println("Hai realizzato +"+punticarta1[contaCComuni1]+" punti");
					giocatori.get(i).punti=punticarta1[contaCComuni1];
					contaCComuni1--;
				}
				if(ObbComuni.PuntiPersonali(Libreria.librerie.get(i), cartacomune2, i)) {
					System.out.println("Hai realizzato per "+(4-contaCComuni1)+" il secondo obbiettivo comune");
					System.out.println("Hai realizzato +"+punticarta2[contaCComuni2]+" punti");
					giocatori.get(i).punti=punticarta2[contaCComuni2];
					contaCComuni2--;
				}
				int spaziliberi=Libreria.calcolaSpazi(i);
				if(spaziliberi==0 && primo==false) {
					System.out.println("Hai riempito per primo tutta la libreria, realizzando 1 punto");
					primo=true;
					giocatori.get(i).punti+=1;
				}	
		}
		}
		System.out.println("Fine gioco e ora si avvia conteggio");
			
		for(int i=0; i<n; i++){
			System.out.println("Il giocatore "+giocatori.get(i).nome+" :");
			giocatori.get(i).punti+=ObbPersonale.PuntiPersonali(Libreria.librerie.get(i), giocatori.get(i).numeroobb);
			System.out.println();
			giocatori.get(i).punti+=ObbPersonale.PuntiPersonali(Libreria.librerie.get(i));
			System.out.println();
		}
		int [][] classifica= new int [n][2];
		for(int i=0; i<n; i++){
			classifica[i][0]=i;
			classifica[i][1]=giocatori.get(i).punti;
		}
	    int temp = 0;  
	    int temp1=0;
	    for(int i=0; i < n; i++){  
	       for(int j=1; j < (n-i); j++){  
	           if(classifica[j-1][1] > classifica[j][1]){  
	                //swap elements  
	             temp = classifica[j-1][1];  
	             temp1=classifica[j-1][0];
	             classifica[j-1][1] = classifica[j][1];  
	             classifica[j-1][0]=classifica[j][0]; 
	             classifica[j][1] = temp;  
	             classifica[j][0]=temp1;
		
	           }
	       }
	    }
	    System.out.println("Classifica");
	    for(int i=n-1; i >=0; i--) {
	    	System.out.println(i+" :"+giocatori.get(classifica[i][0]).nome+" con "+ classifica[i][1]+" punti");
	    }

	}
}
	
