public class Arete { //implementsComparable<Arete> 
    
 Carré sommet1;
 Carré sommet2;


public Arete(Carré sommet1, Carré sommet2){
 this.sommet1 = sommet1;
 this.sommet2 = sommet2;
}


 public Carré getDepuis(){
      return sommet1;
      }
      
 public Carré getVers(){
     return sommet2;
     }
 //public boolean equals(Object o){ /* … */ }
 //public int compareTo(Arete a){ /* … */ }
}
