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
     * Récupère un sommet grâce à son nom, dans une liste de sommet.
     * @param listeSommet La liste de sommet.
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

    @Override
    public int compareTo(Sommet sommet) {
        return this.accesNom().compareTo(sommet.accesNom());
    }
}
