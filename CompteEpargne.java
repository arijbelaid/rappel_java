public class CompteEpargne extends Compte {

    private double tauxInteret;

    public CompteEpargne(int id, String proprietaire, double solde, double tauxInteret) {
        super(id, proprietaire, solde);
        this.tauxInteret = tauxInteret;
    }

    public double calculerInterets() {
        return solde * tauxInteret;
    }

   @Override
public String toString() {
    return "CompteEpargne [id=" + id +
           ", proprietaire=" + proprietaire +
           ", solde=" + solde +
           ", tauxInteret=" + tauxInteret +
           ", interets=" + calculerInterets() + "]";
}}