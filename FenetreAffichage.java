/**
*La fenêtre pour afficher le labyrinthe
*
*/

// Chargement des bibliotheques Swing, AWT
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;


public class FenetreAffichage extends JFrame implements ActionListener, ChangeListener{

	private Grille grille; // la grille représentant notre labyrinthe

	//Déclaration des composants grpahiques nécessaires au bon affichage de la fenêtre
	private JButton boutonEtape;
	private JPanel racine;
	private PanelLabyrinthe lab;
	private JLabel infoTps;
	private Timer timerEtape;
	private JSlider tempsAttente;
	private JButton play;
	private JButton pause;

	public DecimalFormat formatTemps = new DecimalFormat("#.###");	//Le format utilisé pour afficher le temps, on évite ainsi d'avoir trop de chiffres décimaux ce qui rend l'affichage impossible
	private int tempsEtape; //Le temps à attendre entre chaque étape

	public FenetreAffichage(Grille grille) {

		this.grille = grille;
		int largeur = grille.getLargeur();
    int hauteur = grille.getHauteur();
    Case[][] tableau = grille.getTableau(); //La tableau de case

		lab = new PanelLabyrinthe(grille); //Objet PanelLabyrinthe qui sert à afficher le labyrinthe en lui même

		this.setTitle("Affichage Labyrinthe");
		this.setSize(700,700);
		this.setLocation(700,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		racine = new JPanel();//La racine de la JFrame : c'est le ContentPane
		BorderLayout racineLayout = new BorderLayout(); //Layout utilisé pour arranger nos composants
		racineLayout.setVgap(10); //Les espacements entre composants
		racineLayout.setHgap(10);
		racine.setLayout(racineLayout);
		racine.setBackground(Color.white);

		JPanel panelControle = new JPanel(); //Le JPanel qui contient tous les composants de contrôle (play, pause, réglage timer)

    play = new JButton("Go !"); //Le bouton qui permet de lancer le timer
    play.addActionListener(this);
    panelControle.add(play);

    pause = new JButton("Pause !"); //Le bouton qui permet d'arrêter le timer
    pause.addActionListener(this);
    panelControle.add(pause);

    timerEtape = null; //On instancie le timer à null pour le moment

		boutonEtape = new JButton("Prochaine etape"); //Ce bouton sert à avancer d'une seule étape
		boutonEtape.addActionListener(this);
		panelControle.add(boutonEtape);

		/*Le slider utilisé pour faire varier le temps d'attente entre chaque étape :
		*Entre 500ms et 1ms, avec 200ms comme valeur par défaut
		*/

		tempsAttente = new JSlider(1, 500, 200);
		tempsEtape = 200;
		tempsAttente.setPaintLabels(true);
		tempsAttente.setPaintTicks(true);
		tempsAttente.addChangeListener(this);//Le listener utilisé pour écouter les changements du slider
		panelControle.add(tempsAttente);

		infoTps = new JLabel(); //JPanel dédié à l'affichage du temps


		//Ajout des JPanels dans la racine
		racine.add(infoTps, BorderLayout.WEST);

		racine.add(panelControle, BorderLayout.NORTH);

		racine.add(lab, BorderLayout.CENTER);

		//On déclare la racine comme la ContentPane de notre fenêtre
		this.setContentPane(racine);
		this.setVisible(true);

	}

	/*redéfinition de la méthode paint
	Necéssaire pour afficher les changements du temps mis par l'algorithme à chaque fois que la fenêtre se redessine
	*/
	public void paint(Graphics g){

		String txt1 = new String();
		String txt2 = new String();

		txt1= ("Temps generation : " + formatTemps.format(grille.getTempsGene()) + "ms."); // Le temps de génération

		txt2= ("Temps resolution : " + formatTemps.format(grille.getTempsReso()) + "ms."); //Le temps de résolution

		infoTps.setText("<html> "+txt1+" <br> "+txt2+" </html>"); //On formate en html pour pouvoir faire un retour à la ligne


		infoTps.setPreferredSize(new Dimension(200,50)); //Définition de la taille préférée pour éviter l'effet de tremblement de la fenêtre à chaque fois que le texte change de longueur


		super.paint(g); //Le reste de la fenêtre se redessine normalement

	}

	public void actionPerformed(ActionEvent e){ //Implémentation de ActionListener


		if(e.getSource() == boutonEtape || e.getSource() == timerEtape){ //Si le timer à envoyer un nouvel évent ou si l'utiliser à appuyer sur le bouton nouvelleEtape

			grille.attendreFenetre(); //On attend que la grille soit libre (que l'algorithme ait fini son étape)

			/*
			On repeint toute la fenêtre. Nécessaire de tout repeindre sinon il y a des problèmes d'artefacts visuels avec des gros labyrinthe
			et pour pouvoir afficher le temps.
			*/
			repaint();

			grille.finEtape(); //On signale à la grille que la fenêtre est à jour, on peut avancer d'une étape dans l'algorithme

		}

    if(e.getSource() == play && timerEtape == null){ //Si aucun timer n'est en place et que l'utilisateur appuie sur "Go" il faut en construire un

      timerEtape = new Timer(tempsEtape, this); //Le délai du timer est retenu dans tempsEtape
      timerEtape.start();

    }

    if(e.getSource() == pause && timerEtape != null){ //Si un timer est déjà en place et que l'utilisateur appuie sur pause

      timerEtape.stop();//On arrête le timer
      timerEtape = null;//On le met à null pour s'assurer qu'il n'envoie aucun événement qui pourrait bloquer la fenêtre

    }


	}

	public void stateChanged(ChangeEvent e){ //Implémentation de ChangeListener


		if(e.getSource() == tempsAttente){ //Si notre slider a été touché

			tempsEtape = tempsAttente.getValue(); //On récupère le temps défini

			if(timerEtape != null){

				timerEtape.setDelay(tempsEtape); //Si un timer est déjà en place on change son délai

			}

		}

	}

	public void desactiverTimer(){ //Méthode utilisé pour s'assurer qu'aucun timer ne tourne en fond et lance des events

		if(timerEtape != null){

			timerEtape.stop();
			timerEtape = null;

		}

	}

}
