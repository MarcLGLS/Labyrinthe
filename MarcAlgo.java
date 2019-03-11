

public class MarcAlgo extends Generation{
	private boolean[][] visite;
  
  public MarcAlgo(Grille g){

    this.maGrille = g;
    
    // initialisation + remplissage du tableau visite par des false 
    this.visite = new boolean[maGrille.getLargeur()][maGrille.getHauteur()];
    for(int y =0; y< maGrille.getHauteur(); y++) {
		for(int x =0; x < maGrille.getLargeur(); x++) {
			this.visite[x][y] = false; 
		}
		
	}
	
	
    


  }

  public void generer(){



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
		System.out.println("********************* début nouvelle boucle"+(z+1)+" !!");
		System.out.println(" x = "+x+" ///  y="+y+"  ");
		int position3[] = {x,y};
		
		f(position3);
		z++;
		
	} while(finie()==false);

	arrivee();
		
		
	





  }

  //méthode vérifiant si l'intégralité du labyrinthe a été visité, renvoie true si labyrinthe complet et false si incomplet
  public boolean finie() {
	boolean finie = true;
  			for(int x=0; x<maGrille.getLargeur();x++) {
  				for(int y = 0; y<maGrille.getHauteur(); y++) {
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
  		while( bloque == false) {
             // initialisation
             
                
  			// choix de la case adjacente
				int tour = 0;
				
				
  				do{
					
					position1[0] = position[0];
					position1[1] = position[1];


  					if((int)(2*Math.random())==0) {
  						position1[0]= position[0] +(int)(3*Math.random())-1;
  						// position1[0] = position[0]+1;
  					} else {
						// position1[1] = position[1]+1;
  						position1[1] = position[1] + (int)(3*Math.random())-1;
  					
  					}
  					tour ++;
  					
  					if(tour >50) {
						System.out.println("Je suis bloqué, sortez moi de cette boucle");
						bloque = true;
						break;
					}
					
					
  				}while(position1[0]<0 || position1[0]>= largeur ||position1[1]<0 || position1[1]>= hauteur || visite[position1[0]][ position1[1]] == true);
					
					
			// destruction du mur choix. 
			if(bloque == false) {
			if( position[0] > position1[0] ) {
				maGrille.tableau[position[0]][position[1]].setMurs(0,false);
				maGrille.tableau[position1[0]][position1[1]].setMurs(2,false);
				System.out.println("Je casse le mur 0 et 2");
			}
			
			if(position[0] < position1[0]) {
				maGrille.tableau[position[0]][position[1]].setMurs(2,false);
				maGrille.tableau[position1[0]][position1[1]].setMurs(0,false);
				System.out.println("Je casse le mur 0 et 2");
			}
			
			if( position[1] > position1[1]) {
				maGrille.tableau[position1[0]][position1[1]].setMurs(3,false);
				maGrille.tableau[position[0]][position[1]].setMurs(1,false);
				System.out.println("Je casse le mur 1 et 3");
			}
			
			if( position[1] < position1[1]) {
				maGrille.tableau[position1[0]][position1[1]].setMurs(1,false);
				maGrille.tableau[position[0]][position[1]].setMurs(3,false);
				System.out.println("Je casse le mur 1 et 3");
				
			}
				
			
				
  				
				
  		
	
			System.out.println("test"+i+"/ x="+position1[0]+"  y="+position1[1]+"  ");
			System.out.println("***********");
		
			position[0] = position1[0];
  			position[1] = position1[1];
  			visite[position[0]][position[1]] = true;
		}



			
			i++;
    }
	  
  
  }
	public void arrivee() {
		int y = (int)(Math.random()*maGrille.getHauteur()); 
		maGrille.tableau[maGrille.getLargeur()-1][y].setMurs(2,false);
		maGrille.tableau[maGrille.getLargeur()-1][y].setArrivee();
	}


}
