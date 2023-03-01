package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;

import javax.swing.*;
import control.*;

/**
 * une class de fenetre pour view5
 * fonctionalité : confirmer les choix on fait
 * @author Peiyao LI
 * @since 20/12/2022
 * @version 0.2
 */

public class Fenetre5{ 
    public static void main(String[] args){
        new Fenetre5("a,b,c,d,e,f,g", null);
    }
    //Attribus
    String[] info;
    PrintWriter out;
    //public static void main(String[] args){
    public Fenetre5(String tousinfo ,PrintWriter out){
        this.out = out;
        //creation du frame
        JFrame Fenetre5 = new JFrame();
        Fenetre5.setSize(600,600);
        Fenetre5.setLocation(0,0);

        //creation un panel
        JPanel panel = new JPanel();   

        //creation un container
        Container container = Fenetre5.getContentPane();
        panel.setLayout(null);

        //Affichage de l'ID 
        info = Message.traiteMessage(tousinfo);
        JLabel ID = new JLabel("ID:"+info[0]);
        ID.setBounds(10,20,80,25);
        panel.add(ID);

        //Affichage de le titre
        JLabel Confirmation = new JLabel("Confirmation");
        Confirmation.setBounds(50,50,100,25);
        panel.add(Confirmation);

        //Affichage de voeux1
        JLabel voeux1 = new JLabel("Voeux1:"+info[1]);
        voeux1.setBounds(10,80,200,25);
        panel.add(voeux1);
        String[] v1 = Message.cutMessage(info[2]);
        for(int i = 0;i < v1.length;i++ ){
            JLabel Voeux = new JLabel((i+1) + "." + v1[i]);
            Voeux.setBounds(10,100+i*30,350,25);
            panel.add(Voeux);
        }

        //Affichage de voeux2
        JLabel voeux2 = new JLabel("Voeux2:");
        voeux2.setBounds(10,350,200,25);
        panel.add(voeux2);
        String[] v2 = Message.cutMessage(info[3]);
        for(int i = 0;i < v2.length;i++ ){
            JLabel Voeux = new JLabel((i+1) + "." + v2[i]);
            Voeux.setBounds(10,380+i*30,350,25);
            panel.add(Voeux);
        }        
        

        //Bouton "Modifier"
        JButton Modifier = new JButton("Modifier");
        Modifier.setBounds(10,500,100,25);
        Modifier.addActionListener(event -> {
            Message m =new Message(5,"0");
            this.out.println(m.toStirng());
            this.out.flush();
            Fenetre5.dispose();

        });
        panel.add(Modifier);

        //Bouton "Confirmer"
        JButton Confirmer = new JButton("Confirmer");
        Confirmer.setBounds(200,500,120,25);
        Confirmer.addActionListener(event -> {
            Message m =new Message(5,"1");
            this.out.println(m.toStirng());
            this.out.flush();
            Fenetre5.dispose();

        });
        panel.add(Confirmer);


        container.add(panel);
        //setVisible
        Fenetre5.setVisible(true);

        //close the program when close the window
        Fenetre5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    
}
