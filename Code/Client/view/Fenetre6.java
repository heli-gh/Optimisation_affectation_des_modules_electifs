package view;
import java.awt.*;
import java.io.PrintWriter;

import javax.swing.*;
import control.*;
import control.Message;

/**
 * une class de fenetre pour view6
 * fonctionalité : demander les infectation
 * @author Peiyao LI
 * @since 20/12/2022
 * @version 0.2
 */

public class Fenetre6{
    public static void main(String[] args){

    }
    //Attribus
    private PrintWriter out;
    private String[] info;

    public  Fenetre6(String tousinfo,PrintWriter out) {
        this.out = out;
        //creation du frame
        JFrame Fenetre6 = new JFrame();
        Fenetre6.setSize(500,600);
        Fenetre6.setLocation(0,0);

        //creation un panel
        JPanel panel = new JPanel();   

        //creation un container
        Container container = Fenetre6.getContentPane();
        panel.setLayout(null);

        //Affichage de l'ID 
        info = Message.traiteMessage(tousinfo);
        JLabel ID = new JLabel("ID:"+info[0]);
        ID.setBounds(10,20,80,25);
        panel.add(ID);

        //Affichage de le titre
        JLabel Confirmation = new JLabel("Votre Voeux");
        Confirmation.setBounds(50,50,150,25);
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
        //Bouton "Demander l'affectation"
        JButton Demander = new JButton("Demander l'affectation");
        Demander.setBounds(200,500,200,25);
        
        Demander.addActionListener(event -> {
            Message m =new Message(6," ");
            this.out.println(m.toStirng());
            this.out.flush();
            Fenetre6.dispose();});
            
        panel.add(Demander);


        container.add(panel);
        //setVisible
        Fenetre6.setVisible(true);

        //close the program when close the window
        Fenetre6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
    }
}