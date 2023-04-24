package main;

public class Libreria { //da legare con giocatore
	
	private static int colonne=5;
	private static int righe=6;
	
	Tile[][] libreria=new Tile[righe][colonne]; //dubbio che non sia la soluzione migliore
	
	public Libreria() { //setta/resetta a null
		
		for(int riga=0; riga<righe; riga++) {
			for(int colonna=0; colonna<colonne; colonna++) {
				libreria[riga][colonna]=null;
			}
		}
	}
	
	public void stampaLibreria() { //da inquadrare meglio output per farlo simmetrico
		
		for(int riga=righe-1; riga>=0; riga--) { //per stampare da alto verso il basso
			System.out.println("");
			for(int colonna=colonne-1; colonna>=0; colonna--) {
				if(libreria[riga][colonna]==null) {
					System.out.print("        ");
				}else {
				System.out.println(libreria[riga][colonna].getColor());
				}
			}
		}
	}
	
	
	
	
	
}
