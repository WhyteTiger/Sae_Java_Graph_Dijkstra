package logiciel.modele;

import java.util.List;

public class Sommet implements Comparable<Sommet>{

    // <editor-fold defaultstate="collapsed" desc="ATTRIBUTS">

    /**
     * Le nom du sommet, pour les identifier
     */
    private String nom;

    /**
     * Le type du sommet, "M" pour maternité, "N" pour nutrition et "O" pour opération
     */
    private String type;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ATTRIBUTS">

    public Sommet(String nom, String type){
        this.nom = nom;
        this.type = type;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="METHODES PUBLICS">

    // <editor-fold defaultstate="collapsed" desc="Accesseurs et mutateurs">

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

    // </editor-fold>

    @Override
    public String toString() {
        return "Sommet{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
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
     * Récupère un sommet grâce à son nom, dans une liste de sommet.
     * @param listeSommet La liste de sommet.
     * @param nom Le nom du sommet recherché.
     * @return Le sommet recherché, ou null s'il n'existe pas.
     */
    public static Sommet recupererViaNom(List<Sommet> listeSommet, String nom){

        //Parcours tous les sommets de la liste jusqu'à trouver le bon
        for (Sommet sommet: listeSommet) {
            //Le bon, c'est celui qui a le même nom
            if (sommet.accesNom().equals(nom)){
                return sommet;
            }
        }
        //S'il ne le trouve pas, retourne null
        return null;
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

    // </editor-fold>
}
