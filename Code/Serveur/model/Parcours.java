package model;

import java.util.*;
/**
 * un Parcours{@code Parcours} contient plusieurs Modules,chaque etudiant faut choisir un comme parcours preferentiel
 * @author Hengshuo LI
 * @version 0.0
 * @since 15/12/2022
 */

public class Parcours {
    
    private String name; //le nom de parcours 
    private ArrayList<Module> lModules = new ArrayList<>(); // une liste des Modules, qui sont appartient dans cette Parcours
    private ArrayList<Etudiant> lEtudiants =new ArrayList<>();// une liste des etudiant , qui choisie cette parcours comme preferentiel
    private static int nextId =0;

    private int id;
    {
        id = nextId;
        nextId++;
    }

    public int getId()//correct
    {
        return this.id;
    }

    // constructeur

    public Parcours( String name )//correct
    {
        this.name = name;

    }

    /**
     * constructeur 
     * @param name le nom de Parcours 
     * @param lModules les Module qui est contient dans le parcours
     */
    public Parcours(String name, Module... lModules)//correct
    {
        this(name);
        for (Module m : lModules)
        {
            this.lModules.add(m);
        }

    }

    /**
     * ajouter des Modules dans le parcours
     * @param lModules les Module qui est contient dans la parcours 
     */

    public void setModule(Module... lModules)//correct
    {
        for (Module m : lModules)
        {
            this.lModules.add(m);
        }

    }

    /**
     * obtenir la liste de Module existe dans le parcours
     * @return alModules un liste de Module existe
     */

    public Module[] getModule() //correct
    {
        Module[] alModules =new Module[this.lModules.size()];
        this.lModules.toArray(alModules);
        return alModules;
    }
    /**
     * retouner une liste de identité de Module dans cette parcours
     * @return une liste de integre qui indique le numéro de identité de Module 
     */
    public int[] getIdDeModule()//correct
    {
        Module[] alModules =new Module[this.lModules.size()];
        this.lModules.toArray(alModules);
        int[] liste= new int[alModules.length];
        for(int i=0 ; i<alModules.length ; i++)
        {
            liste[i]=alModules[i].getId();
        }
        return liste;

    }
    /**
     *  retourner le nombre de Module totale dans cette parcours
     * @return un integre indique le nombre de Module totale
     */
    public int getNombreModule() //correct
    {

        return this.lModules.size();
        
    }
    /**
     * retourner une liste de étudiant choisie cette parcours comme préférentiel.
     * @return une liste de étudiant choisie cette parcours comme préférentiel
     */
    public Etudiant[] getEtudiant()//almost correct
    {
        Etudiant[] alEtudiants =new Etudiant[this.lEtudiants.size()];
        this.lEtudiants.toArray(alEtudiants);
        return alEtudiants;
    }
    /**
     * retourner la nombre de étudiant choisie cette parcours comme préférentiel
     * @return un integre inidique le nombre de étudaint
     */
    public int getNombreEtudiant()//  correct
    {
        return this.lEtudiants.size();
    }
    /**
     * lirer le listed des nom de Modules delimiter par ","n par example: par1,par2,par3,par4
     * @return le liste des nom de Modules
     */

    public String getNomModules()//correct
    {
        Module[] alModules = this.getModule();
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
     * lirer les inforamtion basic sur le parcour,
     * c'est à dire le nom de parcours +"/" + les nom de Modules 
     * delimiter par ","
     */
    public String toString()//correct
    {
        return name +"/"+this.getNomModules(); 
    }

    /**
     * lirer le nom de parcours
     * @return le nom de parcours
     */
    public String getNomParcours()//correct
    {
        return this.name;
    }
    /**
     * tester si les deux parcours est égale
     * @param nomParcours
     * @return un boo
     */

    public boolean egale( String nomParcours)//correct
    {
        return this.name.equals(nomParcours);
    }

    /**
     * enregistrer le etudiant dans la liste 
     * @param e Etudiant qui remplir tous les information,et choisie cet parcours comme preferentiel
     */

    public void enregistreEtudiant(Etudiant e)//correct
    {
        this.lEtudiants.add(e);
    }
    /**
     * obtenir liste de etudaint dans une string, chaque étudiant délimite par "," 
     * @return info sur Nom Etudiant 
     */

    public String listeNomEtudiant()//correct
    {
        Etudiant[] aLEtudiants = this.getEtudiant();
        if(aLEtudiants ==null) return "null";
        int iMax = aLEtudiants.length;

        if (iMax == 0) return "";
        StringBuilder liste = new StringBuilder() ;
    
       
        for (int i =0 ; i<iMax;i++ )
        {
            liste.append(aLEtudiants[i].getName());
            if(i != iMax-1) liste.append(",");
        }


        return liste.toString() ;
    }



    public static void main(String[] args)
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
        System.out.println(INFO);
        int[]a =INFO.getIdDeModule();
        System.out.println(Integer.toString(INFO.getNombreModule())+"ID:"+INFO.getId() +" ?egale: "+ INFO.egale("INFO"));
        System.out.println(MMSN); 
        int[] b=MMSN.getIdDeModule();
        System.out.println(Integer.toString(MMSN.getNombreModule())+"?egale:"+MMSN.egale("INFO"));



    }

}
 