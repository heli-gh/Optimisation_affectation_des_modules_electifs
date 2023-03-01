package model;
import java.io.IOException;
import java.util.*;

/**
 * Cette classe est responsable du stockage de toutes les informations de notre système, y compris toutes les 
 * informations sur nos parcours et modules, toutes les informations sur nos étudiants, et les outils que nous utilisons 
 * pour optimiser notre système d'attribution des Modules.
 * @author Yuchen MO
 * @since 18/22/2022
 * @version 0.0
 */

public class Database {

    private static final int MaxParModule =10;
    private static final int  MinParModule =5;

    
    private static final int NP= 2; //le nombre des parcours existants

    private Parcours[] listeParcours = new Parcours[NP];//la liste de parcours existant dans GM5

    private int[][][] affectationListe = new int[NP][][];

    /**
     * constructeur 
     * @param listeParcours une liste de parcours totale 
     * 
     */

    public Database(Parcours[] listeParcours)//correct
    {
        this.listeParcours = listeParcours;
    }
    /**
     * obtenir la liste de Module par message qui est un String contient tous les Modules 
     * @param taille un integre indique nombre de Modules.
     * @param listeModules un String qui contiente les listeModules délimte par ",".
     * @return une liste de Modules
     */

    public Module[] retournerlisteModule(int taille,String listeModules)//correct
    {
        Module[] result = new Module[taille];
        String[] str =listeModules.split(",");
        for(int i=0;i<NP;i++)
        {
            Module[] ref=listeParcours[i].getModule();
            for(int j=0;j<ref.length;j++)
            {
                for(int k =0 ;k<taille;k++)
                {
                    if(ref[j].egaleName(str[k])) 
                    {
                        result[k] = ref[j];
                        break;
                    }
                }
            }
            
        }

        return result;
    }

    /**
    * retourner nom de étudiant dans le système 
    * @return liste de nom de étudiant 
     */

    public synchronized String listeNomTousEtudiant()//correct
    {
        StringBuilder liste = new StringBuilder();

        for (int i =0 ; i < NP ;i++)
        {
            String str=this.listeParcours[i].listeNomEtudiant();
           liste.append(str);
           if (i != NP-1&& str.length() !=0) liste.append(",");
        }

        return liste.toString();
    }

    /**
     * retourner liste de nom de parcours en forme par1,par2,par3,...
     * @return un String indique liste de nom de parcours
     */
    public String listeNomParcours()//correct
    {
        StringBuilder liste =new StringBuilder();

        for(int i =0 ; i<NP ; i++)
        {
            String str=this.listeParcours[i].getNomParcours();
            liste.append(str);
            if ( i != NP-1&&str.length()!=0) liste.append(",");
        }
        return liste.toString();
    }
    /**
     * chercher le Parcours par le nom
     * @param nomParcours un String indique le nom de parcours 
     * @return parcours objectif
     */

    public Parcours chercheParcours(String nomParcours)//correct
    {
        for( int i =0 ; i<NP ; i++){
           if (this.listeParcours[i].egale(nomParcours) ) return this.listeParcours[i];
        }
        return null;

    }
    /**
     * cherche liste de Module dans un parccours qui est indiqué par le nom
     * @param nomParcours un String indique le nom de parcours 
     * @return un String qui contient le information sur tous les nom de Modules dans le parcours indiqué sous en forme par1,par2,par3...
     */

    public String listeModuleDansParcours(String nomParcours)//correct
    {
        return chercheParcours(nomParcours).getNomModules();
    }
    /**
     * cherche liste de Modules dans autre parcours (sauf que parcours préférentiel)
     * @param parcoursP le parcours indiqué
     * @return un String contient liste de Modules dans tous les autre parcours sous forme par1,par2,par3...
     */

    public String listeModuleDansAutreParcours(Parcours parcoursP)//correct
    {
        StringBuilder liste = new StringBuilder();
        int i =0;
        boolean atteint =false;
        //doit assurer chaque parcours dans liste et different
        while( i<NP)
        {
            
            if ( this.listeParcours[i] != parcoursP)
            {
                liste.append(this.listeParcours[i].getNomModules());
            
                if (i < NP-2) liste.append(",");
                if (i == NP-2 && atteint) liste.append(",");
                
            }
            else{
                atteint = true;
            }

            i++;

        }
        return liste.toString();
    }

    /**
     * cherche le liste de numéro identité de Module dans autre parcours 
     * @param parcoursP parcours indiqué
     * @return un liste de numéro identité de Module
     */

    public int[] idModuleAutreParcours(Parcours parcoursP)//correct
    {
        int[] result= new int[0];
        for(int i = 0 ;i<NP;i++)
        {
            if(this.listeParcours[i] != parcoursP)
            {
                result = concatenerDeuxArray(result,this.listeParcours[i].getIdDeModule());
            }
        }
        return result;
    }

    /**
     * cherche un module par le nom indiqué
     * @param nomModule le nom de module 
     * @return module cherché
     */
    public Module chercheModule(String nomModule)//correct
    { 
        
        for(int i =0 ; i<NP ; i++)
        {
            Module[] lModules=this.listeParcours[i].getModule();
            for (Module m : lModules)
            {
                if (m.getName().equals(nomModule)) return m;

            }
        }
        return null;

    }
    /**
     * enregistre un etudiant dans le système 
     * @param e etudiant qui remplir tous information
     */

    public synchronized void enregistreEtudiantDansParcours(Etudiant e)//correct
    {
        e.getParcoursP().enregistreEtudiant(e);
    }


    // outil pour choisir les élément dans ensemble aléatoire et pas répété

    private static int[] randomAffectation(int taille , int[] ensemble)//correct 
    {
        int[] ensemblecopie = (int[]) Arrays.copyOf(ensemble,ensemble.length);

        int[] affectation = new int[taille];
        int n = ensemblecopie.length;
        for(int i =0; i<taille ; i++)
        {
            int r = (int) (Math.random() * n );
            affectation[i] = ensemblecopie[r];
            ensemblecopie[r] = ensemblecopie[n-1];
            n--;

        }
        return affectation;
    }

    //outil pour concaténéer deux liste.

    private static int[] concatenerDeuxArray(int[] a , int[] b)//correct
    {
        int[] result = new int[a.length + b.length];
        for(int i =0 ; i<result.length ; i++)
        {
            if(i< a.length){
                result[i] =a[i];
            }
            else{
                result[i] = b[i -a.length];
            }
        }
        return result;
    }


    // outil pour initlilize Affectation liste

    private synchronized int[][][]  initializeAffectationListe()
    {
        int[][][] preaffectationListe = new int[NP][][];
        for(int i=0 ;i<NP; i++)
        {
            Etudiant[] lEtudiants = this.listeParcours[i].getEtudiant();
            preaffectationListe[i] = new int [lEtudiants.length][];
            for (int j =0 ;j<lEtudiants.length;j++)
            {
                int[] a = randomAffectation(4,lEtudiants[j].getParcoursP().getIdDeModule());
                int[] b = randomAffectation(2,idModuleAutreParcours(lEtudiants[j].getParcoursP()));
                preaffectationListe[i][j] =concatenerDeuxArray(a,b);


            }
        }
        return preaffectationListe;
    }

    // outil pour pénaliser la liste de affectation qui hors de contraint 

    private int coutNombrelimite(int[][][] affectationListe)
    {
        int cout =0;


        int[][] listeModule = new int[NP][];
        int[][] nombreEtudiantdansModule = new int[NP][];
        for(int p =0 ; p< NP ;p++)
        {
            listeModule[p]=this.listeParcours[p].getIdDeModule();
            nombreEtudiantdansModule[p] = new int[listeModule[p].length];
            for(int q =0 ;q < listeModule[p].length;q++)
            {
                nombreEtudiantdansModule[p][q] =0;


                for(int i =0; i< NP ; i++)
                {
                    for(int j=0; j< affectationListe[i].length;j++)//!!!!working or not need test
                    {
                        if(includeDansListe(affectationListe[i][j],listeModule[p][q])) nombreEtudiantdansModule[p][q] +=1;
                

                    }
                }
                if(nombreEtudiantdansModule[p][q] > MaxParModule || nombreEtudiantdansModule[p][q]< MinParModule)
                {
                    cout += 1000;
                }
            }
        }

        

        return cout;
    }
    // outil pour cherche le cout

    private synchronized int  chercheCoutTotal(int[][][] affectationliste)
    { 
        int cout =0;

        for ( int i =0; i<NP; i++)
        {
           int[][] afP= affectationliste[i];
           Etudiant[] el= this.listeParcours[i].getEtudiant();
           for(int j =0 ; j< el.length ;j++)
           {
                cout += el[j].chercheCout(afP[j]);
           }
           
        }

        cout += coutNombrelimite(affectationliste);

        return cout;

    }

    //outil pour cherche déterminer si un élément est dans le liste


    private static boolean includeDansListe(int[]liste , int a )//correct
    {
        boolean result = false;

        for (int i=0 ;i<liste.length ; i++)
        {
            if (a == liste[i]) {
                result = true;
                break;

            }
        }
        return result;
    }
    //outil pour transfère la liste de identité de Module à une liste de module

    private Module[] idModuleToModule(int[] listeAffecation)//correct
    {
        Module[] result = new Module[listeAffecation.length]; //
        int k=0;
        for(int i =0 ; i<NP; i++)
        {
            Module[] lModule = this.listeParcours[i].getModule();
            for(int j=0 ; j<lModule.length;j++)
            {
                if(includeDansListe( listeAffecation, lModule[j].getId())) 
                {
                    result[k] = lModule[j] ;
                    k++;

                }

            }
        }
        return result;

    }

    //outil pour envoyer le liste de affectation vers chaque client
    private void retounerListeAffectation() throws IOException
    {
        for(int i=0 ;i < NP ;i++)
        {
            Etudiant[] lEtudiants = this.listeParcours[i].getEtudiant();
            for (int j =0 ;j<lEtudiants.length;j++)
            {
                lEtudiants[j].setAffectation( idModuleToModule(this.affectationListe[i][j]) );
                lEtudiants[j].EnvoyerAffectation();
            }
        }
         
    }

    //partie optimisation


    // 100 inital affectationliste
    private int[][][][] initalilize()
    {
        final int max =100;
        int[][][][] initialize=new int[max][][][];
        for(int i=0;i<max;i++)
        {
            initialize[i] = this.initializeAffectationListe();

        }
        return initialize;
    }


    //selection 10 meilleur cout
    private int[][][][] selection(int[][][][] ensembleaffectationListe)
    {
      Arrays.sort(ensembleaffectationListe,(first, second)-> chercheCoutTotal(first)- chercheCoutTotal(second));
      final int selectNumber =10;
      int[][][][] select ;
      
        select = Arrays.copyOf(ensembleaffectationListe, selectNumber) ;
      
      return select;

    }

    //mutation vers un jumeaux
    private int[] jumeaux(int[] be ,Parcours p )
    { 
      int[] jumeaux = (int[]) Arrays.copyOf( be,be.length);
      if(Math.random() < 0.5){
        
        int[] ensemblecopie1 = (int[]) Arrays.copyOf(p.getIdDeModule(),p.getIdDeModule().length);
        int n = ensemblecopie1.length;
        for(int i =0; i<4 ; i++)
        {
            for(int j=0;j< n; j++)
            {
                if(jumeaux[i] == ensemblecopie1[j]){
                    ensemblecopie1[j] = ensemblecopie1[n-1];
                    n--;
                    break;
                }
            }

        }

        int r =(int ) (Math.random()*(ensemblecopie1.length-4));// 4 nombre affectation de premier parcours
        int t =(int) (Math.random()*4);
        jumeaux[t] = ensemblecopie1[r];
      }
      else{
        int[] ensemblecopie2 = (int[]) Arrays.copyOf(idModuleAutreParcours(p),idModuleAutreParcours(p).length);
        int  n= ensemblecopie2.length;

        for(int i =4 ; i<6;i++)
        {
            for(int j=0 ;j<n;j++)
            {
                if(jumeaux[i] == ensemblecopie2[j]){
                    ensemblecopie2[j] = ensemblecopie2[n-1];
                    n--;
                    break;
                }
            }
        }
        int r =(int)(Math.random()*(ensemblecopie2.length-2));// 2 nombre affectation de autre parcours
        int t = (int) (Math.random()*2+4);
        jumeaux[t] = ensemblecopie2[r];
      }



        
        return jumeaux;
        
    }

    //crossover pour enfantement

    private int[][][] echangeligne(int[][][] firstlist ,int[][][] secondlist,double tauxCrossover, double tauxMutation)
    {
        int[][][] child= new int[firstlist.length][][];
        for(int i=0 ;i<firstlist.length;i++)
        {
            child[i] = new int[firstlist[i].length][];
            for(int j=0; j< firstlist[i].length;j++)
            {
                //c'est la premier version de mutation le principe est dans certain cas change totalement affectation de un etudiant aléatoirement , à mon avis, il ne servire pas de convergent solution optimale, mais offret un possiblité de changer. 
                /* 
                if(Math.random() < tauxMutation) 
                {
                    int[] a = randomAffectation(4,this.listeParcours[i].getIdDeModule());
                    int[] b = randomAffectation(2,idModuleAutreParcours(this.listeParcours[i]));
                    child[i][j] =concatenerDeuxArray(a,b);
                }
                else{

                    if(Math.random() < tauxCrossover) child[i][j] = Arrays.copyOf(firstlist[i][j], 6);
                    else child[i][j] =Arrays.copyOf(secondlist[i][j], 6);
                }
                */

                //deuxiem version 
                //cherche le jumeaux de cette affectation , change que un élément dans affectation.

                if(Math.random() < tauxCrossover) child[i][j] = Arrays.copyOf(firstlist[i][j], 6);
                else child[i][j] =Arrays.copyOf(secondlist[i][j], 6);

                //cherche le jumeaux
                if(Math.random() < tauxMutation ) child[i][j] = jumeaux(child[i][j], this.listeParcours[i]);


                


                 
            }

        }
        return child;

    }


    //5 paire totale et chaque paire enfanter 9 (crossover+mutation)
    private int[][][][] givebirth(int[][][][] select)
    {
        final int Numbrechild =10;
        final int NombrechildTotal =100;
        final double tauxCrossover = 0.4;
        final double tauxMutation =0.1;
        int[][][][] child = new int[NombrechildTotal][][][];
        int k=0;
        for(int i=0 ;i< select.length ;i++)
        {
            child[k] = select[i];
            k+=1;
        }
        
        for (int i =0; i< select.length-1;i++ )
        {
            for(int j=0; j< Numbrechild;j++)
            {
                child[k]= echangeligne( select[i], select[i+1],tauxCrossover, tauxMutation);
                k+=1;
            }
        }
        return child;


    }
    /**
     * outil pour optimiser le affectation système par le méthode de génétique
     * @throws IOException
     */

    public synchronized void optimisation() throws IOException
    {
        final int nbSelect = 100;
        int i= 0;
        int[][][][] init = initalilize();
        while(i< nbSelect){
            int[][][][] select=selection(init);
            init = givebirth(select);
            i=i+1;

        }
        this.affectationListe = init[0];
        retounerListeAffectation();

    }
    

   



    


    
}
