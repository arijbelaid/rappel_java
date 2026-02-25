public class Compte {

    protected int id;
    protected String proprietaire;
    protected double solde;

    public Compte(int id, String proprietaire, double solde) {
        this.id = id;
        this.proprietaire = proprietaire;
        this.solde = solde;
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

    @Override
    public String toString() {
        return "Compte [id=" + id +
                ", proprietaire=" + proprietaire +
                ", solde=" + solde + "]";
    }
}