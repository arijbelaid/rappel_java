public class CompteEpargne extends Compte {
    private double tauxInteret; // ex: 0.05 = 5%

    public CompteEpargne(int id, String proprietaire, double solde, double tauxInteret) {
        super(id, proprietaire, solde);
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret() { return tauxInteret; }
    public void setTauxInteret(double tauxInteret) { this.tauxInteret = tauxInteret; }

    public double calculerInterets() {
        return getSolde() * tauxInteret;
    }

    @Override
    public String toString() {
        return super.toString() + ", tauxInteret=" + tauxInteret + ", interets=" + calculerInterets();
    }
}