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
		affichage.setLayout(new BorderLayout());
		affichage.setBackground(Color.white);

		JPanel labyrinthe = new JPanel();
		labyrinthe.setLayout(new GridLayout(largeur, hauteur));

		for(int i = 0; i < largeur; i ++){

			for(int j = 0; j< hauteur; j++){

				labyrinthe.add(Case[i][j]);

			}

		}

		racine.add(labyrinthe, BorderLayout.CENTER);

		this.setContentPane(racine);
		this.setVisible(false);

	}
}
