package sources;

/*
 * Created on 3 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author aurelie
 *Classe abstraite décrivant un élement du résultat d'une requete
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
  * retourne la page web associée à l'url de l'element
 * @return
 */
public String getPage(){
     return page;
 }
 
 public abstract String toString();
 
 
/**
 * récupère la page associée a l'url de l'element
 */
public abstract void setPage();

}