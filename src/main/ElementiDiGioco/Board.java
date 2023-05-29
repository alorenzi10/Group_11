package main.ElementiDiGioco;

import java.util.Random;
import java.util.Scanner;

import main.Tessere.Tile;
import main.Tessere.Tiles;

public class Board{

	private static int counter=0;
	private static int righe=11;
	private static int colonne=11;
	static Scanner input=new Scanner(System.in);
	public static Tile[][] board=new Tile[righe][colonne]; //tavola da gioco
	public static boolean [][] prendibili= new boolean [righe][colonne]; //tavola da gioco booleana per capire quali tessere sono prendibili

	public static void Board(int ngiocatori){
		Tiles.Inizializza(); //if primo giro
		Tiles.Mischia();
		Board.BoardSetUp(ngiocatori);
	} 


	public static void BoardSetUp(int ngiocatori) {

		Tile[] avanzate=new Tile[10]; // 10 perché non dovrebbero avanzarne di più
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

		if(ngiocatori>2) { //tessere non sempre presenti a seconda dei giocatori
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

	public static boolean TesserePrendibili() throws InterruptedException {

		for(int riga=0; riga<prendibili.length; riga++) { //resetta board booleana che controlla le tessere prendibili
			for(int colonna=0; colonna<prendibili[0].length; colonna++) {
				prendibili[riga][colonna]=false;
			}
		}
		for(int x=0; x<11;x++) {
			for(int y=0; y<11; y++) {
				if(board[x][y]!=null) {
					if(board[x+1][y]==null || board[x-1][y]==null || board[x][y+1]==null || board[x][y-1]==null) {
						prendibili[x][y]=true; //tavola di verità con le cordinate delle tessere prendibili
						//System.out.println("la tessera alla riga"+x+" e colonna "+y+" e' prendibile");
					}
				}
			}
		}
		int counter=0;
		for(int x=0; x<11;x++) {
			for(int y=0; y<11; y++) {
				if(prendibili[x][y]==true) {
					if(prendibili[x+1][y]==true || prendibili[x-1][y]==true || prendibili [x][y+1]==true || prendibili[x][y-1]==true) {
						counter++; //verifica che ci sia almeno una coppia di tessere prendibili
					}
				}
			}
		}
		if(counter<1) {
			System.out.println("Le tessere prendibili sono tutte singole bisogna sistemare la board");
			Thread.sleep(1000);
			return true;
		}
		return false;
	}
	
	public static boolean ControlloScelta(int [][] selezionate, int counter) throws InterruptedException {

		boolean invalido=true;
		if(counter==1) { //sempre giusta se prendibile
			invalido=true;
		}
		else if(counter==2) {
			if((selezionate[0][1]==selezionate[1][1] && (selezionate[0][0]==(selezionate[1][0]+1) || selezionate[0][0]==(selezionate[1][0]-1)))
					||(selezionate[0][0]==selezionate[1][0] && (selezionate[0][1]==(selezionate[1][1]+1) || selezionate[0][1]==(selezionate[1][1]-1)))){
				invalido=true;
			}
			else {
				System.out.println("Le due tessere non sono allineate");
				Thread.sleep(1000);
				invalido=false;
			}
		}
		else if(counter==3){
			if(selezionate[0][0]==selezionate[1][0] && selezionate[1][0]==selezionate[2][0]){ //riga uguale
				int [] uno=new int [3];
				for(int i=0; i<3; i++) {
					uno[i]=selezionate[i][1];
				}
				for(int i=0; i < 3; i++){  
					for(int j=1; j < (3-i); j++){  
						if(uno[j-1] > uno[j]){  
							
							int temp=uno[j-1];
							uno[j-1]=uno[j];
							uno[j]=temp;

						}
					}
				}
				if(uno[0]==(uno[1]-1) && uno[0]==(uno[2]-2)) {
					invalido=true;
				}
				else {
					System.out.println("Le tre tessere non sono allineate");
					Thread.sleep(1000);
					invalido=false;
				}
			}
			else if(selezionate[0][1]==selezionate[1][1] && selezionate[1][1]==selezionate[2][1]) { //colonna uguale
				int [] uno=new int [3];
				for(int i=0; i<3; i++) {
					uno[i]=selezionate[i][0];
				}
				for(int i=0; i < 3; i++){  
					for(int j=1; j < (3-i); j++){  
						if(uno[j-1] > uno[j]){  
							
							int temp=uno[j-1];
							uno[j-1]=uno[j];
							uno[j]=temp;

						}
					}
				}
				if(uno[0]==(uno[1]-1) && uno[0]==(uno[2]-2)) {
					invalido=true;
				}
				else {
					System.out.println("Le tre tessere non sono allineate");
					Thread.sleep(1000);
					invalido=false;
				}
			}
			else {
				System.out.println("Le tre tessere non sono allineate");
				Thread.sleep(1000);
				invalido=false;
			}
		}
		return invalido;
	}

	public static Tile[] SceltaUtente(int giocatore) throws InterruptedException {

		boolean boardfinita=false;
		boolean invalido=false;
		int[][] cordinatescelte= new int[3][2];

		do {//rinizio da capo se invalide tessere

			Board.StampaBoard();
			boardfinita=Board.TesserePrendibili();
			if(boardfinita) {
				Board.BoardSetUp(giocatore);
				Board.StampaBoard();
				Thread.sleep(1000);
				boardfinita=Board.TesserePrendibili();
			}
			int spaziliberi=Libreria.calcolaSpazi(giocatore);
			int spazimax=Libreria.calcolaSpaziColonnaMax(giocatore); //spazio massimo delle colonne
			System.out.println("Spazi liberi della tua libreria = "+spaziliberi);
			System.out.println("Spazi liberi max della tua libreria = "+spazimax);
			Tile[] scelte=new Tile[3];
			for(int i=0; i<3; i++) {
				for(int z=0; z<2; z++) { //resetta vecchie scelte
					cordinatescelte[i][z]=0;
				}
			}
			String risposta=new String();
			boolean esiste=false;
			int x=0,y=0; //coordinate
			int counter=0;
			boolean giascelta=false;
			boolean fine=true;
			do {
				do {
					do {
						giascelta=false;
						boolean userInt=true;
						System.out.println("Scegli le cordinate delle tessere che vuoi prendere");
						do { 
							System.out.print("Scegli il numero della riga e premi invio");  
							if(counter>0) {
								System.out.println(", per resettare le scelte selezionate una tessera non allineata");
							}else {
								System.out.println("");
							}
							try {

								x=Integer.parseInt(input.nextLine());
								userInt=true;
							}
							catch(NumberFormatException e) {
								System.out.println("L'input non e' valido, riprova");
								userInt=false;
							}
						}while(!userInt);
						do {

							try {
								System.out.println("Scegli il numero della colonna e premi invio");  
								y=Integer.parseInt(input.nextLine());
								userInt=true;
							}
							catch(NumberFormatException e) {
								System.out.println("L'input non e' valido, riprova");
								userInt=false;
							}
						}while(!userInt);

						if(counter>0) {
							if(cordinatescelte[0][0]==x && cordinatescelte[0][1]==y) { //per verificare non prenda stesse tessre
								giascelta=true;
							}
							if(counter>1) {
								if(cordinatescelte[1][0]==x && cordinatescelte[1][1]==y) {
									giascelta=true;
								}
							}
						}
						if(giascelta) {
							System.out.println("Non puoi prendere la tessera gia scelta");  
						}else {
							cordinatescelte[counter][0]=x; //riempe matrice con le 2 coordinate
							cordinatescelte[counter][1]=y;
						}
					}while((x<0 || x>10) || (y<0 || y>10) || giascelta); //loop per scegliere cordinate esistenti e non uguali
					////////////////

					if(prendibili[x][y]==true) {
						Tile temp=board[x][y];
						scelte[counter]=temp;	
						esiste=false; 
					}else {
						esiste=true;
						System.out.println("Tessera non prendibile");
					}
				}while(esiste); //loop per scegliere tessere prendibili

				counter++;

				if(counter==spazimax) {
					System.out.println("Stai per riempire la libreria non puoi piu prendere altre tessere");
					fine=false;
					//counter invece di i
				}
				else if(counter<3){
					System.out.print("vuoi selezionare un altra tessera? 'no' per uscire, premere invio per continuare: " ); 
					risposta=input.nextLine();
					if(risposta.equals("no")) {
						fine=false; 
					}

				}
				else if(counter>2){
					System.out.println("Non puoi prendere altre tessere");
					fine=false;
				}
			}while(fine); //loopa 3 volte o max prendibili)

			invalido=ControlloScelta(cordinatescelte, counter);
			if(!invalido) {
				System.out.println("Scegliere solo le tessere prendibili e adiacenti");
				System.out.println("Effettua nuovamente la scelta");
				Thread.sleep(1000);
			}else {
				for(int i=0; i<3; i++) {
					int a=cordinatescelte[i][0];
					int b=cordinatescelte[i][1];
					board[a][b]=null;
				}
				StampaBoard();
				return scelte;
			}
		}while(!invalido); //fa riniziare da capo
		return null;
	}

	
	public static void StampaBoard() { 
		String cornice = "__________"+"__________"+"__________"+
				"__________"+"__________"+"__________"+"__________"+
				"__________"+"__________"+"__________";

		System.out.println(cornice);
		for(int riga=0; riga<righe-1; riga++) {
			for(int colonna=0; colonna<colonne-1; colonna++) {
				if(board[riga][colonna]==null) {

					if(colonna==0) {
						System.out.print("   "+riga+"     |");
					}
					else if(riga==0) {
						System.out.print("   "+colonna+"     |");
					}
					else {
						System.out.print("         |");
					}
				}
				else {
					int lunghezza=board[riga][colonna].getColor().toString().length();

					if(lunghezza==5) {
						System.out.print(board[riga][colonna].getColor()+"    "+"|");
					}
					if(lunghezza==4) {
						System.out.print(board[riga][colonna].getColor()+"     "+"|");
					}
					if(lunghezza==9) {
						System.out.print(board[riga][colonna].getColor()+"|");
					}
					if(lunghezza==6) {
						System.out.print(board[riga][colonna].getColor()+"   "+"|");
					} 
				}
			}

			System.out.println(" ");

		}
		System.out.println(cornice);
	}
}

