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
        String code=OutilsTexte.getTexteFromHtml(page);
        code=OutilsTexte.sentencer(page);
        page=code;
    }
    
   
    public String toString(){
        return "url : "+url+"\n resume: "+resume+"\n contexte: "+contexte;       
    }
    
    public static void main(String[] args) {
    }

   

}
