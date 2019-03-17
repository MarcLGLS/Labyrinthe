

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
    tableau[ximp][yimp].setEtat(Case.EtatCase.Arrivee);

    int dernierX = 0;
    int dernierY = 0;

    for(int y = 0; y < hauteur; y++){

      for(int x = 0; x < largeur; x++){

        maGrille.attendreEtape();

        if(x == ximp && y == yimp) continue;

        tableau[dernierX][dernierY].setEtat(Case.EtatCase.Normal);

        tableau[x][y].setEtat(Case.EtatCase.Selection);

        int creuser;

        do {

          creuser = ((int) (Math.random() * 2 + bias)) %4;

        } while (tableau[x][y].getVoisins()[creuser] == null);

        tableau[x][y].setMurs(creuser, false);
        //System.out.println("x : " + x + " y :" + y + " " + creuser);

        maGrille.finEtape();

        dernierX = x;
        dernierY = y;

      }

    }

    maGrille.attendreEtape();

    tableau[dernierX][dernierY].setEtat(Case.EtatCase.Normal);

    tableau[(int) (Math.random() * largeur)][(int) (Math.random() * hauteur)].setEtat(Case.EtatCase.Depart);

    maGrille.geneEstFinie();
    maGrille.finEtape();

  }

}
