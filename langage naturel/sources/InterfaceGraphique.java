/*
 * Created on 22 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sources;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author aurelie
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InterfaceGraphique extends JFrame implements ActionListener{
    private final int longueurMax=50;
    private JPanel leTout;
    private JPanel zoneRequete;
    private JPanel zoneResultats;
    private JButton launch;
    private JTextField requete;
    private JScrollBar scrollbar;
    private JTextArea resultats;
    private JRadioButton yahoo;
    private JRadioButton google;
    private JPanel zoneChoix;
    private JScrollPane scrollpane;
    private InterfaceRequete iR;
    private JLabel mxResults;
    private JTextField chxmresults;
    
  public InterfaceGraphique(){
      iR=new InterfaceRequete();
      Container contenu=getContentPane();
      this.setSize(800,450);
      leTout=new JPanel();
      leTout.setPreferredSize(new Dimension(800,200));
     launch=new JButton("ok");
     launch.addActionListener(this);
     yahoo=new JRadioButton("yahoo");
     google=new JRadioButton("google");
     mxResults=new JLabel("résultats");
     chxmresults=new JTextField(4);
     //a changer quand svetlana aura finis ...
     //yahoo.setEnabled(false);
     yahoo.addActionListener(this);
     google.addActionListener(this);
     google.setSelected(true);
     requete=new JTextField(longueurMax);
     requete.setEditable(true);
     requete.setHorizontalAlignment(JTextField.LEFT);
     requete.addActionListener(this);
     zoneRequete=new JPanel();
     zoneRequete.setPreferredSize(new Dimension(800,100));
     zoneRequete.add(requete);
     zoneRequete.add(launch);
     zoneChoix=new JPanel();
     zoneChoix.setPreferredSize(new Dimension(600,30)); 
     zoneChoix.add(yahoo);
     zoneChoix.add(google);
     zoneChoix.add(chxmresults);
     zoneChoix.add(mxResults);
     zoneRequete.add(zoneChoix);
     scrollbar = new JScrollBar();
 	 scrollbar.setPreferredSize(new Dimension(20,200));
     resultats = new JTextArea(20,70);
     resultats.setLineWrap(true);
	scrollpane= new JScrollPane(resultats);
	scrollpane.setPreferredSize(new Dimension(700,240));
	scrollpane.setVerticalScrollBar(scrollbar);
	leTout.add(zoneRequete);
    leTout.add(scrollpane); 
    contenu.add(leTout);
  }
  
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
  
  	public static void messageErreur(String s){
  	  JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(frame,s,"warning",JOptionPane.WARNING_MESSAGE);
  	    };
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        	if (source==google){
        	    yahoo.setSelected(false);
        	}
        if (source==yahoo){
            google.setSelected(false);
        }
        if (source==launch){
            int nbResults=10;
            try {
                nbResults=Integer.parseInt(chxmresults.getText());
                if (google.isSelected()){
                		String reqTmp=requete.getText();
                    resultats.setText(iR.launchRequeteGoogle("lan-en",nbResults,reqTmp,"\\W"));
                      }
            }
            catch (NumberFormatException e1) {
              InterfaceGraphique.messageErreur("veuillez entrer un entier");
            }
        }
    }
    public static void main(String[] args){
       InterfaceGraphique iG = new InterfaceGraphique();
    iG.setVisible(true);
    }
}
