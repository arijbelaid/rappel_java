public class TestCompte {
    public static void main(String[] args) {
        try {
            // Création d'un compte normal
            Compte c1 = new Compte(1, "Alice", 1000);
            System.out.println(c1);

            c1.deposer(500);
            System.out.println("Après dépôt: " + c1);

            c1.retirer(200);
            System.out.println("Après retrait: " + c1);

            // Création d'un compte épargne
            CompteEpargne c2 = new CompteEpargne(2, "Bob", 2000, 0.05);
            System.out.println(c2);

            c2.deposer(300);
            c2.retirer(100);
            System.out.println("Après opérations: " + c2);
            System.out.println("Intérêts calculés: " + c2.calculerInterets());

        } catch (MontantNonValideException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
}