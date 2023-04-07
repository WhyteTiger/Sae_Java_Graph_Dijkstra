package Logiciel.Modele;

import java.util.List;

public class Sommet {
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

    public String toString(){
        return this.nom + " "+this.type;
    }

    public static Sommet recupererViaNom(List<Sommet> listeSommet, String nom){
        for (Sommet sommet: listeSommet) {
            if (sommet.accesNom().equals(nom)){
                return sommet;
            }
        }
        return null;
    }
}
