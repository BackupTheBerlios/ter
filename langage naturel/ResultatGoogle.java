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
     
 public void addElementResultatGoogle(GoogleSearchResultElement element,int index){
     listeResultat[index]=new ElementResultatGoogle();
     listeResultat[index].url=element.getURL();
     listeResultat[index].resume=element.getSnippet();
 }
 
 public String toString(){
     return "";
 }

/* (non-Javadoc)
 * @see Resultat#getElementResultat()
 */
public ElementResultat getElementResultat(int index) {
    return listeResultat[index];
}

/* (non-Javadoc)
 * @see Resultat#getListeElementsResultat()
 */
public ElementResultat[] getListeElementsResultat() {
    return listeResultat;
}
 
}
