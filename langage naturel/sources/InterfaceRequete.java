/*
 * Created on 22 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sources;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author aurelie
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InterfaceRequete {
    
    /**
     * lance la requete google et renvoie une chaine de caractere contenant l'url,
     * le resume et le contexte de la requete
     * @param langue
     * @param maxResults
     * @param req
     * @param reqRegex il s'agit de l'expression reguliere que l'on doit trouver entre les mots
     * de la requete. Par exemple : \W prend en compte les ponctuations et les blancs.
     * @return
     */
    public String launchRequeteGoogle(String langue,int maxResults,String req,String reqRegex){
        RequeteGoogle requete=new RequeteGoogle(langue,maxResults,req);
        requete.requeteGoogle();
        requete.getContexts(OutilsTexte.transRequeteRegex(req,reqRegex));
        ElementResultatGoogle[] resultats=requete.resultat.listeResultat;
        StringBuffer sb=new StringBuffer("nombre de mots dans la requete : "+requete.nbMots+"\n\nnombre de resultats : "+requete.resultat.nbResultats+"\n\n");
        BasicScoreGoogle score=new BasicScoreGoogle();
        for (int i=0;i<resultats.length;i++){
            sb.append("URL : "+resultats[i].url+"\n\n");
            sb.append("RESUME : "+resultats[i].resume+"\n\n");
        for (int j=0;j<resultats[i].contexte.size();j++)
            sb.append("CONTEXTE : "+(String)resultats[i].contexte.get(j)+"\n\n");
        }
        		sb.append("SCORE :"+score.giveScore((requete))+"%");
    try {
    	FichierXML.generateXMLfile(requete.requete,requete.resultat);
    } catch (FileNotFoundException e1) {
    	// TODO Auto-generated catch block
    	e1.printStackTrace();
    } catch (IOException e1) {
    	// TODO Auto-generated catch block
    	e1.printStackTrace();
    }	
    return sb.toString();
        };
        
    public void launchRequeteYahoo(){}

    public static void main(String[] args){
        InterfaceRequete iR=new InterfaceRequete();
       System.out.println(iR.launchRequeteGoogle("lan-en",10,"long black hair",OutilsTexte.transRequeteRegex("long black hair","\\W")));
    }
    
}
