/**
*La fenêtre pour afficher le labyrinthe
*
*/

// Chargement des bibliotheques Swing, AWT
import javax.swing.*;
import java.awt.*;


public class FenetreAffichage extends JFrame {
	
	public FenetreAffichage() {
		this.setTitle("Affichage Labyrinthe");
		this.setSize(600,600);
		this.setLocation(700,200);
		this.setResizable(false);
		
		JPanel affichage = new JPanel();
		affichage.setBounds(0,0,500,500);
		affichage.setLayout(null);
		affichage.setBackground(Color.white);
		
		this.add(affichage);	
		this.setVisible(false); 
		
	}
}
