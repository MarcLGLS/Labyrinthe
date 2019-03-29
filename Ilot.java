public class Ilot extends Generation implements Runnable{

  public Ilot(Grille g){

    this.maGrille = g;

  }


  public void run(){

    generer();

  }

  public void generer(){

    maGrille.remplir(true);

    int largeur = maGrille.getLargeur();
    int hauteur = maGrille.getHauteur();

    Case[][] tableau = maGrille.getTableau();
    int c = (int)(Math.min(largeur, hauteur));
    int i=0;
    
    while(i<(int)(c/2)){
        
        //int dir = (int)(Math.random()*2);
        
            for(int j=i; j<c-i; j++){
                tableau[j][i].setMurs(2,false);
                //tableau[i][j].setMurs(0,false);
            }
            for(int j=i; j<c-i; j++){
                tableau[i][j].setMurs(3,false);
                //tableau[j][i].setMurs(1,false);
            }
            for(int j=i; j<c-i;j++){
                System.out.println("j="+j+" c="+c+" et i="+i);
                tableau[j][c-i-1].setMurs(0,false);
                
                //tableau[c-i][j].setMurs(0,false);
            }
            for(int j=i; j<c-i-1; j++){
                tableau[c-i-1][j].setMurs(2,false);
                //tableau[j][c-i].setMurs(0,false);
            }
        
        
        tableau[(int)(Math.random()*(c-i))][i].setMurs(1,false);
        tableau[(int)(Math.random()*(c-i))][c-i-1].setMurs(3,false);
        
        /*if(i%2==0){//ouverture en haut
            tableau[i][(int)(Math.random()*(c-i))].setMurs(1,false);
        }
        if(i%2==1){
            tableau[c-i][(int)(Math.random()*(c-i))].setMurs(3,false);
        }*/
        
        
        
        
      i++;  
    }
    tableau[0][0].setEtat(Case.EtatCase.Depart);
    tableau[i][i].setEtat(Case.EtatCase.Arrivee);
    System.out.println("Le labyrinthe imparfait à ilots est généré ");
    maGrille.geneEstFinie();
    
    
    
    
    }
}