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
public abstract class Requete {
    public String requete;
    public int maxResults;
    public String langue;
    public int nbMots;
    
public abstract Resultat  getResultat();

public void setRequete(String req){
    requete=req;
}

public String getRequete(){
    return requete;
}

public void getPageFromUrl(String url){}


}
