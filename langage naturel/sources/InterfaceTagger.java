/*
 * Created on 1 mai 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sources;

import java.io.IOException;
import java.util.ArrayList;

import qtag.Tagger;

/**
 * @author aureliezara
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InterfaceTagger {
private static final String RESSOURCES="ressources/qtag-eng";
public static boolean init=false;
static Tagger tagger;


/**
 * initialise qtag, le lancer qu'une fois au debut du programme !
 * @throws IOException
 */
public static void initTagger() throws IOException{
	tagger=new Tagger(RESSOURCES);
	init=true;
}

/**
 * prend une liste de mots en parametre et renvoie un tableau de tags correspondant ˆ la liste
 * pour transformer une chaine de caractere en tokens utiliser la fonction segmenter de
 * la classe OutilsTexte
 * @param enonce
 * @return
 * @throws InitTaggerException
 */
public static String[] tag(ArrayList enonce) throws InitTaggerException{
	String[] s=null;
	if (!init)
	 throw new InitTaggerException("il faut d'abord lancer la methode initTagger de la classe InterfaceTagger");
	else {
	s=tagger.tag(enonce);
	}
	return s;
}

public static void main(String[] args) throws IOException{
String[] tags=null;
InterfaceTagger.initTagger();
try {
	tags=InterfaceTagger.tag(OutilsTexte.segmenter("hello! it is nice to see you again"));
} catch (InitTaggerException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
for (int i=0;i<tags.length;i++)
	System.out.println(tags[i]);
}
}
