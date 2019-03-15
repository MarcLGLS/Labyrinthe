/**
*La fenêtre pour afficher le labyrinthe
*
*/

// Chargement des bibliotheques Swing, AWT
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


public class FenetreAffichage extends JFrame implements ActionListener, ChangeListener{

	private Grille grille;
	private JButton boutonEtape;
	private JPanel racine;
	private PanelLabyrinthe lab;

  private Timer timerEtape;
	private int tempsEtape;

	private JSlider tempsAttente;

  private JButton play;
  private JButton pause;


	public FenetreAffichage(Grille grille) {
		this.grille = grille;
		int largeur = grille.getLargeur();
    int hauteur = grille.getHauteur();
    Case[][] tableau = grille.getTableau();

		lab = new PanelLabyrinthe(grille);


		this.setTitle("Affichage Labyrinthe");
		this.setSize(600,600);
		this.setLocation(700,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		racine = new JPanel();
		BorderLayout racineLayout = new BorderLayout();
		racineLayout.setVgap(10);
		racineLayout.setHgap(10);
		racine.setLayout(racineLayout);
		racine.setBackground(Color.white);

		/*
		JPanel labyrinthe = new JPanel();
		labyrinthe.setBackground(Color.white);
		labyrinthe.setLayout(new GridLayout(hauteur,largeur));

		for(int i = 0; i < hauteur; i ++){

			for(int j = 0; j< largeur; j++){

				labyrinthe.add(tableau[j][i]);

			}

		}*/

		JPanel panelControle = new JPanel();



    play = new JButton("Go !");
    play.addActionListener(this);
    panelControle.add(play);

    pause = new JButton("Pause !");
    pause.addActionListener(this);
    panelControle.add(pause);

    timerEtape = null;

		boutonEtape = new JButton("Prochaine etape");
		boutonEtape.addActionListener(this);
		panelControle.add(boutonEtape);

		tempsAttente = new JSlider(1, 500, 200);
		tempsEtape = 200;
		tempsAttente.setPaintLabels(true);
		tempsAttente.setPaintTicks(true);
		tempsAttente.addChangeListener(this);
		panelControle.add(tempsAttente);


		racine.add(panelControle, BorderLayout.NORTH);

		racine.add(lab, BorderLayout.CENTER);

		JTextField info = new JTextField("La case d'arrivée est la case rouge");

		racine.add(info, BorderLayout.SOUTH);

		this.setContentPane(racine);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e){


		if(e.getSource() == boutonEtape || e.getSource() == timerEtape){

			grille.attendreFenetre();

			racine.revalidate();
			racine.repaint();

			this.setContentPane(racine);
			grille.finEtape();

		}

    if(e.getSource() == play && timerEtape == null){

      timerEtape = new Timer(tempsEtape, this);
      timerEtape.start();

    }

    if(e.getSource() == pause && timerEtape != null){

      timerEtape.stop();
      timerEtape = null;

    }


	}

	public void stateChanged(ChangeEvent e){


		if(e.getSource() == tempsAttente){

			tempsEtape = tempsAttente.getValue();

			if(timerEtape != null){

				timerEtape.setDelay(tempsEtape);

			}

		}

	}

}
