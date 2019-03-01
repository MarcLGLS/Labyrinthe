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
	// Remplissage de la totalité du labyrinthe en case fermée + remplissage du tableau visité
	
		boolean t[] = {true,true,true,true};
		boolean visite[][] = new boolean[this.taille[0]][this.taille[1]]; 
		
		for(int x=0; x<this.taille[0];x++) {
			for(int y = 0; y<this.taille[1]; y++) {
				this.grille[x][y] = new Case(t);
				visite[x][y] = false; 
			}
		}

	// selection du case en bordure au hasard
		int a = (int)(Math.random() * (this.taille[1]+1));
		double position[] = {0,a}; 
		double position1[] = {0,0};
		visite[0][a] = true; 
		boolean finie = false;
		boolean t1 = {false,true,false,true};
		grille[0][a].setMurs(t1);
		
		while( finie == false) {
			// choix position case adjacente
			for(int i =0; i<2;i++) {
				do{
					position1[i]= position[i] +(int)(3*Math.random())-1;
				}while(position1[i]<0 || position1[i]>= this.taille[i] || position1[i] == position[i]) ;
			}
			
			
			// verification labyrinthe complète
			finie = true;
			for(int x=0; x<this.taille[0];x++) {
				for(int y = 0; y<this.taille[1]; y++) {
					if(visite[x][y] == false){
						finie = false;
					}
				}
			}
			
			
		}
	//
		
	
		
		
	}
	
	
}
