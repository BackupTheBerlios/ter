/*
 * Created on Mar 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sources;


import jregex.Pattern;
import jregex.Replacer;

import com.google.soap.search.GoogleSearch;
import com.google.soap.search.GoogleSearchFault;

/**
 * @author azara
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SegmenterGoogle extends Segmenter {
	String page;
	
	public SegmenterGoogle(ElementResultatGoogle er){
		String key=RequeteGoogle.getKey();
		GoogleSearch cache = new GoogleSearch();
		cache.setKey(key);
		try {
			page= new String(cache.doGetCachedPage(er.url));
		} catch (GoogleSearchFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void getTexteFromHtml(){
    Pattern p=new Pattern("(.*<body[^>]*>)|(.*<BODY[^>]*>)","s");
    Replacer r=p.replacer("");
    page=r.replace(page);
     p=new Pattern("<[^>]*>","s");
    r=p.replacer("");
	page=r.replace(page);
	p=new Pattern("(\\s\\s)+","s");
	r=p.replacer("");
	page=r.replace(page);
	p=new Pattern("&nbsp|&#160|&#151|&#8482");
	r=p.replacer(" ");
	p=new Pattern("");
	page=r.replace(page);
	System.out.println(page);
	
}
public void replaceUrlMailExtension(){
    String extension="\\s(.)*[.](jpg|jpeg|gif)";
    
}
public void sentencerGoogle(){
   
}
	


}
