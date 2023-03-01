package control;

import model.*;

/**
 * une controleur pour etudiant enregistrer le voeux sur autre parcours et envoyer tous les information de étudiant
 * @author Hengshuo LI
 * @since 17/12/2022
 * @version 0.1
 */
public class Message4 {

    private String voeux2; 


    public Message4(Message m)
    { 
        
        this.voeux2 = m.getcontent();
        

    }

    public void enregistreVoeux2(Etudiant e,Database database)
    {
        String[] modules= this.voeux2.split(",");
        model.Module[] v = new model.Module[4];
        for (int i=0;i<modules.length;i++)
            v[i]=database.chercheModule(modules[i]);

        e.setVoeux2(v);
    }

    public String envoyertousinfo(Etudiant e)
    {
        return e.toString();
    }

    

    
}
