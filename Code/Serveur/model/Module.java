package model;

/**
 * un{@code Module} objet qui est signifique un cours suivi par des etudiants
 * un Module possède le nom et le numéro de identification
 * @version 0.0
 * @author Hengshuo LI
 * @since 15/12/2022
 */
public class Module {//correct

    private static final int MAX =10;

    private static final int NP = 2;//le nombre de parcours

    private static int[] nMP= new int[NP];// le numéro de identification de la prochaine créer Module pour différent parcours

    private String name ;// le nom de Module
    private int parcours;// de 0 à NP-1
    private int id;


    // separer chaque parcours appartient pour identifier chaque module c'est vraiment inutile et était bete. 
    static
    {
        for(int i=0;i<NP;i++)
        {
            nMP[i] = MAX*i+1;//supposons que nombre de Module dans chaque parcours inférieur que 10*********change par Max de nombre de Module dans un parcours.
        }

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

        System.out.println(auto_non_lineaire +":" +auto_non_lineaire.getId()+"egale?Image"+auto_non_lineaire.egale(Image.getId()));
        System.out.println(cal_sto_fin +":" +cal_sto_fin.getId());
        System.out.println(con_pro_lo +":" +con_pro_lo.getId());
        System.out.println(crypto +":" +crypto.getId());
        System.out.println(Image +":" +Image.getId());
        System.out.println(intro_IA +":" +intro_IA.getId());
        

    }
    /**
     * constructeur 
     * @param name le nom de Module
     * @param parcours le parcours cette module est appartient
     */
    public Module(String name,int parcours)
    {
        this.name = name;
        this.parcours = parcours;

        id = nMP[this.parcours];
        nMP[this.parcours] ++;
        
        
    }

    /**
     * obtenir le id de module 
     * @return un intègre indique le numéro de identité
     */
    public int getId(){
        return this.id;
    }
    /**
     * obtenir le nom de module
     * @return un String indique le nom
     */
    public String getName()
    {
        return this.name;
    }
    //toStirng 
    public String toString()
    {
        return this.name;
    }

    /**
     * tester si le nom de module est egale cette nom de module(inutile) 
     * @param name le nom de module
     * @return le boolean les noms de modules est egale ou pas 
     */
    public boolean egaleName(String name)
    {
        return this.name.equals(name);
    }
    /**
     * test si deux Module est même 
     * @param id le numéro id de module
     * @return si les deux Module est égale
     */

    public boolean egale(int id )
    {
        return this.id == id; 
    }


}

