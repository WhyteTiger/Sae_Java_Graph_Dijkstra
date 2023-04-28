package logiciel.modele;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

public class Algorithmes {

    public static double INFINI_POSITIF = Double.MAX_VALUE;
    public static double INFINI_NEGATIF = Double.MIN_VALUE;

    //public static Arrete[][] creationMaitreAdjacence(List<Sommet> listeDeSommet, List<Arrete> listeDArrete){

    //}



    /**
     * Crée une liste chainée de sommet grâce à une liste de sommet donnée
     * @param listeSommet liste des sommets qu'on veut copier dans listeDesSommetsATraiter
     * @return la liste chainée des sommets à traiter
     */
    private static ListeChaineeSommet ajoutDesSommets(List<Sommet> listeSommet){
        ListeChaineeSommet listeDesSommetsATraiter = new ListeChaineeSommet();

        for (Sommet sommet : listeSommet) {
            listeDesSommetsATraiter.ajouterSommet(sommet);
        }

        return listeDesSommetsATraiter;
    }


    private static Sommet rechercherSiADistanceMinimale(HashMap<Sommet, Double> mapDijktra) {

        double dSiMinimale = INFINI_POSITIF;
        Sommet sDMin = null;

        for (Map.Entry<Sommet, Double> sommetMapDijktra : mapDijktra.entrySet()) {
            if (sommetMapDijktra.getValue() < dSiMinimale) {
                dSiMinimale = sommetMapDijktra.getValue();
                sDMin = sommetMapDijktra.getKey();
            }
        }
        return sDMin;
    }

    /**
     * Donne la distance entre deux sommets donnés, grâce à une liste d'arrête.
     * @param si Le premier sommet.
     * @param sj Le second sommet.
     * @param listeArrete La liste des arrêtes.
     * @return La distance recherchée, entre le sommet si et le sommet sj, ou infini si elle n'existe pas.
     */
    private static double distanceEntreDeuxSommets(Sommet si, Sommet sj, List<Arrete> listeArrete){

        for (Arrete arrete : listeArrete) {
            if ((arrete.accesSommet1().equals(si) && arrete.accesSommet2().equals(sj)) || (arrete.accesSommet1().equals(sj) && arrete.accesSommet2().equals(si))){
                return arrete.accesDistance();
            }
        }
        return INFINI_POSITIF;
    }

    //Relache deux arcs entre le sommet à traité et les sommets d'indice i et j.
    public static void relacher(Sommet si, Sommet sj, HashMap<Sommet, Double> mapDijktra, HashMap<Sommet, Sommet> mapPrecedents, List<Arrete> listeArrete){

        double distanceEntreSiEtSj = mapDijktra.get(si)+ Algorithmes.distanceEntreDeuxSommets(si, sj, listeArrete);

        if (mapDijktra.get(sj) > distanceEntreSiEtSj){
            mapDijktra.replace(sj,  distanceEntreSiEtSj);
            mapPrecedents.replace(sj, si);
        }
    }

    /**
     * Algorithme Dijktra, il permet de connaître les plus courtes distances, dans un graphe, entre un sommet donné et les autres, grâce à la liste de sommet et la liste des arrêtes du graphe, et d'un sommet de départ à étudier.
     * @param listeSommet La liste des sommets du graphe
     * @param listeArrete La liste des arrêtes du graphe
     * @param s0 le sommet de départ étudié
     * @return Un tableau contenant, dans sa première ligne la distance entre un sommet et son précédant pour avoir le chemin le plus court en partant de s0, et dans sa seconde ligne le sommet précédant.
     */
    public static HashMap<Sommet, Sommet> dijktra(List<Sommet> listeSommet, List<Arrete> listeArrete, Sommet s0){

        HashMap<Sommet, Double> mapDijktra = new HashMap<>();
        HashMap<Sommet, Sommet> mapPrecedents= new HashMap<>();

        for (Sommet sommet:listeSommet) {

            if(sommet.equals(s0)){
                mapDijktra.put(sommet, 0.0);
            } else {
                mapDijktra.put(sommet, INFINI_POSITIF);
            }

            mapPrecedents.put(sommet, null);
        }

        while(mapDijktra.size() > 1){
            //On récupère l'indice du sommet ayant la plus courte distance au sommet étudié
            Sommet si = Algorithmes.rechercherSiADistanceMinimale(mapDijktra);

            for (Arrete arrete: listeArrete) {
                if (arrete.accesSommet1().equals(si)){
                    Sommet sj = arrete.accesSommet2();
                    Algorithmes.relacher(si, sj, mapDijktra, mapPrecedents, listeArrete);
                }
                if (arrete.accesSommet2().equals(si)){
                    Sommet sj = arrete.accesSommet1();
                    Algorithmes.relacher(si, sj, mapDijktra, mapPrecedents, listeArrete);
                }
            }
            mapDijktra.remove(si); //Du coup, on le supprime des sommets à traiter, vu qu'on vient de le faire
        }
        return mapPrecedents;
    }
}
