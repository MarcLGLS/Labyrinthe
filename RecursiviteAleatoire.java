import java.util.*;

public class RecursiviteAleatoire extends Generation implements Runnable{
	private boolean[][] visite;

	public RecursiviteAleatoire(Grille g){

		this.maGrille = g;
		this.visite = new boolean[maGrille.getLargeur()][maGrille.getHauteur()];

		

	  }

	 public void run(){



		generer();


		}

	public void generer(){
		long heure1 = System.nanoTime();  // Début du chronomètre.

		// Initialisation de la grille
  		maGrille.remplir(true); // Remplissage de l'integralité de la grille par des murs.
    	Case[][] tableau = maGrille.getTableau(); // Création d'un tableau d'objet case.

		// Selection aléatoire de la case départ du labyrinthe et création de celle-ci.
    	int a = (int)(Math.random() * (maGrille.getHauteur()));
  		int position[] = {0,a};
  		int position1[] = {0,a};

  		// Définition de la case départ
  		visite[0][a] = true; // Définition de l'état visité de la case départ.
  		boolean depart[] = {false,true,true,true};// Tableau représentant l'état de la case départ.

		long heure2 = System.nanoTime();  // Fin du chronomètre
		maGrille.ajouterTempsGene(heure2 - heure1); // Enregistrement du temps d'éxécution.

		maGrille.attendreEtape(); // Methode synchrone utilisee pour attendre que la fenetre se mette a jour avant de modifier la grille.


		long heure3 = System.nanoTime();  // Début du chronomètre.

		// Mise en place de la case départ.
  		tableau[0][a].setMurs(depart);// Affectation des murs de la case départ
		tableau[0][a].setEtat(Case.EtatCase.Depart);// Affectation de l'état Depart de la case.

		long heure4 = System.nanoTime();  // Fin du chronomètre
		maGrille.ajouterTempsGene(heure4 - heure3); // Enregistrement du temps d'exécution.


		maGrille.finEtape(); // On indique qu'une etape vient de se finir, la fenetre va donc pourvoir se redessiner.

		// Réalisation du premier chemin
		long heure5 = System.nanoTime();  // Début du chronomètre.

		chemin(position1);// Réalisation du premier chemin partant de la case départ.

		long heure6 = System.nanoTime();  // Fin du chronomètre.
		maGrille.ajouterTempsGene(heure6 - heure5); // Enregistrement du temps d'exécution.

		// Creation de nouveaux chemins ayant comme point de départ une case visitée tant que le labyrinthe n'est pas intégralement complété.
		do {
			long heure7 = System.nanoTime(); // Début du chronomètre.
			int x =0;
			int y = 0;



			// Détermination aléatoire des coordonnées d'une case visitée.
			do {

				x = (int)(Math.random()*(maGrille.getLargeur()));
				y = (int)(Math.random()*(maGrille.getHauteur()));

			} while(this.visite[x][y] == false);

			int position2[] = {x,y};

			long heure8 = System.nanoTime();  // Fin du chronomètre.
			maGrille.ajouterTempsGene(heure8 - heure7); // Enregistrement du temps d'exécution.

			chemin(position2); // Création d'un nouveau chemin à partir de la case déterminée précédemment.


		} while(finie()==false);

		arrivee(); // Création de la case arrivée du labyrinthe.

		maGrille.attendreEtape();
		maGrille.geneEstFinie(); //On indique a la grille que la generation est finie, ainsi on peut lancer un algorithme de resolution.
		maGrille.finEtape();

  }

  //Méthode parcourant l'intégralité de la grille afin de savoir si toutes les cases ont été visité. Renvoie true si le labyrinthe finie et false dans le cas contraire.
	public boolean finie() {
		boolean finie = true;
  		for(int x=0; x<maGrille.getLargeur();x++) {
  			for(int y = 0; y<maGrille.getHauteur(); y++) {
				maGrille.getTableau()[x][y].setEtat(Case.EtatCase.Normal);
				if(this.visite[x][y] == false){
					finie = false;
  				}

  				}
  			}
	return finie;
  }


// Méthode générant un chemin à partir d'une case départ définie en paramètre.
  public void chemin(int a[]) {
		long heure1 = System.nanoTime();  // Début chronomètre.

	    int largeur = maGrille.getLargeur();
		int hauteur = maGrille.getHauteur();

	  	// Création du début du chemin
		int position[] = {a[0],a[1]};
		int position1[] = {a[0],a[1]};
		boolean bloque = false;
		maGrille.getTableau()[position[0]][position[1]].setEtat(Case.EtatCase.Normal);
		long heure2 = System.nanoTime();  // Fin chronomètre.
		maGrille.ajouterTempsGene(heure2 - heure1); //Enregistrement du temps d'éxécution.

  		// On continue d'agrandir le chemin tant que ça est toujours possible.

  		while( bloque == false) {

			maGrille.attendreEtape();
			//Ajout d'une nouvelle case au chemin.
			long heure3 = System.nanoTime(); // Début du chronomètre.
			int tour = 0;

			// Recherche d'une case vierge aux abords de la dernière case du chemin
  			do{
				position1[0] = position[0];
				position1[1] = position[1];

				//Détermination aléatoire des coordonnées d'une case adjacente
  				if((int)(2*Math.random())==0) {
  					position1[0]= position[0] +(int)(3*Math.random())-1;
  				} else {
  					position1[1] = position[1] + (int)(3*Math.random())-1;
  				}

				// Si au bout de 11 exècutions de la boucle sans trouver de case compatible, on considère que le chemin est bloqué.
  				if(tour >10) {
					bloque = true;
					break;
				}

				tour ++;

  			}while(position1[0]<0 || position1[0]>= largeur ||position1[1]<0 || position1[1]>= hauteur || visite[position1[0]][ position1[1]] == true);



			// Destruction des murs entre la nouvelle case et l'ancienne case dans le cas où me chemin n'est pas bloqué.
			if(bloque == false) {

			if( position[0] > position1[0] ) {
				maGrille.getTableau()[position[0]][position[1]].setMurs(0,false);
				maGrille.getTableau()[position1[0]][position1[1]].setMurs(2,false);
			}

			if(position[0] < position1[0]) {
				maGrille.getTableau()[position[0]][position[1]].setMurs(2,false);
				maGrille.getTableau()[position1[0]][position1[1]].setMurs(0,false);
			}

			if( position[1] > position1[1]) {
				maGrille.getTableau()[position1[0]][position1[1]].setMurs(3,false);
				maGrille.getTableau()[position[0]][position[1]].setMurs(1,false);
			}

			if( position[1] < position1[1]) {
				maGrille.getTableau()[position1[0]][position1[1]].setMurs(1,false);
				maGrille.getTableau()[position[0]][position[1]].setMurs(3,false);
			}









			position[0] = position1[0];
			position[1] = position1[1];

			maGrille.getTableau()[position1[0]][position1[1]].setEtat(Case.EtatCase.Selection);// Affectation de l'état de la case pour l'affichage dynamique.
			visite[position[0]][position[1]] = true; // La nouvelle case est enregistré comme visitée.




		}
		long heure4 = System.nanoTime();  // Fin chronomètre.
		maGrille.ajouterTempsGene(heure4 - heure3); // Enregistrement du temps d'éxécution.

		maGrille.finEtape();

    }


  }

	// Méthode créant la case arrivée.
	public void arrivee() {
		int y = (int)(Math.random()*maGrille.getHauteur());
		maGrille.getTableau()[maGrille.getLargeur()-1][y].setMurs(2,false);
		maGrille.getTableau()[maGrille.getLargeur()-1][y].setEtat(Case.EtatCase.Arrivee);
	}



}
