import com.google.soap.search.GoogleSearchResultElement;

/*
 * Created on 3 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author aurelie
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResultatGoogle extends Resultat {
    public ElementResultatGoogle[] listeResultat;
    
    ResultatGoogle(int nbMaxResult){
        listeResultat = new ElementResultatGoogle[nbMaxResult];
        }
  /**
   * permet d'ajouter un element au resultat
   * @param element :element de type GoogleSearchResultElement
   * @param index : indice dans le tableau des elements du resultats
   */   
 public void addElementResultatGoogle(GoogleSearchResultElement element,int index){
     listeResultat[index]=new ElementResultatGoogle();
     listeResultat[index].url=element.getURL();
     listeResultat[index].resume=element.getSnippet();
 }
 
 public String toString(){
     return "";
 }

/*permet de recuperer un element du resultat c'est a dire un objet contenant une url, un resume et une page
 * @see Resultat#getElementResultat()
 */
public ElementResultat getElementResultat(int index) {
    return listeResultat[index];
}

/* permet de recuperer la liste des elements du resultat
 * @see Resultat#getListeElementsResultat()
 */
public ElementResultat[] getListeElementsResultat() {
    return listeResultat;
}
 
}
