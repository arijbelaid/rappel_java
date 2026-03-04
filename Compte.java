public class Compte {
    protected int id;
    protected String proprietaire;
    protected double solde;
    protected int banqueId;

    public Compte(int id, String proprietaire, double solde) {
        this(id, proprietaire, solde, 0);
    }

    public Compte(int id, String proprietaire, double solde, int banqueId) {
        this.id = id;
        this.proprietaire = proprietaire;
        this.solde = solde;
        this.banqueId = banqueId;
    }

    public void deposer(double montant) throws MontantNonValideException {
        if (montant <= 0) {
            throw new MontantNonValideException("Montant invalide");
        }
        solde += montant;
    }

    public void retirer(double montant) throws MontantNonValideException {
        if (montant <= 0 || montant > solde) {
            throw new MontantNonValideException("Retrait invalide");
        }
        solde -= montant;
    }

    public int getBanqueId() {
        return banqueId;
    }

    @Override
    public String toString() {
        return "Compte{id=" + id + 
               ", proprietaire='" + proprietaire + "'" + 
               ", solde=" + solde + "}";
    }
}