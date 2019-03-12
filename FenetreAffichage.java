/**
*La fenêtre pour afficher le labyrinthe
*
*/

// Chargement des bibliotheques Swing, AWT
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class FenetreAffichage extends JFrame implements ActionListener{

	private Grille grille;
	private JButton boutonEtape;
	private JPanel racine;
	private PanelLabyrinthe lab;

  private Timer timerEtape;

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


    play = new JButton("Go !");
    play.addActionListener(this);
    racine.add(play, BorderLayout.WEST);

    pause = new JButton("Pause !");
    pause.addActionListener(this);
    racine.add(pause, BorderLayout.EAST);

    timerEtape = null;

		boutonEtape = new JButton("Prochaine etape");
		boutonEtape.addActionListener(this);

		racine.add(boutonEtape, BorderLayout.NORTH);

		racine.add(lab, BorderLayout.CENTER);

		JTextField info = new JTextField("La case d'arrivée est la case rouge");

		racine.add(info, BorderLayout.SOUTH);

		this.setContentPane(racine);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e){

    System.out.println(e.getSource());

		if(e.getSource() == boutonEtape){

			grille.attendreFenetre();

			racine.revalidate();
			racine.repaint();

			this.setContentPane(racine);
			synchronized(grille){

				grille.notifyAll();

			}

		}

    if(e.getSource() == play && timerEtape == null){

      timerEtape = new Timer(200, this);
      timerEtape.start();

    }

    if(e.getSource() == pause && timerEtape != null){

      timerEtape.stop();
      timerEtape = null;

    }

    if(e.getSource() == timerEtape){

      grille.attendreFenetre();

      racine.revalidate();
      racine.repaint();

      this.setContentPane(racine);
      synchronized(grille){

        grille.notifyAll();

      }

    }

	}

}
