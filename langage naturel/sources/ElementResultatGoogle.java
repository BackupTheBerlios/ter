package sources;



/*
 * Created on 3 mars 2005
 *
 *
 */

/**
 * @author aurelie
 *Decrit un element d un resultat d une requete Google
 *
 */
public class ElementResultatGoogle extends ElementResultat {
  //  public String url;
  //  public String resume;
  //  public String page;
    
    
    public ElementResultatGoogle(){}
  
    /**
     * Constructeur 
     * @param lien : url donnee par GoogleResult
     * @param res :  resume donne par GoogleResult
     */
    public ElementResultatGoogle(String lien,String res) {
        url =lien;
        resume=res;
        // TODO Auto-generated constructor stub
    }
    
    /* (non-Javadoc)
     * @see sources.ElementResultat#setPage()
     */
    public void setPage(){
        String p=RequeteGoogle.getCachedPage(url);
        OutilsTexte ot=new OutilsTexte();
        p=ot.getTexteFromHtml(p);
        p=ot.sentencer(p);
        page=p;
        
    }
    
    public String toString(){
        return "url : "+url+"\n resume: "+resume+"\n page: "+page;       
    }
    
    public static void main(String[] args) {
    }

   

}
