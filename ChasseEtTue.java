import java.util.*;

public class ChasseEtTue extends Generation implements Runnable{
	private LinkedList<Position> tue;

	public ChasseEtTue(Grille g){
		this.maGrille = g;
		this.tue = new LinkedList<Position>(); 
		
	}
	
	public void run() {
		// INITIALISATION
		maGrille.remplir(true); // remplissage du labyrinthe en entier par des murs
		
		
    	Case[][] tableau = maGrille.getTableau(); // création d'un tableau de case associée à grille

		int a = (int)(Math.random() * (maGrille.getHauteur())); // selection de la position case de départ et création de celle-ci
  		
  		boolean depart[] = {false,true,true,true};// tableau représentant l'état de la case départ

  		tableau[0][a].setMurs(depart);// affectation des murs de la case départ
		tableau[0][a].setEtat(Case.EtatCase.Depart);// affectation de l'état Depart de la case
		
		Position p1 = new Position(0,a);
		this.tue.add(p1);
		
		// BOUCLE
		do {
			
			
		} while(finie() == false);
		
		
		
	}
	
	  //méthode vérifiant si l'intégralité du labyrinthe a été visité, renvoie true si labyrinthe complet et false si incomplet
	public boolean finie() {
		boolean finie = true;
  		for(int x=0; x<maGrille.getLargeur();x++) {
  			for(int y = 0; y<maGrille.getHauteur(); y++) {
				if(this.tue.contains[x][y] == false){
  				finie = false;
  				}

  				}
  			}
	return finie;
  }
  
	public int[] random() {
		int r[] = {(int)(maGrille.getLargeur()*Math.random()),(int)(maGrille.getHauteur()*Math.random())};
		return r;
	}
  
	
	
}
