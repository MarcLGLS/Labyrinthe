import javax.swing.*;
import java.awt.*;

/*
Cette classe est destinée à l'affichage du labyrinthe. Elle étends JPanel et redéfinit sa méthode paintComponent pour permettre l'affichage des cases.
*/

public class PanelLabyrinthe extends JPanel {


  private Grille maGrille; //la grille à dessiner
  private Case[][] tableau; //Le tableau de case associé à la grille
  private float nbreCouleur;

  public PanelLabyrinthe(Grille grille){

    maGrille = grille;
    tableau = maGrille.getTableau();
    this.setBackground(Color.white); //On peint le fond en blanc
    nbreCouleur = maGrille.getLargeur() * maGrille.getHauteur() -1;


  }


  public void paintComponent(Graphics g){

    Graphics2D g2 = (Graphics2D) g; //On cast notre objet graphics en objet graphics2d : plus de fonctionnalité, notamment la largeur des traits dessinés.

    g2.setStroke(new BasicStroke(5)); //Grâce à l'objet graphics2d on peut augmenter la largeur du pinceau utilisé pour tracer les traits

    int largeur = maGrille.getLargeur();
    int hauteur = maGrille.getHauteur();


    int largFen = this.getWidth(); //On recupère la largeur et hauteur du panel pour savoir quelle place chaque case doit prendre
    int hautFen = this.getHeight();

    int largCase = largFen / largeur;
    int hautCase = hautFen / hauteur;

    /*
    La représentation graphique de l'algorithme "UniciteChemin" se fait via des zones de couleurs, au contraires des deux autres algorithmes proposés.
    On cherche donc a savoir si l'attribut zone (z) des cases a été modifié, ce qui veut dire qu'il faut changer de méthode d'affichage.
    */
    boolean dessinZone = (tableau[0][0].z == -1)? false : true;

    //Boucle principale qui parcourt tout le tableau de case pour le dessiner

    for(int y = 0; y < hauteur; y++){

      for(int x = 0; x < largeur; x++){


        int actuelX = largCase * x; //On calcule les coordonnées actuelles du coin supérieur gauche de la case
        int actuelY = hautCase * y;


        if(dessinZone == false || maGrille.verifGene() == true){

          /*
          Si l'algorithme de generation par zone n'est pas selectionné, on récupère alors l'état de la case en train d'être dessinée,
          et on change la couleur du fond en fonction.
          */

          if(tableau[x][y].getEtat() == Case.EtatCase.Normal) g2.setColor(Color.white);
          if(tableau[x][y].getEtat() == Case.EtatCase.Selection) g2.setColor(Color.orange);
          if(tableau[x][y].getEtat() == Case.EtatCase.Arrivee) g2.setColor(Color.red);
          if(tableau[x][y].getEtat() == Case.EtatCase.Depart) g2.setColor(Color.green);
  		    if(tableau[x][y].getEtat() == Case.EtatCase.Chemin) g2.setColor(Color.cyan);


          //Dessin du fond de la case
          g2.fillRect(actuelX, actuelY, largCase, hautCase);

        }else{

          /*
          Si on doit dessiner la grille en fonction des zones, ont recupère l'attibut z de la case et on change sa couleur en fonction.
          La parametre hue représente la teinte de la couleur, il varie de 0 à 360. Le parametre saturation (sat) représente l'intensité de la couleur (compris entre 0 et 1).
          */

          float hue = (float)(tableau[x][y].z/360f);
          float sat = (float)(tableau[x][y].z/nbreCouleur);

          //On transforme la couleur en fonction de son hue et saturation, en couleur rgb.
          Color couleurZone = Color.getHSBColor(hue,sat,1f);

          //On repeint le fond de la case
          g2.setColor(couleurZone);
          g2.fillRect(actuelX, actuelY, largCase, hautCase);

        }

        //Couleur des murs
        g2.setColor(Color.black);

        //On récupère les murs de chaque case et on les dessine.

        boolean[] murs = tableau[x][y].getMurs();

        if(murs[0] == true) g2.drawLine(actuelX, actuelY, actuelX, actuelY + hautCase);

    		if(murs[1] == true) g2.drawLine(actuelX, actuelY, actuelX + largCase, actuelY);

    		if(murs[2] == true) g2.drawLine(actuelX + largCase, actuelY, actuelX + largCase, actuelY + hautCase);

    		if(murs[3] == true) g2.drawLine(actuelX + largCase, actuelY + hautCase, actuelX, actuelY + hautCase);

      }

    }

  }

}
