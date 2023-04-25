package main;

import java.util.Random;

public class Board{
	
	private static Tile[] tiles;
	private static int counter=0;
	private static Tile[] riga1= new Tile[2];
	private static Tile[] riga2= new Tile[3];
	private static Tile[] riga3= new Tile[5];
	private static Tile[] riga4= new Tile[8];
	private static Tile[] riga5= new Tile[9];
	private static Tile[] riga6= new Tile[8];
	private static Tile[] riga7= new Tile[5];
	private static Tile[] riga8= new Tile[3];
	private static Tile[] riga9= new Tile[2];
	
	public static void Board(){
		counter=0;
		Board.Inizializza();
		Board.Mischia();
		for(int i=0; i<2; i++) {
			riga1[i]=tiles[counter];
			counter++;
		}
		for(int i=0; i<3; i++) {
			riga2[i]=tiles[counter];
			counter++;
		}
		for(int i=0; i<5; i++) {
			riga3[i]=tiles[counter];
			counter++;
		}
		for(int i=0; i<8; i++) {
			riga4[i]=tiles[counter];
			counter++;
		}
		for(int i=0; i<9; i++) {
			riga5[i]=tiles[counter];
			counter++;
		}
		for(int i=0; i<8; i++) {
			riga6[i]=tiles[counter];
			counter++;
		}
		for(int i=0; i<5; i++) {
			riga7[i]=tiles[counter];
			counter++;
		}
		for(int i=0; i<3; i++) {
			riga8[i]=tiles[counter];
			counter++;
		}
		for(int i=0; i<2; i++) {
			riga9[i]=tiles[counter];
			counter++;
		}
	}
	
	public static void StampaBoard() {
		
		for(int i=0; i<2; i++) {
			System.out.print(riga1[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<3; i++) {
			System.out.print(riga2[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<5; i++) {
			System.out.print(riga3[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<8; i++) {
			System.out.print(riga4[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<9; i++) {
			System.out.print(riga5[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<8; i++) {
			System.out.print(riga6[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<5; i++) {
			System.out.print(riga7[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<3; i++) {
			System.out.print(riga8[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<2; i++) {
			System.out.print(riga9[i]+" ");
			
		}
		System.out.print(counter);
	}
	
	public static void Inizializza() {				//per creare le tiles da riordinare
		tiles=new Tile[132];						
		Tile.Color[] colors=Tile.Color.values();
		int bagtiles=0;
		for(int i=0; i<colors.length;i++) {
			Tile.Color color=colors[i];
			for(int n=0; n<22; n++) {
				tiles[bagtiles]=new Tile (color);
				bagtiles++;
			}
		}
		for(int i=0; i<132; i++) {
			System.out.println(tiles[i]+" "+i);
		}
	}
	
	public static void Mischia(){ //unire con inizializza probabilmente convine (?)
		
		Random rand=new Random();
		
		for(int i=0; i<132;i++) {
			int r=i+rand.nextInt(132-i);
			Tile temp=tiles[r];
			tiles[r]=tiles[i];
			tiles[i]=temp;
		}
		for(int i=0; i<132; i++) {
			System.out.println(tiles[i]+" "+i);
		}
	}
	
}

