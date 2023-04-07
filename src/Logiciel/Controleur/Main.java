package Logiciel.Controleur;

import Logiciel.Modele.Arrete;
import Logiciel.Modele.Sommet;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Sommet> listeSommet = LectureFichier.creerListeSommet("Liste_Sommet.csv");
        List<Arrete> listeArrete = LectureFichier.creerListeArrete("Liste_Arrete.csv", listeSommet);
    }
}
