package main;

import java.util.Random;
import java.util.Scanner;

public class Board{
	
	private static int counter=0;
	private static int righe=11;
	private static int colonne=11;
	static Scanner input=new Scanner(System.in);
	public static Tile[][] board=new Tile[righe][colonne];
	public static boolean [][] prendibili= new boolean [righe][colonne];
	
	public static void Board(int ngiocatori){
		
		Tiles.Inizializza(); //if primo giro
		Tiles.Mischia();
		Board.BoardSetUp(ngiocatori);
		Board.StampaBoard();
		Board.TesserePrendibili();
	} 
	 
	
	public static void BoardSetUp(int ngiocatori) {
		
		Tile[] avanzate=new Tile[10]; //preso 10 perché non penso ne avanzino di più
		int i=0;
		for(int riga=0; riga<righe; riga++) {  //controlla pezzi avanzati e resetta tutto a null
			for(int colonna=0; colonna<colonne; colonna++) {
				if(board[riga][colonna]!=null) {
					avanzate[i]=board[riga][colonna];
					i++;
				}
				board[riga][colonna]=null;
			}
		}
		
		if(i>0) { //se avanzano pezzi li rimetto nelle tiles e mischio
			counter=counter-i;
			for(int x=counter; x<counter; x++) {
				
				Tiles.tiles[x]=avanzate[i-1];
				i--;
			}
			Random rand=new Random();
			
			for(int x=counter; x<132;x++) {
				int r=x+rand.nextInt(132-counter);
				Tile temp=Tiles.tiles[r];
				Tiles.tiles[r]=Tiles.tiles[x];
				Tiles.tiles[x]=temp;
			}
		}
		
		for (int riga=3; riga<8; riga++) { //aggiungo le tiles alla board
			for(int colonna=3; colonna<8; colonna++) {
				if(!((colonna==3 || colonna==7) && (riga==3 || riga==7))){
				
					board[riga][colonna]=Tiles.tiles[counter];
					counter++;
				}	
			}
		}
		board[2][4]=Tiles.tiles[counter];
		board[2][5]=Tiles.tiles[++counter];
		board[4][8]=Tiles.tiles[++counter];
		board[5][2]=Tiles.tiles[++counter];
		board[5][8]=Tiles.tiles[++counter];
		board[6][2]=Tiles.tiles[++counter];
		board[8][5]=Tiles.tiles[++counter];
		board[8][6]=Tiles.tiles[++counter];
		
		if(ngiocatori>2) {
			board[1][4]=Tiles.tiles[++counter];
			board[3][3]=Tiles.tiles[++counter];
			board[3][7]=Tiles.tiles[++counter];
			board[4][9]=Tiles.tiles[++counter];
			board[6][1]=Tiles.tiles[++counter];
			board[7][3]=Tiles.tiles[++counter];
			board[7][7]=Tiles.tiles[++counter];
			board[9][6]=Tiles.tiles[++counter];
			if(ngiocatori>3) {
				board[1][5]=Tiles.tiles[++counter];
				board[2][6]=Tiles.tiles[++counter];
				board[4][2]=Tiles.tiles[++counter];
				board[5][1]=Tiles.tiles[++counter];
				board[5][9]=Tiles.tiles[++counter];
				board[6][8]=Tiles.tiles[++counter];
				board[8][4]=Tiles.tiles[++counter];
				board[9][5]=Tiles.tiles[++counter];
			}
		}
	}
	
	public static void TesserePrendibili() {
		
		for(int riga=0; riga<prendibili.length; riga++) {
			for(int colonna=0; colonna<prendibili[0].length; colonna++) {
				prendibili[riga][colonna]=false;
			}
		}
		for(int x=0; x<11;x++) {
			for(int y=0; y<11; y++) {
				if(board[x][y]!=null) {
					if(board[x+1][y]==null || board[x-1][y]==null || board[x][y+1]==null || board[x][y-1]==null) {
						prendibili[x][y]=true; //tavola di verità con le cordinate delle tessere prendibili
						System.out.println("la tessera alla riga"+x+" e colonna "+y+" e' prendibile");
					}
				}
			}
		}
	}
	
	public static Tile[] SceltaUtente() {
		
		Tile[] scelte=new Tile[3];
		String risposta=new String();
		boolean esiste=false;
		int x,y;
		int i=0;
		
		do {
			do {
				do {
				System.out.println("Scegli le cordinate delle tessere che vuoi prendere"); 
				x=input.nextInt();
				y=input.nextInt();
				}while((x<0 || x>10) || (y<0 || y>10)); //da aggiungere metodo per verificare che siano adiacenti
				if(prendibili[x][y]==true) {
					Tile temp=board[x][y]; 
					scelte[i]=temp;					//riceve tessera dalla board
					board[x][y]=null;	//non c'è più la tessera sulla board
					esiste=false; //condizione per passsare al prossimo step
				}else {
					esiste=true;
					System.out.println("Tessera non prendibile");
				}
			}while(esiste);
			i++;
			esiste=false;
			System.out.println("vuoi selezionare un altra tessera? si/no");
			risposta=input.next();
			if(risposta.equals("no")) {
				i=4;
			}
		}while(i<3);
		StampaBoard();
		return scelte;
	}
	
	
	public static void StampaBoard() {
		
		for(int riga=0; riga<righe; riga++) {
			System.out.println(" ");
			for(int colonna=0; colonna<colonne; colonna++) {
				if(board[riga][colonna]==null) {
					System.out.print("     ");
				}
				else {
				System.out.print(board[riga][colonna].getColor()+" ");
				}
			}
		}
		System.out.println(" ");
	}
}

