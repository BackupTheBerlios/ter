/*
 * Created on 1 mai 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sources;

import java.io.*;
import java.util.ArrayList;

import org.jdom.*;
import org.jdom.output.*;


/**
 * @author aureliezara
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FichierXML {

public static void generateXMLfile(String requete,Resultat resultat) throws FileNotFoundException, IOException{
	Element racine = new Element("requete");
	Attribute enonce=new Attribute("enonce",requete);
	racine.setAttribute(enonce);
	org.jdom.Document document = new Document(racine);
	Element tokens = new Element("tokens");
	racine.addContent(tokens);		      
	ArrayList toks=OutilsTexte.segmenter(requete); 
	String[] tags=null;
	try {
		tags = InterfaceTagger.tag(toks);
	} catch (InitTaggerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for (int i=0;i<toks.size();i++){
		Element token =new Element("token");
		Attribute tag=new Attribute("tag",tags[i]);
		token.setAttribute(tag);
		token.setText((String)toks.get(i));
		tokens.addContent(token);	
	}
	Element contextes=new Element("contextes");
	racine.addContent(contextes);
	ElementResultat[] elems=resultat.getListeElementsResultat();
	for (int i=0;i<elems.length;i++){
		for(int j=0;j<elems[i].contexte.size();j++){
		Element contexte= new Element("contexte");
		Attribute numElem=new Attribute("numELem",String.valueOf(i));
		contexte.setAttribute(numElem);
		contexte.setText((String)elems[i].contexte.get(j));
		contextes.addContent(contexte);
		}
	}
	 XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	 sortie.output(document, new FileOutputStream("requete.xml"));
}
}
