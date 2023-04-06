package Logiciel.Modele;

public class MaillonSommet {
    private Sommet sommet;
    private MaillonSommet maillonSuivant;

    public MaillonSommet(Sommet sommet, MaillonSommet maillonSuivant){
        this.sommet = sommet;
        this.maillonSuivant = maillonSuivant;
    }

    public Sommet accesSommet() {
        return sommet;
    }

    public MaillonSommet accesMaillonSuivant() {
        return maillonSuivant;
    }

    public void fixeMaillonSuivant(MaillonSommet maillonSuivant) {
        this.maillonSuivant = maillonSuivant;
    }
}
