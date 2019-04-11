
import java.util.*;

/*

  Cette classe abstraite est utilisee pour representer tous les algorithmes de generation, et faciliter leur manipulation.
  Elle implemente l'interface Runnable pour permettre un controle sur le thread de generation (le mettre en pause, le ralentir).

*/

public abstract class Generation implements Runnable{

  //Objet grille dans lequel la generation va s'effectuer
  protected Grille maGrille;

  //Methode de generation
  public abstract void generer();

  //Implementation de l'interface Runnable
  public abstract void run();


}
