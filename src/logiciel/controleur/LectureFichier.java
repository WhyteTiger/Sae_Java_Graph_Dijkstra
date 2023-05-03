package logiciel.controleur;

import exceptions.FichierIncorrectException;
import logiciel.modele.Arete;
import logiciel.modele.Sommet;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectureFichier {

    // <editor-fold defaultstate="collapsed" desc="CREER LISTE SOMMET">

    /**
     * Cré une liste de Sommet grâce à un fichier csv donné, agencé d'une certaine manière ([nom sommet],[type du sommet]).
     *
     * @param nomFichier Nom du fichier.
     * @return La liste des sommets.
     * @throws FichierIncorrectException Lorsque le fichier est incorrect.
     */
    public static @NotNull List<Sommet> creerListeSommet(String nomFichier) throws FichierIncorrectException {

        //Initialisation des variables utiles et de la liste
        String ligne;
        String delimiteur = ","; //On prendra toutes les chaines de caractères en les séparant par des ","
        List<Sommet> listeSommet = new ArrayList<>();

        try (BufferedReader lecteurF = new BufferedReader(new FileReader(nomFichier))) {

            //Tant que la ligne n'est pas vide, on va l'analyser
            while ((ligne = lecteurF.readLine()) != null) {
                String[] donees = ligne.split(delimiteur);
                String nom = donees[0]; //La 1ère donnée de la ligne est le nom
                String type = donees[1]; //La 2de donnée de la ligne est le type ("M", "N" ou "O")

                //Sauf si c'est la 1ère ligne du fichier, précisant les données et leur agencement.
                //On analyse le nom et le type pour savoir s'ils sont corrects
                if(!nom.equals("Nom du sommet")) {
                    //Pour vérifier que les noms soient différents
                    for (Sommet sommet: listeSommet) {
                        if(sommet.accesNom().equals(nom)){
                            throw new FichierIncorrectException("Erreur : Deux nom de sommets pareils"); //Si ce n'est pas le cas, on lance une FichierIncorrectException
                        }
                    }

                    //Pour vérifier que le type soit bien égal à "M", "N" ou "O"
                    if(!type.equals("M") && !type.equals("N") && !type.equals("O")){
                        throw new FichierIncorrectException("Erreur : Type incorrect"); //Si ce n'est pas le cas, on lance une FichierIncorrectException
                    }

                    //Si tout est bon, on ajoute le sommet à la liste
                    listeSommet.add(new Sommet(nom, type));
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return listeSommet;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CREER LISTE ARETE">

    /**
     * Cré une liste d'Arête grâce à un fichier csv donné et d'une liste de Sommet, agencé d'une certaine manière ([nom de l'arête],[fiabilité],[distance],[durée],[sommet1],[sommet2]).
     *
     * @param nomFichier Nom du fichier.
     * @param listeSommet La liste de sommet donnée.
     * @return La liste des arêtes.
     * @throws FichierIncorrectException Lorsque le fichier est incorrect.
     */
    public static @NotNull List<Arete> creerListeArete(String nomFichier, List<Sommet> listeSommet) throws FichierIncorrectException {

        //Initialisation des variables utiles et de la liste
        String ligne;
        String delimiteur = ","; //On prendra toutes les chaines de caractères en les séparant par des ","
        List<Arete> listeArete = new ArrayList<>();

        try (BufferedReader lecteurF = new BufferedReader(new FileReader(nomFichier))) {

            //Tant que la ligne n'est pas vide, on va l'analyser
            while ((ligne = lecteurF.readLine()) != null) {
                String[] donees = ligne.split(delimiteur);
                String nom = donees[0]; //La 1ère donnée de la ligne est le nom

                //Sauf si c'est la 1ère ligne du fichier, précisant les données et leur agencement.
                //On analyse le nom et le type pour savoir s'ils sont corrects
                if(!nom.equals("Nom de l’arrête")) {

                    //Pour vérifier que les noms soient différents
                    for (Arete arete: listeArete) {
                        if(arete.accesNom().equals(nom)){
                            throw new FichierIncorrectException("Erreur : Deux nom d'arête pareils"); //Si ce n'est pas le cas, on lance une FichierIncorrectException
                        }
                    }

                    double fiabilite = Double.parseDouble(donees[1]); //La 2e donnée de la ligne est la fiabilité
                    //Pour vérifier que la fiabilité soit bien comprise entre 0 et 1
                    if(fiabilite<0.0 || fiabilite>1.0){
                        throw new FichierIncorrectException("Erreur : Valeur fiabilité de l'arête "+nom+" est incorrecte."); //Si ce n'est pas le cas, on lance une FichierIncorrectException
                    }

                    double distance = Double.parseDouble(donees[2]); //La 3e donnée de la ligne est la distance
                    //Pour vérifier que la distance soit bien supérieur à 0
                    if(distance<0.0){
                        throw new FichierIncorrectException("Erreur : Valeur distance de l'arête "+nom+" est incorrecte."); //Si ce n'est pas le cas, on lance une FichierIncorrectException
                    }

                    double duree = Double.parseDouble(donees[3]); //La 4e donnée de la ligne est la durée
                    //Pour vérifier que la distance soit bien supérieur à 0
                    if(duree<0.0){
                        throw new FichierIncorrectException("Erreur : Valeur durée de l'arête "+nom+" est incorrecte."); //Si ce n'est pas le cas, on lance une FichierIncorrectException
                    }

                    Sommet sommet1 = Sommet.recupererViaNom(listeSommet, donees[4]); //La 5e donnée de la ligne est un sommet de l'arête
                    //Pour vérifier que le sommet1 soit correct
                    if(sommet1 == null){
                        throw new FichierIncorrectException("Erreur : Sommet 1 de l'arête "+nom+" est incorrecte."); //Si ce n'est pas le cas, on lance une FichierIncorrectException
                    }

                    //Pour vérifier que le sommet2 soit correct
                    Sommet sommet2 = Sommet.recupererViaNom(listeSommet, donees[5]); //La 6e donnée de la ligne est l'autre sommet de l'arête
                    if(sommet2 == null){
                        throw new FichierIncorrectException("Erreur : Sommet 2 de l'arête "+nom+" est incorrecte."); //Si ce n'est pas le cas, on lance une FichierIncorrectException
                    }

                    //Pour vérifier que le sommet1 et le sommet2 soient différents
                    if(sommet1.equals(sommet2)){
                        throw new FichierIncorrectException("Erreur : Les Sommet 1 et 2 de l'arête "+nom+" sont les mêmes."); //Si ce n'est pas le cas, on lance une FichierIncorrectException
                    }

                    //Pour ranger les sommets par ordre croissant entre sommet1 et sommet2
                    if(sommet1.compareTo(sommet2) > 0){
                        Sommet sommetTemporaire = sommet1;
                        sommet1 = sommet2;
                        sommet2 = sommetTemporaire;
                    }

                    //Si tout est bon, on ajoute l'arête à la liste
                    listeArete.add(new Arete(nom, fiabilite, distance, duree, sommet1, sommet2));
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return listeArete;
    }

    // </editor-fold>
}
