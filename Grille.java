/** Objet grille
*
*
*/

public class Grille {
	private Case tableau[][];
	private Case tableauSecours[][];
	private int largeur, hauteur;
	private boolean nouvEtape = false;

	private boolean geneFinie = false;
	private boolean resoDebut = false;
	private boolean resoFinie = false;

	public Grille(int l, int h) {

		largeur = l;
		hauteur = h;

		tableau = new Case[largeur][hauteur];
		tableauSecours = new Case[largeur][hauteur];

		remplir(true);
	}


	public void remplir(boolean avecMurs){

		for(int i = 0; i < tableau.length; i++){

			for(int j = 0; j < tableau[0].length; j++){

				tableau[i][j] = new Case(avecMurs);

			}

		}

		for(int i = 0; i < tableau.length; i++){

			for(int j = 0; j < tableau[0].length; j++){

				Case[] vois = new Case[4];

				if(i == 0){

					vois[0] = null;

				}else{

					vois[0] = tableau[i-1][j];

				}

				if(i == largeur -1){

						vois[2] = null;
				}else{

					vois[2] = tableau[i+1][j];

				}

				if(j == 0){

					vois[1] = null;

				}else{

					vois[1]= tableau[i][j-1];

				}

				if(j == hauteur-1){

					vois[3] = null;

				}else{

					vois[3] = tableau[i][j+1];

				}

				tableau[i][j].setVoisins(vois);

			}

		}

	}

	public int getLargeur(){

		return this.largeur;

	}

	public int getHauteur(){

		return this.hauteur;

	}

	public Case[][] getTableau(){

		return this.tableau;
	}

	public synchronized void attendreEtape(){

		try{

			while(nouvEtape == false) wait();

		}catch(InterruptedException e) {e.printStackTrace();}


		nouvEtape = false;

	}

	public synchronized void attendreFenetre(){

		try{

			while(nouvEtape == true) wait();

		}catch(InterruptedException e) {e.printStackTrace();}

		if(geneFinie == false || (resoDebut == true && resoFinie == false)){

			nouvEtape = true;

		}


	}

	public synchronized void finEtape(){

		this.notifyAll();

	}

	public void geneEstFinie(){

		this.geneFinie = true;

		for(int i =0; i < tableau.length; i++){

			for(int j = 0; j < tableau[0].length; j++){

					tableauSecours[i][j] = tableau[i][j];

			}

		}

	}

	public void reinitialiser(){

		for(int i =0; i < tableau.length; i++){

			for(int j = 0; j < tableau[0].length; j++){

					tableau[i][j] = tableauSecours[i][j];

			}

		}

	}

	public boolean verifGene(){

		return this.geneFinie;

	}


}
