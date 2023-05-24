package tests;

import exceptions.FichierIncorrectException;
import logiciel.controleur.LectureFichier;
import logiciel.modele.Arete;
import logiciel.modele.Forme;
import logiciel.modele.Sommet;
import logiciel.vue.ElementDuGraphe;
import logiciel.vue.Graphe;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TestGraphe {
    public static void main(String[] args) {

        List<ElementDuGraphe> listeDElements = new LinkedList<>();
        Random alea = new Random();

        List<Sommet> listeSommet;
        try {
            listeSommet = LectureFichier.creerListeSommet("Liste_Sommet_Test.csv");
        } catch (FichierIncorrectException e) {
            throw new RuntimeException(e);
        }

        for(Sommet sommet : listeSommet){
            Color couleur = Color.BLACK;
            if(sommet.accesType().equals("M")){
                couleur = Color.BLUE;
            }
            if (sommet.accesType().equals("N")){
                couleur = Color.YELLOW;
            }
            if (sommet.accesType().equals("O")){
                couleur = Color.ORANGE;
            }
            ElementDuGraphe nouvelElement = new ElementDuGraphe(sommet.accesNom(), alea.nextInt(900), alea.nextInt(700), 0, 0, 50, couleur, Forme.ROND);
            listeDElements.add(nouvelElement);
        }

        List<Arete> listeArete;
        try {
            listeArete = LectureFichier.creerListeArete("Liste_Arete_Test.csv", listeSommet);
        } catch (FichierIncorrectException e) {
            throw new RuntimeException(e);
        }
        for(Arete arete : listeArete){

            int coordonneeX1 = 0, coordonneeY1 = 0, coordonneeX2 = 0, coordonneeY2 = 0;

            for (ElementDuGraphe element: listeDElements) {
                if (element.accesNomElement().equals(arete.accesSommet1().accesNom())){
                    coordonneeX1 = (int) element.accesCoordX1();
                    coordonneeY1 = (int) element.accesCoordY1();
                }
                if (element.accesNomElement().equals(arete.accesSommet2().accesNom())){
                    coordonneeX2 = (int) element.accesCoordX1();
                    coordonneeY2 = (int) element.accesCoordY1();
                }
            }

            ElementDuGraphe nouvelElement = new ElementDuGraphe(arete.accesNom(), coordonneeX1, coordonneeY1, coordonneeX2, coordonneeY2, 10, Color.BLACK, Forme.TRAIT);
            listeDElements.add(nouvelElement);
        }

        JFrame fenetre = new JFrame("Test Graphe");

        JPanel grapheTest = new Graphe(listeDElements);
        grapheTest.repaint();

        fenetre.add(grapheTest);
        fenetre.pack();
        fenetre.setVisible(true);

        System.out.println("Fin main");
    }
}
