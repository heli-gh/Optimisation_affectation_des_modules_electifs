package model;

import java.io.IOException;
import java.io.PrintWriter;
/**
 * Etudiant est un class enregistre tous information besoin de client
 * @author Hengshuo LI
 * @since 15/12/2022
 */

public class Etudiant {
    private String name;// le nom de etudiant
    private Parcours parcoursP;//parcours preferentiel
    private Module[] voeux1 ;// voeux sur le parcours preferentiel
    private Module[] voeux2 ;// voeux sur le parcours preferentiel
    private Module[] affectation = new Module[6];
    private PrintWriter out;//Aide nous à réaliser  de envoyer affectation en même temps

    /**
     * constructeur 
     */
    public Etudiant()//correct
    {

    }
    /**
     * enregistre le output
     * @param out le output
     */
    public void setOut(PrintWriter out)
    {
        this.out = out;

    }
    /**
     * enregistre le nom
     * @param name le nom
     */

    public void setName( String name)//correct
    {
        this.name = name;
    }
    /**
     * retourner le nom de etudiant
     * @return
     */
    public String getName() //correct
    {
        return this.name;
    }
    /**
     * enregistre le parcours prférentiel 
     * @param parcoursP le parcours préférentiel
     */
    public void setParcourP(Parcours parcoursP) //correct
    {
        this.parcoursP = parcoursP;
    }
    /**
     * envoyer le parcours préférentiel
     * @return le parcours préférentiel
     */
    public Parcours getParcoursP() //correct
    {
        return this.parcoursP;
    }

    /**
     * envoyer le nom de parcours préférentiel
     * @return le nom de parcours préférentiel
     */
    public  String getNomParcourP() //correct
    {
        return this.parcoursP.getNomParcours();
    }
    /**
     * enregistre le liste de voeux dans parcours préférentiel
     * @param voeux1 le liste de voeux dans parcours préférentiel
     */
    public void setVoeux1 (Module[] voeux1)//correct
    {
        this.voeux1 = voeux1;
    }

    /**
     * envoyer les noms de Modules de voeux dans parcours préférentiel
     * @return les noms de Modules de voeux 
     */
    public String getNomVoeux1() //correct
    {
        Module[] alModules = this.voeux1;
        if (alModules == null) return "null";

        int iMax = alModules.length ;

        if (iMax == 0) return "";

        StringBuilder b = new StringBuilder();
        for (int i =0 ; i<iMax ; i++)
        {
            b.append(alModules[i]);

            if (i != iMax-1 ) b.append(",");
        }


        return b.toString();
    }

    /**
     * enregistre la liste de voeux de Modules dans autre parcours  
     * @param voeux2 la liste de voeux de Modules dans autre parcours 
     */
    
    public void setVoeux2 (Module[] voeux2)//correct
    {
        this.voeux2 = voeux2;
    }

    /**
     * envoyer les nom de Modules dans voeux sur autr parcours 
     * @return les nom de Modules sous forme a,b,c,...
     */
    public String getNomVoeux2()//correct
    {
        Module[] alModules = this.voeux2;
        if (alModules == null) return "null";

        int iMax = alModules.length ;

        if (iMax == 0) return "";

        StringBuilder b = new StringBuilder();
        for (int i =0 ; i<iMax ; i++)
        {
            b.append(alModules[i]);

            if (i != iMax-1 ) b.append(",");
        }


        return b.toString();
    }
    /**
     * cherche le coût pours cette affectation
     * @param aT les liste de numéro de idendité de module
     * @return le coût 
     */

    public int chercheCout(int[] aT)// maybe correct
    {
        int cout=0;
        for (int i=0 ; i<6 ;i++)
        {
            if (i<4)
            {   int j =0;
                while (j<8 && aT[i] != voeux1[j].getId()){
                    j++;
                    
                }
                if (j<8) cout += j*j;
                else cout += 10*j*j;
            }
            else{
                int j =0;
                while (j<4 && aT[i] != voeux2[j].getId()){
                    j++;
                    
                }
                if (j<4) cout += j*j;
                else cout += 10*j*j;

            }
        }
        return cout;

    }
    /**
     * enregistre les Modules de affectation
     * @param affectation liste de Module de affectation
     */

    public void setAffectation(Module[] affectation)
    {
        this.affectation = affectation;

    }
    /**
     * envoyer affecation vers le client par le output 
     * @throws IOException
     */
    public void EnvoyerAffectation() throws IOException
    {
        out.println(this.getAffectation());
        out.flush();
    }
    

    /**
     * obtenir les modules de affectation
     * @return un String contient les nom de Modules sous forme a,b,c,...
     */

    public String getAffectation()
    {
        Module[] alModules = this.affectation;
        if (alModules == null) return "null";

        int iMax = alModules.length ;

        if (iMax == 0) return "";

        StringBuilder b = new StringBuilder();
        for (int i =0 ; i<iMax ; i++)
        {
            b.append(alModules[i]);

            if (i != iMax -1 ) b.append(",");
        }


        return b.toString();
    }
    /**
     * fomre le nom de Etudiant/nom de parcours prférentiel/ voeux sur parcours préférentiel/voeux sur autre parcours 
     */

    public String toString()
    {
        return getName() +"/"+getNomParcourP()+"/" +getNomVoeux1()+"/"+getNomVoeux2();
    }
    //unit test  
    public static void main(String args[])
    {
        Module auto_non_lineaire = new Module("Automatique non lineaire",0);
        Module cal_sto_fin = new Module("Calcul stochastique pour la finance",0) ;
        Module con_pro_lo = new Module("Contraintes et programmation Logique",0) ;
        Module crypto = new Module("cryptographie",1) ;
        Module Image = new Module("Image",1) ;
        Module intro_IA = new Module("introduction à l'IA",1) ;
        Parcours MMSN = new Parcours("MMSN");
        Parcours INFO = new Parcours("INFO");
        MMSN.setModule(auto_non_lineaire,cal_sto_fin,con_pro_lo);
        INFO.setModule(crypto,Image,intro_IA);
        Parcours[] listeParcours = {MMSN,INFO};
        Database database = new Database(listeParcours);
        
        Etudiant wentai = new Etudiant();
        wentai.setName("FU Wentai");
        System.out.println(wentai.getName());
        wentai.setParcourP(INFO);
        System.out.println(wentai.getNomParcourP());
        wentai.setVoeux1(database.retournerlisteModule(3, wentai.getParcoursP().getNomModules()));

        System.out.println(wentai.getNomVoeux1());
        int[] affectationliste = {11,12,13};

        //System.out.println(wentai.chercheCout(affectationliste));
        
        database.enregistreEtudiantDansParcours(wentai);
        System.out.println(database.listeNomTousEtudiant());
        System.out.println(database.listeNomParcours());
        System.out.println( database.chercheParcours("INFO"));
        System.out.println( database.listeModuleDansAutreParcours(wentai.getParcoursP()));
        
        int[] a=database.idModuleAutreParcours(INFO);
        //Module[] c=database.idModuleToModule(a);
        int[] ensemble ={11,16,91,99,78,67,87};
        //a=database.randomAffectation(4, ensemble);
        //int[] k=database.concatenerDeuxArray(a, ensemble);
        Module b=database.chercheModule("Image");
        System.out.println( database.listeModuleDansParcours("INFO"));
        

        
        



    }

    

    
}

