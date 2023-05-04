package logiciel.vue;

import java.awt.*;
import java.util.Objects;

public class Trait {

    private final String nom;
    private Point p1;

    private Point p2;

    private Color couleur = Color.BLACK;

    public Trait(String nom, Point p1, Point p2) {
        this.nom = nom;
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point accesP1() {
        return p1;
    }

    public Point accesP2() {
        return p2;
    }

    public String accesNom() {
        return nom;
    }

    public Color accesCouleur() {
        return couleur;
    }

    public void fixeP1(Point p1) {
        this.p1 = p1;
    }

    public void fixeP2(Point p2) {
        this.p2 = p2;
    }

    public void fixeCouleur(Color couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "Trait{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", couleur=" + couleur +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trait trait = (Trait) o;
        return Objects.equals(p1, trait.p1) && Objects.equals(p2, trait.p2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }
}
