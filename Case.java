/** Objet case: murs[0] = true : mur à gauche/urs[1] = true mur en haut/murs[2] = true : mur à droite /murs[3] = true: mur en dessous
*
*
*/
import java.awt.*;
import javax.swing.*;


public class Case {

	private boolean murs[] = new boolean[4];
	private Case[] voisins = new Case[4];
	private boolean estArrivee  = false;
	public int x, y, z;

	private EtatCase etat;

	public Case(boolean avecMurs) {

		this.x = x;
		this.y = y;
        this.z = z;

		for(int i =0; i < murs.length; i++){

			murs[i] = avecMurs;

		}

		for(Case c : voisins) c = null;

		this.etat = EtatCase.Normal;

	}
/*
	public void paintComponent(Graphics g){

		setOpaque(true);

		Graphics2D g2 = (Graphics2D) g;

	  g2.setStroke(new BasicStroke(5));

		if(estArrivee == false) g2.setColor(Color.white);

		else {
			g2.setColor(Color.red);
			System.out.println("arrivée");
		}

		System.out.println("w :" + getWidth() + " h : " + getHeight());

		g2.fillRect(0,0,getWidth(), getHeight());

		g2.setColor(Color.black);

		if(murs[0] == true) g2.drawLine(0,0,0, this.getHeight());

		if(murs[1] == true) g2.drawLine(0,0,this.getWidth(), 0);

		if(murs[2] == true) g2.drawLine(this.getWidth(),0,this.getWidth(), this.getHeight());

		if(murs[3] == true) g2.drawLine(this.getWidth(), this.getHeight(), 0, this.getHeight());


	}
	*/


	public boolean[] getMurs(){

		return this.murs;

	}

	public boolean getMurs(int i) {

		return this.murs[i];

	}

	public void setMurs(int n, boolean b){

		murs[n] = b;

		int t = ((n+2)>3)? n-2 : n+2;

		if(voisins[n] != null) voisins[n].modifVoisin(t, b);

	}

	public void setMurs(boolean[] b){

		murs = b;

		for(int i = 0; i < b.length; i++){

			int t = ((i+2)>3)? i-2 : i+2;

			if(voisins[i] != null) voisins[i].modifVoisin(t, b[i]);

		}

	}




	public void modifVoisin(int n, boolean b){

		murs[n] = b;

	}

	public void setVoisins(Case[] c){

		voisins = c;

	}

	public Case[] getVoisins(){

		return voisins;

	}

	public boolean estArrivee(){

		return this.estArrivee;

	}

	public void setArrivee(){

		this.estArrivee = true;

	}

	public enum EtatCase{

		Normal,
		Selection,
		Chemin,
		Arrivee,
		Depart;

	}

	public EtatCase getEtat(){

		return this.etat;

	}

	public void setEtat(EtatCase et){

		if((this.etat != EtatCase.Arrivee) == true && (this.etat != EtatCase.Depart) == true){

			this.etat = et;

		}

	}

}
