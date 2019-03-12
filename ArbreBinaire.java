

public class ArbreBinaire extends Generation implements Runnable{

  public ArbreBinaire(Grille g){

    this.maGrille = g;

  }

  public void run(){

    generer();

  }

  public void generer(){

    maGrille.remplir(true);

    int largeur = maGrille.getLargeur();
    int hauteur = maGrille.getHauteur();

    Case[][] tableau = maGrille.getTableau();

    int bias = (int) (Math.random() * 4);

    int ximp = 0, yimp = 0;

    switch(bias){

      case 0:
        ximp = 0; yimp = 0;
        break;
      case 1:
        ximp = largeur - 1; yimp = 0;
        break;
      case 2:
        ximp = largeur -1; yimp = hauteur -1;
        break;
      case 3 :
        ximp = 0; yimp = hauteur -1;
        break;

    }

    tableau[ximp][yimp].setMurs(bias, false);
    tableau[ximp][yimp].setArrivee();


    for(int y = 0; y < hauteur; y++){

      for(int x = 0; x < largeur; x++){

        maGrille.attendreEtape();

        if(x == ximp && y == yimp) continue;

        int creuser;

        do {

          creuser = ((int) (Math.random() * 2 + bias)) %4;

        } while (tableau[x][y].getVoisins()[creuser] == null);

        tableau[x][y].setMurs(creuser, false);
        System.out.println("x : " + x + " y :" + y + " " + creuser);

        synchronized(maGrille){

  				maGrille.notifyAll();

  			}

      }

    }

  }

}
