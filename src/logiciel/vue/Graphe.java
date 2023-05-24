package logiciel.vue;

import logiciel.modele.Forme;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class Graphe extends JPanel {

    private List<ElementDuGraphe> listeDElements;


    public Graphe(List<ElementDuGraphe> listeDElements) {
        this.listeDElements = listeDElements;

        Dimension dimension = new Dimension(1000, 800);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);

        repaint();

        //TO DO
        //Ajouter les mouseClickedListener et mouseDraggedListener pour avoir des information des les noeuds du graphe et pouvoir les déplacer
        //TO DO

    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        g = g2D;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1000, 800);

        for (ElementDuGraphe element: listeDElements) {

            g.setColor(element.accesCouleur());

            if(element.accesForme().equals(Forme.ROND)){
                g.fillOval((int) (element.accesCoordX1()-element.accesTaille()/2), (int) (element.accesCoordY1()-element.accesTaille()/2), element.accesTaille(), element.accesTaille());
            }
            if(element.accesForme().equals(Forme.TRAIT)){
                g.drawLine((int) element.accesCoordX1(), (int) element.accesCoordY1(), (int) element.accesCoordX2(), (int) element.accesCoordY2());
            }
        }
    }
}

