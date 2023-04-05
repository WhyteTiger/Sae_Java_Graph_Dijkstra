package Logiciel.Modele;

public class ListeSommet {
    private Sommet premierSommet;
    private int nombreSommet;

    public ListeSommet(){
        this.premierSommet = null;
    }

    public Sommet accesPremierSommet() {
        return premierSommet;
    }

    public int accesNombreSommet() {
        return nombreSommet;
    }

    public void fixePremierSommet(Sommet premierSommet) {
        this.premierSommet = premierSommet;
    }

    /**
     * /!\ MARCHE PAS /!\
     * @param pos
     */
    public void supprimerALaPosition(int pos){

    }
}
