public class CompteEpargne extends Compte {
    private double tauxInteret;

    public CompteEpargne(int id, String proprietaire, double solde, double tauxInteret) {
        super(id, proprietaire, solde, 0);
        this.tauxInteret = tauxInteret;
    }

    public CompteEpargne(int id, String proprietaire, double solde, double tauxInteret, int banqueId) {
        super(id, proprietaire, solde, banqueId);
        this.tauxInteret = tauxInteret;
    }

    public double calculerInterets() {
        return solde * tauxInteret;
    }
    
    public double getTauxInteret() {
        return tauxInteret;
    }

    @Override
    public String toString() {
        return "CompteEpargne{id=" + id + 
               ", proprietaire='" + proprietaire + "'" + 
               ", solde=" + solde + 
               ", tauxInteret=" + (tauxInteret * 100) + "%}";
    }
}