import java.util.*;

public class ResolutionDroite extends Resolution{
	private LinkedList<Case> chemin;
	private int position;
	private int x;
	private int y;
	
	public ResolutionDroite(Grille g){
	this.maGrille = g;
	this.chemin = new LinkedList<Case>();
	
	}

	public void run(){

		resoudre();

	}

	public void resoudre() {
	  // détermination case départ
		int xd =0;
		int yd =0;
		maGrille.debutReso();
		  for(this.x =0; this.x < maGrille.getLargeur() ; this.x++) {
				for(y = 0; y < maGrille.getHauteur(); y++) {
					System.out.println("x = "+x+"  /  y = "+y+" !!");

					if(maGrille.getTableau()[x][y].getEtat() == Case.EtatCase.Depart){
						xd=x;
						yd=y;
					}

				}
		  }



	  System.out.println(" La case départ est : x = "+xd+" //  y = "+yd+" !!");
		this.position = 2;
	  f(xd,yd);
		maGrille.finReso();
	  System.out.println(" Algo finie");



	}

	public void f(int x, int y) {
		
		int x1 = x;
		int y1 = y;
		this.x=x;
		this.y = y;
		int i =0;
		do {
			x1=this.x;
			y1=this.y;
			System.out.println("aaa x1 ="+x1+" / y1 ="+y1+"  /this.position =="+this.position+" !!");
		maGrille.attendreEtape();
		maGrille.getTableau()[x1][y1].setEtat(Case.EtatCase.Chemin);
		// AVANCEMENT
		
		// cas continuer tout droit

		if(maGrille.getTableau()[x1][y1].getMurs(droite()) == true && maGrille.getTableau()[x1][y1].getMurs(gauche()) == true && maGrille.getTableau()[x1][y1].getMurs(this.position) == false ) {
				avancement(x1,y1);
				System.out.println("x = "+this.x+" //  y = "+this.y+"  ");
				chemin.add(maGrille.getTableau()[this.x][this.y]);
				System.out.println("tout droit");
		}
		
		// cas cul de sac faire demi tour
		else if(maGrille.getTableau()[x1][y1].getMurs(droite()) == true && maGrille.getTableau()[x1][y1].getMurs(gauche()) == true && maGrille.getTableau()[x1][y1].getMurs(this.position) == true ) {
				// faire demi tour
				this.position = gauche();
				this.position = gauche();
				avancement(x1,y1);
				System.out.println("x = "+this.x+" //  y = "+this.y+"  ");
				chemin.add(maGrille.getTableau()[this.x][this.y]);
				System.out.println("demi tour");
		}

		// cas chemin à droite
		else if( maGrille.getTableau()[x1][y1].getMurs(droite()) == false && maGrille.getTableau()[x1][y1].getMurs(gauche()) == true && maGrille.getTableau()[x1][y1].getMurs(this.position) == true ) {
				this.position= droite();
				avancement(x1,y1);
				System.out.println("x = "+this.x+" //  y = "+this.y+"  ");
				chemin.add(maGrille.getTableau()[this.x][this.y]);
				System.out.println("droite");
		}

		// cas chemin à gauche
		else if(maGrille.getTableau()[x1][y1].getMurs(droite()) == true && maGrille.getTableau()[x1][y1].getMurs(gauche()) == false && maGrille.getTableau()[x1][y1].getMurs(this.position) == true ) {
				this.position= gauche();
				avancement(x1,y1);
				System.out.println("x = "+this.x+" //  y = "+this.y+"  ");
				chemin.add(maGrille.getTableau()[this.x][this.y]);
				System.out.println("gauche");

		}
		
		// cas intersection en T
		else if(maGrille.getTableau()[x1][y1].getMurs(droite()) == false && maGrille.getTableau()[x1][y1].getMurs(gauche()) == false) {
				this.position= droite();
				avancement(x1,y1);
				System.out.println("x = "+this.x+" //  y = "+this.y+"  ");
				chemin.add(maGrille.getTableau()[this.x][this.y]);
				System.out.println("on tourne à droite suite à une intersection en T");

		}
		
		//cas intersection en L(tout droit et à gauche)
		else if(maGrille.getTableau()[x1][y1].getMurs(gauche()) == false && maGrille.getTableau()[x1][y1].getMurs(this.position) == false) {
				avancement(x1,y1);
				System.out.println("x = "+this.x+" //  y = "+this.y+"  ");
				chemin.add(maGrille.getTableau()[this.x][this.y]);
				System.out.println("on tourne à droite suite à une intersection L (tout droit/ gauche)");

		}
		
				//cas intersection en L(tout droit et à droite)
		else if(maGrille.getTableau()[x1][y1].getMurs(droite()) == false && maGrille.getTableau()[x1][y1].getMurs(this.position) == false) {
				this.position = droite();
				avancement(x1,y1);
				System.out.println("x = "+this.x+" //  y = "+this.y+"  ");
				chemin.add(maGrille.getTableau()[this.x][this.y]);
				System.out.println("on tourne à droite suite à une intersection L (tout droit/ droite)");

		}
		
		
		
		System.out.println("bbb"+i+" !!");
		maGrille.getTableau()[x1][y1].setEtat(Case.EtatCase.Chemin);
		maGrille.getTableau()[this.x][this.y].setEtat(Case.EtatCase.Selection);

		maGrille.finEtape();
		i++;
	}while(maGrille.getTableau()[x][y].getEtat() != Case.EtatCase.Arrivee);
	//maGrille.getTableau()[x][y].getEtat() != Case.EtatCase.Arrivee

	}

	public int droite() {
		int droite = this.position + 1;
		if(droite >3) {
			droite = 0;
		}

		return droite;
	}

	public int gauche() {
		int gauche = this.position - 1;
		if(gauche < 0) {
			gauche = 3;
		}

		return gauche;
	}

	public void avancement(int x1, int y1) {
		if(this.position == 0) {
			this.x =x1-1;
		}
		if(this.position == 1) {
			this.y =y1-1;
		}
		if(this.position == 2) {
			this.x =x1+1;
		}
		if(this.position == 3) {
			this.y =y1+1;
		}
		
	}
}
