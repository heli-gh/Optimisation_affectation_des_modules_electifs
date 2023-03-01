package view;
import java.awt.*;
import java.io.PrintWriter;

import javax.swing.*;
import control.*;

/**
 * une class de view7
 * fonctionalité: afficher l'affectation et demander l'affectation 
 * @author Peiyao LI
 * @since 20/12/2022
 * @version 0.2
 */

public class Fenetre7 extends JFrame{
    public static void main(String[] args){
        //Fenetre7 f =new Fenetre7();
    }
    //Attribus
    PrintWriter out;
    String Affectation;
    String nom;
    public Fenetre7(PrintWriter out,String Affectation,String nom){
        super();
        this.out = out;
        this.Affectation = Affectation;
        this.nom = nom;
        //creation du frame
        //JFrame Fenetre7 = new JFrame();
        this.setSize(500,600);
        this.setLocation(0,0);

        //creation un panel
        JPanel panel = new JPanel();   

        //creation un container
        Container container = this.getContentPane();
        panel.setLayout(null);

        //Affichage de l'ID 
        JLabel ID = new JLabel("ID:"+nom);
        ID.setBounds(10,20,80,25);
        panel.add(ID);

        //Affichage de le titre
        JLabel Confirmation = new JLabel("Votre Affectation");
        Confirmation.setBounds(50,50,200,25);
        panel.add(Confirmation);

        String[] affectation =  Message.cutMessage(Affectation);
        //Affichage de voeux1
        for(int i = 0;i < affectation.length;i++ ){
            JLabel Voeux = new JLabel((i+1) + "." + affectation[i]);
            Voeux.setBounds(10,100+i*30,350,25);
            panel.add(Voeux);
        }

        //Bouton "Demander l'affectation"
        JButton Demander = new JButton("Demander l'affectation");
        Demander.setBounds(200,500,200,25);
        
        Demander.addActionListener(event -> {
            Message m =new Message(6," ");
            this.out.println(m.toStirng());
            this.out.flush();
            this.dispose();});

        panel.add(Demander);

        container.add(panel);
        //setVisible
        this.setVisible(true);

        //close the program when close the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
   

}