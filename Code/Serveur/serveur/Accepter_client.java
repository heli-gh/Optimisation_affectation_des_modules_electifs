package serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;
import model.*;
import control.*;
/**
 * Accepter_client  met en œuvre la interface Runnable, qui est principalement responsable de la connexion avec un 
 * utilisateur exclusif spécifique et permet l'échange d'informations. Mise en œuvre des fonctions de notre projet 
 * actuel, enregistrement et attribution des Modules étudies
 * @author Hengshuo LI
 * @since 18/22/2022
 * @version 0.1
 */

public class Accepter_client implements Runnable {
    
    private Socket socket;
    private Database database;
    private Etudiant etudiant= new Etudiant();
    BufferedReader in;
    PrintWriter out;
    /**
     * constructeur
     * @param s Socket lié aux client 
     * @param database tous information de système on créer.
     */
    public Accepter_client(Socket s, Database database)
    {
        this.socket = s;
        this.database = database;
        try{
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        out = new PrintWriter(this.socket.getOutputStream());
        etudiant.setOut(out);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        try{
            out.println("Vous êtes connecté !");
            out.flush();
        }
        catch(Exception et)
        {
            et.printStackTrace();
        }
        boolean close = false ;
        while ( close == false)
        {
            try{
                String message;
                //while (in.readLine() == null){}
                message = in.readLine();
               // System.out.print(message);
                Message m = new Message(message);
                switch(m.gettype())
                {
                    case 1 :  
                        Message1 m1 =new Message1(m) ;
                        m1.enregistreName(etudiant);
                        out.println(Message1.envoyerNomNomParcours(etudiant, database));
                        out.flush();
                        break;
                    case 2 :
                        Message2 m2 =new Message2(m) ;
                        m2.enregistreParcourP(etudiant, database);
                        out.println(m2.envoyerModuleDeParcoursP(database));
                        out.flush();
                        break;
                    case 3 :
                        Message3 m3 =new Message3(m);
                        m3.enregistreVoeux1(etudiant, database);
                        out.println(m3.envoyerModuleDeAP(etudiant, database));
                        out.flush();
                        break;
                    case 4 :

                        Message4 m4 =new Message4(m);
                        m4.enregistreVoeux2(etudiant, database);
                        out.println(m4.envoyertousinfo(etudiant));
                        out.flush();
                        break;

                    case 5 :
                        Message5 m5=new Message5(m);
                        
                        out.println(m5.action(etudiant, database));
                        out.flush();
                        if(m5.getconfirm() == 0) out.println(Message1.envoyerNomNomParcours(etudiant, database));
                        out.flush();
                        break;
                    case 6 :
                        database.optimisation();
                        //out.println(Message6.EnvoyerAffectation(etudiant));
                        //out.flush();
                        break;
                    default:

                        System.out.print("bad input exceed bound");
                        break;

                            


                }



            


            }
            catch(IOException e)
            {
                e.printStackTrace();
                close =true;
                try{
                    this.socket.close();
                }
                catch(IOException ed)
                {
                    ed.printStackTrace();
                }
                
            }
            
        }   
    }

}

    

