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

    private Sommet accesSommetParMaillonSommet(MaillonSommet maillonSommet){
        MaillonSommet maillonCourrant = this.premierSommet;
        while(!maillonCourrant.equals(maillonSommet)){
            maillonCourrant = maillonSommet.accesMaillonSuivant();
        }
        return maillonCourrant.accesSommet();
    }

    public Sommet accesSommetParPosition(int position){
        if (position <= this.nombreSommet) {
            MaillonSommet maillonCourrant = this.premierSommet;
            int positionCourrante = 1;
            while (positionCourrante < position) {
                maillonCourrant = maillonCourrant.accesMaillonSuivant();
                positionCourrante++;
            }
            return this.accesSommetParMaillonSommet(maillonCourrant);
        }
        return null;
    }

    public int accesPositionParSommet(Sommet sommet){
        if (this.nombreSommet > 0) {
            MaillonSommet maillonCourrant = this.premierSommet;
            int positionCourrante = 1;
            while (!(sommet.equals(maillonCourrant.accesSommet()))) {
                maillonCourrant = maillonCourrant.accesMaillonSuivant();
                positionCourrante++;
            }
            return positionCourrante;
        }
        return -1;
    }

    public void ajouterSommet(Sommet sommet){
        MaillonSommet nouveauMaillonSommet = new MaillonSommet(sommet, null);
        if (this.premierSommet == null){
            this.premierSommet = nouveauMaillonSommet;
        } else {
            MaillonSommet maillonCourrant = this.premierSommet;
            while (maillonCourrant.accesMaillonSuivant() != null){
                maillonCourrant.fixeMaillonSuivant(maillonCourrant.accesMaillonSuivant().accesMaillonSuivant());
            }
            maillonCourrant.fixeMaillonSuivant(nouveauMaillonSommet);
        }
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
