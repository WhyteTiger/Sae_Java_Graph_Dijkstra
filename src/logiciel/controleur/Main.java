package logiciel.controleur;

import logiciel.vue.FenetreP;
import logiciel.vue.FenetrePrincipale;


public class Main {

    public static void main(String[] args) {

        //Crée la fenêtre principale et l'affiche
        FenetrePrincipale fenetrePrincipale = new FenetrePrincipale();
        fenetrePrincipale.pack();
        fenetrePrincipale.setVisible(true);

        FenetreP f1 = new FenetreP();
    }
}
