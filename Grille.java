/** Objet grille
*
*
*/

public class Grille {
	private Case tableau[][];
	private int largeur, hauteur;

	public Grille(int l, int h) {

		largeur = l;
		hauteur = h;

		tableau = new Case[largeur][hauteur];
	}


	public void remplir(boolean avecMurs){

		for(int i = 0; i < tableau.length; i++){

			for(int j = 0; j < tableau[0].length; j++){

				tableau[i][j] = new Case(avecMurs);

			}

		}

		for(int i = 0; i < tableau.length; i++){

			for(int j = 0; j < tableau[0].length; j++){

				

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

}
