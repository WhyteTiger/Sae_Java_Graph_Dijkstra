package tests;

import logiciel.controleur.LectureFichier;
import logiciel.modele.Algorithmes;
import logiciel.modele.Arete;
import logiciel.modele.Sommet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestAlgo {
    public static void main(String[] args) {

        //Pour test dijktra

        //Crée les listes de sommet et d'arête
        List<Sommet> listeSommet = LectureFichier.creerListeSommet("Liste_Sommet_Test.csv");
        List<Arete> listeArete = LectureFichier.creerListeArete("Liste_Arrete_Test.csv", listeSommet);

        //Choix du sommet à traiter
        Sommet s0 = listeSommet.get(1);


        Map<Sommet, Sommet> mapPrecedentsDijktra = Algorithmes.dijktra(listeSommet, listeArete, s0);


        //Affiche la map retournée
        for (Map.Entry<Sommet, Sommet> precedentsDijktra : mapPrecedentsDijktra.entrySet()) {
            System.out.println("Sommet : "+precedentsDijktra.getKey().accesNom() +"   Précédant : "+ precedentsDijktra.getValue().accesNom());
        }
        //Fin test dijktra
    }
}
