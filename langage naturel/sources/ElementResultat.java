package sources;

/*
 * Created on 3 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author aurelie
 *Classe abstraite d?crivant un ?lement du r?sultat d'une requete
 *Un element est une url, une page et un resume
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * @author aurelie
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class ElementResultat {
    String url;
    String resume;
    String page;
    String contexte;
   
 /**
  * Retourne l'url  de l'element 
 * @return
 */
public String getURL(){   
     return url;
 }
 
 /**
  * retourne le resume de l'element
 * @return
 */
public String getResume(){
     return resume;
 }
 
 /**
  * retourne la page web associ?e ? l'url de l'element
 * @return
 */
public String getPage(){
     return page;
 }
 
 public abstract String toString();
 
 
/**
 * r?cup?re la page associ?e a l'url de l'element
 */
public abstract void setPage();

}