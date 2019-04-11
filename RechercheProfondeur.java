import java.util.*;

/*
*Implémentation de la recherche en profondeur de graphe (Depth-First Search). Il reprend fondamentalement le même principe que le parcours en largeur, mais avec une pile au lieu d'une queue.
La pile est implémentée par l'intermédiaire d'une fonction récursive.
*/
public class RechercheProfondeur extends Resolution{

  private HashMap<Case, Case> parents; //La map des parents pour pouvoir peindre le chemin

  private ArrayList<Case> dejaTraite; //La liste des cases déjà traitées

  private boolean fin = false; //Booléen utile pour savoir si la recherche est finie est ainsi vider la pile

  private long h1; //long utlisé pour mesurer le temps d'une étape

  public RechercheProfondeur(Grille g){

    this.maGrille = g;

    dejaTraite = new ArrayList<Case>();
    parents = new HashMap<Case, Case>();

  }

  public void run(){


    resoudre();

  }

  //Méthode résoudre : elle enclenche la récursivité qui se fait dans la méthode resoudreRec

    public void resoudre(){

      int[] coord = trouverDepart();
      maGrille.debutReso(); //On signale que la résolution démarre
      maGrille.attendreEtape(); //Attente d'une étape disponible
      h1 = System.nanoTime(); //début du chrono
      resoudreRec(maGrille.getTableau()[coord[0]][coord[1]]); //Lancement de la méthode récursive

    }

    //méthode récursive quiparcours le tableau

    public void resoudreRec(Case c){ //Elle prend la case à traiter en paramètre

    if(fin == true) return; //Si la fin a été trouvée on libère la pile en finissant la méthode

    maGrille.ajouterTempsReso(System.nanoTime()-h1); //On ajoute le temps pris depuis le début du chrono (qui se situe dans l'appel en dessous dans la pile)
    maGrille.finEtape(); //fin de l'étape d'avant (méthode se situant en dessous dans la pile)
    maGrille.attendreEtape(); //On attend qu'une étape soit  disponible
    h1 = System.nanoTime();

    dejaTraite.add(c); //Ajout de la case aux cases déjà traitées

    if(c.getEtat() == Case.EtatCase.Arrivee){

      peindreChemin(c, parents, dejaTraite, dejaTraite); //Si la case est celle d'arrivée on peint le chemin et on libère la pile
      fin = true;
      return;

    }else{

      c.setEtat(Case.EtatCase.Selection); //Sinon on ajoute cette case à la sélection

    }

    Case[] voisinsValides = new Case[4]; //Tableau représentant les voisins valides de la case c

    for(int i =0; i < 4; i++){

      if(c.getMurs(i) == false){

        if(c.getVoisins()[i] != null && dejaTraite.contains(c.getVoisins()[i]) == false){ //Les voisins valides sont les voisins où le mur est ouvert, qui existent, et qui n'ont pas dejà été visités

          parents.put(c.getVoisins()[i], c); //On met cette case en valeur et le voisin valide en clé

          voisinsValides[i] = c.getVoisins()[i]; //On ajoute le voisin au tableau voisin valide

        }

      }

    }


    /*Le fait de parcourir les voisins de 0 à 3 (depuis le voisin de gauche dans le sens horaire) induit un biais : le premier voisin traité sera toujours celui le plus à gauche.
    * Ce biais est beaucoup moins présent sur le parcours en largeur du fait de la queue utilisée et non une pile.
    * Pour palier ce biais on "mélange" les voisins de la case en cours de traitement. Cela ajoute une partie d'aléatoire dans l'algorithme et peut influer sur son temps d'éxécution.
    * De manière générale le temps d'éxécution avec cette méthode est plus faible que celle avec le bais, surtout dans des labyrinthe où un biais est aussi présent (ArbreBinaire par exemple)
    */

    voisinsValides = melangeurVoisin(voisinsValides);

    for(int j = 0; j < 4; j++){

      if(voisinsValides[j] != null){

        resoudreRec(voisinsValides[j]); //On relance la fonction récursive avec tous les voisins disponibles

      }

    }


  }

  //Méthode permettant de mélanger les élément d'un tableau (ici de case) de manière aléatoire

  public Case[] melangeurVoisin(Case[] c){

    Case ret[] = new Case[4];

    for(int i = 0; i < c.length; i++){

      if(c[i] != null){

        int pos;

        do {

          pos = (int) (Math.random() * 4);

        } while (ret[pos] != null);

        ret[pos] = c[i];

      }


    }

    return ret;

  }


}
