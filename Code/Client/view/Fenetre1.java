package view;
import java.awt.*;
import java.io.PrintWriter;

import control.*;
import javax.swing.*;

/**
 * une class de fenetre pour le view1
 * fonctionnalité : enregistre le nom de client
 * @author Peiyao LI
 * @since 20/12/2022
 * @version 0.2
 */

public class Fenetre1 {

    private PrintWriter out ;
    private JTextField tf; 
    public static void main(String[] args){


    }

    public Fenetre1(PrintWriter out)
    {

        this.out  = out;
        //creation du Fenetre1
        JFrame Fenetre1 = new JFrame("Systeme de selection des cours");
        Fenetre1.setSize(400,300);
        Fenetre1.setLocation(0,0);
        //creation un panel
        JPanel panel = new JPanel();   

        //creation un container
        Container container = Fenetre1.getContentPane();
        panel.setLayout(null);

        //Affichage de l'ID 
        JLabel ID = new JLabel("ID:");
        ID.setBounds(80,90,80,25);
        panel.add(ID);

        //creation du textfield
        tf = new JTextField();
        tf.setBounds(100,90,80, 25);
        panel.add(tf);
        
        //creation du bouton
        JButton b = new JButton("Entrer");
        b.setBounds(200,200,80, 25);
        panel.add(b);
        b.addActionListener(event -> {
            Message m =new Message(1,tf.getText());
            this.out.println(m.toStirng());
            this.out.flush();
            Fenetre1.dispose();
            
        });
        //ajouter panel dans container
        container.add(panel);

        //setVisible
        Fenetre1.setVisible(true);

        //close the program when close the window!!!!error: the clienttest program don't close!!!
        Fenetre1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }




}