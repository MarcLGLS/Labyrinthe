import java.util.*;

/*
*Implémentation de l'algorithme de parcours en largeurs d'un graphe (Breadth First Search en anglais) appliqué aux labyrinthes.
*/

public class RechercheLargeur extends Resolution{

  private HashMap<Case, Case> parents; //Map qui associe chaque Case découverte pas l'algorithme à son parent, pour pouvoir afficher le chemin correctement.
  private LinkedList<Case> aTraiter; //Liste des cases déjà traitées
  private LinkedList<Case> dejaTraite; //Liste des cases non traitées

  public RechercheLargeur(Grille g){

    this.maGrille = g;

    aTraiter = new LinkedList<Case>();
    dejaTraite = new LinkedList<Case>();
    parents = new HashMap<Case, Case>();

  }

  public void run(){

    resoudre();

  }

  public void resoudre(){

    maGrille.debutReso(); //On indique que la résolution a commencée

    Case[][] tableau = maGrille.getTableau(); //On récupère le tableau de case

    int[] dep = trouverDepart(); //Méthode permettant de trouver le départ dans le tableau de cases

    aTraiter.add(tableau[dep[0]][dep[1]]); //Ajout de la case de départ à la queue (liste des cases à traitées)

    while(aTraiter.size() > 0){ //Tant qu'il reste des cases à traiter

      maGrille.attendreEtape(); //On attend qu'une étape soit disponible pour modifier la grille

      long h1 = System.nanoTime(); //On récupère le temps au début de l'étape

      Case tmp = aTraiter.get(0); //La case en cours de traitement est la première case de la liste

      if(tmp.getEtat() == Case.EtatCase.Arrivee){ //Si la case est la case d'arrivée

        peindreChemin(tmp, parents, aTraiter, dejaTraite); //On peint le chemin et on sort de la méthode
        return;

      }else{

        tmp.setEtat(Case.EtatCase.Selection); //Sinon on ajoute cette case à la sélection et à la liste de case déjà traitée
        dejaTraite.add(tmp);

      }

      for(int i = 0; i < 4; i++){ //On parcours tous les voisins de la case courante

        if(tmp.getMurs()[i] == false){ //Si le mur i est ouvert

          if(dejaTraite.contains(tmp.getVoisins()[i]) == false && tmp.getVoisins()[i] != null){ //On vérifie que cette case n'a pas déjà été traitée ou qu'elle est existe bien (certains voisins des cases en bourdures sont nulls)

            if(aTraiter.contains(tmp.getVoisins()[i]) == false){ //On vérifie que la case n'est pas déjà dans la queue

              parents.put(tmp.getVoisins()[i], tmp); //On ajoute le voisin i dans la map en tant que clé, et la case courante en tant que valeur
              aTraiter.add(tmp.getVoisins()[i]); //On ajoute le voisin i dans les cases à traiter
              tmp.getVoisins()[i].setEtat(Case.EtatCase.Selection); //On change l'état du voisin en tant que case sélectionnée

            }

          }

        }

      }

      aTraiter.remove(tmp); //On enlève la case courante de la liste à traiter


      maGrille.ajouterTempsReso(System.nanoTime() - h1); //Ajout du temps de l'étape au temps total de l'algorithe
      maGrille.finEtape();//Fin de l'étape

    }



  }



}
