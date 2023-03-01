package client;

import java.io.*;
import java.net.*;
import control.*;
import view.*;
/**
 * Le classe Clienttest est le programme principal que notre client final exécute, ce programme intègre l'affichage de 
 * l'interface et la connexion des informations au client. On l'appelle ainsi parce que nous n'étions pas très sûrs que  * notre programme principal fonctionnerait.
 * 
 * @author Hengshuo LI
 * @version 0.9
 * @since 22/12/2022
 * 
 */

public class Clienttest{



    

    public static void main(String[] args)
    {
        Socket socketclient;
        BufferedReader in;
        PrintWriter out;
        try{

            socketclient = new Socket(InetAddress.getLocalHost(),2009);
            System.out.println("demande de connexion");
            

            in = new BufferedReader(new InputStreamReader(socketclient.getInputStream()));
            out = new PrintWriter(socketclient.getOutputStream());
            
            String info =in.readLine();
            System.out.println(info);
            int confirm =0;
            String tousinfo ="";
            String nom = "";
            
            Fenetre1 f1= new Fenetre1(out);
            while (confirm ==0 )
            {   
                String nomEtlisteNomparcours= in.readLine();
                /*while(nomEtlisteNomparcours == null){
                 nomEtlisteNomparcours = in.readLine();
                }*/
                
                String[] pre = Message.traiteMessage(nomEtlisteNomparcours);
                nom = pre[0];
                String listeNomparcours= pre[1];
                Fenetre2 f2 =  new Fenetre2(nom,listeNomparcours,out);
                String listeModulePP = in.readLine();
                Fenetre3 f3 = new Fenetre3(nom,listeModulePP,out);
                String listeModuleAp = in.readLine();
                Fenetre4 f4 = new Fenetre4(nom,listeModuleAp , out);
                tousinfo = in.readLine();
                Fenetre5 f5 =new Fenetre5(tousinfo , out);
                String confirmstring = in.readLine();
                confirm = Integer.parseInt(Message.traiteMessage(confirmstring)[0]) ;

            }

            Fenetre6 f6=new Fenetre6(tousinfo,out);
            String Affectation =in.readLine();
            Fenetre7 f7=new Fenetre7(out , Affectation,nom);
            while(true)
            {
                Affectation =in.readLine();
                f7.dispose();
                f7 =new Fenetre7(out , Affectation,nom);
            }










            //socketclient.close();

        }catch(IOException e)
        {
            e.printStackTrace();
            System.out.println(" il y a eu déja 20 étudiant sur connection ou serveur n'est pas lancé,byebye");
        }

    }
}