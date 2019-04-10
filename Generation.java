
import java.util.*;

/*

  Cette classe abstraite est utilisee pour representer tous les algorithmes de generation, et faciliter leur manipulation.
  Elle implemente l'interface Runnable pour permettre un controle sur le thread de generation (le mettre en pause, le ralentir).

*/

public abstract class Generation implements Runnable{

  //Objet grille dans lequel la generation va s'effectuer
  protected Grille maGrille;

  //Liste du temps de chaque boucle de labyrinthe
  protected LinkedList<Long> temps;

  //Methode de generation
  public abstract void generer();

  //Implementation de l'interface Runnable
  public abstract void run();

  //Calcul du temps total de generation
  public double tempsTotal() {
	  double somme = 0;
	  for(int i =0; i< temps.size() ; i++) {
		  somme =+ temps.get(i);
	  }
	  return somme;
  }

}
