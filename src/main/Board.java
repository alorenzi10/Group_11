package main;

import java.util.Random;

public class Board{
	
	private static int counter=0;
	private static int righe=11;
	private static int colonne=11;
	
	public static Tile[][] board=new Tile[righe][colonne];
	public static Tile[] avanzate=new Tile[10]; //preso 10 perché non penso ne avanzino di più
	
	public static void Board(int ngiocatori){
		
		Tiles.Inizializza(); //if primo giro
		Tiles.Mischia();
		Board.BoardSetUp(ngiocatori);
		Board.StampaBoard();
		Board.TesserePrendibili();
	} 
	 
	
	public static void BoardSetUp(int ngiocatori) {
		
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
			board[1][4]=Tiles.tiles[counter];
			board[3][3]=Tiles.tiles[++counter];
			board[3][7]=Tiles.tiles[++counter];
			board[4][9]=Tiles.tiles[counter];
			board[6][1]=Tiles.tiles[counter];
			board[7][3]=Tiles.tiles[++counter];
			board[7][7]=Tiles.tiles[++counter];
			board[9][6]=Tiles.tiles[++counter];
			if(ngiocatori>3) {
				board[1][5]=Tiles.tiles[counter];
				board[2][6]=Tiles.tiles[counter];
				board[4][2]=Tiles.tiles[counter];
				board[5][1]=Tiles.tiles[counter];
				board[5][9]=Tiles.tiles[counter];
				board[6][8]=Tiles.tiles[counter];
				board[8][4]=Tiles.tiles[counter];
				board[9][5]=Tiles.tiles[counter];
			}
		}
	}
	
	public static void TesserePrendibili() {
		//9x9 all'interno di 11x11
		for(int x=0; x<11;x++) {
			for(int y=0; y<11; y++) {
				if(board[x][y]!=null) {
					if(board[x+1][y]==null || board[x-1][y]==null || board[x][y+1]==null || board[x][y-1]==null) {
						System.out.println("la tessera "+x+" "+y+" e' prendibile");
					}
				}
			}
		}
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
	}
}

