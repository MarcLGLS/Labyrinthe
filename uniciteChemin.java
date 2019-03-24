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
    ArrayList<Arete> al = new ArrayList<Arete>();
    ArrayList<Case> zone = new ArrayList<Case>();
    for(int i = 0; i<tableau.length; i++){
    for(int j = 0; j<tableau[i].length; j++){
        tableau[i][j].z = i*tableau.length+j;
        }
        }





    int dir=0; //attribut de direction aleatoire
    int xa=0; //coordonnee aléatoire dans le tableau
    int ya=0; //coordonnee aléatoire dans le tableau


  //  Zone maZone = new Zone();//(lesAretes, lesSommets);
    //ArrayList<Zone> lz = new ArrayList<Zone>(); // liste de zones
    boolean continuer = true;
    int nbc = 0;

    if(largeur>1 && hauteur>1){//sécurité bug
    while(continuer /*&& nbc < (hauteur)*(largeur)*/){
        //while(Case c : maZone.lesAretes){//tant que toutes les cases du tableau ne sont pas dans la liste
            dir=(int)(Math.random()*4);

            xa=(int)(Math.random()*(largeur));
            ya=(int)(Math.random()*(hauteur));
            int f = tableau[xa][ya].z;


                    if((dir==0 && xa>0) || (dir==2 && xa<tableau[ya].length-1) || (dir==1 && ya>0) || (dir==3 && ya<tableau[xa].length-1)){//voisins existent
                    if(tableau[xa][ya].getMurs()[dir]==true  && tableau[xa][ya].getVoisins()[dir].z != tableau[xa][ya].z){
                        tableau[xa][ya].setMurs(dir,false);
                      //  System.out.println("dir = "+dir+" xa = "+xa+" ya = "+ya);
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
                            al.add(new Arete(tableau[xa][ya], tableau[xa-1][ya]));
                        }
                        if(dir==2 && xa<tableau[xa].length-1){
                            tableau[xa][ya].z=tableau[xa+1][ya].z;
                            al.add(new Arete(tableau[xa][ya], tableau[xa+1][ya]));

                        }
                        if(dir==1 && ya>0){
                            tableau[xa][ya].z=tableau[xa][ya-1].z;
                            al.add(new Arete(tableau[xa][ya], tableau[xa][ya-1]));

                        }
                        if(dir==3 && ya<tableau.length-1){
                            tableau[xa][ya].z=tableau[xa][ya+1].z;
                            al.add(new Arete(tableau[xa][ya], tableau[xa][ya+1]));

                        }

                        //DEBUG : mettre les cases dans des listes




                    }
                    }
                    /*for(Arete a : al){
                        if(a.sommet1.z != a.sommet2.z){
                            a.sommet1.z = a.sommet2.z;
                        }
                    }*/


                    for(int k =0; k<tableau.length; k++){
                    for(int l =0; l<tableau[k].length; l++){
                        if(tableau[k][l].z==f){
                            tableau[k][l].z=tableau[xa][ya].z;
                        }

                    }}





                    int m=0;

                    for(int k =0; k<tableau.length; k++){
                    for(int l =0; l<tableau[k].length; l++){
                        if(tableau[0][0].z != tableau[k][l].z){
                            m++;
                        }
                        /*for(Arete a : al){
                        if(a.sommet1.z != a.sommet2.z){
                            a.sommet1.z = a.sommet2.z;
                            //System.out.println("Sommet 1 = "+a.sommet1.z+" et sommet 2 = "+a.sommet2.z);
                        }
                    }*/

                    }
                    }
                    continuer = (m>0);
                    //System.out.println("continuer = "+continuer+" car m = "+m);



        maGrille.attendreEtape();
        maGrille.finEtape();

    }
    }
    /*for(int k =0; k<tableau.length; k++){
        for(int l =0; l<tableau[k].length; l++){
            if(tableau[k][l].getMurs()[0]==true && tableau[k][l].getMurs()[1]==true && tableau[k][l].getMurs()[2]==true && tableau[k][l].getMurs()[3]==true){
                tableau[k][l].setMurs((int)(Math.random()*4),false);
            }
        }}*/
        
        
    tableau[0][(int)(Math.random()*hauteur)].setEtat(Case.EtatCase.Depart);
    tableau[largeur-1][(int)(Math.random()*hauteur)].setEtat(Case.EtatCase.Arrivee);
    System.out.println("Labyrinthe généré ");
    maGrille.geneEstFinie();
}
}
