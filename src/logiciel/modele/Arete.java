package logiciel.modele;

public class Arete {
    private String nom;
    private double fiabilite;
    private double distance;
    private double duree;
    private Sommet sommet1;
    private Sommet sommet2;

    public Arete(String nom, double fiabilite, double distance, double duree, Sommet sommet1, Sommet sommet2){
        this.nom = nom;
        this.fiabilite = fiabilite;
        this.distance = distance;
        this.duree = duree;
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
    }

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
}
