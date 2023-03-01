package control;

import model.*;

/**
 * une controleur pour etudiant enregistrer le parcours préférentiel et envoyer les nom de module dans parcours 
 * préférentiel 
 * @author Hengshuo LI
 * @since 17/12/2022
 * @version 0.1
 */
public class Message2 {

    private String nomdeParcours1 ;


    public Message2(Message m )
    { 
        
        this.nomdeParcours1 = m.getcontent();
        
        
        

    }

    public void enregistreParcourP(Etudiant e,Database database)
    {
        e.setParcourP(database.chercheParcours(this.nomdeParcours1));
    }

    public String envoyerModuleDeParcoursP(Database database)
    {
        return database.listeModuleDansParcours(this.nomdeParcours1);
    }

    
}
