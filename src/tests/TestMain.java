package tests;

import exceptions.FichierIncorrectException;
import logiciel.controleur.LectureFichier;
import logiciel.modele.Arete;
import logiciel.modele.Sommet;
import logiciel.vue.Graphe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestMain {
    public static class Fenetre extends JFrame {
        public Fenetre(List<Sommet> listeSommet, List<Arete> listeArete) {
            this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
            this.setSize(300, 250);
            this.setTitle("Test dessins");
            this.getContentPane().setBackground(Color.ORANGE);
            this.getContentPane().add(new Graphe(listeSommet, listeArete, 300, 250));
        }

    }



    public static void main(String[] args) {
        java.util.List<Sommet> listeSommet;
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
        new Fenetre(listeSommet, listeArete).setVisible(true);
    }

}
