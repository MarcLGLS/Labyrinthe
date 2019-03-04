/**
*La fenêtre pour rentrer les paramétres de générations du labyrinthe
*
*/

// Chargement des bibliotheques Swing, AWT, et Event
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreParametre extends JFrame implements ActionListener {

	private FenetreAffichage affichage;
	private JButton generer;

	public FenetreParametre() {

		this.setTitle("Commande Labyrinthe");
		this.setSize(300,600);
		this.setLocation(200,200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contenu = new JPanel();
		//contenu.setLayout(new BoxLayout());

		generer = new JButton("Go !");
		generer.addActionListener(this);

		contenu.add(generer);

		this.setContentPane(contenu);
		this.setVisible(true);
		Grille grille = new Grille(20,20);
		Generation algo = new MarcAlgo(grille);
		algo.generer();
		affichage = new FenetreAffichage(grille);


	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == generer) {

			Grille grille = new Grille(20,20);
			Generation algo = new MarcAlgo(grille);
			algo.generer();
			affichage = new FenetreAffichage(grille);

		}

	}

}
