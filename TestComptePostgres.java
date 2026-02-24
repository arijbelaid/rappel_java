import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestComptePostgres {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/banque";
        String user = "postgres";
        String password = "0000";

        List<Compte> comptes = new ArrayList<>();

        try {

            // Charger le driver
            Class.forName("org.postgresql.Driver");

            // Connexion
            Connection conn = DriverManager.getConnection(url, user, password);

            System.out.println("Connexion PostgreSQL réussie !");

            // Requête SQL
            String sql = "SELECT id, proprietaire, solde, taux_interet FROM comptes";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            // Lire les données
            while (rs.next()) {

                int id = rs.getInt("id");
                String proprietaire = rs.getString("proprietaire");
                double solde = rs.getDouble("solde");
                double tauxInteret = rs.getDouble("taux_interet");

                Compte compte;

                if (tauxInteret > 0) {
                    compte = new CompteEpargne(id, proprietaire, solde, tauxInteret);
                } else {
                    compte = new Compte(id, proprietaire, solde);
                }

                comptes.add(compte);
            }

            // Fermer ResultSet et Statement
            rs.close();
            stmt.close();
            conn.close();

            // Affichage
            System.out.println("\nListe des comptes importés :");

            for (Compte c : comptes) {
                System.out.println(c);
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }

    }
}