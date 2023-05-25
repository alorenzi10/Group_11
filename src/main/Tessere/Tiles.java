package main.Tessere;

import java.util.Random;

public class Tiles {
	
	public static Tile[] tiles;
	
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
		/*for(int i=0; i<132; i++) {
			System.out.println(tiles[i]+" "+i);
		}*/
	}
	
	public static void Mischia(){ //unire con inizializza probabilmente convine (?)
		
		Random rand=new Random();
		
		for(int i=0; i<132;i++) {
			int r=i+rand.nextInt(132-i);
			Tile temp=tiles[r];
			tiles[r]=tiles[i];
			tiles[i]=temp;
		}
		/*for(int i=0; i<132; i++) {
			System.out.println(tiles[i]+" "+i);
		}*/
	}
	
}
