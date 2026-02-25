import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestComptePostgres {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/banque";
        String user = "postgres";
        String password = "0000";

        List<Compte> comptes = new ArrayList<>();
        List<Banque> banques = new ArrayList<>();

        try {
            // Connexion à PostgreSQL
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion PostgreSQL réussie !");

            // ===============================
            // IMPORT DES COMPTES
            // ===============================
            Statement stmtCompte = conn.createStatement();
            ResultSet rsCompte = stmtCompte.executeQuery(
                    "SELECT id, proprietaire, solde, taux_interet FROM comptes");

            while (rsCompte.next()) {
                int id = rsCompte.getInt("id");
                String proprietaire = rsCompte.getString("proprietaire");
                double solde = rsCompte.getDouble("solde");
                double tauxInteret = rsCompte.getDouble("taux_interet");

                Compte compte;
                if (tauxInteret > 0) {
                    compte = new CompteEpargne(id, proprietaire, solde, tauxInteret);
                } else {
                    compte = new Compte(id, proprietaire, solde);
                }

                comptes.add(compte);
            }
            rsCompte.close();
            stmtCompte.close();

            // ===============================
            // IMPORT DES BANQUES
            // ===============================
            Statement stmtBanque = conn.createStatement();
            ResultSet rsBanque = stmtBanque.executeQuery(
                    "SELECT id, nom, ville FROM banque");

            while (rsBanque.next()) {
                int id = rsBanque.getInt("id");
                String nom = rsBanque.getString("nom");
                String ville = rsBanque.getString("ville");

                banques.add(new Banque(id, nom, ville));
            }
            rsBanque.close();
            stmtBanque.close();

            conn.close();

            // ===============================
            // AFFICHAGE DES COMPTES
            // ===============================
            System.out.println("\n===== Liste des comptes =====");
            for (Compte c : comptes) {
                System.out.println(c);
            }

            // ===============================
            // AFFICHAGE DES BANQUES
            // ===============================
            System.out.println("DEBUG: Nombre de banques dans la liste = " + banques.size());
            System.out.println("\n===== Liste des banques =====");
            for (Banque b : banques) {
                System.out.println(b);
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}