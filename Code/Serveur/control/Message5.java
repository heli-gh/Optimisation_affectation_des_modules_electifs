package control;

import model.Database;
import model.Etudiant;

/**
 * une controleur pour database enregistrer le client 
 * @author Hengshuo LI
 * @since 17/12/2022
 * @version 0.1
 */

public class Message5 {

    private int confirm;

    public Message5(Message m){

        this.confirm =Integer.parseInt(m.getcontent());
    }
    public int getconfirm()
    {
        return this.confirm;
    }

    public static void confirmer(Etudiant e , Database database)
    {
        database.enregistreEtudiantDansParcours(e);
    }

    public String action(Etudiant e , Database database)
    {
        if (this.confirm ==1) confirmer(e, database);
    

        return Integer.toString(this.confirm);

        
    }

    
}
