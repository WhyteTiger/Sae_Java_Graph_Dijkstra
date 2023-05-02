package logiciel.modele;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Algorithmes {

    // <editor-fold defaultstate="collapsed" desc="CONSTANTES">
    public static double INFINI_POSITIF = Double.MAX_VALUE;
    public static double INFINI_NEGATIF = Double.MIN_VALUE;
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="SOUS-METHODES DIJKTRA">
    private static Sommet rechercherSiADistanceMinimale(Map<Sommet, Double> mapDijktra) {

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
    public static void relacher(Sommet si, Sommet sj, Map<Sommet, Double> mapDijktra, Map<Sommet, Sommet> mapPrecedents, List<Arete> listeArete){

        System.out.println("-----------RELACHEMENT----------");
        System.out.println("value sj :"+mapDijktra.get(sj));

        if(mapDijktra.get(sj) == null){
            throw new RuntimeException("sj null dans relacher avant distance");
        }

        double distanceEntreSiEtSj = mapDijktra.get(si)+ Algorithmes.distanceEntreDeuxSommets(si, sj, listeArete);

        System.out.println("\ndistance si sj :"+distanceEntreSiEtSj);

        if(mapDijktra.get(sj) == null){
            throw new RuntimeException("sj null dans relacher");
        }

        if (mapDijktra.get(sj) > distanceEntreSiEtSj){
            mapDijktra.replace(sj,  distanceEntreSiEtSj);
            mapPrecedents.replace(sj, si);

            System.out.println("nouvelle valeur sj : "+mapDijktra.get(sj));
            System.out.println("nouvelle valeur sj map precedente : "+mapPrecedents.get(sj));
        }
        System.out.println("value sj fin:"+mapDijktra.get(sj));
        System.out.println("-----------FIN RELACHEMENT----------");
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
    public static Map<Sommet, Sommet> dijktra(List<Sommet> listeSommet, List<Arete> listeArete, Sommet s0){

        TreeMap<Sommet, Double> mapDijktra = new TreeMap<>();
        TreeMap<Sommet, Sommet> mapPrecedents= new TreeMap<>();

        //Initialisation des map Dijktra et Précédents
        for (Sommet sommet : listeSommet) {

            mapDijktra.put(sommet, INFINI_POSITIF);
            mapPrecedents.put(sommet, null);

            if (sommet.equals(s0)) {
                mapDijktra.replace(sommet, 0.0);
                mapPrecedents.replace(sommet, sommet);
            }
        }

        for (Map.Entry<Sommet, Double> sommetMapDijktra : mapDijktra.entrySet()) {
            System.out.println("Key: "+sommetMapDijktra.getKey()+"  value: "+sommetMapDijktra.getValue());
        }

        System.out.println("\n\n");

        while(mapDijktra.size() > 1){
            //On récupère l'indice du sommet ayant la plus courte distance au sommet étudié
            Sommet si = Algorithmes.rechercherSiADistanceMinimale(mapDijktra);

            System.out.println("sommet si etudié : "+si.accesNom());

            //Pour chaque arête, on vérifie si le sommet étudié en fait parti, si c'est le cas on relâche l'arête
            for (Arete arete : listeArete) {

                System.out.println("arrete etudié : "+arete.accesNom());

                System.out.println("\n"+mapDijktra.containsKey(arete.accesSommet2()));
                System.out.println("ESTCEQUELEIFPASSE : "+(arete.accesSommet1().equals(si) && mapDijktra.containsKey(arete.accesSommet2())));
                if (arete.accesSommet1().equals(si) && mapDijktra.containsKey(arete.accesSommet2())){
                    Sommet sj = arete.accesSommet2();

                    System.out.println("sommet sj : "+sj.accesNom()+" value: "+mapDijktra.get(sj));

                    Algorithmes.relacher(si, sj, mapDijktra, mapPrecedents, listeArete);

                    System.out.println("\narete relache\n");
                }

                /*
                if (arete.accesSommet2().equals(si)){
                    Sommet sj = arete.accesSommet1();

                    System.out.println("sommet sj : "+sj.accesNom());

                    Algorithmes.relacher(si, sj, mapDijktra, mapPrecedents, listeArete);

                    System.out.println("\narete relache\n");
                }
                 */
            }

            mapDijktra.remove(si); //Du coup, on le supprime des sommets à traiter, vu qu'on vient de le faire

            System.out.println("remove "+mapDijktra.containsKey(si));

            for (Map.Entry<Sommet, Double> sommetMapDijktra : mapDijktra.entrySet()) {
                System.out.println("Key: "+sommetMapDijktra.getKey()+"  value: "+sommetMapDijktra.getValue());
            }
        }

        return mapPrecedents;
    }
    // </editor-fold>
}
