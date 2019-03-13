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

	private Generation[] gen = new Generation[5];

	private Thread algoGene;
	private Thread algoReso;


	private FenetreAffichage affichage;


	public FenetreParametre() {
		this.setTitle("Commande Labyrinthe");
		this.setSize(300,500);
		this.setLocation(200,200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		JPanel commande = new JPanel();
		commande.setBounds(50,50,200,500);
		commande.setLayout(null);
		commande.setBackground(new Color(83,109,254));


		JLabel generation = new JLabel("Generation");
		generation.setBounds(20,20,100,35);
		generation.setForeground(Color.white);
		Font font = new Font("Arial",Font.BOLD,18);
		generation.setFont(font);
		commande.add(generation);

		JLabel tailleh = new JLabel("Taille horizontale=");
		tailleh.setBounds(20,60,110,35);
		tailleh.setForeground(Color.white);
		commande.add(tailleh);

		tailleX = new JTextField(" ");
		tailleX.setBounds(130,60,40,30);
		commande.add(tailleX);

		JLabel taillev = new JLabel("Taille verticale=");
		taillev.setBounds(20,95,110,35);
		taillev.setForeground(Color.white);
		commande.add(taillev);

		tailleY = new JTextField(" ");
		tailleY.setBounds(130,95,40,30);
		commande.add(tailleY);



		String[] listeAlgoGen = { "ArbreBinaire", "UniciteChemin", "MarcAlgo", "Algo4", "Algo5" };
		algoGen = new JComboBox<String>(listeAlgoGen);
		algoGen.setSelectedIndex(0);
		algoGen.setBounds(20,145,90,30);
		algoGen.addActionListener(this);
		commande.add(algoGen);


		boutonGenerer = new JButton("Generation");
		boutonGenerer.setBounds(90,190,100,50);
		boutonGenerer.addActionListener(this);
		commande.add(boutonGenerer);

		JLabel resoudre = new JLabel("Resoudre");
		resoudre.setBounds(20,270,100,35);
		resoudre.setForeground(Color.white);
		resoudre.setFont(font);
		commande.add(resoudre);

		String[] listeAlgoRes = { "ArbreBinaire", "MarcAlgo", "uniciteChemin", "Algo4", "Algo5" };
		algoRes = new JComboBox<String>(listeAlgoRes);
		algoRes.setSelectedIndex(0);
		algoRes.setBounds(20,320,90,30);
		algoRes.addActionListener(this);
		commande.add(algoRes);

		boutonResoudre = new JButton("Resoudre");
		boutonResoudre.setBounds(90,370,100,50);
		boutonResoudre.addActionListener(this);
		commande.add(boutonResoudre);



		this.add(commande);
		this.setVisible(true);


	}


	public void actionPerformed(ActionEvent e) {


		if(e.getSource() == boutonGenerer) {


			int x = Integer.parseInt(tailleX.getText().trim());
			int y = Integer.parseInt(tailleY.getText().trim());

			Grille grille = new Grille(x,y);

			Generation algo = null;



			//Generation algo = new ArbreBinaire(grille);

			if(algoGen.getSelectedIndex()==0) {
				System.out.println("arbrebinaire");
				algo = new ArbreBinaire(grille);


			}
			if(algoGen.getSelectedIndex()==2) {
				algo = new MarcAlgo(grille);

			}

		/*	if(algoGen.getSelectedIndex()==1) {
				algo = new uniciteChemin(grille);

			}*/


			affichage = new FenetreAffichage(grille);

			algoGene = new Thread(algo);
			algoGene.start();


		}



	}

}
