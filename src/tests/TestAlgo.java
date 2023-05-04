package tests;

import exceptions.FichierIncorrectException;
import logiciel.controleur.LectureFichier;
import logiciel.modele.Algorithmes;
import logiciel.modele.Arete;
import logiciel.modele.Precedent_Valeur;
import logiciel.modele.Sommet;

import java.util.List;
import java.util.Map;

public class TestAlgo {
    public static void main(String[] args){

        //Pour test dijktra

        //Crée les listes de sommet et d'arête
        List<Sommet> listeSommet;
        try {
            listeSommet = LectureFichier.creerListeSommet("Liste_Sommet_Test.csv");
        } catch (FichierIncorrectException e) {
            throw new RuntimeException(e);
        }

        List<Arete> listeArete;
        try {
            listeArete = LectureFichier.creerListeArete("Liste_Arete_Test.csv", listeSommet);
        } catch (FichierIncorrectException e) {
            throw new RuntimeException(e);
        }

        //Choix du sommet à traiter
        Sommet s0 = listeSommet.get(1);


        Map<Sommet, Precedent_Valeur> mapPrecedentsDijktra = Algorithmes.dijktra(listeSommet, listeArete, s0, Algorithmes.DISTANCE);


        //Affiche la map retournée
        for (Map.Entry<Sommet, Precedent_Valeur> precedentsDijktra : mapPrecedentsDijktra.entrySet()) {
            System.out.println("Sommet : "+precedentsDijktra.getKey().accesNom() +"   Précédant : "+ precedentsDijktra.getValue().accesPrecedent().accesNom());
        }

        System.out.println("Ce chemin à un poids de "+Algorithmes.resultatDijktraValeur(listeSommet.get(0), Algorithmes.dijktra(listeSommet, listeArete, listeSommet.get(4), Algorithmes.DISTANCE)));

        List<Sommet> listePrecedents = Algorithmes.resultatDijktraListePrecedents(listeSommet.get(1), listeSommet.get(0), Algorithmes.dijktra(listeSommet, listeArete, listeSommet.get(1), Algorithmes.DISTANCE));

        for (Sommet sommet:listePrecedents) {
            System.out.println(sommet.accesNom());
        }


        //Fin test dijktra
    }
}
