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
public abstract class ElementResultat {
    String url;
    String resume;
    String page;

 public String getURL(){   
     return url;
 }
 
 public String getResume(){
     return resume;
 }
 
 public String getPage(){
     return page;
 }
 
 public abstract String toString();
 
public abstract void setPage(String url);

}