package control;
import model.*;

/**
 * une controleur pour etudiant enregistrer le nom et envoyer le nom de étudiant et les nom de parcours 
 * @author Hengshuo LI
 * @since 17/12/2022
 * @version 0.1
 */

public class Message1 {

    private String nom ;
   


    public Message1(Message m )
    {
        
        nom=m.getcontent() ;
        

    }

    public void enregistreName(Etudiant e)
    {
        e.setName(nom);
    }

    public static String envoyerNomNomParcours( Etudiant e ,Database database)
    {
        return e.getName()+"/" +database.listeNomParcours();
    }





    
}
