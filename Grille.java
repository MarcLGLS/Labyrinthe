/** Objet grille
*Cet objet est utilisé pour stocker l'état du labyrinthe. Il fait le lien entre la partie IHM et les algorithmes de générations/Résolutions.
*
*/

public class Grille {
	private Case tableau[][]; //Le tableau de case représentant le layrinthe
	private Case tableauSecours[][];//Tableau de "secours" utilisé pour réinitialiser le labyrinthe
	private int largeur, hauteur;//Largeur, hauteur du labyrinthe

	private boolean nouvEtape = false;//Indique si une nouvelle étape à été effectuée par le labyrinthe.

	/*
	Booleens utilisés pour savoir où se situe le programme.
	*/
	private boolean geneFinie = false;
	private boolean resoDebut = false;
	private boolean resoFinie = false;

	public Grille(int l, int h) {

		largeur = l;
		hauteur = h;

		tableau = new Case[largeur][hauteur];
		tableauSecours = new Case[largeur][hauteur];

		remplir(true); //On remplit le labyrinthe avec des cases fermées.
	}


	//Remplit le labyrinthe de cases et assignent les voisins.
	public void remplir(boolean avecMurs){

		for(int i = 0; i < tableau.length; i++){

			for(int j = 0; j < tableau[0].length; j++){

				tableau[i][j] = new Case(avecMurs);

			}

		}

		/*
		On assigne les voisins de la même manière que les murs : 0 gauche, 1 haut, 2 droite, 3 bas. Si une case n'a pas de voisin car elle se trouve sur un bord alors celui ci reste null.
		*/
		for(int i = 0; i < tableau.length; i++){

			for(int j = 0; j < tableau[0].length; j++){

				Case[] vois = new Case[4];

				if(i == 0){

					vois[0] = null;

				}else{

					vois[0] = tableau[i-1][j];

				}

				if(i == largeur -1){

						vois[2] = null;
				}else{

					vois[2] = tableau[i+1][j];

				}

				if(j == 0){

					vois[1] = null;

				}else{

					vois[1]= tableau[i][j-1];

				}

				if(j == hauteur-1){

					vois[3] = null;

				}else{

					vois[3] = tableau[i][j+1];

				}

				tableau[i][j].setVoisins(vois);

			}

		}

	}

	public int getLargeur(){

		return this.largeur;

	}

	public int getHauteur(){

		return this.hauteur;

	}

	//Retourne le tableau de cases
	public Case[][] getTableau(){

		return this.tableau;
	}

	/*
	Les méthodes synchronisées sont utilisées pour contrôler le déroulement des algorithmes.
	La génération/résolution prend place dans un thread différent que l'affichage.
	Le fonctionnement est le suivant : l'algorithme effectue une étape, il l'indique à la fenêtre par l'intermédiaire de cet objet
	et se met en attente. La fenêtre se redessine alors et indique à l'algorithme qu'une nouvelle étape peut être effectuée.

	Le mot clé synchronized permet de mettre le "lock" sur cet objet : le thread qui a le lock peut s'éxécuter.
	*/

	//méthode utilisée dans les algorithmes pour attendre une étape
	public synchronized void attendreEtape(){

		/*
		A ce moment précis, c'est l'algorithme qui a le lock sur la grille, il est le seul à pouvoir y accéder
		*/

		try{
		/*
		Le thread de l'algorithme relâche le lock et se met en attente. Il est encapsulé dans une boucle while pour éviter tout réveil intempestif.
		*/

			while(nouvEtape == false) wait();

		}catch(InterruptedException e) {e.printStackTrace();}


		nouvEtape = false; //On signale que l'étape a été commencée.

	}

	public synchronized void attendreFenetre(){

		/*
			A ce moment précis, c'est la fenêtre qui a le lock sur la grille, il est le seul à pouvoir y accéder
		*/

		try{

			/*
			Le thread de la fenêtre relâche le lock et se met en attente. Il est encapsulé dans une boucle while pour éviter tout réveil intempestif.
			*/
			while(nouvEtape == true) wait();

		}catch(InterruptedException e) {e.printStackTrace();}

		if(geneFinie == false || (resoDebut == true && resoFinie == false)){

			nouvEtape = true;// on ne peut pas relancer une étape si l'algorithme de génération est terminé et que l'algorithme de résolution n'a pas démarré.
		}


	}

	/*
	Cette méthode sert à signaler qu'une étape à été effectuée ou la fenêtre redessinée. Elle permet de réveiller tous les threads qui avait relachés leur lock sur la grille avec wait()
	*/
	public synchronized void finEtape(){

		this.notifyAll();

	}

	//Quand la génération est finie on sauvegarde le tableau dans le tableau de secours
	public void geneEstFinie(){

		this.geneFinie = true;

		for(int i =0; i < tableau.length; i++){

			for(int j = 0; j < tableau[0].length; j++){

					tableauSecours[i][j] = tableau[i][j];

			}

		}

	}

	//Si on veut reinitialiser le tableau
	public void reinitialiser(){

		for(int i =0; i < tableau.length; i++){

			for(int j = 0; j < tableau[0].length; j++){

					tableau[i][j] = tableauSecours[i][j];

			}

		}

	}

	//Retourne vrai si la génération est terminée
	public boolean verifGene(){

		return this.geneFinie;

	}

	//On indique que la résolution débute
	public void debutReso(){


		resoDebut = true;

	}

	//On indique que la résolution est finie
	public void finReso(){

		resoFinie = true;

	}

}
