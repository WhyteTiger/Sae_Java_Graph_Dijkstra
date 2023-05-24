package logiciel.vue;

import java.awt.*;

public class VisuelSommet {

    private final String nomVisuel;
    private int coordX;
    private int coordY;
    private final int taille;
    private Color couleur;
    private Color couleurContour;

    public VisuelSommet(String nomVisuel, int coordX, int coordY, int taille, Color couleur) {
        this.nomVisuel      = nomVisuel;
        this.coordX         = coordX;
        this.coordY         = coordY;
        this.taille         = taille;
        this.couleur        = couleur;
        this.couleurContour = couleur;
    }

    public String accesNomVisuel() {
        return nomVisuel;
    }

    public int accesCoordX() {
        return coordX;
    }

    public int accesCoordY() {
        return coordY;
    }

    public int accesTaille() {
        return taille;
    }

    public Color accesCouleur() {
        return couleur;
    }
    public Color accesCouleurContour() {
        return couleurContour;
    }

    public void fixeCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void fixeCoordY(int coordY) {
        this.coordY = coordY;
    }

    public void fixeCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void fixeCouleurContour(Color couleurContour) {
        this.couleurContour = couleurContour;
    }

    @Override
    public String toString() {
        return "VisuelSommet{" +
                "nomVisuel='" + nomVisuel + '\'' +
                '}';
    }
}
