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
public class ElementResultatGoogle extends ElementResultat {
    public String url;
    public String resume;
    public String page;
    
    
    public ElementResultatGoogle(){}
    /**
     * 
     */
    public ElementResultatGoogle(String lien,String res) {
        url =lien;
        resume=res;
        // TODO Auto-generated constructor stub
    }
    public void setPage(String p){
    }
    
    public String toString(){
        return "url : "+url+"\n resume: "+resume+"\n";       
    }
    
    public static void main(String[] args) {
    }
}
