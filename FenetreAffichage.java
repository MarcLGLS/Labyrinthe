/**
*La fenêtre pour afficher le labyrinthe
*
*/

// Chargement des bibliotheques Swing, AWT
import javax.swing.*;
import java.awt.*;


public class FenetreAffichage extends JFrame {

	private Grille grille;

	public FenetreAffichage(Grille grille) {
		this.grille = grille;
		int largeur = grille.getLargeur();
    int hauteur = grille.getHauteur();
    Case[][] tableau = grille.getTableau();


		this.setTitle("Affichage Labyrinthe");
		this.setSize(600,600);
		this.setLocation(700,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel racine = new JPanel();
		BorderLayout racineLayout = new BorderLayout();
		racineLayout.setVgap(10);
		racineLayout.setHgap(10);
		racine.setLayout(racineLayout);
		racine.setBackground(Color.white);

		JPanel labyrinthe = new JPanel();
		labyrinthe.setBackground(Color.white);
		labyrinthe.setLayout(new GridLayout(hauteur,largeur));

		for(int i = 0; i < hauteur; i ++){

			for(int j = 0; j< largeur; j++){

				labyrinthe.add(tableau[j][i]);

			}

		}

		racine.add(labyrinthe, BorderLayout.CENTER);

		JTextField info = new JTextField("La case d'arrivée est la case rouge");

		racine.add(info, BorderLayout.SOUTH);

		this.setContentPane(racine);
		this.setVisible(true);

	}
}
