import java.util.*;

public class ResolutionDroite extends Resolution{
	private ArrayList<Case> chemin;
	private ArrayList<Case> cheminASupprimer;
	private int position;
	private int x;
	private int y;
	
	public ResolutionDroite(Grille g){
		this.maGrille = g;
		this.chemin = new ArrayList<Case>();
		this.cheminASupprimer = new ArrayList<Case>();
	}

	public void run(){
		resoudre();
	}

	public void resoudre() {
		
	  // Détermination de la case départ.
	    long heure1 = System.nanoTime(); // Début du chronomètre.
		int xd =0;
		int yd =0;
		maGrille.debutReso();
		  for(this.x =0; this.x < maGrille.getLargeur() ; this.x++) {
				for(y = 0; y < maGrille.getHauteur(); y++) {

					if(maGrille.getTableau()[x][y].getEtat() == Case.EtatCase.Depart){
						xd=x;
						yd=y;
					}

				}
		  }


		this.position = 2;
		chemin(xd,yd); // Création du chemin ayant pour départ la case départ.
		long heure2 = System.nanoTime(); // Fin chrono.
		maGrille.ajouterTempsReso(heure2 - heure1); // Enregistrement du temps d'éxécution. 
		maGrille.finReso();



	}
	// Méthode créant un chemin suivant le mur de droite.
	public void chemin(int x, int y) {
		
		int x1 = x;
		int y1 = y;
		this.x=x;
		this.y = y;
		
		
		//On parcourt le labyrinthe tant que l'on ne rencontre pas la case arrivée.
		do {
			x1=this.x;
			y1=this.y;
		
		maGrille.attendreEtape();
		
		// Choix de la case suivante, selon la configuration des murs
		
		// cas continuer tout droit
		if(maGrille.getTableau()[x1][y1].getMurs(droite()) == true && maGrille.getTableau()[x1][y1].getMurs(gauche()) == true && maGrille.getTableau()[x1][y1].getMurs(this.position) == false ) {
				avancement(x1,y1);
				chemin.add(0,  maGrille.getTableau()[this.x][this.y]);
		}
		
		// cas cul de sac faire demi tour
		else if(maGrille.getTableau()[x1][y1].getMurs(droite()) == true && maGrille.getTableau()[x1][y1].getMurs(gauche()) == true && maGrille.getTableau()[x1][y1].getMurs(this.position) == true ) {
				this.position = gauche();
				this.position = gauche();
				avancement(x1,y1);
				chemin.add(0,maGrille.getTableau()[this.x][this.y]);
					}

		// cas chemin à droite
		else if( maGrille.getTableau()[x1][y1].getMurs(droite()) == false && maGrille.getTableau()[x1][y1].getMurs(gauche()) == true && maGrille.getTableau()[x1][y1].getMurs(this.position) == true ) {
				this.position= droite();
				avancement(x1,y1);
				chemin.add(0, maGrille.getTableau()[this.x][this.y]);
		}

		// cas chemin à gauche
		else if(maGrille.getTableau()[x1][y1].getMurs(droite()) == true && maGrille.getTableau()[x1][y1].getMurs(gauche()) == false && maGrille.getTableau()[x1][y1].getMurs(this.position) == true ) {
				this.position= gauche();
				avancement(x1,y1);
				chemin.add(0, maGrille.getTableau()[this.x][this.y]);
		}
		
		// cas intersection en T
		else if(maGrille.getTableau()[x1][y1].getMurs(droite()) == false && maGrille.getTableau()[x1][y1].getMurs(gauche()) == false) {
				this.position= droite();
				avancement(x1,y1);
				chemin.add(0, maGrille.getTableau()[this.x][this.y]);
		}
		
		//cas intersection en L(tout droit et à gauche)
		else if(maGrille.getTableau()[x1][y1].getMurs(gauche()) == false && maGrille.getTableau()[x1][y1].getMurs(this.position) == false) {
				avancement(x1,y1);
				chemin.add(0, maGrille.getTableau()[this.x][this.y]);
				
				

		}
		
		//cas intersection en L(tout droit et à droite)
		else if(maGrille.getTableau()[x1][y1].getMurs(droite()) == false && maGrille.getTableau()[x1][y1].getMurs(this.position) == false) {
				this.position = droite();
				avancement(x1,y1);
				chemin.add(0, maGrille.getTableau()[this.x][this.y]);

		}
		
		
	
		
		
		
		maGrille.getTableau()[x1][y1].setEtat(Case.EtatCase.Chemin); // Changement d'état des cases parcourues.
		
	
		// Sélection des cases en doublons sauf les intersections afin de déterminer le chemin le plus rapide.
		
		int i1 = chemin.indexOf(maGrille.getTableau()[this.x][this.y]);
		int i2 = chemin.lastIndexOf(maGrille.getTableau()[this.x][this.y]);
		
		if(i1 != i2) {
			while(chemin.contains(maGrille.getTableau()[this.x][this.y])) {
				cheminASupprimer.add(chemin.get(0));
				chemin.remove(0);
			}
			cheminASupprimer.remove(maGrille.getTableau()[this.x][this.y]);
			cheminASupprimer.remove(maGrille.getTableau()[this.x][this.y]);
			chemin.add(0,maGrille.getTableau()[this.x][this.y]);
		}
		
		if(maGrille.getTableau()[this.x][this.y].getEtat() != Case.EtatCase.Arrivee) {
			maGrille.getTableau()[this.x][this.y].setEtat(Case.EtatCase.Selection);
		}
		maGrille.finEtape();
		
		
	}while(maGrille.getTableau()[this.x][this.y].getEtat() != Case.EtatCase.Arrivee);
	
	// Disparition des cases en doublon sur l'affichage. 
	for(Case c : cheminASupprimer) {
		c.setEtat(Case.EtatCase.Normal);
		
	}
	
	
	
	

	

	}
/**
 * 
 */
	// Méthode faisant varier l'attribut position si on doit tourner à droite.
	public int droite() {
		
		int droite = this.position + 1;
		
		if(droite >3) 
			droite = 0;
	
		return droite;
		
	}
	
	// Méthode faisant varier l'attribut position si on doit tourner à gauche.
	public int gauche() {
		int gauche = this.position - 1;
		if(gauche < 0)
			gauche = 3;


		return gauche;
		
	}
	

	//Méthode modifiant les attributs x et y selon l'attribut position.
	public void avancement(int x1, int y1) {
		
		if(this.position == 0)
			this.x =x1-1;
			
		if(this.position == 1)
			this.y =y1-1;
			
		if(this.position == 2) 
			this.x =x1+1;
		
		if(this.position == 3) 
			this.y =y1+1;
		
	}


	
	
}
