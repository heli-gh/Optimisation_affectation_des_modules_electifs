package control;

import model.*;

/**
 * une controleur pour etudiant enregistrer le voeux sur parcours préférentiel et envoyer les nom de module de autre 
 * parcours 
 * @author Hengshuo LI
 * @since 17/12/2022
 * @version 0.1
 */

public class Message3 {

    private String voeux1;


    public Message3(Message m)
    { 
        this.voeux1 = m.getcontent();
        
    
    }

    public void enregistreVoeux1(Etudiant e,Database database)
    {
        String[] modules= this.voeux1.split(",");
        model.Module[] v = new model.Module[8];
        for (int i=0;i<modules.length;i++)
            v[i]=database.chercheModule(modules[i]);

        e.setVoeux1(v);
    }

    public String envoyerModuleDeAP(Etudiant e,Database database)
    {
        return database.listeModuleDansAutreParcours(e.getParcoursP());
    }

    
}
