package sources;


import java.io.IOException;

import com.google.soap.search.GoogleSearch;
import com.google.soap.search.GoogleSearchFault;
import com.google.soap.search.GoogleSearchResult;
import com.google.soap.search.GoogleSearchResultElement;

/*
 * Created on 3 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author aurelie Zara
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RequeteGoogle extends Requete {
	private static String key;
	private	GoogleSearch search = new GoogleSearch();
	public ResultatGoogle resultat;
	/**
	 * prend ma cle par defaut 
	 *
	 */

	RequeteGoogle(){
	    key="ThsJGMdQFHJv1bi01gu0TtohJFclwDs/";
	    search.setKey(key);
	    }
	
	/**
	 * 
	 * @param userkey cle de l'api google
	 */
	RequeteGoogle(String userKey){
	    	key=userKey;
	    search.setKey(key); 	
	}
	/**
	 * 
	 * @param langueRestrict null si pas de restriction la langue voulue sinon, 
	 * pour le format des langues voir :
	 * <a href="http://www.google.com/apis/reference.html"> http://www.google.com/apis/reference.html </a>
	 * @param maxResult nombre maximum de resultats retournes 
	 * @param req la requete
	 */
	public void requeteGoogle(String langueRestrict,int maxResult, String req){
	    GoogleSearchResult resultatGoogle;
	    		if (langueRestrict!=null) {
	    		 //   	search.setLanguageRestricts(langueRestrict);
	    		}
	    		search.setMaxResults(maxResult);
	    		search.setQueryString("\""+req+"\"");
	    		try {
	    		   resultatGoogle=search.doSearch();
	    		   resultat = new ResultatGoogle(maxResult);
	    		   resultat.setNbResultat(resultatGoogle.getEstimatedTotalResultsCount());
	    		   GoogleSearchResultElement[] elements = resultatGoogle.getResultElements();
	    		   for (int i=0;i<elements.length;i++){ 
	    		       resultat.addElementResultatGoogle(elements[i],i);
	    		     //  System.out.println(elements[i].toString());
	    		   }
	    		   //	System.out.println(resultatGoogle.toString());
                } catch (GoogleSearchFault e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }              
	    	this.requete=req;
	    }
	
/**
 * permet de recuperer la cle courante pour l'utilisation de l'api google.
 * @return
 */
public static String getKey(){
    return key;
}
	 /*permet de recuperer le resultat de la requete
     * @see Requete#getResultat()
     */
    public Resultat getResultat() {
        return resultat;
    }
	   
	/**
	 * permet de rŽcuperer une page en cache donnŽe par une requete google
	 * @param url
	 * @return
	 */
	public static String getCachedPage(String url){
	    GoogleSearch cache = new GoogleSearch();
	    cache.setKey(key);
		String page=null;
		try {
			page= new String(cache.doGetCachedPage(url));
		} catch (GoogleSearchFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	

	public static void main(String[] args) {
	    if (args.length != 3) {
	        	System.out.print("pas le bon nombre d'arguments");
	    }
	    RequeteGoogle requete = new RequeteGoogle();
	    String langue=args[0];
	    int max=Integer.parseInt(args[1]);
	    String req=args[2];
	    requete.requeteGoogle(langue,max,req);
	    System.out.println(requete.resultat.nbResultats);
	    try {
            OutilsTexte.initOutilsTexte();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(0);
        }
	    for (int i=0;i<requete.resultat.listeResultat.length;i++){
	       requete.resultat.listeResultat[i].setPage();
	       System.out.println(requete.resultat.listeResultat[i].toString());    		
	}	    
}
	
}

	
	
	
	
	
	

