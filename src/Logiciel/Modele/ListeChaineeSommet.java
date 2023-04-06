package Logiciel.Modele;

public class ListeChaineeSommet {
    private MaillonSommet premierSommet;
    private int nombreSommet;

    public ListeChaineeSommet(){
        this.premierSommet = null;
    }

    public MaillonSommet accesPremierSommet() {
        return premierSommet;
    }

    public int accesNombreSommet() {
        return nombreSommet;
    }

    public void fixePremierSommet(MaillonSommet premierSommet) {
        this.premierSommet = premierSommet;
    }

    private MaillonSommet rechercheSommetPrecedent(int position){
        if (position <= this.nombreSommet) {
            MaillonSommet maillonCourrant = this.premierSommet;
            MaillonSommet maillonPrecedent = null;
            int positionCourrante = 1;
            while (positionCourrante < position) {
                maillonPrecedent = maillonCourrant;
                maillonCourrant = maillonCourrant.accesMaillonSuivant();
                positionCourrante++;
            }
            return maillonPrecedent;
        }
        return null;
    }

    /**
     *
     * @param position position du maillon à supprimer
     */
    public void supprimerALaPosition(int position){
        MaillonSommet sommetPrecedent = this.rechercheSommetPrecedent(position);
        if(sommetPrecedent != null) {
            sommetPrecedent.fixeMaillonSuivant(sommetPrecedent.accesMaillonSuivant().accesMaillonSuivant());
        }else{
            this.fixePremierSommet(null);
        }
    }
}
