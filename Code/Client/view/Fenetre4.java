package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;
import control.*;

/**
 * une class de fenetre pour view4
 * fonctionalité : choisir le voeux dans autre parcours préférentiel
 * @author Peiyao LI
 * @since 20/12/2022
 * @version 0.2
 */
public class Fenetre4{ //extends Fenetre{
    public static void main(String[] args){
        new Fenetre4("peli","a,b,c,d,e,f",null);
    }
    //Attribus
    private String nom;
    private String listeModuleAP; 
    private PrintWriter out;
    private String voeux2 = "";
    protected Integer choix;
    ArrayList<JComboBox<String>> comboBoxs = new ArrayList<>() ;

    public Fenetre4(String nom,String listeModuleAp, PrintWriter out ){
        this.nom = nom;
        this.listeModuleAP = listeModuleAp;
        this.out =out;
        //creation du frame
        JFrame Fenetre4 = new JFrame();
        Fenetre4.setSize(500,400);
        Fenetre4.setLocation(0,0);

        //creation un panel
        JPanel panel = new JPanel();   

        //creation un container
        Container container = Fenetre4.getContentPane();
        panel.setLayout(null);

        //Affichage de l'ID 
        JLabel ID = new JLabel("ID:"+nom);
        ID.setBounds(10,20,80,25);
        panel.add(ID);

        //Affichage de le choix
        JLabel Parcours = new JLabel("Parcours2:");
        Parcours.setBounds(10,40,80,25);
        panel.add(Parcours);

        //Affichage du indication
        JTextArea Indication = new JTextArea();
         
        Indication.setLineWrap(true);
        Indication.setEditable(false);
        Indication.setBackground(new Color(238,238,238));
        Indication.setText("La première option est le module le plus souhaité, et ainsi de suite, pour sélectionner les 8 modules que vous souhaitez le plus suivre.");
        Indication.setBounds(10,60,350,45);
        panel.add(Indication);

        for(int i = 0;i<4;i++){
            JLabel label = new JLabel( (i+1) + ".");
            label.setBounds(10,120+i*20,80,25);
            panel.add(label);
            JComboBox<String> cb = new JComboBox<String>();
        
            //String[] liste = {"1","2","3"};
            String[] liste = Message.cutMessage(listeModuleAp);
            for (String item : liste){
	            cb.addItem(item);
            }
            cb.setFont(new Font("宋体", Font.PLAIN, 14));
            cb.setBounds(30, 120+i*20, 470, 23);
            panel.add(cb);
            comboBoxs.add(cb);
        }

        //creation du bouton
        JButton b = new JButton("Suivant");
        b.setBounds(300,300,100, 25);
        b.addActionListener(event -> {
            Boolean send = true;
            errorDetect:
            for(int i = 0;i < 4;i++){
                for(int j = 3;j>i;j--){
                    if(comboBoxs.get(i).getSelectedIndex() == comboBoxs.get(j).getSelectedIndex()){
                        new errorFrame();
                        Fenetre4.dispose();
                        send =false;
                        break errorDetect;

                    }    
                }
            }
            if(send){
            String[] liste = Message.cutMessage(listeModuleAp);
            String[] text = new String[4];
            for(int i = 0;i<4;i++){
                choix = comboBoxs.get(i).getSelectedIndex();
                text[i] = liste[choix];
            }  
            voeux2 = Message.putinfoEnsemble(text);
            
            Message m =new Message(4,voeux2);
            this.out.println(m.toStirng());
            this.out.flush();
            Fenetre4.dispose();
        }

        });
        panel.add(b);

        container.add(panel);

        

        //setVisible
        Fenetre4.setVisible(true);

        //close the program when close the window
        Fenetre4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * pour avertir le client il y a répétition
     */
    public final class errorFrame{

        public errorFrame(){
                    JFrame errorF = new JFrame();
                    errorF.setSize(500,400);
                    errorF.setLocation(0,0);

                    //creation un panel
                    JPanel errorPanel = new JPanel();   
                    errorF.setSize(500,400);
                    errorF.setLocation(0,0);

                    //creation un container
                    Container errorContainer = errorF.getContentPane();
                    errorPanel.setLayout(null);
                    //Affichage de l'erreur 
                    JLabel error = new JLabel("Erreur");
                    error.setBounds(10,20,80,25);
                    errorPanel.add(error);

                    //Affichage du indication
                    JTextArea tf = new JTextArea();
     
                    tf.setLineWrap(true);
                    tf.setEditable(false);
                    tf.setBackground(new Color(238,238,238));
                    tf.setText("Le même cours apparaît dans vos options, veuillez modifier.");
                    tf.setBounds(10,60,350,45);
                    errorPanel.add(tf);

                    //creation du bouton
                    JButton back = new JButton("D'accord");
                    back.setBounds(200,200,80, 25);
                    errorPanel.add(back);
                    back.addActionListener(event1 -> {
                        new Fenetre4(nom, listeModuleAP, out);
                        errorF.dispose();
                        
                    });



                    errorContainer.add(errorPanel);

                    //setVisible
                    errorF.setVisible(true);

                    //close the program when close the window
                    errorF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



                }
            }
   
}