import java.util.ArrayList;
public class uniciteChemin extends Generation{

  public uniciteChemin(Grille g){

    this.maGrille = g;

  }

  public void generer(){

    maGrille.remplir(true);

    int largeur = maGrille.getLargeur();
    int hauteur = maGrille.getHauteur();

    Case[][] tableau = maGrille.getTableau();
    //ArrayList<Case> zone = new ArrayList<Case>();
    
    int dir=0; //attribut de direction aleatoire
    int xa=0; //coordonnee aléatoire dans le tableau
    int ya=0; //coordonnee aléatoire dans le tableau
    
    //ArrayList<Arete> lesAretes = new ArrayList<Arete>();
    //ArrayList<Case> lesSommets= new ArrayList<Case>();
    Zone maZone = new Zone();//(lesAretes, lesSommets);
    
    if(largeur>1 && hauteur>1){//sécurité bug 
    while(maZone.lesSommets.size()<largeur*hauteur){
        //while(Case c : maZone.lesAretes){//tant que toutes les cases du tableau ne sont pas dans la liste
            dir=(int)(Math.random()*4);
            xa=(int)(Math.random()*(largeur-2))+1;//on prend pas les cases du bord
            ya=(int)(Math.random()*(hauteur-2))+1;
            
            
                if(maZone.appartient(tableau[xa][ya])==false){
                    tableau[xa][ya].setMurs(dir,false);
                    maZone.addSommet(tableau[xa][ya]);
                    if(maZone.lesSommets.size()>1){
                    maZone.addArete(maZone.lesSommets.get(maZone.lesSommets.size()-1),tableau[xa][ya]);
                    }
                
                

                }
                
            //System.out.println(maZone.lesSommets(maZone.lesSommets.size()-1));
            //System.out.println(maZone.lesAretes.sommet1.x);
        //}

    }
    }

  }
}
