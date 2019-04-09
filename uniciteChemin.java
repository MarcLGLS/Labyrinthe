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
    for(int i = 0; i<tableau.length; i++){
    for(int j = 0; j<tableau[i].length; j++){
        tableau[i][j].z = i*tableau.length+j;
        }
        }





    int dir=0; //attribut de direction aleatoire
    int xa=0; //coordonnee aléatoire dans le tableau
    int ya=0; //coordonnee aléatoire dans le tableau

    boolean continuer = true;

    if(largeur>1 && hauteur>1){//sécurité bug
    while(continuer){
        maGrille.attendreEtape();
        long h1 = System.nanoTime();

            dir=(int)(Math.random()*4);

            xa=(int)(Math.random()*(largeur));
            ya=(int)(Math.random()*(hauteur));
            int f = tableau[xa][ya].z;//mémorise valeur case


                    if((dir==0 && xa>0) || (dir==2 && xa<tableau[ya].length-1) || (dir==1 && ya>0) || (dir==3 && ya<tableau[xa].length-1)){//voisins existent (notament pour les cases du bord)
                    if(tableau[xa][ya].getMurs()[dir]==true  && tableau[xa][ya].getVoisins()[dir].z != tableau[xa][ya].z){
                        tableau[xa][ya].setMurs(dir,false);//on casse le mur


                        if(dir==0 && xa>0){
                            tableau[xa][ya].z=tableau[xa-1][ya].z;//les cases sont dans la meme zone
                            
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




                    }
                    }

                    for(int k =0; k<tableau.length; k++){
                    for(int l =0; l<tableau[k].length; l++){
                        if(tableau[k][l].z==f){
                            tableau[k][l].z=tableau[xa][ya].z;//toutes les cases appartenant aux zones réunies sont dans la meme zone(avant il n'y avait que les deux cases adjacentes)
                        }

                    }}





                    int m=0;//sert à verifier si toutes les cases appartiennent à la meme zone (fin algo)

                    for(int k =0; k<tableau.length; k++){
                    for(int l =0; l<tableau[k].length; l++){
                        if(tableau[0][0].z != tableau[k][l].z){
                            m++;
                        }
                    }
                    }
                    continuer = (m>0);


        maGrille.ajouterTempsGene(System.nanoTime() - h1);
        maGrille.finEtape();

    }
    }



    tableau[0][(int)(Math.random()*hauteur)].setEtat(Case.EtatCase.Depart);
    tableau[largeur-1][(int)(Math.random()*hauteur)].setEtat(Case.EtatCase.Arrivee);
    System.out.println("Labyrinthe généré ");
    maGrille.geneEstFinie();
}
}
