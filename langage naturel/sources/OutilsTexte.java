/*
 * Created on Mar 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package sources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


import jregex.Matcher;
import jregex.Pattern;
import jregex.Replacer;


import com.sun.tools.javac.v8.util.Hashtable;

/**
 * @author azara
 *Outils pour textes. Contient un sentencer, un outil qui permet de récuperer le texte d'une
 *page HTML.
 *
 *
 */
public class OutilsTexte {
    
	private final static String URLABBREV="ressources/abbrev";
	private final static String URLSUFFIX="ressources/suffixes.txt";
	private final static String URLLATIN1="ressources/Latin-1.txt";
	private static Hashtable abbrev= new Hashtable();
	private static Hashtable suffix=new Hashtable();
	private static Hashtable latin1=new Hashtable();
	public OutilsTexte(){}

/**
 *charge les dictionnaires. A mettre au debut du programme et ne pas le relancer à chaque fois
 */
public static void initOutilsTexte() throws IOException{
	String mot=null;
    InputStream is=ClassLoader.getSystemResourceAsStream(URLABBREV);
  BufferedReader in=new BufferedReader(new InputStreamReader(is));
   String word=in.readLine();
	while(word!=null){
	    abbrev.put(word,word) ; 
	    word=in.readLine();
	}  		  
	in.close();
	is.close();
	 is=ClassLoader.getSystemResourceAsStream(URLSUFFIX);
  	in=new BufferedReader(new InputStreamReader(is));
  	word =in.readLine();
	while(word!=null){
	   suffix.put(word,word);
	   word=in.readLine();
	}
	in.close();
	is.close();
	is=ClassLoader.getSystemResourceAsStream(URLLATIN1);
	in=new BufferedReader(new InputStreamReader(is));
	StringTokenizer st;
	String line =in.readLine();
	while(line!=null){
			st=new StringTokenizer(line);
		    	latin1.put(st.nextToken(),st.nextToken());
		    	line=in.readLine();	    
		} 
	in.close();
	is.close();
}
	
/**
 * Permet de récuperer une page HTML sans les tags et dont les caractères speciaux ont ete
 * remplacé.
 * @param
 * @return page retourne la page sans les balises HTML et nettoiyee des caractères spéciaux
 */
public String getTexteFromHtml(String page){
    Pattern p=new Pattern("(.*<body[^>]*>)|(.*<BODY[^>]*>)","s");
    Replacer r=new Replacer(p,"");
    page=r.replace(page);
    p= new Pattern("<[^>]*>");
    r=new Replacer(p,"");
    page=r.replace(page);
    p=new Pattern("(\\s\\s)+","s");
    r=new Replacer(p,"");
    page=r.replace(page);
	java.util.regex.Pattern pt=java.util.regex.Pattern.compile("(&#(\\d)*;)|&(\\w)*;");
	java.util.regex.Matcher mt=pt.matcher(page);
	StringBuffer sb=new StringBuffer();
	String tmp=null;
	while (mt.find()) {
	    tmp=(String)latin1.get(mt.group());
	    if (tmp!=null) 
	        mt.appendReplacement(sb,tmp);
	    else {
	        mt.appendReplacement(sb,"");
	    }
	}
	   sb=mt.appendTail(sb);
	 page=sb.toString();	
	return page;
}


private String analyseVoisinnage(String candidat){
    Pattern p=new Pattern("[.!?]");
    Matcher m=p.matcher(candidat);
    String prefixe=null;
    String suffixe=null;
    StringBuffer sb=new StringBuffer(candidat);
    int index;
    if (!m.find()){
        return candidat;
    }
    if (abbrev.get(candidat)!=null)
        	return candidat;
    if (candidat.matches("(([A-Z0-9].)+)|([a-z0-9].)+")) {
    		return candidat;
    }
    index=m.start();
    if (index==(candidat.length()-1)){
        sb.insert(index+1,"</s>\n<s>");
        return sb.toString();
    }
    if (index>0)
        prefixe=candidat.substring(0,index);
    else 
        prefixe="";
    suffixe=candidat.substring(index+1,candidat.length());
    String ls=getLastSuffix(suffixe);
    if (ls=="."){
        sb.append("</s>\n<s>");
        return sb.toString();
    }
    if (suffix.get(ls)!=null){
        return candidat;
    }
    sb.insert(index+1,"</s>\n<s>");
     return sb.toString();
}

private String getLastSuffix(String suffixe){
    Pattern p=new Pattern("[.]");
    Matcher m=p.matcher(suffixe);
    if (!m.find())
        return suffixe;
    else {
      int index=m.start();
       if (index!=suffixe.length()-1){
       return getLastSuffix(suffixe.substring(index+1,suffixe.length()));
       } 
       return ".";
    }   
}


/**
 * Découpe un texte en phrases. Les phrases sont balisées par <s>...</s>
 * @param page 
 * @return un texte balisé
 */
public String sentencer(String page){
 page=getTexteFromHtml(page);
 Pattern p=new Pattern("(\\S)+","s");
 StringBuffer sb= new StringBuffer();
 sb.append("<s> ");
 Matcher m=p.matcher(page);
 while (m.find()){
    sb.append(analyseVoisinnage(m.group(0))+" ");
 }
 sb.append("</s>");
return sb.toString();
}
	
public static void main(String[] args){
    OutilsTexte outils= new OutilsTexte();
    try {
        OutilsTexte.initOutilsTexte();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        System.exit(0);
    }
    System.out.println("he is bac.jones.jpg Jones.His adress is http://www.macromedia.com.");
    System.out.println(outils.sentencer("he is bac.jones.jpg Jones.His adress is http://www.macromedia.com. he works for C.J.A.B.."));
}

}
