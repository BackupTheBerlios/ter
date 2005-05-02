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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;
import jregex.Matcher;
import jregex.Pattern;
import jregex.REFlags;
//import jregex.RETokenizer;
import jregex.Replacer;


/**
 * @author azara
 *Outils pour textes. Contient un sentencer, un outil qui permet de r?cuperer le texte d'une
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
 *charge les dictionnaires. A mettre au debut du programme et ne pas le relancer ? chaque fois
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
 * Permet de r?cuperer une page HTML sans les tags et dont les caract?res speciaux ont ete
 * remplac?.
 * @param
 * @return page retourne la page sans les balises HTML et nettoiyee des caract?res sp?ciaux
 */
public static String getTexteFromHtml(String page){
    Pattern p1= new Pattern("(<[^>]*>)|(\\[[^\\]]*\\])");
    Replacer r1=new Replacer(p1,"");
    page=r1.replace(page);
    p1=new Pattern("(\\s\\s)+","s");
    r1=new Replacer(p1,"");
    page=r1.replace(page);
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

private static String getLastSuffix(String suffixe){
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
 * transforme une chaine de caractere en ajoutant entre chaque mots de la chaine une 
 * expression reguliere donnee en parametre .
 * @param requete
 * @param regex
 * @return
 */
public static String transRequeteRegex(String requete,String regex){
	StringBuffer sb = new StringBuffer();
	StringTokenizer st = new StringTokenizer(requete);
	while (st.hasMoreTokens()){
		sb.append(st.nextToken()+regex);	
	}
	return sb.toString();
}

public static String tagRequete(String reg,String texte){
	Pattern p = new Pattern(reg,REFlags.IGNORE_CASE);
	Matcher m=p.matcher(texte);
	StringBuffer sb=new StringBuffer(texte);
	if ( m.find()){
		sb.insert(m.end(),"</req>");
		sb.insert(m.start(),"<req>");
	}
	texte=sb.toString();
	return texte;
}


/**
 * �tant donn� une phrase, renvoie le nombre de mots que contient cette phrase
 * @param texte
 * @return
 */
public static int countWord(String texte){
	Pattern p=new Pattern("\\w+");
	Matcher m=p.matcher(texte);
	int cpt=0;
	while (m.find())
		cpt=cpt+1;
	return cpt;
}



/**
 * Etant donne une requete sous forme d'expression reguliere et un texte
 * renvoie la phrase qui contient l'expression reguliere.
 * @param reqRegex
 * @param texte
 * @return
 */
public static ArrayList getContext(String reqRegex,String texte){
	ArrayList contextes=new ArrayList();
	Pattern p=new Pattern(reqRegex,REFlags.IGNORE_CASE);
	Matcher m=p.matcher(texte);
	String contexte=null;
	while (m.find()){
		int start=OutilsTexte.getStartIndexOfSentence(m.start(),texte.toString());
		int end=OutilsTexte.getEndIndexOfSentence(m.end(),texte.toString());
		contexte=texte.substring(start,end);
//		contexte=OutilsTexte.getTexteFromHtml(contexte);
		contextes.add(contexte);
//		System.out.println(contexte);
//		System.out.println((String)contextes.get(contextes.size()-1));
	}
return contextes;	
}

private static boolean analyseVoisinnageBis(String candidat){
    Pattern p=new Pattern("[.!?]");
    Matcher m=p.matcher(candidat);
    String prefixe=null;
    String suffixe=null;
    StringBuffer sb=new StringBuffer(candidat);
    int index;
    if (!m.find()){
 //   		System.out.println(candidat+" : pas de points");
        return false;
    }
  //  if (candidat.matches("(\\.\\.\\.)|!|\\?")){
  //  		System.out.println("... ou ! ou  ?");
  //  		return true;
  //  }
    if (abbrev.get(candidat)!=null){
 //  		System.out.println(candidat+" : est une abbreviation");
        	return false;
    }
    if (candidat.matches("(([A-Z0-9]\\.)+)|([a-z0-9]\\.)+")) {
//    		System.out.println(candidat+" : est un reel ou une abbreviation");
    		return false;
    }
    index=m.start();
    if (index==(candidat.length()-1)){
//    		System.out.println(candidat+" : est une fin de phrase");
        return true;
    }
    if (index>0)
        prefixe=candidat.substring(0,index);
    else 
        prefixe="";
    suffixe=candidat.substring(index+1,candidat.length());
    String ls=OutilsTexte.getLastSuffix(suffixe);
    if (ls=="."){
//    		System.out.println(candidat+" : le suffixe est un point c'est une fin de phrase");
        return true;
    }
    if (suffix.get(ls)!=null){
//    		System.out.println(candidat+" : le suffixe est un extansion connue ce n'est pas une fin de phrase");
        return false;
    }
//    	System.out.println(candidat+" : je suppose que c'est une fin de phrase ");
     return true;
}

/**
 * etant donne un indice dans une chaine de caractere, 
 * renvoie l'indice de la premiere lettre de la phrase dans laquelle l'indice est contenu.
 * @param indice
 * @param texte
 * @return
 */
public static int getStartIndexOfSentence(int indice,String texte){
	int ind=indice;
	while(ind>0){
		char current=texte.charAt(ind);
		if (current=='!' || current=='?' || current=='.'){
			String candidat=texte.substring(getStartIndex(ind,texte),getEndIndex(ind,texte));
			if( OutilsTexte.analyseVoisinnageBis(candidat))
				return ind+1;
		}
			ind--;
	}
	return ind;
}



/**
 * etant donne un indice dans une chaine de caractere, 
 * renvoie l'indice de la derniere lettre de la phrase dans laquelle l'indice est contenu.
 * @param indice
 * @param texte
 * @return
 */
public static int getEndIndexOfSentence(int indice,String texte){
	int ind=indice;
	while(ind<texte.length()){
		char current=texte.charAt(ind);
		if (current=='!' || current=='?' || current=='.'){
			String candidat=texte.substring(getStartIndex(ind,texte),getEndIndex(ind,texte));
			if( OutilsTexte.analyseVoisinnageBis(candidat))
				return ind+1;
		}
			ind++;
	}
	return ind;
}

/**
 * �tant donn� un indice dans une chaine de caract�re renvoie l'indice de la premiere lettre
 * dans lequel l'indice donn� est contenu
 * @return l'indice
 */
public static int getStartIndex(int indice,String texte){
	int ind=indice;
	while (ind>0) {
		char current=texte.charAt(ind);
		if (current==' ' || current=='\n' || current=='\r' || current=='\f' || current=='\t'){
			return ind +1;
		}
		else
			ind--;
	}
	return ind;
}

/**
 * �tant donn� un indice dans une chaine de caract�re, renvoie la derni�re lettre du mot contenant cet indice.
 * @param indice
 * @param texte
 * @return
 */
public static int getEndIndex(int indice,String texte){
	int ind=indice;
	while (ind<texte.length()) {
		char current=texte.charAt(ind);
		if (current==' ' || current=='\n' || current=='\r' || current=='\f' || current=='\t'){
			return ind;
		}
		else
			ind++;
	}
	return ind;
}

/**
 * prend en parametre une chaine de parametre et renvoie une liste de mots et de ponctuation
 * composant cette chaine de caractere
 * @param enonce
 * @return
 */
public static ArrayList segmenter(String enonce){
	ArrayList tokens=new ArrayList();
	Pattern del=new Pattern("\\w+|[?,;.:/%�$&'()!�\"]");
	Matcher m=del.matcher(enonce);
	String tmp;
	while (m.find()){
		tmp=m.group(0);
		//	System.out.println(tmp);
			tokens.add(tmp);
	}
return tokens;	
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
    StringBuffer exemple=new StringBuffer();
    String ligne=null;
    InputStream is=ClassLoader.getSystemResourceAsStream("ressources/exemple.html");
    BufferedReader in=new BufferedReader(new InputStreamReader(is)); 
    try {
		ligne=in.readLine();
		while (ligne!=null){
			exemple.append(ligne+"\n");
			ligne=in.readLine();
		}
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	String requete="renon�er � ses mesures";
	String reqRegex=OutilsTexte.transRequeteRegex(requete,"\\W*");
	String page=OutilsTexte.getTexteFromHtml(exemple.toString());
	System.out.println(OutilsTexte.getContext(reqRegex,page));
	
}

}
