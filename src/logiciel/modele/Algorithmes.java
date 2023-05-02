package logiciel.modele;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithmes {

    // <editor-fold defaultstate="collapsed" desc="VARIABLES COMMUNES">
    public static double INFINI_POSITIF = Double.MAX_VALUE;
    public static double INFINI_NEGATIF = Double.MIN_VALUE;
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="SOUS-METHODES DIJKTRA">
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
     * Donne la distance entre deux sommets donnés, grâce à une liste d'arête.
     * @param si Le premier sommet.
     * @param sj Le second sommet.
     * @param listeArete La liste des arêtes.
     * @return La distance recherchée, entre le sommet si et le sommet sj, ou infini si elle n'existe pas.
     */
    private static double distanceEntreDeuxSommets(Sommet si, Sommet sj, List<Arete> listeArete){

        for (Arete arete : listeArete) {
            if ((arete.accesSommet1().equals(si) && arete.accesSommet2().equals(sj)) || (arete.accesSommet1().equals(sj) && arete.accesSommet2().equals(si))){
                return arete.accesDistance();
            }
        }
        return INFINI_POSITIF;
    }

    //Relâche deux arcs entre le sommet à traiter et les sommets d'indice i et j.
    public static void relacher(Sommet si, Sommet sj, HashMap<Sommet, Double> mapDijktra, HashMap<Sommet, Sommet> mapPrecedents, List<Arete> listeArete){

        double distanceEntreSiEtSj = mapDijktra.get(si)+ Algorithmes.distanceEntreDeuxSommets(si, sj, listeArete);

        if (mapDijktra.get(sj) > distanceEntreSiEtSj){
            mapDijktra.replace(sj,  distanceEntreSiEtSj);
            mapPrecedents.replace(sj, si);
        }
    }
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="DIJKTRA">
    /**
     * Algorithme Dijktra, il permet de connaître les plus courtes distances, dans un graphe, entre un sommet donné et les autres, grâce à la liste de sommet et la liste des arêtes du graphe, et d'un sommet de départ à étudier.
     * @param listeSommet La liste des sommets du graphe
     * @param listeArete La liste des arêtes du graphe
     * @param s0 le sommet de départ étudié
     * @return Un tableau contenant, dans sa première ligne la distance entre un sommet et son précédant pour avoir le chemin le plus court en partant de s0, et dans sa seconde ligne le sommet précédant.
     */
    public static HashMap<Sommet, Sommet> dijktra(List<Sommet> listeSommet, List<Arete> listeArete, Sommet s0){

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

            for (Arete arete : listeArete) {
                if (arete.accesSommet1().equals(si)){
                    Sommet sj = arete.accesSommet2();
                    Algorithmes.relacher(si, sj, mapDijktra, mapPrecedents, listeArete);
                }
                if (arete.accesSommet2().equals(si)){
                    Sommet sj = arete.accesSommet1();
                    Algorithmes.relacher(si, sj, mapDijktra, mapPrecedents, listeArete);
                }
            }
            mapDijktra.remove(si); //Du coup, on le supprime des sommets à traiter, vu qu'on vient de le faire
        }
        return mapPrecedents;
    }
    // </editor-fold>
}
