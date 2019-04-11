import java.util.*;
public abstract class Resolution implements Runnable{
 protected Grille maGrille;



 public abstract void resoudre();

 public abstract void run();

 //méthode utilisée pour touver les coordonées du départ
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

 //méthode utilisée pour peindre le chemin de la case de départ à la case d'arrivée

 public void peindreChemin(Case c,  HashMap<Case, Case> parents, AbstractList<Case> aTraiter, AbstractList<Case> dejaTraite){

   Case par = c; //La case où le chemin commence

   //Cette boucle permet de remonter parent par parent les cases qui font partie du chemin

   do{

     par = parents.get(par);
     if(par != null)
     par.setEtat(Case.EtatCase.Chemin);

   }while(par.getEtat() != Case.EtatCase.Depart);

   //Il faut ensuite changer le reste des cases qui étaient dans un état de sélection en état normal

   for(Case a : aTraiter){

     if(a.getEtat() != Case.EtatCase.Chemin){

       a.setEtat(Case.EtatCase.Normal);

     }

   }

   for(Case a : dejaTraite){

     if(a.getEtat() != Case.EtatCase.Chemin){

       a.setEtat(Case.EtatCase.Normal);

     }

   }

   //On marque que la résolution est terminée ainsi que l'étape actuelle (cette méthode est utilisée dans l'algorithme)

   maGrille.finReso();
   maGrille.finEtape();


 }
}
