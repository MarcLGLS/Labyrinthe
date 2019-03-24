

public class ArbreBinaire extends Generation implements Runnable{

  public ArbreBinaire(Grille g){

    this.maGrille = g;

  }

  public void run(){

    generer();

  }

  public void generer(){

    /*

    Fonctionnement de l'algorithme : On choisit un biais a utiliser selon une diagonale (detailler plus bas) ex: nord-ouest.
    On parcourt tout le tableau en enlevant un mur a chaque case de facon aleatoire, avec l'exemple celui de gauche (ouest) ou du haut (nord).
    Le labyrinthe obtenu est parfait mais presente un fort bias dans la direction du bias. De plus des corridors sur toute la hauteur/largeur sont presents dans les direction du bias
    (a gauche et en haut dans l'exemple).

    */

    long h1, h2; //long necessaires pour calculer le temps que l'algo prends.

    maGrille.remplir(true); // on remplit la grille avec des cases fermees.

    int largeur = maGrille.getLargeur();
    int hauteur = maGrille.getHauteur();

    Case[][] tableau = maGrille.getTableau(); //le tableau de case de la grille


    //On choisit aleatoirement le biais a utiliser : 0 nord-ouest, 1 nord-est, 2 sud-est, 3 sud ouest


    int bias = (int) (Math.random() * 4);

    int ximp = 0, yimp = 0;

    //Pour chaque biais possible il y a une case dont aucun mur n'est detruit (car elle se situe dans le coin associe au biais).
    //On identifie cette case pour la traiter specialement lors du parcours de la grille.

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

    //On choisit arbitrairement de mettre l'arrivee dans la case identifiee au dessus.

    tableau[ximp][yimp].setMurs(bias, false);
    tableau[ximp][yimp].setEtat(Case.EtatCase.Arrivee);

    //variable utile pour l'affichage.
    int dernierX = 0;
    int dernierY = 0;

    /*

    Boucle principale de l'algorithme : on n'effectue qu'un seul parcourt du tableau; complexité de n^2.

    */
    for(int y = 0; y < hauteur; y++){

      for(int x = 0; x < largeur; x++){

        maGrille.attendreEtape(); //Methode synchrone utilisee pour attendre que la fenetre se mette a jour avant de modifier la grille.

        h1 = System.nanoTime(); // debut du chrono

        if(x == ximp && y == yimp) continue; //case dans le coin, impossible d'y casser des murs, on passe au tour de boucle suivant

        // On fait attention de changer quelle case est selectionnee (regardee actuellement par le labyrinthe).
        //On remet l'etat de la case precedente en normal

        tableau[dernierX][dernierY].setEtat(Case.EtatCase.Normal);

        //On met l'etat de la case actuelle en selectionne
        tableau[x][y].setEtat(Case.EtatCase.Selection);

        //int qui indique quel mur de la case on va casser
        int creuser;

        /*

        Tant qu'un voisin de la case est null, on relance le tirage de la variable aleatoire creuser.
        Cette boucle est necessaire pour les cases se trouvant dans les bords du labyrinthe : certains de leurs voisins sont null.
        Il est plus aisé de traiter ce probleme avec une boucle qu'avec des cas speciaux pour chaque bords du labyrinthe.

        */

        do {

          creuser = ((int) (Math.random() * 2 + bias)) %4;

        } while (tableau[x][y].getVoisins()[creuser] == null);

        //On creuse le mur choisi.

        tableau[x][y].setMurs(creuser, false);

        //System.out.println("x : " + x + " y :" + y + " " + creuser);

        //Fin du chrono
        h2 = System.nanoTime();

        long tot = h2-h1;
        System.out.println(tot);

        //On ajoute ce temps de boucle au temps total de l'algo.
        maGrille.ajouterTempsGene(h2-h1);

        //on indique qu'une etape vient de se finir, la fenetre va donc pourvoir se redessiner.
        maGrille.finEtape();


        //La derniere case devient alors celle qu'on vient de traiter.
        dernierX = x;
        dernierY = y;

      }

    }

    //Fin de la boucle principale

    maGrille.attendreEtape();

    //On s'occupe de la derniere case parcourue par le labyrinthe, dont l'etat n'a pas ete mis a jour dans la deniere boucle.
    tableau[dernierX][dernierY].setEtat(Case.EtatCase.Normal);

    //On choisit une case aleatoire dans tout le labyrinthe qui sera le point de depart pour la resolution.
    tableau[(int) (Math.random() * largeur)][(int) (Math.random() * hauteur)].setEtat(Case.EtatCase.Depart);

    //On indique a la grille que la generation est finie, ainsi on peut lancer un algorithme de resolution.
    maGrille.geneEstFinie();
    maGrille.finEtape();

  }

}
