package Logiciel.Controleur;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectureFichier {
    public void lectureFichier() {
        try {
            FileReader reader = new FileReader(""); //mettre le chemin vers le fichier
            int data = reader.read();
            while (data != -1) {
                System.out.print((char) data);
                data = reader.read();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("not found");
        } catch (IOException e) {
            System.out.println("err");
        }
    }
}
