public class Arete { //implementsComparable<Arete> 
    
 Case sommet1;
 Case sommet2;


public Arete(Case sommet1, Case sommet2){
 this.sommet1 = sommet1;
 this.sommet2 = sommet2;
}


 public Case getDepuis(){
      return sommet1;
      }
      
 public Case getVers(){
     return sommet2;
     }
 //public boolean equals(Object o){ /* … */ }
 //public int compareTo(Arete a){ /* … */ }
}
