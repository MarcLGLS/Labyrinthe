import java.util.*;

public class RechercheProfondeur extends Resolution{

  private HashMap<Case, Case> parents;

  private ArrayList<Case> dejaTraite;

  private boolean fin = false;

  private long h1;

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
      h1 = System.nanoTime();
      resoudreRec(maGrille.getTableau()[coord[0]][coord[1]]);

    }

    public void resoudreRec(Case c){

    if(fin == true) return;

    maGrille.ajouterTempsReso(System.nanoTime()-h1);
    maGrille.finEtape();
    maGrille.attendreEtape();
    h1 = System.nanoTime();

    dejaTraite.add(c);
    //System.out.println(c.getEtat());



    if(c.getEtat() == Case.EtatCase.Arrivee){

      peindreChemin(c);
      fin = true;
      return;

    }else{

      c.setEtat(Case.EtatCase.Selection);

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

  public void peindreChemin(Case c){

    Case par = c;

    do{

      par = parents.get(par);
      if(par != null)
      par.setEtat(Case.EtatCase.Chemin);

    }while(par.getEtat() != Case.EtatCase.Depart);

    for(Case a : dejaTraite){

      if(a.getEtat() != Case.EtatCase.Chemin){

        a.setEtat(Case.EtatCase.Normal);

      }

    }

    maGrille.finReso();
    maGrille.finEtape();


  }

}
