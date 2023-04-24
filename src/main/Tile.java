package main;

public class Tile {
	
	private final Color color;
	
	public Tile(final Color color) { //costruttore
		this.color=color; 
	}
	
	enum Color{
		
		Green, White, Yellow, Blue, Pink, LightBlue; //tipologia di tessere
		private static final Color[] colors=Color.values();
		public static Color getColor(int i) { //restituisce il colore da valore int
			return Color.colors[i];
		}
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public String toString() {
		return color+"";
	}
	
	
}
