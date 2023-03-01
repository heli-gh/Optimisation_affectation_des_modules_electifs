package view;
import java.awt.*;
//import java.util.Enumeration;
//import control.*;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.swing.*;
import control.*;
/**
 * une class de fenetre pour view2 
 * fonctionnalité : selectionner le parcours préférentiel
 * @author Peiyao LI
 * @since 20/12/2022
 * @version 0.2
 */

public class Fenetre2 {//extends Fenetre{
    public static void main(String[] args){
        
    }
//Attribus
    String choix; 
    ButtonGroup group;   
    String nom;
    String[] listeNomParcours;
    PrintWriter out;
//Methode
    public Fenetre2(String nom, String listeNomParcour, PrintWriter out){
        this.nom = nom;
        this.out = out;
        this.listeNomParcours= Message.cutMessage(listeNomParcour);

        //creation du Fenetre2
        JFrame Fenetre2 = new JFrame("Systeme de selection des cours");
        Fenetre2.setSize(400,300);
        Fenetre2.setLocation(0,0);
        //creation un panel
        JPanel panel = new JPanel();   

        //creation un container
        Container container = Fenetre2.getContentPane();
        panel.setLayout(null);

        //Affichage de l'ID 
        JLabel ID = new JLabel("ID:"+nom);
        ID.setBounds(10,20,80,25);
        panel.add(ID);

        //Affichage de le choix
        JLabel Parcours = new JLabel("Parcours:");
        Parcours.setBounds(10,50,80,25);
        panel.add(Parcours);
        //creation des Boutons
        group = new ButtonGroup();
        for(int i = 0;i < this.listeNomParcours.length;i++){
            JRadioButton rdobButton = new JRadioButton(this.listeNomParcours[i]);
            rdobButton.setBounds(20,80+i*30,80,25);
            panel.add(rdobButton);
            group.add(rdobButton);
        }
        /*JRadioButton rdoMMSN = new JRadioButton(this.listeNomParcours[0]);
        rdoMMSN.setBounds(20,80,80,25);
        JRadioButton rdoMSRO = new JRadioButton(this.listeNomParcours[1]);
        rdoMSRO.setBounds(20,110,80,25);
        panel.add(rdoMMSN);
        panel.add(rdoMSRO);

        group = new ButtonGroup();
        group.add(rdoMMSN);
        group.add(rdoMSRO);*/

        //creation du bouton
        JButton b = new JButton("Suivant");
        b.setBounds(200,200,100, 25);
        b.addActionListener(event -> {

            for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {

                AbstractButton button = (AbstractButton) buttons.nextElement();
                
                if (button.isSelected()) {
                
                choix = button.getText();

                break;
                
                }
            }    
            
            Message m =new Message(2,choix);
            this.out.println(m.toStirng());
            this.out.flush();
            Fenetre2.dispose();

        });
        panel.add(b);

        
        //ajouter panel dans container
        container.add(panel);

        //setVisible
        Fenetre2.setVisible(true);

        //close the program when close the window
        Fenetre2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   // }

   

    }
    /*public void actionPerformed(ActionEvent e){
    String buttonName = e.getActionCommand();
    if(buttonName.equals("Suivant")){
        for (Enumeration buttons = group.getElements(); buttons.hasMoreElements();) {

            AbstractButton button = (AbstractButton) buttons.nextElement();
            
            if (button.isSelected()) {
            
            choix = button.getText();
            
            }
            
            }
        Message2 message2 = new Message2(choix);
        message2.enregistreParcourP(null, null);//??

        }

    }*/
}
    
