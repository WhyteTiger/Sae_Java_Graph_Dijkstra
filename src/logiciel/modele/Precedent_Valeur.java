package logiciel.modele;

/**
 * Permet de stocker l'antécédent et sa valeur dans la map retournée par la méthode Dijktra
 */
public class Precedent_Valeur {
    private final Sommet precedent;
    private final double valeur;

    public Precedent_Valeur(Sommet precedent, double valeur) {
        this.precedent = precedent;
        this.valeur = valeur;
    }

    public Sommet accesPrecedent() {
        return precedent;
    }
    public double accesValeur() {
        return valeur;
    }
}
