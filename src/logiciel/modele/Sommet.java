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

    /**
     * Compare les sommets via leur nom.
     * @param sommet the object to be compared.
     * @return int comparaison
     */
    @Override
    public int compareTo(Sommet sommet) {
        return this.accesNom().compareTo(sommet.accesNom());
    }

    /**
     * Non utilisé
     * @param listeArete Liste d'arête.
     * @param s0 Sommet de base.
     * @return Une distance.
     */
    public Double accesDistanceAuSommet(List<Arete> listeArete, Sommet s0) {
        for (Arete arete : listeArete) {
            if ((arete.accesSommet1() == this && arete.accesSommet2() == s0) || (arete.accesSommet2() == this && arete.accesSommet1() == s0)){
                return arete.accesDistance();
            }
        }
        return Algorithmes.INFINI_NEGATIF;
    }
}
