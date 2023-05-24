package logiciel.vue;

import logiciel.modele.Arete;
import logiciel.modele.Sommet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.awt.RenderingHints.*;

public class Graphe extends JPanel {
    private final List<VisuelSommet> listeVisuelSommet;
    private final List<VisuelArete> listeVisuelArete;


    public Graphe(List<Sommet> listeSommet, List<Arete> listeArete, Dimension dimension) {

        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);

        Random alea = new Random();
        listeVisuelSommet = new LinkedList<>();
        listeVisuelArete = new LinkedList<>();

        for (Sommet sommet : listeSommet){

            Color couleur = Color.BLACK;
            if(sommet.accesType().equals("M")){
                couleur = Color.BLUE;
            }
            if(sommet.accesType().equals("N")){
                couleur = Color.YELLOW;
            }
            if(sommet.accesType().equals("O")){
                couleur = Color.ORANGE;
            }

            listeVisuelSommet.add(new VisuelSommet(sommet.accesNom(), 50+alea.nextInt(800), 50+alea.nextInt(600), 50, couleur));
        }

        for (Arete arete : listeArete){

            int coordonneeX1 = 0, coordonneeY1 = 0, coordonneeX2 = 0, coordonneeY2 = 0;

            for (VisuelSommet sommet : listeVisuelSommet) {
                if (sommet.accesNomVisuel().equals(arete.accesSommet1().accesNom())){
                    coordonneeX1 = sommet.accesCoordX();
                    coordonneeY1 = sommet.accesCoordY();
                }
                if (sommet.accesNomVisuel().equals(arete.accesSommet2().accesNom())){
                    coordonneeX2 = sommet.accesCoordX();
                    coordonneeY2 = sommet.accesCoordY();
                }
            }

            listeVisuelArete.add(new VisuelArete(arete.accesNom(), coordonneeX1, coordonneeY1, coordonneeX2, coordonneeY2, Color.BLACK));
        }

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                for (VisuelSommet sommet : listeVisuelSommet) {

                    if(e.getX() >= sommet.accesCoordX()-sommet.accesTaille()/2 && e.getX() <= sommet.accesCoordX()+sommet.accesTaille()/2  &&  e.getY() >= sommet.accesCoordY()-sommet.accesTaille()/2 && e.getY() <= sommet.accesCoordY()+sommet.accesTaille()/2){
                        sommet.fixeCouleurContour(Color.RED);
                    }else{
                        sommet.fixeCouleurContour(sommet.accesCouleur());
                    }
                    repaint();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                for (VisuelSommet sommet : listeVisuelSommet) {
                    System.out.println("2 e X :"+e.getX()+"   e Y :"+e.getY());
                    if(e.getX() >= sommet.accesCoordX()-sommet.accesTaille()/2 && e.getX() <= sommet.accesCoordX()+sommet.accesTaille()/2  &&  e.getY() >= sommet.accesCoordY()-sommet.accesTaille()/2 && e.getY() <= sommet.accesCoordY()+sommet.accesTaille()/2){
                        System.out.println("3 e X :"+e.getX()+"   e Y :"+e.getY());
                        sommet.fixeCoordX(e.getX());
                        sommet.fixeCoordY(e.getY());

                        listeVisuelArete.clear();
                        for (Arete arete : listeArete){

                            int coordonneeX1 = 0, coordonneeY1 = 0, coordonneeX2 = 0, coordonneeY2 = 0;

                            for (VisuelSommet nouveauSommet : listeVisuelSommet) {
                                if (nouveauSommet.accesNomVisuel().equals(arete.accesSommet1().accesNom())){
                                    coordonneeX1 = nouveauSommet.accesCoordX();
                                    coordonneeY1 = nouveauSommet.accesCoordY();
                                }
                                if (nouveauSommet.accesNomVisuel().equals(arete.accesSommet2().accesNom())){
                                    coordonneeX2 = nouveauSommet.accesCoordX();
                                    coordonneeY2 = nouveauSommet.accesCoordY();
                                }
                            }

                            listeVisuelArete.add(new VisuelArete(arete.accesNom(), coordonneeX1, coordonneeY1, coordonneeX2, coordonneeY2, Color.BLACK));
                        }

                        repaint();
                    }
                }
            }
        });

    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_OFF);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1000, 800);

        //Print des traits avant les ronds pour que se soit plus beau
        for (VisuelArete arete : listeVisuelArete) {

            g.setColor(arete.accesCouleur());
            g.drawLine(arete.accesCoordX1(), arete.accesCoordY1(), arete.accesCoordX2(), arete.accesCoordY2());
        }

        for (VisuelSommet sommet : listeVisuelSommet) {

            g.setColor(sommet.accesCouleur());
            g.fillOval(sommet.accesCoordX()-sommet.accesTaille()/2, sommet.accesCoordY()-sommet.accesTaille()/2, sommet.accesTaille(), sommet.accesTaille());

            g.setColor(sommet.accesCouleurContour());
            g.drawOval((sommet.accesCoordX()-sommet.accesTaille()/2), (sommet.accesCoordY()-sommet.accesTaille()/2), sommet.accesTaille(), sommet.accesTaille());
        }



    }
}

