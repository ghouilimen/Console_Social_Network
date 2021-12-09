import Database.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Membre {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String datedenaissance;
    protected String email;
    protected String motdepasse;
    protected  String ville;
    protected HashMap<String, Membre> MembreBD = new HashMap<String, Membre>();;
    protected ArrayList<Membre> listeAmis = new ArrayList<Membre>();
    protected ArrayList<Invitation> listeInvitation = new ArrayList<Invitation>();
    protected ArrayList<Groupe> listeGroupe = new ArrayList<Groupe>();
    protected ArrayList<Page> listePage = new ArrayList<Page>();
    static Connection cnx;
    static PreparedStatement st;
    static ResultSet rst;


    
    public Membre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Membre(String nom, String prenom, String datedenaissance, String email, String motdepasse, String ville) {
        this.nom = nom;
        this.prenom = prenom;
        this.datedenaissance = datedenaissance;
        this.email = email;
        this.motdepasse = motdepasse;
        this.ville = ville;
    }

    public void sinscrire()throws InstantiationException,IllegalAccessException, SQLException{
        DBconnection db = new DBconnection();
        Scanner sc1 = new Scanner(System.in);
        System.out.print("nom : ");
        String nom = sc1.nextLine();
        System.out.print("prenom : ");
        String prenom = sc1.nextLine();
        System.out.print("date de naissance : ");
        String datenaissance = sc1.nextLine();
        System.out.print("email : ");
        String email = sc1.nextLine();
        System.out.print("mot de passe : ");
        String mdp = sc1.nextLine();
        System.out.print("ville : ");
        String ville = sc1.nextLine();
        	String query1="select * from Membre where email=?";
            String query2="INSERT INTO page VALUES(?,?,?,?,?,?)";
		cnx=db.connectionDB();
		st=cnx.prepareStatement(query1);
		st.setString(4, email);
		rst=st.executeQuery();
		if(!rst.next()){
			st=cnx.prepareStatement(query2);
			st.setString(1, nom);
			st.setString(2, prenom);
			st.setString(3, datenaissance);
			st.setString(4, email);
			st.setString(5, mdp);
			st.setString(6, ville);
			st.executeUpdate(query2);
			st.execute();
            System.out.println("félicitation! vous venez de vous inscrire");
		}else{
			System.out.println("email déjà utilisé");
		}

    }


    public Membre seConnecter(String email_in, String password_in) {
        Membre user = MembreBD.get(email_in);
        if (user == null)
            System.out.println("Incorrect email.");
        else if (!user.getMotdepasse().equals(password_in))
            System.out.println("Incorrect password.");
        else {
            System.out.println("User identified.");
            System.out.println("Welcome, " + user.getPrenom() + "!");

        }
        return user;
    }

    public void envoyerInvitation (String nomInv) {
        Membre user2 = MembreBD.get(nomInv);
        if(!this.listeAmis.contains(user2)){
            this.listeInvitation.add(new Invitation(this, user2));
            user2.getListeInvitation().add(new Invitation(this, user2));
            System.out.println("Invitation envoyée avec succées");

        }else
            System.out.println("Vous etes deja amis");
    }

    public void repondreInvitation() {
        Scanner sc = new Scanner(System.in);
        while (!this.getListeInvitation().isEmpty()) {
            Invitation i = this.listeInvitation.get(0);
            System.out.println(i.getSender().getNom() + " "
                    + i.getSender().getPrenom()
                    + " vous demande en amis");
            System.out.println("tapez a si vous voulez accepter");
            System.out.println("\n");
            System.out.println("tapez r si vous voulez refuser");
            char answer = sc.nextLine().charAt(0);
            if (answer == 'a') {
                if(!this.listeAmis.contains(i.sender)){
                    this.getListeInvitation().remove(i);
                    this.getListeAmis().add(i.getSender());
                    i.getSender().getListeAmis().add(i.getReceiver());
                    System.out.println(i.sender.getNom() + " " + i.sender.getPrenom() + " est désormés votre amis");
                }else
                {
                    this.getListeInvitation().remove(i);
                    System.out.println("vous etes déjà ami");
                }
            } else if (answer == 'r') {
                this.getListeInvitation().remove(i);
                System.out.println(i.sender + " invitation refusée.\n");
            } else {
                System.out.println("caractère invalide.\n");
            }
        }
        System.out.println("Liste invitation vide.\n");
    }


    public void creerGroupe(Membre user)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nom groupe: ");
        String nomGroupe = sc.nextLine();
        System.out.println("genre: ");
        String genre = sc.nextLine();
        Groupe groupe = new Groupe(genre,nomGroupe);
        if (listeGroupe.contains(groupe))
        {
            System.out.println("Groupe existant");
        }
        else
        {
            listeGroupe.add(groupe);
            groupe.ListeMembreG.add(user);
            System.out.println("Group created with success!");
        }
    }

    public void creerPage(Membre user)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nom page : ");
        String nomPage = sc.nextLine();
        System.out.println("genre : ");
        String genre = sc.nextLine();

        // check if the page is already in your pages list
        Page page = new Page(genre,nomPage);
        if (listePage.contains(page))
        {
            System.out.println("Page existante");
        }
        else {
            listePage.add(page);
            System.out.println("Page crée avec succés");
        }

    }






    public int getIdmembre() {
        return id;
    }

    public void setIdmembre(int idmembre) {
        this.id = idmembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(String datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    public ArrayList<Membre> getListeAmis() {
        return listeAmis;
    }

    public ArrayList<Invitation> getListeInvitation() {
        return listeInvitation;
    }

}

