/** Objet grille
*
*
*/

public class Grille {
	private Case grille[][];
	private int largeur, hauteur;

	public Grille(int l, int h) {

		largeur = l;
		hauteur = h;

		grille = new Case[largeur][hauteur];
	}


	public void remplir(boolean avecMurs){

		for(Case c : grille){

			c = new Case(avecMurs);

		}

	}

	public int getLargeur(){

		return this.largeur;

	}

	public int getHauteur(){

		return this.hauteur;

	}

	public Case[][] getTableau(){

		return this.grille;		
	}

}
