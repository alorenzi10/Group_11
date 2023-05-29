package main.Giocatore;

public class Player {
	
	public String nome;
	public int numeroobb;
	public int punti;
	public boolean fine;
	public int cartacomune1;
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
