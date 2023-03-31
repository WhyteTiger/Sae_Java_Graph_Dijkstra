package liste;

public class Sommet {
    private String nomSommet;
    private char typeSommet;
    private Arrete premiereArrete;
    private Sommet sommetSuivant = null;

    public Sommet(String nomSommet, char typeSommet){
        this.nomSommet = nomSommet;
        this.typeSommet = typeSommet;
    }

    public String getNomSommet() {
        return nomSommet;
    }
    public Sommet getSommetSuivant() {
        return sommetSuivant;
    }
    public Arrete getPremiereArrete() {
        return premiereArrete;
    }

    public void setPremiereArrete(Arrete premiereArrete) {
        this.premiereArrete = premiereArrete;
    }
    public void setSommetSuivant(Sommet sommetSuivant) {
        this.sommetSuivant = sommetSuivant;
    }
}
