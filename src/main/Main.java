package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		
		Scanner input=new Scanner(System.in);
		
		int n;
		System.out.println("In quanti volete giocare? da 2 a 4");
		do {
		n=input.nextInt();
		}while(n<2 && n>4);

		List<Player> giocatori= new ArrayList<Player>();
		for(int i=0; i<n; i++) {
			String nome=new String();
			int cartaobb;
			System.out.println("Inserisci nome del giocatore "+(1+i));
			nome=input.next();
			cartaobb=ObbPersonale.AssegnaCarta();
			Player inserimento=new Player(nome, cartaobb);
			giocatori.add(inserimento);
		}
		
		int cartacomune1, cartacomune2; //aggiungi token per ogni carta (ngiocatori) 4-8 4-6-8 2-4-6-8
		//token vittora si puo evitare creazione basta tenerne conto +1 punto per chi finisce per primo
		cartacomune1=ObbComuni.AssegnaCarta();
		cartacomune2=ObbComuni.AssegnaCarta();
		
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
		
		/*List<Libreria> librerie= new ArrayList<Libreria>();
		for(int i=0; i<n; i++) {
			Libreria prova=new Libreria(i); //eliminabile
			librerie.add(prova); //stesso ordine dei giocatori (primo giocatore, prima libreria)
		}*/ 
		int turno=0;
		boolean finegioco=true;
		Board.Board(n); //set up Board;
		while(finegioco) {
			turno++;
			System.out.println("E' iniziato il "+turno+" turno");
			for(int i=0; i<n; i++){
				System.out.println("Tocca al giocatore di nome "+giocatori.get(i).nome);
				//ciclo for/ do while e aggiunta finite le tessere su board, finito il gioco e countdown se vince non l'ultimo del giro 
				Tile[] provatessere=Board.SceltaUtente(i);
				Libreria.aggiungiTiles(provatessere, i);
			}
		//controllo su common golas (come ne tengo traccia di chi l'ha gia fatto senza assegnare più punti
		}
	}

}
