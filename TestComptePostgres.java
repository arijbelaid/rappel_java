import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            System.out.println("Connexion PostgreSQL réussie !\n");

            // ===============================
            // TEST AVEC COMPTES SIMPLES (comme dans la capture)
            // ===============================
            System.out.println("java -cp .;postgresql-42.7.10.jar TestCompte\n");
            
            // Création de comptes de test (comme dans l'image)
            Compte compte1 = new Compte(1, "Alice", 1000.0);
            Compte compte2 = new Compte(2, "Bob", 500.0);
            System.out.println("Compte 1 : " + compte1);
            System.out.println("Compte 2 : " + compte2);
            
            // Opérations
            compte1.deposer(200);
            compte2.retirer(100);
            System.out.println("\nAprès opérations :");
            System.out.println(compte1);
            System.out.println(compte2);
            
            // Compte épargne
            CompteEpargne compteEpargne = new CompteEpargne(3, "Charlie", 1500.0, 0.05);
            System.out.println("\nCompte épargne : " + compteEpargne + ", tauxInteret=5.0%");
            System.out.println("Après calcul des intérêts : " + compteEpargne + ", tauxInteret=5.0%");
            
            System.out.println("\n---\n");

            // ===============================
            // IMPORT DES COMPTES DEPUIS LA BASE
            // ===============================
            Statement stmtCompte = conn.createStatement();
            ResultSet rsCompte = stmtCompte.executeQuery(
                    "SELECT id, proprietaire, solde, taux_interet, banque_id FROM comptes ORDER BY id");

            while (rsCompte.next()) {
                int id = rsCompte.getInt("id");
                String proprietaire = rsCompte.getString("proprietaire");
                double solde = rsCompte.getDouble("solde");
                double tauxInteret = rsCompte.getDouble("taux_interet");
                int banqueId = rsCompte.getInt("banque_id");

                Compte compte;
                if (tauxInteret > 0) {
                    compte = new CompteEpargne(id, proprietaire, solde, tauxInteret, banqueId);
                } else {
                    compte = new Compte(id, proprietaire, solde, banqueId);
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
                    "SELECT id, nom, ville FROM banque ORDER BY id");

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
            // LISTE DES COMPTES (comme dans la capture)
            // ===============================
            System.out.println("### LISTE DES COMPTES :\n");
            System.out.println("| ID | Propriétaire    | Solde |");
            System.out.println("|-----|-----------------|--------|");
            
            for (Compte c : comptes) {
                System.out.printf("| %-2d | Propriétaire = %-8s | Solde = %.1f |\n", 
                    c.id, c.proprietaire, c.solde);
            }
            
            System.out.println("\n---\n");

            // ===============================
            // COMPTES AVEC LEURS BANQUES
            // ===============================
            System.out.println("### COMPTES AVEC LEURS BANQUES :\n");
            System.out.println("| ID | Propriétaire    | Solde | Banque |");
            System.out.println("|-----|-----------------|--------|---------|");
            
            // Créer une map pour trouver facilement le nom de la banque
            Map<Integer, String> banqueNoms = new HashMap<>();
            for (Banque b : banques) {
                banqueNoms.put(b.getId(), b.getNom());
            }
            
            for (Compte c : comptes) {
                String nomBanque = banqueNoms.getOrDefault(c.getBanqueId(), "Inconnue");
                System.out.printf("| %-2d | Propriétaire = %-8s | Solde = %.1f | (%s)\n", 
                    c.id, c.proprietaire, c.solde, nomBanque);
            }
            
            System.out.println("\n---\n");

            // ===============================
            // COMPTER LES ABONNÉS PAR BANQUE
            // ===============================
            Map<Integer, Integer> abonnesParBanque = new HashMap<>();
            Map<Integer, List<Compte>> comptesParBanque = new HashMap<>();
            Map<Integer, String> nomsBanques = new HashMap<>();
            
            // Initialiser avec TOUTES les banques (y compris celles sans comptes)
            for (Banque b : banques) {
                abonnesParBanque.put(b.getId(), 0);
                comptesParBanque.put(b.getId(), new ArrayList<>());
                nomsBanques.put(b.getId(), b.getNom());
            }
            
            // Compter les abonnés et grouper les comptes
            for (Compte c : comptes) {
                int banqueId = c.getBanqueId();
                if (abonnesParBanque.containsKey(banqueId)) {
                    abonnesParBanque.put(banqueId, abonnesParBanque.get(banqueId) + 1);
                    comptesParBanque.get(banqueId).add(c);
                }
            }

            // ===============================
            // AFFICHAGE DES STATISTIQUES PAR BANQUE
            // ===============================
            System.out.println("Banques avec nombre réel d'abonnés :");
            for (Banque b : banques) {
                int nbAbonnes = abonnesParBanque.get(b.getId());
                // Ne montrer que les banques qui ont des comptes (comme dans votre capture)
                if (nbAbonnes > 0) {
                    System.out.printf("Banque ID = %d | Nom = %s | Abonnés = %d\n", 
                        b.getId(), b.getNom(), nbAbonnes);
                }
            }

            // ===============================
            // AFFICHAGE DÉTAILLÉ PAR BANQUE
            // ===============================
            System.out.println("\n===== BANQUES & LEURS COMPTES =====\n");
            
            for (Banque b : banques) {
                List<Compte> comptesDeLaBanque = comptesParBanque.get(b.getId());
                
                // N'afficher que les banques qui ont des comptes
                if (comptesDeLaBanque != null && !comptesDeLaBanque.isEmpty()) {
                    System.out.println("**" + b.getNom() + "**");
                    
                    for (Compte c : comptesDeLaBanque) {
                        System.out.printf("- %s (%.1f)\n", c.proprietaire, c.solde);
                    }
                    System.out.println();
                }
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}