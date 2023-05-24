package tests;

import exceptions.FichierIncorrectException;
import logiciel.controleur.LectureFichier;
import logiciel.modele.Arete;
import logiciel.modele.Sommet;
import logiciel.vue.Graphe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestGraphe {
    public static void main(String[] args) {

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

        JFrame fenetre = new JFrame("Test Graphe");

        JPanel grapheTest = new Graphe(listeSommet, listeArete, new Dimension(1000, 800));

        fenetre.add(grapheTest);
        fenetre.pack();
        fenetre.setVisible(true);

        System.out.println("Fin main");
    }
}
