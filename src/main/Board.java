package main;

public class Board{
	
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
	
	public static void Board(int giocatori){
		counter=0;
		Tiles.Inizializza();
		Tiles.Mischia();
		
		for(int i=0; i<riga1.length; i++) {
			riga1[i]=Tiles.tiles[counter];
			counter++;
		}
		for(int i=0; i<riga2.length; i++) {
			riga2[i]=Tiles.tiles[counter];
			counter++;
		}
		for(int i=0; i<riga3.length; i++) {
			riga3[i]=Tiles.tiles[counter];
			counter++;
		}
		for(int i=0; i<riga4.length; i++) {
			riga4[i]=Tiles.tiles[counter];
			counter++;
		}
		for(int i=0; i<riga5.length; i++) {
			riga5[i]=Tiles.tiles[counter];
			counter++;
		}
		for(int i=0; i<riga6.length; i++) {
			riga6[i]=Tiles.tiles[counter];
			counter++;
		}
		for(int i=0; i<riga7.length; i++) {
			riga7[i]=Tiles.tiles[counter];
			counter++;
		}
		for(int i=0; i<riga8.length; i++) {
			riga8[i]=Tiles.tiles[counter];
			counter++;
		}
		for(int i=0; i<riga9.length; i++) {
			riga9[i]=Tiles.tiles[counter];
			counter++;
		}
	}
	
	public static void StampaBoard() {
		
		for(int i=0; i<riga1.length; i++) {
			System.out.print(riga1[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<riga2.length; i++) {
			System.out.print(riga2[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<riga3.length; i++) {
			System.out.print(riga3[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<riga4.length; i++) {
			System.out.print(riga4[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<riga5.length; i++) {
			System.out.print(riga5[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<riga6.length; i++) {
			System.out.print(riga6[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<riga7.length; i++) {
			System.out.print(riga7[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<riga8.length; i++) {
			System.out.print(riga8[i]+" ");
			
		}
		System.out.println(" ");
		for(int i=0; i<riga9.length; i++) {
			System.out.print(riga9[i]+" ");
			
		}
		System.out.print(counter);
	}
	
	public static void Prendibili() {
		
	}
	
}

