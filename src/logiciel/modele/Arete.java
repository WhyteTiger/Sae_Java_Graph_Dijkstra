package logiciel.modele;

public class Arete {

    // <editor-fold defaultstate="collapsed" desc="ATTRIBUTS">

    /**
     * Nom de l'arête, pour les identifier
     */
    private String nom;

    /**
     * La fiabilité de l'arête, soit à quelle point le chemin est sécurisé ou non
     */
    private double fiabilite;

    /**
     * La distance de l'arête
     */
    private double distance;

    /**
     * La durée de l'arête, soit en combien de temps on la traverse
     */
    private double duree;

    /**
     * Un sommet de l'arête
     */
    private Sommet sommet1;

    /**
     * L'autre sommet de l'arête
     */
    private Sommet sommet2;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CONSTRUCTEUR">

    public Arete(String nom, double fiabilite, double distance, double duree, Sommet sommet1, Sommet sommet2){
        this.nom = nom;
        this.fiabilite = fiabilite;
        this.distance = distance;
        this.duree = duree;
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="METHODES PUBLIQUES">

    @Override
    public String toString() {
        return "Arrete{" +
                "nom='" + nom + '\'' +
                ", fiabilite=" + fiabilite +
                ", distance=" + distance +
                ", duree=" + duree +
                ", sommet1=" + sommet1.accesNom() +
                ", sommet2=" + sommet2.accesNom() +
                '}';
    }

    public String accesNom() {
        return nom;
    }
    public double accesFiabilite() {
        return fiabilite;
    }
    public double accesDistance() {
        return distance;
    }
    public double accesDuree() {
        return duree;
    }
    public Sommet accesSommet1() {
        return sommet1;
    }
    public Sommet accesSommet2() {
        return sommet2;
    }

    public void fixeNom(String nom) {
        this.nom = nom;
    }
    public void fixeFiabilite(double fiabilite) {
        this.fiabilite = fiabilite;
    }
    public void fixeDistance(double distance) {
        this.distance = distance;
    }
    public void fixeDuree(double duree) {
        this.duree = duree;
    }
    public void fixeSommet1(Sommet sommet1) {
        this.sommet1 = sommet1;
    }
    public void fixeSommet2(Sommet sommet2) {
        this.sommet2 = sommet2;
    }

    // </editor-fold>
}
