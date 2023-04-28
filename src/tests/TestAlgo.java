package tests;

import logiciel.controleur.LectureFichier;
import logiciel.modele.Algorithmes;
import logiciel.modele.Arrete;
import logiciel.modele.Sommet;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestAlgo {
    public static void main(String[] args) {

        //Pour test dijktra

        //Crée les listes de sommet et d'arrête
        List<Sommet> listeSommet = LectureFichier.creerListeSommet("Liste_Sommet.csv");
        List<Arrete> listeArrete = LectureFichier.creerListeArrete("Liste_Arrete.csv", listeSommet);

        //Choix du sommet à traiter
        Sommet s0 = listeSommet.get(0);


        HashMap<Sommet, Sommet> mapPrecedentsDijktra = Algorithmes.dijktra(listeSommet, listeArrete, s0);


        //Affiche la map retournée
        for (Map.Entry<Sommet, Sommet> precedentsDijktra : mapPrecedentsDijktra.entrySet()) {
            System.out.println(precedentsDijktra.getKey() +" "+ precedentsDijktra.getValue());
        }
        //Fin test dijktra
    }
}
