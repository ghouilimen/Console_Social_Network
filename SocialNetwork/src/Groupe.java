import java.util.ArrayList;

public class Groupe extends EntiteSociale{
    protected String nomgroupe;
    protected Membre admin;
    protected ArrayList<Membre> ListeMembreG ;


    public Groupe(String genre, String nomgroupe) {
        super(genre);
        this.nomgroupe = nomgroupe;
    }

    public Groupe(int identitesocial, String genre, String datecreation, String nomgroupe) {
        super(identitesocial, genre, datecreation);
        this.nomgroupe = nomgroupe;
    }

    public String toString()
    {
        return "Bienvenue au groupe " + this.nomgroupe + " crée par " + this.admin;
    }
}
