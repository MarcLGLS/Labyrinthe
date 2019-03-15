import java.util.*;

public class ResolutionDroite extends Resolution{
	private LinkedList<Case> chemin; 
	private int position;
	public ResolutionDroite(Grille g){
	this.maGrille = g;
	this.chemin = new LinkedList<Case>();
	}
	
	public void resoudre() {
	  this.position = 2; 
	  
	}
	
	public void f(int x, int y) {
		// cas continuer tout droit
		
		if(maGrille.getTableau()[x][y].getMurs(droite()) == true && maGrille.getTableau()[x][y].getMurs(gauche()) == true && maGrille.getTableau()[x][y].getMurs(this.position) == false ) {	
						
				chemin.add(maGrille[avancement(x,y)[0]][avancement(x,y)[1]]);
		}
		// cas cul de sac faire demi tour
		if(maGrille.getTableau()[x][y].getMurs(droite()) == true && maGrille.getTableau()[x][y].getMurs(gauche()) == true && maGrille.getTableau()[x][y].getMurs(this.position) == true ) {	
				// faire demi tour
				this.position = gauche();
				this.position = gauche();		
				chemin.add(maGrille[avancement(x,y)[0]][avancement(x,y)[1]]);
		}
		
		// cas chemin à droite
		if( maGrille.getTableau()[x][y].getMurs(droite()) == false && maGrille.getTableau()[x][y].getMurs(gauche()) == true && maGrille.getTableau()[x][y].getMurs(this.position) == true ) {	
				this.position= droite();	
				chemin.add(maGrille[avancement(x,y)[0]][avancement(x,y)[1]]);
		}
		
		// cas chemin à gauche
		if(maGrille.getTableau()[x][y].getMurs(droite()) == true && maGrille.getTableau()[x][y].getMurs(gauche()) == false && maGrille.getTableau()[x][y].getMurs(this.position) == true ) {	
				this.position= gauche();	
				chemin.add(maGrille[avancement(x,y)[0]][avancement(x,y)[1]]);
		}
		
	}
	
	public int droite() {
		int droite = this.position + 1; 
		if(droite >3) {
			droite = 0;
		}
		
		return droite;
	}
	
	public int gauche() {
		int gauche = this.position - 1; 
		if(gauche < 0) {
			gauche = 3;
		}
		
		return gauche;
	}
	
	public int[] avancement(int x, int y) {
		if(this.position = 0) {
			x =x-1; 
		}
		if(this.position = 1) {
			y =y-1; 
		}
		if(this.position = 2) {
			x =x+1; 
		}
		if(this.position = 3) {
			y =y+1; 
		}
		int position1[] = {x,y};
		return position1;
	}
}
