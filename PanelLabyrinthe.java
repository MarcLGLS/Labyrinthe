import javax.swing.*;
import java.awt.*;

public class PanelLabyrinthe extends JPanel {

  private Grille maGrille;
  private Case[][] tableau;

  public PanelLabyrinthe(Grille grille){

    maGrille = grille;
    tableau = maGrille.getTableau();
    this.setBackground(Color.white);

  }


  public void paintComponent(Graphics g){

    Graphics2D g2 = (Graphics2D) g;

    g2.setStroke(new BasicStroke(5));

    int largeur = maGrille.getLargeur();
    int hauteur = maGrille.getHauteur();

    int largFen = this.getWidth();
    int hautFen = this.getHeight();

    int largCase = largFen / largeur;
    int hautCase = hautFen / hauteur;

    for(int y = 0; y < hauteur; y++){

      for(int x = 0; x < largeur; x++){


        int actuelX = largCase * x;
        int actuelY = hautCase * y;

        if(tableau[x][y].getEtat() == Case.EtatCase.Normal) g2.setColor(Color.white);
        if(tableau[x][y].getEtat() == Case.EtatCase.Selection) g2.setColor(Color.orange);
        if(tableau[x][y].getEtat() == Case.EtatCase.Arrivee) g2.setColor(Color.red);
        if(tableau[x][y].getEtat() == Case.EtatCase.Depart) g2.setColor(Color.green);
		    if(tableau[x][y].getEtat() == Case.EtatCase.Chemin) g2.setColor(Color.cyan);

        System.out.println(tableau[x][y].getEtat());

        g2.fillRect(actuelX, actuelY, largCase, hautCase);

        g2.setColor(Color.black);

        boolean[] murs = tableau[x][y].getMurs();

        if(murs[0] == true) g2.drawLine(actuelX, actuelY, actuelX, actuelY + hautCase);

    		if(murs[1] == true) g2.drawLine(actuelX, actuelY, actuelX + largCase, actuelY);

    		if(murs[2] == true) g2.drawLine(actuelX + largCase, actuelY, actuelX + largCase, actuelY + hautCase);

    		if(murs[3] == true) g2.drawLine(actuelX + largCase, actuelY + hautCase, actuelX, actuelY + hautCase);

      }

    }


  }

}
