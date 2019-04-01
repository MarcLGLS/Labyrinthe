import java.util.*;

public class RecursiviteAleatoire extends Generation implements Runnable{
	private boolean[][] visite;

	public RecursiviteAleatoire(Grille g){

		this.maGrille = g;
		this.visite = new boolean[maGrille.getLargeur()][maGrille.getHauteur()];

		temps = new LinkedList<Long>();

	  }

	 public void run(){
		

		
		generer();
		
		
		}

	public void generer(){
		long heure1 = System.nanoTime();  // H1 ..................................
  		maGrille.remplir(true); // remplissage de la grille entierement par des murs
    	Case[][] tableau = maGrille.getTableau(); // création d'un tableau de case associée à grille

		// selection de la position case de départ et création de celle-ci
    	int a = (int)(Math.random() * (maGrille.getHauteur()));
  		int position[] = {0,a};
  		int position1[] = {0,a};
  		visite[0][a] = true;
  		boolean depart[] = {false,true,true,true};// tableau représentant l'état de la case départ
		long heure2 = System.nanoTime();  // H2 ..................................
		maGrille.ajouterTempsGene(heure2 - heure1);
		 
		maGrille.attendreEtape();
		
		long heure3 = System.nanoTime();  // H2 
  		tableau[0][a].setMurs(depart);// affectation des murs de la case départ
		tableau[0][a].setEtat(Case.EtatCase.Depart);// affectation de l'état Depart de la case
		long heure4 = System.nanoTime();  // H2 ..................................
		maGrille.ajouterTempsGene(heure4 - heure3);


		maGrille.finEtape();
		long heure5 = System.nanoTime();  // H2 
		f(position1);// réalisation du premier chemin à partir de la case départ
		long heure6 = System.nanoTime();  // H2 ..................................
		maGrille.ajouterTempsGene(heure6 - heure5);
		
		// création de nouveaux chemins à partir de case déjà visitée tant que la grille n'est pas totalement visitée
		do {
			long heure7 = System.nanoTime();  // H2 ..................................
			int x =0;
			int y = 0;

			
			
	
			do {
				
				x = (int)(Math.random()*(maGrille.getLargeur()));
				y = (int)(Math.random()*(maGrille.getHauteur()));

			} while(this.visite[x][y] == false);
		
				
			

			int position2[] = {x,y};
			long heure8 = System.nanoTime();  // H2 ..................................
			maGrille.ajouterTempsGene(heure4 - heure3);
			
			f(position2); // création d'un nouveau chemin à partir d'une case de coordonnées déterminées précédamment
			
			


		} while(finie()==false);

		arrivee(); // création de la case arrivée du labyrinthe
		
		maGrille.attendreEtape();
		maGrille.geneEstFinie();
		maGrille.finEtape();

  }

  //méthode vérifiant si l'intégralité du labyrinthe a été visité, renvoie true si labyrinthe complet et false si incomplet
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


  public void f(int a[]) {
		long heure1 = System.nanoTime();  // H2 ..................................
	    int largeur = maGrille.getLargeur();
		int hauteur = maGrille.getHauteur();
	  	// début remplissage de la grille
		int position[] = {a[0],a[1]};
		int position1[] = {a[0],a[1]};
		int i =0;
		boolean bloque = false;

		maGrille.getTableau()[position[0]][position[1]].setEtat(Case.EtatCase.Normal);
		long heure2 = System.nanoTime();  // H2 ..................................
		maGrille.ajouterTempsGene(heure2 - heure1);
  		while( bloque == false) {
             // initialisation

						 maGrille.attendreEtape();

						 long heure3 = System.nanoTime();
  			// choix de la case adjacente
				int tour = 0;


  				do{



					position1[0] = position[0];
					position1[1] = position[1];

  					if((int)(2*Math.random())==0) {
  						position1[0]= position[0] +(int)(3*Math.random())-1;
  					} else {
  						position1[1] = position[1] + (int)(3*Math.random())-1;
  					}

  					tour ++;

  					if(tour >10) {
						bloque = true;
						break;
					}

					long heure4 = System.nanoTime();  // H2 ..................................
					maGrille.ajouterTempsGene(heure4 - heure3);

  				}while(position1[0]<0 || position1[0]>= largeur ||position1[1]<0 || position1[1]>= hauteur || visite[position1[0]][ position1[1]] == true);

			long heure5 = System.nanoTime();  // H2 ..................................
			// Destruction des murs
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

			maGrille.getTableau()[position1[0]][position1[1]].setEtat(Case.EtatCase.Selection);
  		visite[position[0]][position[1]] = true;
			


		long heure6 = System.nanoTime();  // H2 ..................................
		maGrille.ajouterTempsGene(heure6 - heure5);
		}



			maGrille.finEtape();
    }


  }
	public void arrivee() {
		int y = (int)(Math.random()*maGrille.getHauteur());
		maGrille.getTableau()[maGrille.getLargeur()-1][y].setMurs(2,false);
		maGrille.getTableau()[maGrille.getLargeur()-1][y].setEtat(Case.EtatCase.Arrivee);
	}



}