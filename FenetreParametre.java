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
	private JTextField tailleX;
	private JTextField tailleY;
	private JComboBox<String> algoGen;
	private JComboBox<String> algoRes;
	public Generation algo;

	private Generation[] gen = new Generation[5];

	// Déclaration de fils d'éxécutions pour les algorithmes de générations et de résolutions afin de pouvoir les séquencer.
	private Thread algoGene;
	private Thread algoReso;

	private Grille grille;

	private FenetreAffichage affichage;


	public FenetreParametre() {

		// Initialisation de FenetreParametre.
		this.setTitle("Commande Labyrinthe");
		this.setSize(300,500);
		this.setLocation(200,200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// Initialisation de JPanel commande.
		JPanel commande = new JPanel();
		commande.setBounds(50,50,200,500);
		commande.setLayout(null);
		commande.setBackground(new Color(83,109,254));



		//Initialisation de la partie génération de la fenêtre de paramétrage.

		// Initialisation du JLabel generation.
		JLabel generation = new JLabel("Generation");
		generation.setBounds(20,20,100,35);
		generation.setForeground(Color.white);
		Font font = new Font("Arial",Font.BOLD,18);
		generation.setFont(font);
		commande.add(generation);

		//Initialisation du JLabel tailleh.
		JLabel tailleh = new JLabel("Taille horizontale=");
		tailleh.setBounds(20,60,110,35);
		tailleh.setForeground(Color.white);
		commande.add(tailleh);

		//Initialisation du JTextField tailleX.
		tailleX = new JTextField(" ");
		tailleX.setBounds(130,60,40,30);
		commande.add(tailleX);

		//Initialisation du JLabel taillev.
		JLabel taillev = new JLabel("Taille verticale=");
		taillev.setBounds(20,95,110,35);
		taillev.setForeground(Color.white);
		commande.add(taillev);

		//Initialisation du JTextField tailleY.
		tailleY = new JTextField(" ");
		tailleY.setBounds(130,95,40,30);
		commande.add(tailleY);


		//Initialisation du JComboBox listeAlgoGen
		String[] listeAlgoGen = { "ArbreBinaire", "UniciteChemin", "MarcAlgo", "Ilot"};
		algoGen = new JComboBox<String>(listeAlgoGen);
		algoGen.setSelectedIndex(0);
		algoGen.setBounds(20,145,130,30);
		algoGen.addActionListener(this);
		commande.add(algoGen);

		//Initialisation du JButton boutonGenerer.
		boutonGenerer = new JButton("Generation");
		boutonGenerer.setBounds(90,190,100,50);
		boutonGenerer.addActionListener(this);
		commande.add(boutonGenerer);



		//Initialisation de la partie résolution de la fenêtre de paramétrage.

		//Initialisation du JLabel resoudre.
		JLabel resoudre = new JLabel("Resoudre");
		resoudre.setBounds(20,270,100,35);
		resoudre.setForeground(Color.white);
		resoudre.setFont(font);
		commande.add(resoudre);

		//Initialisation du JComboBox algoRes.
		String[] listeAlgoRes = { "ResolutionAleatoire","ResolutionDroite", "RechercheLargeur", "AlgoEmilien"};
		algoRes = new JComboBox<String>(listeAlgoRes);
		algoRes.setSelectedIndex(0);
		algoRes.setBounds(20,320,130,30);
		algoRes.addActionListener(this);
		commande.add(algoRes);

		//Initialisation du JButton boutonResoudre.
		boutonResoudre = new JButton("Resoudre");
		boutonResoudre.setBounds(90,370,100,50);
		boutonResoudre.addActionListener(this);
		commande.add(boutonResoudre);



		this.add(commande);
		this.setVisible(true);


	}

/**Méthode permettant l'interraction avec les JButton, JTextField et JComboBox.
 *
 */

	public void actionPerformed(ActionEvent e) {

		// Gestion de la partie génération
		if(e.getSource() == boutonGenerer) {

			//Récupération des dimensions de la grille.
			int x = Integer.parseInt(tailleX.getText().trim());
			int y = Integer.parseInt(tailleY.getText().trim());

			grille = new Grille(x,y); //Création de la grille.

			Generation algo = null; //?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????

			// Lancement de l'algorithme de résolution en fonction de celui-choisi dans le JComboBox.

			if(algoGen.getSelectedIndex()==0) {
				algo = new ArbreBinaire(grille); // Lancement de l'algorithme arbre binaire.
			}

			if(algoGen.getSelectedIndex()==2) {
				algo = new RecursiviteAleatoire(grille); // Lancement de l'algorithme récursivité aléatoire.
			}

			if(algoGen.getSelectedIndex()==1) {
				algo = new uniciteChemin(grille); // Lancement de l'algorithme unicité chemin.
			}

            if(algoGen.getSelectedIndex()==3) {
                    algo = new Ilot(grille); // Lancement de l'algorithme ilot.
			}


			affichage = new FenetreAffichage(grille); // Lancement de la fenêtre d'affichage du labyrinthe.

			algoGene = new Thread(algo); // Instanciation du fil de génération.
			algoGene.start(); // Démarage du fil d'exécution de la génération.


		}
		// Gestion de la partie résolution
		if(e.getSource() == boutonResoudre && grille.verifGene() == true){

			Resolution algo = null;

			if(algoRes.getSelectedIndex()==0) {
				algo = new ResolutionAleatoire(grille); // Lancement de l'algorithme resolution aléatoire.
			}

			if(algoRes.getSelectedIndex()==1) {
				algo = new ResolutionDroite(grille); // Lancement de l'algorithme resolution droite.
			}

			if(algoRes.getSelectedIndex()==2) {
				algo = new RechercheLargeur(grille); // Lancement de l'algorithme recherche largeur.
			}

			if(algoRes.getSelectedIndex()==3) {
				algo = new RechercheProfondeur(grille); // Lancement de l'algorithme recherche profondeur.
			}

			if(algoReso == null || algoReso.getState() == Thread.State.TERMINATED){ //On vérifie qu'aucune résolution n'est en cours

				grille.reinitialiser(); // On réinitialise la grille à son état avant résolution
				algoReso = new Thread(algo); // Instanciation du fil de résolution.
				System.out.println(algoReso.getState());
				algoReso.start(); // Démarage du fil d'exécution de la résolution.

			}

		}



	}

}
