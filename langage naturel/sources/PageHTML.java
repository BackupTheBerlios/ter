/*
 * Created on 3 avr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;




/**
 * @author aurelie
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PageHTML extends Thread {
    
    public static final int initial=0;
    public static final int en_cours=1;
    public static final int finis=2;
    public int statut=initial;
    public URL url=null;
    public String codeHTML = null;
    public static int compteur;
    public  ElementResultat eR;
    public  String req=null;
    /**
     * @param arg0
     */
    public PageHTML(ElementResultat eRes,String reqRegex) {
        try {
            url=new URL(eRes.url);
            eR=eRes;
            req=reqRegex;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
/**
 * recupere la source de la page HTML dont l'url est l'attribut url 
 * url est specifiee par l'intermediaire du constructeur de la classe
 * @return
 * @throws IOException
 * @throws InterruptedException
 */
public  String getHTMLcode() throws IOException, InterruptedException{
    String ligne;
    InputStreamReader reader=null;
    StringBuffer code=new StringBuffer();
    reader = new InputStreamReader(url.openStream());
    BufferedReader entree=new BufferedReader(reader);
    while ((ligne = entree.readLine()) !=null){
        code.append(ligne+"\n"); 
    }
  return code.toString();
}

/* (non-Javadoc)
 * @see java.lang.Runnable#run()
 */
public  void run() {
    statut=en_cours;
    try {
    	//	System.out.println("debut getCode");
       codeHTML=getHTMLcode();
     //  System.out.println("fin getCode");
     //  System.out.println("debut netoyage");
       codeHTML=OutilsTexte.getTexteFromHtml(codeHTML);
     //  System.out.println("fin netoyage");
     //  System.out.println("debut contextes");
       eR.contexte=OutilsTexte.getContext(req,codeHTML);
     //  System.out.println("fin contextes");
    } catch (IOException e) {
    	e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    statut=finis;
    compteur--;
  //  System.out.println(compteur);
}


/**
 * R�cup�re en parall�le les contextes dans lesquels apparait l'expression reguliere donnee
 * en parametre
 * @param r
 * @param reqRegex
 */
public static void getAllContexts(Resultat r,String reqRegex){
 ElementResultat[] elemResult=r.getListeElementsResultat();
	compteur=elemResult.length;
for (int i=0;i<elemResult.length;i++){
	PageHTML p=new PageHTML(elemResult[i],reqRegex);
	p.start();
}
while(compteur!=0);
}

public static void main(String[] args) throws IOException{
}

}
       
   
