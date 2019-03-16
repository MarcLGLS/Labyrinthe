

public class MarcAlgo extends Generation implements Runnable{
	private boolean[][] visite;

  public MarcAlgo(Grille g){

    this.maGrille = g;

    // initialisation + remplissage du tableau visite par des false
    this.visite = new boolean[maGrille.getLargeur()][maGrille.getHauteur()];

	  }

	    public void run(){

				generer();

			}

  public void generer(){

		long heure1 = System.currentTimeMillis();

	// initialisation de la grille ( remplissage en entier = choix case de départ)

  		maGrille.remplir(true);

    	Case[][] tableau = maGrille.getTableau();




  	// selection du case en bordure au hasard

    	int a = (int)(Math.random() * (maGrille.getLargeur()));

  		int position[] = {0,a};
  		int position1[] = {0,a};
  		visite[0][a] = true;
  		boolean finie = false;
  	// tableau représentant l'état de la case départ
  		boolean depart[] = {false,true,true,true};


  		tableau[0][a].setMurs(depart);
  		// PROBLEME A GERER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		maGrille.getTableau()[0][a].setEtat(Case.EtatCase.Depart);
		f(position1);

	// on continue à créer des serpents tant que grille pas remplie
		int z =0;

	do {
		// choix case déja visité

		int x;
		int y;
		do {
			x = (int)(Math.random()*(maGrille.getLargeur()));
			y = (int)(Math.random()*(maGrille.getHauteur()));
		} while(this.visite[x][y] == false);

		int position3[] = {x,y};

		f(position3);
		z++;


	} while(finie()==false);

	arrivee();



	maGrille.attendreEtape();
	maGrille.geneEstFinie();
	maGrille.finEtape();




	long heure2 = System.currentTimeMillis();
	System.out.println("Temps = "+(heure2-heure1)+"  ms / nombre de serpent"+z+" !!" );

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
	    int largeur = maGrille.getLargeur();
		int hauteur = maGrille.getHauteur();
	  	// début remplissage de la grille
		int position[] = {a[0],a[1]};
		int position1[] = {a[0],a[1]};
		int i =0;
		boolean bloque = false;

		maGrille.getTableau()[position[0]][position[1]].setEtat(Case.EtatCase.Normal);

  		while( bloque == false) {
             // initialisation

						 maGrille.attendreEtape();
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

  					if(tour >50) {
						bloque = true;
						break;
					}



  				}while(position1[0]<0 || position1[0]>= largeur ||position1[1]<0 || position1[1]>= hauteur || visite[position1[0]][ position1[1]] == true);


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

		}




			i++;

			maGrille.finEtape();
    }


  }
	public void arrivee() {
		int y = (int)(Math.random()*maGrille.getHauteur());
		maGrille.getTableau()[maGrille.getLargeur()-1][y].setMurs(2,false);
		maGrille.getTableau()[maGrille.getLargeur()-1][y].setArrivee();
	}


}
