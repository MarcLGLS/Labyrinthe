/**
*La fenêtre pour rentrer les paramétres de générations du labyrinthe
*
*/

// Chargement des bibliotheques Swing, AWT, et Event
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreParametre extends JFrame implements ActionListener {
	private JButton boutonGenerer;
	private JButton boutonResoudre;
	private JTextField valeurDensite;
	private JRadioButton algo1;
	private JRadioButton algo2;
	private JRadioButton algo3;
	private FenetreAffichage affichage;
	
	
	public FenetreParametre() {
		this.setTitle("Commande Labyrinthe");
		this.setSize(300,600);
		this.setLocation(200,200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// Création de la seconde fenêtre permettant l'affichage du labyrinthe, dans un premier temps invisible
		affichage = new FenetreAffichage(); 
		
		JPanel commande = new JPanel();
		commande.setBounds(50,50,200,500);
		commande.setLayout(null);
		commande.setBackground(Color.gray); 
		
		// Panneau generation 
		
		JPanel panneauGenerer = new JPanel();
		panneauGenerer.setBounds(50,20,150,250);
		panneauGenerer.setLayout(null);
		panneauGenerer.setBackground(Color.blue); 
		commande.add(panneauGenerer); 
		
		JLabel generation = new JLabel("Generation");
		generation.setBounds(20,20,80,35);
		generation.setForeground(Color.white);
		panneauGenerer.add(generation);
		
		JLabel densite = new JLabel("Densite =");
		densite.setBounds(20,50,80,35);
		densite.setForeground(Color.white);
		panneauGenerer.add(densite);
		
		valeurDensite = new JTextField(" ");
		valeurDensite.setBounds(20,80,60,30);
		panneauGenerer.add(valeurDensite);
		
		algo1 = new JRadioButton("algo 1"); 
		algo1.setSelected(false);
		algo1.setBounds(20,140,90,20);
		algo1.addActionListener(this);
		panneauGenerer.add(algo1);
		
		boutonGenerer = new JButton("Generation");
		boutonGenerer.setBounds(20,190,100,50);
		boutonGenerer.addActionListener(this);
		panneauGenerer.add(boutonGenerer);
		
		// Panneau Resoudre
		JPanel panneauResoudre = new JPanel();
		panneauResoudre.setBounds(50,300,150,250);
		panneauResoudre.setLayout(null);
		panneauResoudre.setBackground(Color.blue); 
		commande.add(panneauResoudre); 
		
		JLabel resoudre = new JLabel("Resoudre");
		resoudre.setBounds(20,20,80,35);
		resoudre.setForeground(Color.white);
		panneauResoudre.add(resoudre);
		
		algo2 = new JRadioButton("algo 2"); 
		algo2.setSelected(false);
		algo2.setBounds(20,110,90,20);
		algo2.addActionListener(this);
		panneauResoudre.add(algo2);
		
		algo3 = new JRadioButton("algo 3"); 
		algo3.setSelected(false);
		algo3.setBounds(20,140,90,20);
		algo3.addActionListener(this);
		panneauResoudre.add(algo3);
		
		boutonResoudre = new JButton("Resoudre");
		boutonResoudre.setBounds(20,190,100,50);
		boutonResoudre.addActionListener(this);
		panneauResoudre.add(boutonResoudre);
		
		
		
		
		
		
		
		
		this.add(commande);
		this.setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == algo1) {
				System.out.println("algo1");
		}
		
		if(e.getSource() == algo2) {
				System.out.println("algo2");
		}
		
		if(e.getSource() == algo3) {
				System.out.println("algo3");
		}
		
		if(e.getSource() == boutonGenerer) {
				System.out.println("boutonGenerer");
				affichage.setVisible(true);
		}
		
		if(e.getSource() == boutonResoudre) {
				System.out.println("boutonResoudre");
		}
		
	}

}
