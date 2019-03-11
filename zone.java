import java.util.ArrayList;
public class Zone{
 private ArrayList<Arete> lesAretes = new ArrayList<Arete>();
  ArrayList<Case> lesSommets= new ArrayList<Case>();
  
  public Zone(){
      
      lesAretes = new ArrayList<Arete>();
      lesSommets = new ArrayList<Case>();
      
  }
 
 public void addSommet(Case s) { lesSommets.add(s); }
 
 
 
 public void addArete(Case s1, Case s2) {
 if(!lesSommets.contains(s1)) lesSommets.add(s1);
 if(!lesSommets.contains(s2)) lesSommets.add(s2);
 lesAretes.add(new Arete(s1,s2));
 }
 
 public boolean isAreteEntre(Case s1, Case s2) {
Arete tmp = new Arete(s1,s2);
 return lesAretes.contains(tmp);
 }
 
 
 public boolean appartient(Case s){
 boolean b=false;
 for(Arete a : lesAretes) {
 if(a.getVers().equals(s)){
 b=true;
 }
}
 return b;
 }
}
