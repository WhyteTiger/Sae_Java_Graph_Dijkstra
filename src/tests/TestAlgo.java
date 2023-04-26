package tests;

import logiciel.controleur.LectureFichier;
import logiciel.modele.Algorithmes;
import logiciel.modele.Arrete;
import logiciel.modele.Sommet;

import java.util.Collections;
import java.util.List;

public class TestAlgo {
    public static void main(String[] args) {

        //Pour test dijktra
        List<Sommet> listeSommet = LectureFichier.creerListeSommet("Liste_Sommet.csv");
        List<Arrete> listeArrete = LectureFichier.creerListeArrete("Liste_Arrete.csv", listeSommet);
        Collections.sort(listeSommet);
        Collections.sort(listeArrete);

        Sommet s0 = listeSommet.get(1);

        double[][] tabDijktra = Algorithmes.dijktra(listeSommet, listeArrete, s0);
        int n = tabDijktra.length;

        for (int i =0; i<n; i++) {
            System.out.println("Colonne "+ i);
            for (int j = 0; j < 2; j++) {
                System.out.print("Ligne "+j+" ");
                System.out.println(tabDijktra[i][j]);
            }
            System.out.println();
        }
        //Fin test dijktra
    }
}
