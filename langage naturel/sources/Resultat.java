package sources;


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
    /**
     * initialise le nombre de resultat
     * @param n
     */
    public void setNbResultat(int n){
        nbResultats=n;
    }
    /**
     * donne le nombre de resultats pour une requete donnee
     * @return
     */
    public int getnbResultats(){
        return nbResultats;
    }
    /**
     * permet de recuperer un element du resultat
     * @param index
     * @return
     */
    public abstract ElementResultat getElementResultat(int index);
    /**
     * permet de recuperer la liste d'elements d'un resultat
     * @return
     */
    public abstract ElementResultat[] getListeElementsResultat();
}
