import java.util.*;

public class RechercheLargeur extends Resolution{

  private HashMap<Case, Case> parents;
  private ArrayList<Case> aTraiter;
  private ArrayList<Case> dejaTraite;

  public RechercheLargeur(Grille g){

    this.maGrille = g;

    aTraiter = new ArrayList<Case>();
    dejaTraite = new ArrayList<Case>();
    parents = new HashMap<Case, Case>();

  }

  public void run(){

    resoudre();

  }

  public void resoudre(){

    maGrille.debutReso();

    Case[][] tableau = maGrille.getTableau();

    int[] dep = trouverDepart();

    aTraiter.add(tableau[dep[0]][dep[1]]);

    while(aTraiter.size() > 0){

      maGrille.attendreEtape();

      long h1 = System.nanoTime();

      Case tmp = aTraiter.get(0);
      if(tmp.getEtat() == Case.EtatCase.Arrivee){

        Case par;

        do{

          par = parents.get(tmp);
          par.setEtat(Case.EtatCase.Chemin);

        }while(par.getEtat() != Case.EtatCase.Depart);

        maGrille.ajouterTempsReso(System.nanoTime() - h1);
        maGrille.finEtape();
        break;

      }else{

        tmp.setEtat(Case.EtatCase.Selection);
        dejaTraite.add(tmp);

      }

      for(int i = 0; i < 4; i++){

        if(tmp.getMurs()[i] == false){

          if(dejaTraite.contains(tmp.getVoisins()[i]) == false && tmp.getVoisins()[i] != null){

            if(tmp.getVoisins()[i].getEtat() == Case.EtatCase.Arrivee){

              Case par = parents.get(tmp);
              par.setEtat(Case.EtatCase.Chemin);

              do{

                par = parents.get(par);
                par.setEtat(Case.EtatCase.Chemin);

              }while(par.getEtat() != Case.EtatCase.Depart);

              aTraiter.clear();

            }

          parents.put(tmp.getVoisins()[i], tmp);
          aTraiter.add(tmp.getVoisins()[i]);
          tmp.getVoisins()[i].setEtat(Case.EtatCase.Selection);

          }

        }

      }

      aTraiter.remove(tmp);


      maGrille.ajouterTempsReso(System.nanoTime() - h1);
      maGrille.finEtape();

    }

    maGrille.attendreEtape();
    maGrille.finReso();
    maGrille.finEtape();

  }

  public int[] trouverDepart(){

    int[] ret = new int[2];

    for(int i = 0; i < maGrille.getLargeur(); i++){

      for(int j = 0; j < maGrille.getHauteur(); j ++){

        if(maGrille.getTableau()[i][j].getEtat() == Case.EtatCase.Depart){

          ret[0] = i;
          ret[1] = j;

          break;
        }

      }



    }

    return ret;

  }

}
