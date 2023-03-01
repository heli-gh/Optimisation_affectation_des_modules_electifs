package serveur;
import java.io.IOException;
import java.net.*;

import model.*;

/**
 * Il s'agit du classe qui s'exécute côté serveur, qui génère la base de données principale(database) et qui crée threads 
 * d'exécution dédié pour chaque client qui s'y connecte.
 * @author Hengshuo LI
 * @since 22/12/2022
 * @version 0.0
 */

public class Serveurtest{
    public static void main (String[] args)
    {
        final int  maxclient =20;
        
        model.Module auto_non_lineaire = new model.Module("Automatique non linéaire",0);
        model.Module cal_sto_fin = new model.Module("Calcul stochastique pour la finance",0) ;
        model.Module con_pro_lo = new model.Module("Contraintes et programmation Logique",0) ;
        model.Module con_opt_sto = new model.Module("contrôle optimale Stocahstique et Application en Finance",0) ;
        model.Module mon_sim_num = new model.Module("Modelisation et simulation numérique",0) ;
        model.Module sim_disc = new model.Module("simulation discret",0) ;
        model.Module meth_num_onde = new model.Module("méthode numérique pour la Propagation de Fronts",0) ;
        model.Module meth_varia_t_i = new model.Module("méthodes Variationelle pour le Traitement d'images",0) ;
        model.Module model_app_pert_prob_inv = new model.Module("Modelistation appliquée:perturbations et problèmes inverses",0) ;
        model.Module equa_jaco = new model.Module("Equations de hamilton-Jacobi et application",0) ;
        
        model.Module crypto = new model.Module("cryptographie",1) ;
        model.Module heur_ai_di = new model.Module("Heuristique et aide à la décision",1) ;
        model.Module Image = new model.Module("Image",1) ;
        model.Module intro_IA_expli = new model.Module("introduction à l'IA Explicable",1) ;
        model.Module app_donn = new model.Module("approximation de données",1) ;
        model.Module ma_learn = new model.Module("Introduction to Machine Learning",1) ;
        model.Module in_metahe = new model.Module("introduction aux Métaheuritisque",1) ;
        model.Module sys_muti_agen = new model.Module("Système Multi-Agents",1) ;
        model.Module cal_par = new model.Module("calcul parallèle",1) ;
        model.Module pro_ori = new model.Module("Programmation Orientée-Objet Avancée",1) ;
        Parcours MMSN = new Parcours("MMSN");
        Parcours INFO = new Parcours("INFO");
        MMSN.setModule(auto_non_lineaire,cal_sto_fin,con_pro_lo,con_opt_sto,mon_sim_num,sim_disc,meth_num_onde,meth_varia_t_i,model_app_pert_prob_inv,equa_jaco);
        INFO.setModule(crypto,heur_ai_di,Image,intro_IA_expli,app_donn,ma_learn,in_metahe,sys_muti_agen,cal_par,pro_ori);
        Parcours[] listeParcours = {MMSN,INFO};
        Database database = new Database(listeParcours);
        int nb=0;

        Thread[] t =new Thread[maxclient];

        ThreadGroup group = new ThreadGroup("group");
        try{
            ServerSocket socket = new ServerSocket(2009);
            
            while (nb< maxclient)//on va tester le status de threads chaque fois pour accueillier toujours le maximum de client dans le prochaine version, maintenant seulemnt connecté 20 fois.
            {
                try{

                    Socket s=socket.accept();

                    t[nb] = new Thread(group,new Accepter_client(s, database));
                    t[nb].start();
                    nb++;
                    //nb=group.activeCount();

                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            for (int j=0 ; j<maxclient;j++)
            {
                try{
                    t[j].join();
                
                } catch (InterruptedException  e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            
            }
            socket.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}
