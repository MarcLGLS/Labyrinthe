//PROBLEME LOGIQUE : lorsqu'on recopie une case pour créer une zone, on modifie une case et aps toute la zone


import java.util.ArrayList;
public class uniciteChemin extends Generation implements Runnable{

  public uniciteChemin(Grille g){

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
    //ArrayList<Case> zone = new ArrayList<Case>();
    for(int i = 0; i<tableau.length; i++){
    for(int j = 0; j<tableau[i].length; j++){
        tableau[i][j].z = i*tableau.length+j;
        }
        }





    int dir=0; //attribut de direction aleatoire
    int xa=0; //coordonnee aléatoire dans le tableau
    int ya=0; //coordonnee aléatoire dans le tableau


    Zone maZone = new Zone();//(lesAretes, lesSommets);
    ArrayList<Zone> lz = new ArrayList<Zone>(); // liste de zones
    boolean continuer = true;
    int nbc = 0;

    if(largeur>1 && hauteur>1){//sécurité bug
    while(continuer && nbc < (hauteur)*(largeur)-1){
        //while(Case c : maZone.lesAretes){//tant que toutes les cases du tableau ne sont pas dans la liste
            dir=(int)(Math.random()*4);

            xa=(int)(Math.random()*(largeur));
            ya=(int)(Math.random()*(hauteur));


                    if((dir==0 && xa>0) || (dir==2 && xa<tableau[ya].length-1) || (dir==1 && ya>0) || (dir==3 && ya<tableau[xa].length-1)){//voisins existent
                    if(tableau[xa][ya].getMurs()[dir]==true  && tableau[xa][ya].getVoisins()[dir].z != tableau[xa][ya].z){
                        tableau[xa][ya].setMurs(dir,false);
                        System.out.println("dir = "+dir+" xa = "+xa+" ya = "+ya);
                        nbc=nbc+1;


                        /*if(dir==0 && ya>0){
                            tableau[xa][ya].z=tableau[xa][ya-1].z;
                        }
                        if(dir==2 && ya<tableau[xa].length-1){
                            tableau[xa][ya].z=tableau[xa][ya+1].z;

                        }
                        if(dir==1 && xa>0){
                            tableau[xa][ya].z=tableau[xa-1][ya].z;

                        }
                        if(dir==3 && xa<tableau.length-1){
                            tableau[xa][ya].z=tableau[xa+1][ya].z;

                        }*/



                        if(dir==0 && xa>0){
                            tableau[xa][ya].z=tableau[xa-1][ya].z;
                        }
                        if(dir==2 && xa<tableau[xa].length-1){
                            tableau[xa][ya].z=tableau[xa+1][ya].z;

                        }
                        if(dir==1 && ya>0){
                            tableau[xa][ya].z=tableau[xa][ya-1].z;

                        }
                        if(dir==3 && ya<tableau.length-1){
                            tableau[xa][ya].z=tableau[xa][ya+1].z;

                        }

                        //DEBUG : mettre les cases dans des listes




                    }
                    }

                    int m=0;

                    for(int k =0; k<tableau.length; k++){
                    for(int l =0; l<tableau[k].length; l++){
                        if(tableau[0][0].z != tableau[k][l].z){
                            m++;
                        }
                    }
                    }
                    continuer = (m>2);
                    System.out.println("continuer = "+continuer);



        maGrille.attendreEtape();
        maGrille.finEtape();

    }
    }
}
}
