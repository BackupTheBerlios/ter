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
public abstract class Resultat {
    public int nbResultats=0;
    public ElementResultat[] listeResultat;
    
    public void setNbResultat(int n){
        nbResultats=n;
    }
    
    public int getnbResultats(){
        return nbResultats;
    }
    
    public abstract ElementResultat getElementResultat(int index);
}