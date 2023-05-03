package tests;

import exceptions.FichierIncorrectException;
import logiciel.controleur.LectureFichier;
import logiciel.modele.Algorithmes;
import logiciel.modele.Arete;
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


        Map<Sommet, Sommet> mapPrecedentsDijktra = Algorithmes.dijktra(listeSommet, listeArete, s0, Algorithmes.DUREE);


        //Affiche la map retournée
        for (Map.Entry<Sommet, Sommet> precedentsDijktra : mapPrecedentsDijktra.entrySet()) {
            System.out.println("Sommet : "+precedentsDijktra.getKey().accesNom() +"   Précédant : "+ precedentsDijktra.getValue().accesNom());
        }
        //Fin test dijktra
    }
}
