import javax.swing.*;
import java.awt.*;

public class InfoTemps extends JTextArea{

  private Grille maGrille;

  public InfoTemps(Grille grille){

    //super();
    System.out.println("d2");
    maGrille = grille;



  }


  public void paintComponent(Graphics g){

    String txt = new String();

    txt = ("Temps total génération : " + Long.toString(maGrille.getTempsGene()) + "ms.\n");

    this.append(txt);

    txt = ("Temps total résolution : " + Long.toString(maGrille.getTempsReso()) + "ms.\n");

    this.append(txt);

    System.out.println(txt);

    System.out.println("d1");

    super.paintComponent(g);
  }

}
