package logiciel.modele;

public class Arrete implements Comparable<Arrete>{
    private String nom;
    private double fiabilite;
    private double distance;
    private double duree;
    private Sommet sommet1;
    private Sommet sommet2;

    /**
     * Crée une arrête avec un nom, une fiabilité, une distance, une durée et les deux sommets qu'elle rattache
     * @param nom Le nom de l'arrête
     * @param fiabilite La fiabilité de l'arrête
     * @param distance La distance de l'arrête
     * @param duree La durée de l'arrête
     * @param sommet1 Un sommet de l'arrête
     * @param sommet2 L'autre sommet de l'arrête
     */
    public Arrete(String nom, double fiabilite, double distance, double duree, Sommet sommet1, Sommet sommet2){
        this.nom = nom;
        this.fiabilite = fiabilite;
        this.distance = distance;
        this.duree = duree;
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
    }

    /**
     * Affiche les caractéristiques de l'arrête, sous forme de chaine de caractère
     * @return la chaine de caractère
     */
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

    /**
     * Accesseur pour le nom
     * @return Le nom
     */
    public String accesNom() {
        return nom;
    }

    /**
     * Accesseur pour la fiabilité
     * @return La fiabilité
     */
    public double accesFiabilite() {
        return fiabilite;
    }

    /**
     * Accesseur pour la distance
     * @return La distance
     */
    public double accesDistance() {
        return distance;
    }

    /**
     * Accesseur pour la durée
     * @return La durée
     */
    public double accesDuree() {
        return duree;
    }

    /**
     * Accesseur pour le sommet 1
     * @return Le sommet 1
     */
    public Sommet accesSommet1() {
        return sommet1;
    }

    /**
     * Accesseur pour le sommet 2
     * @return Le sommet 2
     */
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

    /**
     * Definit que la comparaison des arrêtes se fait sur leur nom.
     * @param a L'arrête qui doit être comparée.
     * @return Un entier positif si l'arrête comparée est plus grande. Un entier négatif si l'arrête comparée est plus petite. 0 si elles sont les mêmes.
     */
    @Override
    public int compareTo(Arrete a) {
        return this.accesNom().compareTo(a.accesNom());
    }
}
