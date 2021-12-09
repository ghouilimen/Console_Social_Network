import Database.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InstantiationException,IllegalAccessException {
        DBconnection bd = new DBconnection();
            Scanner input = new Scanner(System.in);
            int choix1;
            int choix2;
            do {
                System.out.println("Bienvenue dans le RÃ©seau Social , Voulez-vous :");
                System.out.print("1: S'inscrire\n");
                System.out.print("2: Se connecter\n");
                choix1 = input.nextInt();
                switch (choix1) {
                    case 1:
                        /*Scanner sc1 = new Scanner(System.in);
                        System.out.print("nom : ");
                        String name = sc1.nextLine();
                        System.out.print("prenom : ");
                        String prenom = sc1.nextLine();
                        System.out.print("date de naissance : ");
                        String datenaissance = sc1.nextLine();
                        System.out.print("email : ");
                        String mail = sc1.nextLine();
                        System.out.print("mot de passe : ");
                        String mdp = sc1.nextLine();*/
                         try{
                         Membre m = new Membre();
                         m.sinscrire();
                         }catch (Exception e){
                        	 System.out.println(e.getMessage());
                         }

                        break;
                    case 2:
                        Scanner sc2 = new Scanner(System.in);
                        System.out.print("email : ");
                        String email = sc2.nextLine();
                        System.out.print("mot de passe : ");
                        String mdp = sc2.nextLine();
                        Membre m1 = new Membre();
                        Membre user = m1.seConnecter(email, mdp);
                        System.out.println(user);
                        if (user != null) {
                            do {
                                Scanner sc3 = new Scanner(System.in);
                                System.out.print("1: Envoyer invitation \n");
                                System.out.print("2: Consulter invitation\n");
                                System.out.print("3: Créer page\n");
                                System.out.print("4: Aimer page \n");
                                System.out.print("5: Créer groupe  \n");
                                System.out.print("6: Rejoindre groupe\n");
                                System.out.print("7: Publier Message \n");

                                choix2 = sc3.nextInt();
                                switch (choix2) {

                                    case 1:
                                        Scanner sc4 = new Scanner(System.in);
                                        System.out.println("Vous souhaitez envoyer une invitation Ã  : ");
                                        String nom = sc4.nextLine();
                                        user.envoyerInvitation(nom);
                                        break;

                                    case 2:
                                        user.repondreInvitation();
                                        break;
                                    case 3:
                                        user.creerPage(user);
                                        System.out.println("votre page est créer avec succée");

                                        break;
                                   /* case 4 :
                                        System.out.println("nom du page ");
                                        Scanner sc7 = new Scanner(System.in);
                                        String n = sc7.nextLine();
                                        user.aimerPage(n);
                                        break;*/
                                    /*case 5:
                                       user.creerGroupe(user);
                                       System.out.println("votre groupe est crÃ©e avec succÃ©e");

                                        break;
                                    case 6 :
                                        System.out.printf("liste des groupes disponibles \n ");
                                        Groupe.listeGroupeDisponible();
                                        Scanner sc8= new Scanner(System.in);
                                        System.out.print("Nom du groupe Ã  rejoindre ");
                                        String p = sc8.nextLine();
                                        user.rejoindreGroupe(p);

                                        break;*/

                                    default:
                                        System.out.println("une erreur s'est produite");
                                }
                            }while (choix2!=0);
                        }
                        break;
                    default:
                        System.out.println("une erreur s'est produite");
                }

            }while (choix1 !=0);
        }

    }

