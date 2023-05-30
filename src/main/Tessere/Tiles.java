package main.Tessere;

import java.util.Random;

public class Tiles {
	
	public static Tile[] tiles;
	
	public static void Inizializza() {				//per creare le 132 tessere
		tiles=new Tile[132];						
		Tile.Color[] colors=Tile.Color.values();
		int bagtiles=0;
		for(int i=0; i<colors.length;i++) { //per ogni tipo(6) 22 carte
			Tile.Color color=colors[i];
			for(int n=0; n<22; n++) {
				tiles[bagtiles]=new Tile (color);
				bagtiles++;
			}
		}
	}
	
	public static void Mischia(){ //mischia le tessere create
		
		Random rand=new Random();
		
		for(int i=0; i<132;i++) {
			int r=i+rand.nextInt(132-i);
			Tile temp=tiles[r];
			tiles[r]=tiles[i];
			tiles[i]=temp;
		}
	}
	
}
