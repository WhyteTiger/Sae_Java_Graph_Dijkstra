package logiciel.modele;

import java.util.List;

public class Sommet implements Comparable<Sommet>{
    private String nom;
    private String type;

    public Sommet(String nom, String type){
        this.nom = nom;
        this.type = type;
    }

    public String accesNom() {
        return nom;
    }

    public String accesType() {
        return type;
    }

    public void fixeNom(String nom) {
        this.nom = nom;
    }

    public void fixeType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Sommet{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    /**
     * Recupère un sommet via son nom.
     * @param listeSommet Une liste de sommet contenant le sommet recherché.
     * @param nom Le nom du sommet recherché.
     * @return Le sommet recherché.
     */
    public static Sommet recupererViaNom(List<Sommet> listeSommet, String nom){
        for (Sommet sommet: listeSommet) {
            if (sommet.accesNom().equals(nom)){
                return sommet;
            }
        }
        return null;
    }

    /**
     * Definit que la comparaison des sommets se fait sur leur nom.
     * @param s Le sommet qui doit être comparée.
     * @return Un entier positif si le sommet comparé est plus grand. Un entier négatif si le sommet comparé est plus petit. 0 s'ils sont les mêmes.
     */
    @Override
    public int compareTo(Sommet s) {
        return this.accesNom().compareTo(s.accesNom());
    }
}
