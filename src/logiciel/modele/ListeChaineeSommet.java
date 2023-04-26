package logiciel.modele;

public class ListeChaineeSommet {

    private class MaillonSommet {
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

    private MaillonSommet premierSommet;
    private int nombreDeSommet;

    public ListeChaineeSommet(){
        this.premierSommet = null;
    }

    public MaillonSommet accesPremierSommet() {
        return premierSommet;
    }

    public int accesNombreDeSommet() {
        return nombreDeSommet;
    }

    public void fixePremierSommet(MaillonSommet premierSommet) {
        this.premierSommet = premierSommet;
    }

    /**
     * Donne le maillon du sommet précèdent à celui dont on donne l'indice.
     * @param position L'indice du sommet étudié.
     * @return Le maillon du sommet précèdent au sommet étudié.
     */
    private MaillonSommet rechercheSommetPrecedent(int position){
        if (position <= this.nombreDeSommet) {
            //initialisation du sommet précèdent, courant et de son indice
            MaillonSommet maillonCourant = this.premierSommet;
            MaillonSommet maillonPrecedent = null;
            int positionCourante = 1;

            //Tant qu'on n'est pas au sommet étudié, on avance dans la liste
            while (positionCourante < position) {
                maillonPrecedent = maillonCourant;
                maillonCourant = maillonCourant.accesMaillonSuivant();
                positionCourante++;
            }
            return maillonPrecedent;
        }
        return null;
    }

    /**
     * Donne le sommet du maillon sommet donné.
     * @param maillonSommet Le maillon sommet dont on cherche le sommet associé.
     * @return Le sommet cherché.
     */
    private Sommet accesSommetParMaillonSommet(MaillonSommet maillonSommet){
        //Initialisation du maillon courrant
        MaillonSommet maillonCourant = this.premierSommet;

        //Tant qu'on n'est pas au maillon sommet souhaité, on passe au suivant
        while(!maillonCourant.equals(maillonSommet)){
            maillonCourant = maillonCourant.accesMaillonSuivant();
        }

        //On récupère le sommet du maillon sommet souhaité
        return maillonCourant.accesSommet();
    }

    /**
     * Donne le sommet à la position donnée.
     * @param position Une position.
     * @return Le sommet recherché.
     */
    public Sommet accesSommetParPosition(int position){
        if (position <= this.nombreDeSommet) {
            //Initialisation du maillon courant et de sa position.
            MaillonSommet maillonCourant = this.premierSommet;
            int positionCourante = 1;

            //Tant qu'on n'est pas au maillon sommet souhaité, on passe au suivant
            while (positionCourante < position) {
                maillonCourant = maillonCourant.accesMaillonSuivant();
                positionCourante++;
            }

            //On récupère le sommet souhaité
            return this.accesSommetParMaillonSommet(maillonCourant);
        }
        return null;
    }

    /**
     * Donne la position d'un sommet donnée.
     * @param sommet Le sommet donné.
     * @return La position voulu.
     */
    public int accesPositionParSommet(Sommet sommet){
        if (this.nombreDeSommet > 0) {
            //Initialisation du maillon courrant et de sa position
            MaillonSommet maillonCourant = this.premierSommet;
            int positionCourante = 1;

            //Tant qu'on n'est pas au maillon sommet souhaité, on passe au suivant
            while (!(sommet.equals(maillonCourant.accesSommet()))) {
                maillonCourant = maillonCourant.accesMaillonSuivant();
                positionCourante++;
            }

            //On récupère la position souhaitée
            return positionCourante;
        }
        return -1;
    }

    /**
     * Ajoute un sommet à une liste chainée de sommets.
     * @param sommet Le sommet que l'on souhaite ajouter.
     */
    public void ajouterSommet(Sommet sommet){
        //Création du nouveau maillon sommet
        MaillonSommet nouveauMaillonSommet = new MaillonSommet(sommet, null);

        //Test pour savoir si la liste chainée est vide
        if (this.premierSommet == null){ //Si c'est le cas, on initialise le nouveau maillon comme étant le premier
            this.premierSommet = nouveauMaillonSommet;

        } else { //Sinon, on le met à la suite
            MaillonSommet maillonCourant = this.premierSommet;
            while (maillonCourant.accesMaillonSuivant() != null){
                maillonCourant.fixeMaillonSuivant(maillonCourant.accesMaillonSuivant().accesMaillonSuivant());
            }
            maillonCourant.fixeMaillonSuivant(nouveauMaillonSommet);
        }
    }

    /**
     * Supprime le maillon sommet à la position souhaitée
     * @param position position du maillon à supprimer
     */
    public void supprimerALaPosition(int position){
        //On cherche le maillon précèdent de celui à supprimer
        MaillonSommet sommetPrecedent = this.rechercheSommetPrecedent(position);

        if(sommetPrecedent != null) { //S'il existe on le retire grâce au maillon précèdent
            sommetPrecedent.fixeMaillonSuivant(sommetPrecedent.accesMaillonSuivant().accesMaillonSuivant());
        }else{ //Sinon, c'est qu'il ne reste plus qu'un sommet (ou 0), donc on réinitialise le premier sommet à null
            this.fixePremierSommet(null);
        }
    }
}
