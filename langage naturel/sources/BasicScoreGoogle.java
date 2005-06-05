/*
 * Created on 15 mai 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sources;

import java.io.IOException;

/**
 * @author aureliezara
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BasicScoreGoogle implements Score{
	static float[] coefficients;
	int score=0;
	public BasicScoreGoogle(){}

	/* (non-Javadoc)
	 * @see sources.Score#giveScore(java.lang.String)
	 */
	public int giveScore(RequeteGoogle req) {
		int nb_grammes=req.nbMots;
		int nbResultats=req.resultat.nbResultats;
		float ideal=coefficients[nb_grammes-2];
		System.out.println(ideal);
		System.out.println(nbResultats);
		score =(int) ((nbResultats/ideal)*100.0);
		if (score>100 )
			score=100;
		return score;
	}

	public static void initCoefficients(){
		try {
			coefficients=ModelGoogle.CalculCoefficients();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
