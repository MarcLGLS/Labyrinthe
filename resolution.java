import java.util.*;
public abstract class Resolution implements Runnable{
 protected Grille maGrille;
 
 protected LinkedList<Long> temps;
 
 public abstract void resoudre();

 public abstract void run();
 
 public double tempsTotal() {
	  double somme = 0;
	  for(int i =0; i< temps.size() ; i++) {
		  somme =+ temps.get(i);
	  }
	  return somme;
  }

}
