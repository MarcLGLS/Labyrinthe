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
				this.grille[x][y] = new Case(true);
				visite[x][y] = false; 
			}
		}

	// selection du case en bordure au hasard
		int a = (int)(Math.random() * (this.taille[1]+1));
		int position[] = {0,a}; 
		int position1[] = {0,0};
		visite[0][a] = true; 
		boolean finie = false;
		boolean t1[] = {false,true,false,true};
		grille[0][a].setMurs(t1);
		
		while( finie == false) {
			// choix position case adjacente
				do{
					if((int)(2*Math.random())==0) {
						position1[0]= position[0] +(int)(3*Math.random())-1;
					} else {
						position1[1] = position[1] + (int)(3*Math.random())-1;
					}
				}while(position1[0]<0 || position1[0]>= this.taille[0] || position1[0] == position[0] ||position1[1]<0 || position1[1]>= this.taille[1] || position1[1] == position[1] || visite[position1[0] || position1[1]] == true );
				
				position[0] = position1[0];
				position[1] = position1[1];
			// choix mur à détruire bout de code à compléter !!!!!!!!!!!!!!!!
			
				grille[positon[0]][position[1]]; 
				
			
			visite[position[0]][position[1]] = true;
			
			
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
