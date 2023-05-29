package main.Carte;

import java.util.Random;

import main.Tessere.Tile;

public class ObbComuni {
	
	static int [] estratto= new int[2]; //per contenere carte estratte
	static int i=0; //per contare a che carta estratta siamo
	static boolean [][] realizzati=new boolean[4][2]; //per tenere controllati gli obbiettivi realizzati dai giocatori
	
	public static int AssegnaCarta() {

		int valore;
		boolean esiste=false;
		Random random= new Random();
		do {

			esiste=false;
			valore=random.nextInt(12)+1;
			for(int b=0; b<estratto.length; b++) { //per estrarre carte.comuni diverse
				if(valore==estratto[b]) {
					esiste=true;
				}
			}
		}while(esiste);
		Obbiettivo(valore); //per stampare la descrizione della carta
		estratto[i]=valore;
		i++;
		return valore;
	}
	
	public static boolean PuntiPersonali(Tile libreria[][], int ncarta, int giocatore){ 
		
		boolean realizzato=false; 
		boolean primacarta=true; //controlla se sia la seconda o la prima carta
		int conta=0;
		if(realizzati[giocatore][0]==true && realizzati[giocatore][1]==true ) { //per evitare cicli inutili se ha già realizzato tutti e due
			return realizzato;
		}
		if(estratto[0]!=ncarta) {
			primacarta=false;
		}
		if(primacarta==false && realizzati[giocatore][1]==true) { //per evitare cicli inutili se ha già realizzato il secondo obb
			return realizzato;
		}
		if(primacarta==true && realizzati[giocatore][0]==true) { //per evitare cicli inutili se ha già realizzato il primo obb
			return realizzato;
		}
		
		switch (ncarta) { 
		case 1:  ///funziona
			if(libreria!=null) {
				for(int riga=0; riga<6; riga++) { //controllo su riga per coppie di tessere
					for(int colonna=1; colonna<5; colonna++) {
						if(libreria[riga][colonna]!=null && libreria[riga][colonna-1]!=null) {
							if((libreria[riga][colonna].getColor()).equals(libreria[riga][colonna-1].getColor())){ 
								conta++;
							}
						}
					}
				}
				for(int colonna=0; colonna<5; colonna++) { //controllo su colonna per coppie di tessere
					for(int riga=1; riga<6; riga++){
						if(libreria[riga][colonna]!=null && libreria[riga-1][colonna]!=null) {
							if((libreria[riga][colonna].getColor()).equals(libreria[riga-1][colonna].getColor())){
								conta++;
							}
						}
					}
				}
			}
			if(conta>5) { //almeno 6 coppie
				realizzato=true;
			}

			break;

		case 2: ///funziona
			if(libreria!=null) {
				conta=0;
				for(int riga=0; riga<6; riga++) { //controllo su riga per 4 tessere uguali
					for(int colonna=2; colonna<4; colonna++) {
						if(libreria[riga][colonna]!=null && libreria[riga][colonna+1]!=null && libreria[riga][colonna-2]!=null
								&& libreria[riga][colonna-1]!=null) {
							if((libreria[riga][colonna].getColor().equals(libreria[riga][colonna+1].getColor())) 
									&&(libreria[riga][colonna-2].getColor().equals(libreria[riga][colonna-1].getColor()))){
								if(libreria[riga][colonna].getColor().equals(libreria[riga][colonna-2].getColor())) {
									conta++;
								}
							}
						}
					}
				}
				for(int colonna=0; colonna<5; colonna++) { //controllo su colonna per 4 tessere uguali
					for(int riga=2; riga<5; riga++){
						if(libreria[riga][colonna]!=null && libreria[riga+1][colonna]!=null && libreria[riga-2][colonna]!=null
								&& libreria[riga-1][colonna]!=null) {
							if((libreria[riga][colonna].getColor().equals(libreria[riga+1][colonna].getColor()))
									&&(libreria[riga-2][colonna].getColor().equals(libreria[riga-1][colonna].getColor()))){
								if(libreria[riga][colonna].getColor().equals(libreria[riga-2][colonna].getColor())) {
									conta++;
								}
							}
						}
					}
				}
				if(conta>3) {  //almeno 4 
					realizzato=true;
				}
			}
			break;

		case 3:///funziona
			if(libreria!=null) { //controla che i 4 angoli siano uguali
				if(libreria[0][0]!=null && libreria[5][0]!=null && libreria[0][4]!=null && libreria[5][4]!=null) {
					if((libreria[0][0].getColor()==libreria[5][0].getColor())&&(libreria[0][4].getColor()==libreria[5][4].getColor())) {
						realizzato=true;
					}
				}
			}
			break;

		case 4:///funziona 
			if(libreria!=null) { //null safety
				conta=0;
				for(int riga=0; riga<5; riga++) {
					for(int colonna=0; colonna<4; colonna++) {
						if(libreria[riga][colonna]!=null && libreria[riga+1][colonna+1]!=null &&
								libreria[riga][colonna+1]!=null && libreria[riga+1][colonna]!=null) {
							if(libreria[riga][colonna].getColor().equals(libreria[riga+1][colonna+1].getColor())
									&& libreria[riga][colonna+1].getColor().equals(libreria[riga+1][colonna].getColor())
									&& libreria[riga][colonna].getColor().equals(libreria[riga][colonna+1].getColor()) ) {
								conta++;
							}
						}
					}
				}

				if(conta>1) { //almeno 2 quadrati
					realizzato = true;
				}
			}
			break;

		case 5: //funziona
			int z=0;
			if(libreria!=null) {
				conta=0;
				int [] piene= new int [5]; //5 perché numero max di colonne
				Tile [] tipologia =new Tile [3]; //conterra fino a 3 tipi di tessere

				for(int colonna=0; colonna<5; colonna++) { //controlla quante colonne sono piene
					if(libreria[0][colonna]!=null && libreria[1][colonna]!=null && libreria[2][colonna]!=null && libreria[3][colonna]!=null &&
							libreria[4][colonna]!=null && libreria[5][colonna]!=null) {
						piene[conta]=colonna;
						conta+=1;
					}
				}
				if(conta>2) { //controlla che ci siano almeno 3 colonne piene
					for(int y=0; y<5; y++) { //scorre solo le colonne piene
						for(int riga=0; riga<6; riga++) {
							if(tipologia[0]==null ) { //se array tipologie vuote viene riempito con la prima tipologia della tessera
								tipologia[0]=libreria[riga][piene[y]];
								z++;
							}
							else {
								if(tipologia[1]==null && libreria[riga][piene[y]]!=null) {
									if(tipologia[0].getColor()!=libreria[riga][piene[y]].getColor()) { //controlla che le tessere non siano dello stesso tipo della prima tipologia incontrata
										tipologia[1]=libreria[riga][piene[y]];
										z++;
									}
								}else { 
									if(tipologia[2]==null && libreria[riga][piene[y]]!=null) { //controlla che le tessere non siano dello stesso tipo della prima e della seconda tipologia incontrata
										if(tipologia[0].getColor()!=libreria[riga][piene[y]].getColor() && tipologia[1].getColor()!=libreria[riga][piene[y]].getColor() ) { //nel caso riempie con la terza tiplogia
											tipologia[2]=libreria[riga][piene[y]];
											z++;
										}
									}
									else {
										if(libreria[riga][piene[y]]!=null) {  //controlla che le tessere non siano dello stesso tipo della prima,della seconda e della terza tipologia incontrata
											if(tipologia[0].getColor()!=libreria[riga][piene[y]].getColor() && tipologia[1].getColor()!=libreria[riga][piene[y]].getColor()
													&& tipologia[2].getColor()!=libreria[riga][piene[y]].getColor()) {
												z++; //conta quante tiplogie sono state incontrate
											}
										}
									}
								}
							}
						}
					}
				}
				else {
					z=4; //se non ci sono almeno 3 colonne piene, non è realizzabile
				}
				if(z<4) {
					realizzato=true;
				}
				else {
					realizzato=false;
				}
			}
			break;

		case 6: //funziona
			if(libreria!=null) {
				int  pink = 0, blu= 0, lightblue= 0, yellow= 0, green= 0, white= 0; //contatori di tipologie
				for(int riga=0; riga<6;riga++) {
					for(int colonna=0; colonna<5; colonna++){
						if(libreria[riga][colonna]!= null) {
							if(libreria[riga][colonna].toString().equals("Pink")) {
								pink+=1;
							}
							if(libreria[riga][colonna].toString().equals("Blu")) {
								blu+=1;
							}
							if(libreria[riga][colonna].toString().equals("Lightblue")) {
								lightblue+=1;
							}
							if(libreria[riga][colonna].toString().equals("Yellow")) {
								yellow+=1;
							}
							if(libreria[riga][colonna].toString().equals("Green")) {
								green+=1;
							}
							if(libreria[riga][colonna].toString().equals("White")) {
								white+=1;
							}
						}
					}

				}
				if (blu>7 || pink>7 || lightblue>7 || yellow>7 || green>7 || white>7 ) {
					realizzato = true;
				}
			}
			break;

		case 7: //funziona
			if(libreria!=null) { //confronti con le 4 tipologie possibili di diagonali
				if(libreria[0][0]!=null && libreria[1][1]!=null && libreria[2][2]!=null && libreria[3][3]!=null && libreria[4][4]!=null ) {
					if(libreria[0][0].getColor().equals(libreria[1][1].getColor()) && libreria[0][0].getColor().equals(libreria[2][2].getColor())
							&& libreria[0][0].getColor().equals(libreria[3][3].getColor()) && libreria[0][0].getColor().equals(libreria[4][4].getColor())) {
						realizzato=true;
					}
				}
				if(libreria[1][0]!=null && libreria[2][1]!=null && libreria[3][2]!=null && libreria[4][3]!=null && libreria[5][4]!=null ) {
					if(libreria[0][1].getColor().equals(libreria[1][2].getColor()) && libreria[0][1].getColor().equals(libreria[2][3].getColor())
							&& libreria[0][1].getColor().equals(libreria[3][4].getColor()) && libreria[0][1].getColor().equals(libreria[4][5].getColor())){
						realizzato=true;
					}
				}
				if(libreria[0][4]!=null && libreria[1][3]!=null && libreria[2][2]!=null && libreria[3][1]!=null && libreria[4][0]!=null) {
					if(libreria[0][4].getColor().equals(libreria[1][3].getColor()) && libreria[0][4].getColor().equals(libreria[2][2].getColor()) 
							&& libreria[0][4].getColor().equals(libreria[3][1].getColor()) && libreria[0][4].getColor().equals(libreria[4][0].getColor())) {
						realizzato=true;
					}
				}
				if(libreria[1][4]!=null && libreria[2][3]!=null && libreria[3][2]!=null && libreria[4][1]!=null && libreria[5][0]!=null ) {
					if(libreria[1][4].getColor().equals(libreria[2][3].getColor()) && libreria[1][4].getColor().equals(libreria[3][2].getColor())
							&& libreria[1][4].getColor().equals(libreria[4][1].getColor()) && libreria[1][4].getColor().equals(libreria[5][0].getColor())) {
						realizzato=true;
					}
				}
			}
			break;


		case 8: //funziona
			int b=0;  //ragionamento simile alla quinta carta
			if(libreria!=null) {
				conta=0;
				int [] piene= new int [6];
				Tile [] tipologia =new Tile [3];

				for(int riga=0; riga<6; riga++) {
					if(libreria[riga][0]!=null && libreria[riga][1]!=null && libreria[riga][2]!=null && libreria[riga][3]!=null &&
							libreria[riga][4]!=null) {
						piene[conta]=riga;
						conta+=1;
					}
				}
				if(conta>3) {
					for(int y=0; y<5; y++) {
						for(int colonna=0; colonna<5; colonna++) {
							if(tipologia[0]==null ) {
								tipologia[0]=libreria[colonna][piene[y]];
								b++;
							}
							else {
								if(tipologia[1]==null && libreria[colonna][piene[y]]!=null) {
									if(tipologia[0].getColor()!=libreria[colonna][piene[y]].getColor()) {
										tipologia[1]=libreria[colonna][piene[y]];
										b++;
									}
								}else {
									if(tipologia[2]==null && libreria[colonna][piene[y]]!=null) {
										if(tipologia[0].getColor()!=libreria[colonna][piene[y]].getColor() && tipologia[1].getColor()!=libreria[colonna][piene[y]].getColor() ) {
											tipologia[2]=libreria[colonna][piene[y]];
											b++;
										}
									}
									else {
										if(libreria[colonna][piene[y]]!=null) {
											if(tipologia[0].getColor()!=libreria[colonna][piene[y]].getColor() && tipologia[1].getColor()!=libreria[colonna][piene[y]].getColor()
													&& tipologia[2].getColor()!=libreria[colonna][piene[y]].getColor()) {
												b++;
											}
										}
									}
								}
							}
						}
					}
				}
				else {
					b=4;
				}
				if(b<4) {
					realizzato=true;
				}
				else {
					realizzato=false;
				}
			}
			break;

		case 9: // funziona
			if(libreria!=null) {
				conta=0;

				for(int colonna=0; colonna<5; colonna++) { //controlla che per ogni colonna siano tutti diversi
					if(libreria[0][colonna]!=null && libreria[1][colonna]!=null && libreria[2][colonna]!=null && libreria[3][colonna]!=null &&
							libreria[4][colonna]!=null && libreria[5][colonna]!=null) { //contorlla sulla prima che non abbia simili
						if(!(libreria[0][colonna].getColor().equals(libreria[1][colonna].getColor()) || libreria[0][colonna].getColor().equals(libreria[2][colonna].getColor()) 
								|| libreria[0][colonna].getColor().equals(libreria[3][colonna].getColor()) || libreria[0][colonna].getColor().equals(libreria[4][colonna].getColor())
								|| libreria[0][colonna].getColor().equals(libreria[5][colonna].getColor()))) {
							//controllo sulla seconda
							if(!(libreria[1][colonna].getColor().equals(libreria[2][colonna].getColor()) || libreria[1][colonna].getColor().equals(libreria[3][colonna].getColor()) 
									|| libreria[1][colonna].getColor().equals(libreria[4][colonna].getColor()) || libreria[1][colonna].getColor().equals(libreria[5][colonna].getColor()))) {

								//controllo sulla terza
								if(!(libreria[2][colonna].getColor().equals(libreria[3][colonna].getColor()) || libreria[2][colonna].getColor().equals(libreria[4][colonna].getColor()) 
										|| libreria[2][colonna].getColor().equals(libreria[5][colonna].getColor()))) {
									//controllo su quarta
									if(!(libreria[3][colonna].getColor().equals(libreria[4][colonna].getColor()) || libreria[3][colonna].getColor().equals(libreria[5][colonna].getColor()))) {
										//controllo su quinta
										if(!(libreria[4][colonna].getColor().equals(libreria[5][colonna].getColor()))){
											conta+=1;
										}
									}

								}
							}
						}
					}
				}
				if(conta>1) {
					realizzato=true;
				}

			}
			break;

		case 10: //funziona
			if(libreria!=null) { //stesso ragionamento che per la 9
				conta=0;

				for(int riga=0; riga<6; riga++) { //cambia a get color
					if((libreria[riga][0]!=null && libreria[riga][1]!=null && libreria[riga][2]!=null && libreria[riga][3]!=null &&
							libreria[riga][4]!=null)) {
						if(!(libreria[riga][0].getColor().equals(libreria[riga][1].getColor()) || libreria[riga][0].getColor().equals(libreria[riga][2].getColor()) 
								|| libreria[riga][0].getColor().equals(libreria[riga][3].getColor()) || libreria[riga][0].getColor().equals(libreria[riga][4].getColor()))) {
							if(!(libreria[riga][1].getColor().equals(libreria[riga][2].getColor()) || libreria[riga][1].getColor().equals(libreria[riga][3].getColor()) 
									|| libreria[riga][1].getColor().equals(libreria[riga][4].getColor()))) {
								if(!(libreria[riga][2].getColor().equals(libreria[riga][3].getColor()) || libreria[riga][2].getColor().equals(libreria[riga][4].getColor()))) {
									if(!(libreria[riga][3].getColor().equals(libreria[riga][4].getColor()))) {
										conta+=1;
									}
								}

							}
						}
					}
				}
				if(conta>1) {
					realizzato=true;
				}
			}
			break;
		case 11: //funziona
			int c=0;
			if(libreria!=null) {
				for(int riga=0; riga<5; riga++) {
					for(int colonna=0; colonna<3; colonna++) { //controlla figura, con limite su colonna e riga perché andrebbe fuori dalla libreria
						if((libreria[riga][colonna]!=null && libreria[riga][colonna+2]!=null && libreria[riga+1][colonna+1]!=null && libreria[riga+2][colonna]!=null &&
								libreria[riga+2][colonna+2]!=null)) {
							if((libreria[riga][colonna].getColor().equals(libreria[riga][colonna+2].getColor())&& libreria[riga][colonna].getColor().equals(libreria[riga+1][colonna+1].getColor())
									&& libreria[riga][colonna].getColor().equals(libreria[riga+2][colonna].getColor()) &&
									libreria[riga][colonna].getColor().equals(libreria[riga+2][colonna+2].getColor()))){
								c+=1;
							}
						}
					}
				}
			}
			if(c>0) {
				realizzato=true;
			}
			break;
		case 12: //funziona
			if(libreria!=null) { //controlla le 4 combinazioni possibili 
				if(libreria[0][4]!=null && libreria[1][3]!=null && libreria[2][2]!=null && libreria[3][1]!=null && libreria[4][0]!=null) {
					if(libreria[1][4]==null && libreria[2][3]==null && libreria[3][2]==null && libreria[4][1]==null && libreria[5][0]==null){
						realizzato=true;
					}
				}
				if(libreria[1][4]!=null && libreria[2][3]!=null && libreria[3][2]!=null && libreria[4][1]!=null && libreria[5][0]!=null) {
					if(libreria[2][4]==null && libreria[3][3]==null && libreria[4][2]==null && libreria[5][1]==null){
						realizzato=true;
					}
				}
				if(libreria[0][0]!=null && libreria[1][1]!=null && libreria[2][2]!=null && libreria[3][3]!=null && libreria[4][4]!=null) {
					if(libreria[1][0]==null && libreria[2][1]==null && libreria[3][2]==null && libreria[4][3]==null&& libreria[5][4]==null){
						realizzato=true;
					}
				}
				if(libreria[1][0]!=null && libreria[2][1]!=null && libreria[3][2]!=null && libreria[4][3]!=null && libreria[5][4]!=null) {
					if(libreria[2][0]==null && libreria[3][1]==null && libreria[4][2]==null && libreria[4][3]==null){
						realizzato=true;
					}
				}

			}
			break;
		}
		if(realizzato==true && primacarta==true) { //aggiorna stato obbiettivi giocatore
			realizzati[giocatore][0]=true;
		}
		if(realizzato==true && primacarta==false) {
			realizzati[giocatore][1]=true;
		}
		return realizzato;
	}

	public static void Obbiettivo(int numerocarta) {

		switch (numerocarta) { 
		case 1:
			System.out.println("Six groups each containing at least\r\n"
					+ "2 tiles of the same type (not necessarily\r\n"
					+ "in the depicted shape).\r\n"
					+ "The tiles of one group can be different\r\n"
					+ "from those of another group.\r\n"
					+ "");
			break;
		case 2: 
			System.out.println("Four groups each containing at least\r\n"
					+ "4 tiles of the same type (not necessarily\r\n"
					+ "in the depicted shape).\r\n"
					+ "The tiles of one group can be different\r\n"
					+ "from those of another group.");
			break;
		case 3:
			System.out.println("Four tiles of the same type in the four\r\n"
					+ "corners of the bookshelf");
			break;
		case 4:
			System.out.println("Two groups each containing 4 tiles of\r\n"
					+ "the same type in a 2x2 square. The tiles\r\n"
					+ "of one square can be different from\r\n"
					+ "those of the other square.");
			break;
		case 5:
			System.out.println("Three columns each formed by 6 tiles\r\n"
					+ "of maximum three different types. One\r\n"
					+ "column can show the same or a different\r\n"
					+ "combination of another column.");
			break;
		case 6:
			System.out.println("Eight tiles of the same type. There’s no\r\n"
					+ "restriction about the position of these\r\n"
					+ "tiles.");
			break;
		case 7:
			System.out.println("Five tiles of the same type forming a\r\n"
					+ "diagonal. ");
			break;
		case 8:
			System.out.println("Four lines each formed by 5 tiles of\r\n"
					+ "maximum three different types. One\r\n"
					+ "line can show the same or a different\r\n"
					+ "combination of another line"); 
			break;
		case 9:
			System.out.println("Two columns each formed by 6\r\n"
					+ "different types of tiles."); 
			break;
		case 10:
			System.out.println("Two lines each formed by 5 different\r\n"
					+ "types of tiles. One line can show the\r\n"
					+ "same or a different combination of the\r\n"
					+ "other line."); 
			break;
		case 11:
			System.out.println("Five tiles of the same type forming an X."); 
			break;
		case 12:
			System.out.println("Five columns of increasing or decreasing\r\n"
					+ "height. Starting from the first column on\r\n"
					+ "the left or on the right, each next column\r\n"
					+ "must be made of exactly one more tile.\r\n"
					+ "Tiles can be of any type. "); 
			break;

		}
		System.out.println("");
	}
}
