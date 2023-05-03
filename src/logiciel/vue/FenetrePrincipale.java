package logiciel.vue;

import exceptions.FichierIncorrectException;
import logiciel.controleur.LectureFichier;
import logiciel.modele.Arete;
import logiciel.modele.Sommet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;
import java.util.List;

public class FenetrePrincipale extends JFrame implements ActionListener {

    //Defini tous nos Wigets

    private JMenuBar menuBar;
    private JMenu fichier;
    private JMenuItem sauvegarde;
    private JMenuItem chargement;
    private JMenuItem sortie;

    private JLabel lChargement;

    private JComboBox option;
    private JComboBox modification;

    private JLabel lgraphe;

    private JLabel ldescription;

    private String str = "";

    public FenetrePrincipale(){
        this.initComponent();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Ecran Accueil");
    }

    private void initComponent(){
        this.menuBar = new JMenuBar();
        this.fichier = new JMenu("Fichier");
        this.chargement = new JMenuItem("Chargement");
        this.sauvegarde = new JMenuItem("Sauvegarde");
        this.sortie = new JMenuItem("Sortie");

        this.menuBar.add(fichier);
        this.fichier.add(chargement);
        this.fichier.add(sauvegarde);
        this.fichier.add(sortie);

        this.option = new JComboBox();
        this.option.addItem("option1");
        this.option.addItem("option2");
        this.option.addItem("option3");

        this.modification = new JComboBox();
        this.modification.addItem("option1");
        this.modification.addItem("option2");
        this.modification.addItem("option3");

        this.option.setPreferredSize(new Dimension(100, 25));
        this.modification.setPreferredSize(new Dimension(100, 25));

        this.lChargement = new JLabel();
        this.lgraphe = new JLabel();
        this.ldescription = new JLabel();

        this.lChargement.setPreferredSize(new Dimension(100, 25));
        this.lChargement.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        this.lgraphe.setPreferredSize(new Dimension(500, 300));
        this.lgraphe.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        this.ldescription.setPreferredSize(new Dimension(400, 100));
        this.ldescription.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));



        JPanel panPrincipale = (JPanel) getContentPane();

        setLayout(new BorderLayout(10,4));

        this.setJMenuBar(menuBar);

        JPanel pan1 = new JPanel();
        pan1.add(lgraphe);

        JPanel pan2 = new JPanel(new GridLayout(6,1,10,10));
        pan2.add(lChargement);
        pan2.add(option);
        pan2.add(modification);

        JPanel pan3 = new JPanel();
        pan3.add(ldescription);

        panPrincipale.add(pan1, BorderLayout.CENTER);
        panPrincipale.add(pan2, BorderLayout.EAST);
        panPrincipale.add(pan3, BorderLayout.SOUTH);

        chargement.addActionListener(this);
        sauvegarde.addActionListener(this);
        sortie.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==chargement) {

            /* exemple pour ouvrir un fichier
            JFileChooser sl = new JFileChooser();
            int resultat = sl.showDialog(this, "ouvrir");
            if (resultat == JFileChooser.APPROVE_OPTION){
                File f = sl.getSelectedFile();
                String ch = f.getAbsolutePath();

            }

            Changer ListeSommet.csv par ch
            */

            List<Sommet> listeSommet = LectureFichier.creerListeSommet("Liste_Sommet.csv");
            List<Arete> listeArete = null;
            try {
                listeArete = LectureFichier.creerListeArete("Liste_Arrete.csv", listeSommet);
            } catch (FichierIncorrectException ex) {
                throw new RuntimeException(ex);
            }

            lChargement.setText("OK");

            //listeSommet.forEach(System.out::println);
            //listeArrete.forEach(System.out::println);

            for (Sommet sommet: listeSommet) {
                this.str = str +"\n"+ sommet.toString();
            }
            for (Arete arete : listeArete) {
                this.str = str +"\n"+ arete.toString();
            }

            ldescription.setText(str);


        }
        if(e.getSource()==sauvegarde) {
            System.out.println("Le fichier a bien ete sauvegarder");
            JFileChooser sl = new JFileChooser();
            sl.showSaveDialog(this);


        }
        if(e.getSource()==sortie) {
            System.exit(0);
        }
    }
}
