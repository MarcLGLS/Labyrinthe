

public class MarcAlgo extends Generation{

  public MarcAlgo(Grille g){

    this.maGrille = g;

  }

  public void generer(){

		int largeur = maGrille.getLargeur();
		int hauteur = maGrille.getHauteur();

		boolean[][] visite = new boolean[largeur][hauteur];

	// initialisation de la grille ( remplissage en entier = choix case de départ)

  		maGrille.remplir(true);

      	Case[][] tableau = maGrille.getTableau();

  	// selection du case en bordure au hasard

    	int a = (int)(Math.random() * (hauteur+1));
      a = 0;
  		int position[] = {0,a};
  		int position1[] = {0,0};
  		visite[0][a] = true;
  		boolean finie = false;
  	// tableau représentant l'état de la case départ
  		boolean depart[] = {false,true,false,true};
  		tableau[0][a].setMurs(depart);



	// début remplissage de la grille
		int i = 0;

  		while( i<15) {

  			// choix de la case adjacente

  				do{
  					if((int)(2*Math.random())==0) {
  						position1[0]= position[0] +(int)(3*Math.random())-1;
  					} else {
  						position1[1] = position[1] + (int)(3*Math.random())-1;
  					}
  				}while(position1[0]<0 || position1[0]>= largeur || position1[0] == position[0] ||position1[1]<0 || position1[1]>= hauteur || position1[1] == position[1] || visite[position1[0]][ position1[1]] == true );


  				position[0] = position1[0];
  				position[1] = position1[1];

  			// choix mur à détruire bout de code à compléter !!!!!!!!!!!!!!!!




  			visite[position[0]][position[1]] = true;





			i++;
    }


  }

  //méthode vérifiant si l'intégralité du labyrinthe a été visité, renvoie true si labyrinthe complet et false si incomplet
  public boolean finie(boolean visite[][]) {
	boolean finie = true;
  			for(int x=0; x<maGrille.getLargeur();x++) {
  				for(int y = 0; y<maGrille.getHauteur(); y++) {
  					if(visite[x][y] == false){
  						finie = false;
  					}
  				}
  			}
	return finie;
  }



}
