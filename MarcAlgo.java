

public class MarcAlgo extends Generation{

  public MarcAlgo(Grille g){

    this.grille = g;

  }

  public void generer(){

    int largeur = grille.getLargeur();
    int hauteur = grille.getHauteur();
    Case[][] tableau = grille.getTableau();
    boolean[][] visite = new boolean[largeur][hauteur];

  		boolean visite[][] = new boolean[largeur][hauteur];

  		grille.remplir(true);

  	// selection du case en bordure au hasard
  		int a = (int)(Math.random() * (hauteur+1));
  		int position[] = {0,a};
  		int position1[] = {0,0};
  		visite[0][a] = true;
  		boolean finie = false;
  		boolean t1[] = {false,true,false,true};
  		tableau[0][a].setMurs(t1);

  		while( finie == false) {
  			// choix position case adjacente
  				do{
  					if((int)(2*Math.random())==0) {
  						position1[0]= position[0] +(int)(3*Math.random())-1;
  					} else {
  						position1[1] = position[1] + (int)(3*Math.random())-1;
  					}
  				}while(position1[0]<0 || position1[0]>= largeur || position1[0] == position[0] ||position1[1]<0 || position1[1]>= hauteur || position1[1] == position[1] || visite[position1[0] || position1[1]] == true );

  				position[0] = position1[0];
  				position[1] = position1[1];
  			// choix mur à détruire bout de code à compléter !!!!!!!!!!!!!!!!

  				tableau[positon[0]][position[1]];


  			visite[position[0]][position[1]] = true;


  			// verification labyrinthe complète
  			finie = true;
  			for(int x=0; x<largeur;x++) {
  				for(int y = 0; y<hauteur; y++) {
  					if(visite[x][y] == false){
  						finie = false;
  					}
  				}
  			}


  		}


  }

}
