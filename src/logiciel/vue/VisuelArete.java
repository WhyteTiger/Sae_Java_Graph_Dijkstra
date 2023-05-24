package logiciel.vue;

import java.awt.*;

public class VisuelArete {

    private final String nomVisuel;
    private int coordX1;
    private int coordY1;
    private int coordX2;
    private int coordY2;
    private Color couleur;

    public VisuelArete(String nomVisuel, int coordX1, int coordY1, int coordX2, int coordY2, Color couleur) {
        this.nomVisuel = nomVisuel;
        this.coordX1 = coordX1;
        this.coordY1 = coordY1;
        this.coordX2 = coordX2;
        this.coordY2 = coordY2;
        this.couleur = couleur;
    }

    public String accesNomVisuel() {
        return nomVisuel;
    }

    public int accesCoordX1() {
        return coordX1;
    }

    public int accesCoordY1() {
        return coordY1;
    }
    public int accesCoordX2() {
        return coordX2;
    }

    public int accesCoordY2() {
        return coordY2;
    }

    public Color accesCouleur() {
        return couleur;
    }

    public void fixeCoordX1(int coordX1) {
        this.coordX1 = coordX1;
    }

    public void fixeCoordY1(int coordY1) {
        this.coordY1 = coordY1;
    }

    public void fixeCoordX2(int coordX2) {
        this.coordX2 = coordX2;
    }

    public void fixeCoordY2(int coordY2) {
        this.coordY2 = coordY2;
    }

    public void fixeCouleur(Color couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "VisuelArete{" +
                "nomVisuel='" + nomVisuel + '\'' +
                '}';
    }
}