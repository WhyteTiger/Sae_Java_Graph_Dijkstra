package Logiciel.Modele;

public class Sommet {
    private String nom;
    private char type;

    public Sommet(String nom, char type){
        this.nom = nom;
        this.type = type;
    }

    public String accesNom() {
        return nom;
    }

    public char accesType() {
        return type;
    }

    public void fixeNom(String nom) {
        this.nom = nom;
    }

    public void fixeType(char type) {
        this.type = type;
    }
}
