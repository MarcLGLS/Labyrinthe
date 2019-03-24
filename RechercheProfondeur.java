import java.util.*;

public class RechercheProfondeur extends Resolution{

  private HashMap<Case, Case> parents;

  private ArrayList<Case> dejaTraite;

  private boolean fin = false;

  public RechercheProfondeur(Grille g){

    this.maGrille = g;

    dejaTraite = new ArrayList<Case>();
    parents = new HashMap<Case, Case>();

  }

  public void run(){


    resoudre();

  }

    public void resoudre(){

      int[] coord = trouverDepart();
      maGrille.debutReso();
      maGrille.attendreEtape();
      resoudreRec(maGrille.getTableau()[coord[0]][coord[1]]);
      maGrille.attendreEtape();
      maGrille.finReso();
      maGrille.finEtape();


    }

    public void resoudreRec(Case c){

    maGrille.finEtape();
    maGrille.attendreEtape();

    //System.out.println(c.getEtat());

    if(fin == true) return;

    if(c.getEtat() == Case.EtatCase.Arrivee){

      Case par = c;

      do{

        par = parents.get(par);
        par.setEtat(Case.EtatCase.Chemin);

      }while(par.getEtat() != Case.EtatCase.Depart);

      fin = true;
      return;

    }else{

      c.setEtat(Case.EtatCase.Selection);
      dejaTraite.add(c);

    }

    Case[] voisinsValides = new Case[4];

    for(int i =0; i < 4; i++){

      if(c.getMurs(i) == false){

        if(c.getVoisins()[i] != null && dejaTraite.contains(c.getVoisins()[i]) == false){

          parents.put(c.getVoisins()[i], c);

          //System.out.println(i);
          voisinsValides[i] = c.getVoisins()[i];

        }

      }

    }

    voisinsValides = melangeurVoisin(voisinsValides);

    for(int j = 0; j < 4; j++){

      if(voisinsValides[j] != null){

        resoudreRec(voisinsValides[j]);

      }

    }


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
