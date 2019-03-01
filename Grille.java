/** Objet grille
* 
*
*/

public class Grille {
	private Case grille[][];
	private int taille[];
	
	public Grille(int taille[]) {
		this.taille[0] = taille[0];
		this.taille[1] = taille[1]; 
		grille = new Case[this.taille[0]][this.taille[1]];
	}
	
	
	public void generation() {
	// Remplissage de la totalité du labyrinthe
	
		boolean t[] = {true,true,true,true};
		
		for(int i=0; i<this.taille[0];i++) {
			for(int y = 0; y<this.taille[1]; y++) {
				
				this.grille[i][y] = new Case(t); 
			}
		}
		
		
	}
	
}
