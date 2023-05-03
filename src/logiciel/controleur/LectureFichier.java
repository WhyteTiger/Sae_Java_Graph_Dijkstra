package logiciel.controleur;

import exceptions.FichierIncorrectException;
import logiciel.modele.Arete;
import logiciel.modele.Sommet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
                if(!nom.equals("Nom du sommet")) {
                    for (Sommet sommet: listeSommet) {
                        if(sommet.accesNom().equals(nom)){
                            throw new FichierIncorrectException("Erreur : Deux nom de sommets pareils");
                        }
                    }
                    if(!type.equals("M") && !type.equals("N") && !type.equals("O")){
                        throw new FichierIncorrectException("Erreur : Type incorrect");
                    }
                    listeSommet.add(new Sommet(nom, type));
                }
            }
            Collections.sort(listeSommet);
        } catch (FichierIncorrectException e){
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listeSommet;
    }

    public static List<Arete> creerListeArete(String nomFichier, List<Sommet> listeSommet) {
        String ligne;
        String delimiteur = ",";
        List<Arete> listeArete = new ArrayList<>();

        try (BufferedReader lecteurF = new BufferedReader(new FileReader(nomFichier))) {
            while ((ligne = lecteurF.readLine()) != null) {
                String[] donees = ligne.split(delimiteur);
                String nom = donees[0];
                if(nom.startsWith("A")) {
                    double fiabilite = Double.parseDouble(donees[1]);
                    double distance = Double.parseDouble(donees[2]);
                    double duree = Double.parseDouble(donees[3]);
                    Sommet sommet1 = Sommet.recupererViaNom(listeSommet, donees[4]);
                    Sommet sommet2 = Sommet.recupererViaNom(listeSommet, donees[5]);

                    //Pour ranger les sommets par ordre croissant entre sommet1 et sommet2
                    assert sommet1 != null;
                    assert sommet2 != null;
                    if(sommet1.compareTo(sommet2) > 0){
                        Sommet stmp = sommet1;
                        sommet1 = sommet2;
                        sommet2 = stmp;
                    }

                    listeArete.add(new Arete(nom, fiabilite, distance, duree, sommet1, sommet2));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listeArete;
    }
}
