/** Objet case: murs[0] = true : mur à gauche/urs[1] = true mur en haut/murs[2] = true : mur à droite /murs[3] = true: mur en dessous
* 
*
*/
import java.awt.*;


public class Case  { 
	private boolean murs[]; 
	private Image image;
	
	public Case(boolean murs[]) {
		this.murs = new boolean[4]; 
		
		for(int i = 0; i<4;i++) {
			this.murs[i] = murs[i];
		}
		
		
	}
}
