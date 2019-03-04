/** Objet case: murs[0] = true : mur à gauche/urs[1] = true mur en haut/murs[2] = true : mur à droite /murs[3] = true: mur en dessous
*
*
*/
import java.awt.*;
import javax.swing.*;


public class Case extends JPanel {

	private boolean murs[] = new boolean[4];
	private Case[] voisins = new Case[4];
	private boolean estArrivee  = false;

	public Case(boolean avecMurs) {

	 	super();
		this.setLayout(null);


		for(boolean b : murs){

			if(avecMurs == true) b = true;

			if(avecMurs == false) b = false;

		}

		for(Case c : voisins) c = null;

	}

	public void paint(Graphics g){

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

	  g2.setStroke(new BasicStroke(1));


		g.setColor(Color.white);

		g.drawRect(0,0, this.getWidth(), this.getHeight());

		g2.setColor(Color.BLACK);

		if(murs[0] == true) g2.drawLine(0,0,this.getWidth(), this.getHeight());

		if(murs[1] == true) g2.drawLine(0,0,this.getWidth(), 0);

		if(murs[2] == true) g2.drawLine(this.getWidth(),0,this.getWidth(), this.getHeight());

		if(murs[3] == true) g2.drawLine(this.getWidth(), this.getHeight(), this.getHeight(),0);


	}


	public boolean[] getMurs(){

		return this.murs;

	}

	public void setMurs(int n, boolean b){

		murs[n] = b;

		int t = ((n+2)>3)? 1 : n+2;

		voisins[n].modifVoisin(t, b);

	}

	public void setMurs(boolean[] b){

		murs = b;

		for(int i = 0; i < b.length; i++){

			int t = ((i+2)>3)? 1 : i+2;

			voisins[i].modifVoisin(t, b[i]);

		}

	}

	public void modifVoisin(int n, boolean b){

		murs[n] = b;

	}

	public void setVoisins(Case[] c){

		voisins = c;

	}

	public boolean estArrivee(){

		return this.estArrivee;

	}

	public void setArrivee(){

		this.estArrivee = true;

	}

}
