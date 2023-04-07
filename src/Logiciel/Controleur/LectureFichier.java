package Logiciel.Controleur;

import Logiciel.Modele.Arrete;
import Logiciel.Modele.Sommet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectureFichier {
    public static List<Sommet> creerListeSommet(String nomFichier) {
        String ligne;
        String delimiteur = ",";
        List<Sommet> listeSommet = new ArrayList<>();

        try (BufferedReader lecteurF = new BufferedReader(new FileReader(nomFichier))) {
            while ((ligne = lecteurF.readLine()) != null) {
                String[] donees = ligne.split(delimiteur);
                String nom = donees[0];
                String type = donees[1];
                listeSommet.add(new Sommet(nom, type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listeSommet;
    }

    public static List<Arrete> creerListeArrete(String nomFichier, List<Sommet> listeSommet) {
        String ligne;
        String delimiteur = ",";
        List<Arrete> listeArrete = new ArrayList<>();

        try (BufferedReader lecteurF = new BufferedReader(new FileReader(nomFichier))) {
            while ((ligne = lecteurF.readLine()) != null) {
                String[] donees = ligne.split(delimiteur);
                String nom = donees[0];
                double fiabilite = Double.parseDouble(donees[1]);
                double distance = Double.parseDouble(donees[2]);
                double duree = Double.parseDouble(donees[3]);
                Sommet sommet1 = Sommet.recupererViaNom(listeSommet, donees[4]);
                Sommet sommet2 = Sommet.recupererViaNom(listeSommet, donees[5]);
                listeArrete.add(new Arrete(nom, fiabilite, distance, duree, sommet1, sommet2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listeArrete;
    }
}
