public class Compte {
    private int id;
    private String proprietaire;
    private double solde;

    // Constructeur
    public Compte(int id, String proprietaire, double solde) {
        this.id = id;
        this.proprietaire = proprietaire;
        this.solde = solde;
    }

    // Getters
    public int getId() { return id; }
    public String getProprietaire() { return proprietaire; }
    public double getSolde() { return solde; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setProprietaire(String proprietaire) { this.proprietaire = proprietaire; }
    public void setSolde(double solde) { this.solde = solde; }

    // Méthode deposer
    public void deposer(double montant) throws MontantNonValideException {
        if (montant <= 0) throw new MontantNonValideException("Montant de dépôt invalide !");
        solde += montant;
    }

    // Méthode retirer
    public void retirer(double montant) throws MontantNonValideException {
        if (montant <= 0) throw new MontantNonValideException("Montant de retrait invalide !");
        if (montant > solde) throw new MontantNonValideException("Solde insuffisant !");
        solde -= montant;
    }

    @Override
    public String toString() {
        return "Compte [id=" + id + ", proprietaire=" + proprietaire + ", solde=" + solde + "]";
    }
}
