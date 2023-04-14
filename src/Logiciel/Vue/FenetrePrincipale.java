package Logiciel.Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

        JPanel pan2 = new JPanel(new GridLayout(3,1,10,10));
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
            JFileChooser sl = new JFileChooser();
            int resultat = sl.showDialog(this, "ouvrir");
            if (resultat == JFileChooser.APPROVE_OPTION){
                File f = sl.getSelectedFile();
                String ch = f.getAbsolutePath();
                ImageIcon image = new ImageIcon(ch); //test avec une image
                //description.setIcon(image);
            }
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
