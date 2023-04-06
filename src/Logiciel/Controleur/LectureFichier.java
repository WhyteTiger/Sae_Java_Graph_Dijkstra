package Logiciel.Controleur;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectureFichier {public static void afficher(String nomFichier) {
        try {
            FileReader lecteurf = new FileReader(nomFichier); //mettre le chemin vers le fichier
            int donees = lecteurf.read();
            while (donees != -1) {
                System.out.print((char) donees);
                donees = lecteurf.read();
            }
            lecteurf.close();
        } catch (FileNotFoundException e) {
            System.out.println("not found");
        } catch (IOException e) {
            System.out.println("err");
        }
    }
}
