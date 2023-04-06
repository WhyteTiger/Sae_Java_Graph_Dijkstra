package Logiciel.Controleur;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectureFichier {

    public static void afficher(String nomFichier) {
        try {
            FileReader freader = new FileReader(nomFichier); //mettre le chemin vers le fichier
            int data = freader.read();
            while (data != -1) {
                System.out.print((char) data);
                data = freader.read();
            }
            freader.close();
        } catch (FileNotFoundException e) {
            System.out.println("not found");
        } catch (IOException e) {
            System.out.println("err");
        }
    }
}
