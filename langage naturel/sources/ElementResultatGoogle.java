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
    }
    
   
    public String toString(){
    		StringBuffer sb = new StringBuffer();
    		sb.append("url : "+url+"\n");
    		sb.append("resume : "+resume+"\n");
    		for (int i=0;i<contexte.size();i++){
    			sb.append("contexte "+i+": "+(String)contexte.get(i)+"\n");
    		}
        return sb.toString();       
    }
    
    public static void main(String[] args) {
    }

   

}
