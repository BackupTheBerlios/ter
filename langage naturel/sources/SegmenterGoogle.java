/*
 * Created on Mar 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sources;

import jregex.Matcher;
import jregex.Pattern;
import jregex.RETokenizer;
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
	
public void sentencerGoogle(){
	String tag ="<.*?>";
	Pattern p=new Pattern(tag);
	StringBuffer sb=new StringBuffer();
	RETokenizer t=p.tokenizer(page);
	while (t.hasMore()){
		sb.append(t.nextToken());
		sb.append(" ");	
	}
	System.out.println(sb.toString());
}
	


}
