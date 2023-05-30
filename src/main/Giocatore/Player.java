package main.Giocatore;

public class Player {
	
	public String nome;
	public int numeroobb; //numero della carta obbiettivo personale
	public int punti;
	public boolean fine; //token di chi riempie per primo la libreria
	public int cartacomune1; //per segnare in che posizione il giocatore ha realizzato l'obbiettivo
	public int cartacomune2;
		
	public Player(String nome, int carta){
		
			this.nome=nome;
			this.numeroobb=carta;
			punti=0;
			cartacomune1=0;
			cartacomune2=0;
			fine=false;
	}
		
}
