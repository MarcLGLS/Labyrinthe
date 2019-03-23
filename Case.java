/** Objet case: murs[0] = true : mur à gauche/urs[1] = true mur en haut/murs[2] = true : mur à droite /murs[3] = true: mur en dessous
*
*
*/

public class Case {

	private boolean murs[] = new boolean[4]; //Tableau des murs
	private Case[] voisins = new Case[4]; //Tableau des voisins
	public int z; //Attribut de la zone

	private EtatCase etat; //Etat de la case

	public Case(boolean avecMurs) {

      this.z = -1; //On met l'attribut de la zone à -1 pour être sur de ne pas dessiner les zones si ce n'est pas nécessaire.

		for(int i =0; i < murs.length; i++){

			//On remplit le tableau de murs avec le boolean passé en parametre, la case est aisin complétement ouverte (sans murs) ou complétement fermée.
			murs[i] = avecMurs;

		}

		/*
		On met tous les voisins de la case à null, c'est la grille qui se charge de les attribués. Si une case est sur un bord du labyrinthen, certains de ces voisins sont nulls.
		On fait attention dans les algorithmes utilisant les voisins d'une case à ne pas tenter d'accéder aux voisins qui sont nulls.
		*/

		for(Case c : voisins) c = null;

		this.etat = EtatCase.Normal; // L'état de base de la case est l'état Normal.

	}


	//Retourne le tableau de murs
	public boolean[] getMurs(){

		return this.murs;

	}
	//Retourne un mur particulier
	public boolean getMurs(int i) {

		return this.murs[i];

	}

	//Modifie un mur particulier et les murs des voisins accordément.
	public void setMurs(int n, boolean b){

		murs[n] = b;

		int t = ((n+2)>3)? n-2 : n+2;

		if(voisins[n] != null) voisins[n].modifVoisin(t, b);

	}

	//Modifie tous les murs d'un coup avec un tableau
	public void setMurs(boolean[] b){

		murs = b;

		for(int i = 0; i < b.length; i++){

			int t = ((i+2)>3)? i-2 : i+2;

			if(voisins[i] != null) voisins[i].modifVoisin(t, b[i]);

		}

	}



	//Méthode utilisée pour modifier un mur quand une case voisine a eu son mur de modifié.
	public void modifVoisin(int n, boolean b){

		murs[n] = b;

	}

	//Assigne tous les voisins
	public void setVoisins(Case[] c){

		voisins = c;

	}

	//Récupère tous les voisins
	public Case[] getVoisins(){

		return voisins;

	}
	
	//Retourne true si la case est un cul de sac
	
	public boolean culDeSac() {
		boolean culDeSac =false;
		
		if(this.murs[0] == false && this.murs[1] == true && this.murs[2] == true && this.murs[3] == true) {
			culDeSac = true;
		} else if(this.murs[0] == true && this.murs[1] == false && this.murs[2] == true && this.murs[3] == true) {
			culDeSac = true;
		} else if(this.murs[0] == true && this.murs[1] == true && this.murs[2] == false && this.murs[3] == true) {
			culDeSac = true;
		} else if(this.murs[0] == true && this.murs[1] == true && this.murs[2] == true && this.murs[3] == false) {
			culDeSac = true;
		}
		return culDeSac;
		
	}
	
	//Retourne true si la case est une intersection
	public boolean intersection() {
		boolean intersection = false;
		int trou =0;
		for(int i =0; i<4; i++) {
			if(this.murs[i] == false) {
				trou++;
			}
		}
		if(trou >1) {
			intersection = true;
		}
		return intersection;
	}

	/*
	Énumération utilisée pour connaître l'état de la case. Chaque état modifie l'affichage de cette case.
	*/
	public enum EtatCase{

		Normal,
		Selection,
		Chemin,
		Arrivee,
		Depart;

	}

	//Retourne l'état
	public EtatCase getEtat(){

		return this.etat;

	}

	//Modifie l'état de la case. Si celle ci est une case départ ou arrivée son état ne peut plus être modifié.
	public void setEtat(EtatCase et){

		if((this.etat != EtatCase.Arrivee) == true && (this.etat != EtatCase.Depart) == true){

			this.etat = et;

		}

	}

}
