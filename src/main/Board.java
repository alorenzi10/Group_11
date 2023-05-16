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
				int r=x+rand.nextInt(132-x);
				Tile temp=Tiles.tiles[r]; //errore su r al secondo giro why?
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
	
	public static boolean TesserePrendibili() {
		
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
		int counter=0;
		for(int x=0; x<11;x++) {
			for(int y=0; y<11; y++) {
				if(prendibili[x][y]==true) {
						if(prendibili[x+1][y]==true || prendibili[x-1][y]==true || prendibili [x][y+1]==true || prendibili[x][y-1]==true) {
							counter++;
						}
					}
				}
			}
		if(counter<1) {
			System.out.println("Le tessere prendibili sono tutte singole bisogna sistemare la board");
			return true;
			}
		return false;
		}
	
	public static boolean ControlloScelta(int [][] selezionate) {
		
		boolean invalido=false;
		if(selezionate[1][0]!=0) {
			if(!(((((selezionate[0][0]+1)==selezionate[1][0]||(selezionate[0][0]-1)==selezionate[1][0])&&selezionate[0][1]==selezionate[1][1])
						||(((selezionate[0][1]+1)==selezionate[1][1]||(selezionate[0][1]-1)==selezionate[1][1])&&selezionate[0][0]==selezionate[1][0])))){
				System.out.println("la seconda tessera non è allineata con la prima");
				invalido=true;
			}
		}
		if(selezionate[2][0]!=0) {
			if(!((((selezionate[0][0]==(selezionate[2][0]+2)||selezionate[0][0]==(selezionate[2][0]-2))&&selezionate[0][1]==selezionate[2][1])
						||((selezionate[0][1]==(selezionate[2][1]+2)||selezionate[0][1]==(selezionate[2][1]-2))&&selezionate[0][0]==selezionate[2][0])))){
				System.out.println("la terza tessera non è allineata con la prima");
				invalido=true;
			}
		}
		return invalido;
	}
	
	public static Tile[] SceltaUtente(int giocatore) {
		
		boolean boardfinita=false;
		boolean invalido=false;
		int[][] cordinatescelte= new int[3][2];
		
		do {//rinizio da capo se invalide tessere
			
			Board.StampaBoard();
			boardfinita=Board.TesserePrendibili();
			if(boardfinita) {
				Board.BoardSetUp(giocatore);
				Board.StampaBoard();
			}
			int spaziliberi=Libreria.calcolaSpazi(giocatore);
			System.out.println("Spazi liberi = "+spaziliberi);
		
			Tile[] scelte=new Tile[3];
			for(int i=0; i<3; i++) {
				for(int z=0; z<2; z++) {
					cordinatescelte[i][z]=0;
				}
			}
			String risposta=new String();
			boolean esiste=false;
			int x,y; //coordinate
			int counter=0;
			boolean giascelta=false;
			
		do {
			 //per sapere a quante tessere siamo
			
			/////////
			do {
				/////////////
				do {
					giascelta=false;
					System.out.println("Scegli le cordinate delle tessere che vuoi prendere");  
					x=input.nextInt();
					y=input.nextInt();
					if(counter>1) {
						if(cordinatescelte[0][0]==x && cordinatescelte[0][1]==y) { //per verificare non prenda stesse tessre
							giascelta=true;
						}else if(counter>2) {
							if(cordinatescelte[1][0]==x && cordinatescelte[1][1]==y) {
								giascelta=true;
							}
						}
					}
					if(giascelta) {
						System.out.println("Non puoi prendere la tessera gia scelta");  
					}
					cordinatescelte[counter][0]=x; //riempe matrice con le "3" coordinate
					cordinatescelte[counter][1]=y;
					
					}while((x<0 || x>10) || (y<0 || y>10) || giascelta); //loop per scegliere cordinate esistenti e non uguali
				////////////////
				
				if(prendibili[x][y]==true) {
					Tile temp=board[x][y];
					scelte[counter]=temp;	
					//board[x][y]=null;	posso fare dopo grazie a matrice coordinate
					esiste=false; 
				}else {
					esiste=true;
					System.out.println("Tessera non prendibile");
					}
				}while(esiste); //loop per scegliere tessere prendibili
			//////
			//da rimuovere coordinate prese da board
			//i++; rimuovibile
			counter++;
			//esiste=false;
			if(counter<3){
			System.out.println("vuoi selezionare un altra tessera? 'no' per uscire"); 
			risposta=input.next();
			if(risposta.equals("no")) {
				counter=4; //counter invece di i
			}
			else {
				if(counter==spaziliberi) {
					System.out.println("Stai per riempire la libreria non puoi più prendere altre tessere");
					counter=4; //counter invece di i
					}
				}
			}
			}while(counter<3); //loopa 3 volte (max prendibili)
		
		invalido=ControlloScelta(cordinatescelte);
		if(invalido) {
			System.out.println("Scegliere le tessere secondo le regole");
		}else {
			for(int i=0; i<3; i++) {
				int a=cordinatescelte[i][0];
				int b=cordinatescelte[i][1];
				board[a][b]=null;
			}
			StampaBoard();
			return scelte;
		}
		}while(invalido); //fa riniziare da capo
		return null;
	}
	
	
	public static void StampaBoard() { //devo rendere più simmetrica
		
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

