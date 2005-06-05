/*
 * Created on 14 mai 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.StringTokenizer;

import jregex.Matcher;
import jregex.Pattern;

/**
 * @author aureliezara
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ModelGoogle extends Thread{
	static int compteur;
	static int compteur_req=0;
	static StringBuffer out;
	static StringBuffer in;
	int grammes;
	public final static String URLOUT="ressources/stats.txt";
	
	public ModelGoogle(int n){
		grammes=n;
	}
	public void run(){
		try {
			ModelGoogle.constructModel(grammes,in,out);
		} catch (InitOutilsTexteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	System.out.println(compteur);
		ModelGoogle.compteur--;
	}
	
	public static void constructModel(int n_grammes,StringBuffer sbIn,StringBuffer sbOut) 
		throws InitOutilsTexteException{
		Pattern p=new Pattern("(\\w+\\W+){"+n_grammes+"}");
		Matcher m=p.matcher(sbIn.toString());
		String tmp=null;
		try{
		while (m.find() && compteur_req<700) {
			tmp=m.group(0);
			if (!OutilsTexte.contientQueMotsVides(tmp) && 
					(!OutilsTexte.contientDesGuillements(tmp))) {
				RequeteGoogle rg=new RequeteGoogle("lan_en",1,tmp);
				rg.requeteGoogle();
				synchronized (sbOut) {
			 	sbOut.append(n_grammes+" ");
				sbOut.append(rg.resultat.nbResultats+"\n");
				compteur_req++;
				}
				System.out.println(compteur_req+" "+n_grammes+" "
						+rg.resultat.nbResultats+" "+tmp);			
				}
			}
		} catch (Exception e){
			System.out.println("problemes google");
		}
		}
	
	public static void initIn(String chemin) throws IOException{
			ModelGoogle.in =new StringBuffer();
		   InputStream is=ClassLoader.getSystemResourceAsStream(chemin);
		   BufferedReader ins=new BufferedReader(new InputStreamReader(is));
		    String line=ins.readLine();
		    while (line!=null){
		    		in.append(line);
		    		line=ins.readLine();
		    }
		    ins.close();
		    is.close();
	}
	
	public static void outFile() throws IOException{
		Writer fout= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(URLOUT,true)));
		fout.write(out.toString());
		fout.close();		
	}
	
	public static void ConstructModel(String urlCorpus) throws IOException{
		OutilsTexte.initOutilsTexte();
		ModelGoogle.initIn(urlCorpus);
		ModelGoogle.compteur=9;
		for (int i=2;i<9;i++){
			ModelGoogle m=new ModelGoogle(i);
			m.start();
		}
		while (ModelGoogle.compteur>2);
		ModelGoogle.outFile();
	}
	
	public static float[] CalculCoefficients() throws IOException{
		ArrayList[] listes=new ArrayList[7];
		for (int i=0;i<listes.length;i++){
			listes[i]=new ArrayList();
		}
		 InputStream is=ClassLoader.getSystemResourceAsStream(URLOUT);
		  BufferedReader in=new BufferedReader(new InputStreamReader(is));
		   String line=in.readLine();
		   StringTokenizer st;	   
			while(line!=null){
				st=new StringTokenizer(line);
				String s1=st.nextToken();
				String s2=st.nextToken();
				listes[(Integer.parseInt(s1)-2)].add(s2);
			    line=in.readLine();
			}  		  
			in.close();
			is.close();
			float[] coefficients=new float[7];
			long coeftmp;
			for (int i=0;i<7;i++){
				coeftmp=0;
				for (int j=0;j<listes[i].size();j++){
					coeftmp=coeftmp+Integer.parseInt((String)listes[i].get(j));
				}
				coefficients[i]=coeftmp/listes[i].size();
			}
			return coefficients;
	}
	
	
	public static void main(String[] args) throws IOException{
		OutilsTexte.initOutilsTexte();
		ModelGoogle.initIn("ressources/CorpusModel.txt");
		ModelGoogle.out=new StringBuffer();
		ModelGoogle.compteur=9;
		for (int i=2;i<9;i++){
		ModelGoogle m=new ModelGoogle(i);
			m.start();
		}
		while (ModelGoogle.compteur>2);
		System.out.println("fin des threads");
		ModelGoogle.outFile();
		float[] coefs=ModelGoogle.CalculCoefficients();
		for (int i=0;i<coefs.length;i++){
			System.out.println(coefs[i]);
		}
		
	}
}
