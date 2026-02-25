public class Banque {

    private int id;
    private String nom;
    private String ville;

    public Banque(int id, String nom, String ville) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Banque [id=" + id +
                ", nom=" + nom +
                ", ville=" + ville + "]";
    }
}