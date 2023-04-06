package Logiciel.Controleur;

import Logiciel.Modele.Sommet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectureFichier {
    public static ArrayList<Sommet> afficher(String nomFichier) {
        String ligne;
        String delimiter = ",";
        ArrayList<Sommet> listeSommet = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            while ((ligne = br.readLine()) != null) {
                String[] data = ligne.split(delimiter);
                String nom = data[0];
                String type = data[1];
                listeSommet.add(new Sommet(nom, type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        listeSommet.forEach(System.out::println);
        return listeSommet;
    }
}
