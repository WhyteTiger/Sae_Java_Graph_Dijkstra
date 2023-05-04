package logiciel.vue;

import logiciel.modele.Arete;
import logiciel.modele.Sommet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graphe extends JComponent {

    private final List<Rond> listeRond;
    private final List<Trait> listeTrait;
    private Graphics graphe;

    public Graphe(List<Sommet> listeSommet, List<Arete> listeArete, double tailleHorizontale, double tailleVerticale) {
        List<Rond> listeRond = new ArrayList<>();
        List<Trait> listeTrait = new ArrayList<>();
        for (Sommet sommet:listeSommet) {
            listeRond.add(new Rond(sommet.accesNom()));
        }
        for (Arete arete: listeArete){
            listeTrait.add(new Trait(arete.accesNom(), new Point(1, 1), new Point(2*tailleHorizontale, 5*tailleHorizontale)));
        }
        this.listeRond = listeRond;
        this.listeTrait = listeTrait;
    }

    public void paintComponent(Graphics graphe){
        for (Trait trait : listeTrait) {
            graphe.drawLine((int) trait.accesP1().accesCoordX(), (int) trait.accesP1().accesCoordY(), (int) trait.accesP1().accesCoordX(), (int) trait.accesP1().accesCoordY());
        }


    }

}

