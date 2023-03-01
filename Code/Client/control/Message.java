package control;
/**
 * Message est une classe est chargé de stocker les informations, de traiter les informations reçues et de fournir les 
 * outils pour les traiter
 * 
 * @author Hengshuo LI
 * @version 0.1
 * @since 17/22/2022
 */

public class Message {

    private  int type;
    private String cont;

    /**
     * constructeur 
     * @param message le message envoyer entre serveur et cliet sous forme type/content
     */

    public Message(String message)
    {
        String[] m=Message.traiteMessage(message);
        try{
            this.type = Integer.parseInt(m[0]);
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        StringBuilder b = new StringBuilder();
        for(int i=1 ; i<m.length;i++) 
        {   b.append( m[i]);
            if(i != m.length -1) b.append("/");

        }
        this.cont = b.toString();
    }

    public Message(int type ,String message)
    {
        this.type = type;
        this.cont = message;
    }
    /**
     * mettre tous les même type de information ensemble
     * @param info une liste de information de même type,
     * @return b une string de la forme par1,par2,par3,par4
     */

    public static String putinfoEnsemble(String[] info)//correct
    {
        if (info == null) return "null";

        int iMax = info.length ;

        if (iMax == 0) return "";

        StringBuilder b = new StringBuilder();
        for (int i =0 ; i<iMax ; i++)
        {
            b.append(info[i]);

            if (i != iMax-1 ) b.append(",");
        }


        return b.toString();
    }

    /**
     * tester si le même type que type indiqué
     * @param i le type 
     * @return boolean si le même type ou pas
     */

    public boolean ifthistype( int i)
    {
        return (i==this.type);
    }
    /**
     * obtenir le type de cette message 
     * @return type
     */

    public int gettype()
    {
        return this.type;
    }

    /**
     * enregistre le type de message
     * @param type integre type de message
     */
    public void settype( int type)
    {
        this.type = type;
    }
    /**
     * obtenir le information de cette message
     * @return le information de message
     */

    public String getcontent()
    {
        return this.cont;
    }

    /**
     * outil pour couper les message délimité par "/"
     * @param m un String de message
     * @return la liste de information
     */

    public static String[] traiteMessage(String m)
    {
        return  m.split("/");

    }
    /**
     * outil pour couper le message délimité par ","
     * @param m un String de message
     * @return la liste de information 
     */

    public static String[]  cutMessage(String m)
    {
        return m.split(",");
    }
    /**
     * 
     * @return le message type/content
     */

    public String toStirng()
    {
        return Integer.toString(this.type) +"/"+ this.cont;
    }


    
    
}
