import java.util.*;
public abstract class Generation implements Runnable{

  protected Grille maGrille;
  protected LinkedList<Long> temps;

  public abstract void generer();

  public abstract void run();

  public double tempsTotal() {
	  double somme = 0;
	  for(int i =0; i< temps.size() ; i++) {
		  somme =+ temps.get(i);
	  }
	  return somme;
  }


  //public void toString();

}
