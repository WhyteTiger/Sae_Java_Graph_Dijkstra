package logiciel.vue;

import logiciel.modele.Forme;

import java.awt.*;

public class ElementDuGraphe {

    private String nomElement;
    private double coordX1;
    private double coordY1;
    private double coordX2;
    private double coordY2;
    private int taille;
    private Color couleur;
    private Forme forme;

    public ElementDuGraphe(String nomElement, double coordX1, double coordY1, double coordX2, double coordY2, int taille, Color couleur, Forme forme) {
        this.nomElement = nomElement;
        this.coordX1 = coordX1;
        this.coordY1 = coordY1;
        this.coordX2 = coordX2;
        this.coordY2 = coordY2;
        this.taille = taille;
        this.couleur = couleur;
        this.forme = forme;
    }

    public String accesNomElement() {
        return nomElement;
    }

    public double accesCoordX1() {
        return coordX1;
    }

    public double accesCoordY1() {
        return coordY1;
    }

    public double accesCoordX2() {
        return coordX2;
    }

    public double accesCoordY2() {
        return coordY2;
    }

    public int accesTaille() {
        return taille;
    }

    public Color accesCouleur() {
        return couleur;
    }

    public Forme accesForme() {
        return forme;
    }
}
