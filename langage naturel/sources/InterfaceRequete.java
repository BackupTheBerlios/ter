/*
 * Created on 22 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sources;

/**
 * @author aurelie
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InterfaceRequete {
    
    public String launchRequeteGoogle(String langue,int maxResults,String req){
        RequeteGoogle requete=new RequeteGoogle(langue,maxResults,req);
        requete.requeteGoogle();
        ElementResultatGoogle[] resultats=requete.resultat.listeResultat;
        StringBuffer sb=new StringBuffer("nombre de mots dans la requete : "+requete.nbMots+"\n\nnombre de resultats : "+requete.resultat.nbResultats+"\n\n");
        
        for (int i=0;i<resultats.length;i++){
            sb.append("URL : "+resultats[i].url+"\n\n");
            sb.append("RESUME : "+resultats[i].resume+"\n\n");
            sb.append("CONTEXTE : "+resultats[i].contexte+"\n\n");
        }
        return sb.toString();
        };
        
    public void launchRequeteYahoo(){}

    public static void main(String[] args){
        InterfaceRequete iR=new InterfaceRequete();
       System.out.println(iR.launchRequeteGoogle("lan-en",10,"long black hair"));
    }
    
}
