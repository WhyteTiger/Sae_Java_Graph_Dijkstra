package logiciel.modele;

import java.util.*;

public class Algorithmes {

    // <editor-fold defaultstate="collapsed" desc="CONSTANTES">
    public static double INFINI_POSITIF = Double.MAX_VALUE;
    public static double INFINI_NEGATIF = Double.MIN_VALUE;
    public static int FIABILITE = 1;
    public static int DISTANCE = 2;
    public static int DUREE = 3;
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="METHODES PUBLIQUES">

    // <editor-fold defaultstate="collapsed" desc="Sous-méthodes Dijktra">

    /**
     * Donne le sommet ayant la distance ou la durée la plus basse par rapport au sommet étudié ou la fiabilité la plus haute.
     *
     * @param mapDijktra l'ensemble des sommets.
     * @param caracteristique La caractéristique choisie, entre fiabilité, distance et durée.
     * @return sommet, le sommet voulu.
     */
    private static Sommet rechercherSiAyantCaractSpecifique(Map<Sommet, Double> mapDijktra, int caracteristique) {

        //On initialise le sommet retourné à null
        Sommet sommet
                = null;

        //Si la caractéristique recherchée est la fiabilité
        if(caracteristique == Algorithmes.FIABILITE){
            //On met la fiabilité minimale à 0
            double fiabiliteSiMaximale = 0;

            //Pour tous les sommets de la map, si la fiabilité du sommet est plus grande que la fiabilitéMaximale on la garde
            for (Map.Entry<Sommet, Double> sommetMapDijktra : mapDijktra.entrySet()) {
                if (sommetMapDijktra.getValue() > fiabiliteSiMaximale) {
                    fiabiliteSiMaximale = sommetMapDijktra.getValue();

                    sommet = sommetMapDijktra.getKey();
                }
            }
        } else {
            //Sinon, on met la caractSiMinimale à +infini
            double caractSiMinimale = INFINI_POSITIF;

            //Pour chaque sommet de la map, on compare sa caractéristique (distance/durée) avec celle minimale "sommet", si elle est plus petite on réactualise sommet
            for (Map.Entry<Sommet, Double> sommetMapDijktra : mapDijktra.entrySet()) {
                if (sommetMapDijktra.getValue() < caractSiMinimale) {
                    caractSiMinimale = sommetMapDijktra.getValue();

                    sommet = sommetMapDijktra.getKey();
                }
            }
        }
        return sommet;
    }

    /**
     * Donne la caractéristique voulue entre deux sommets donnés, grâce à une liste d'arête.
     *
     * @param si         Le premier sommet.
     * @param sj         Le second sommet.
     * @param listeArete La liste des arêtes.
     * @param caracteristique La caractéristique voulue
     * @return La caractéristique recherchée, entre le sommet si et le sommet sj, ou infini si elle n'existe pas.
     */
    private static double CaracteristiqueEntreDeuxSommets(Sommet si, Sommet sj, List<Arete> listeArete, int caracteristique){

        //Pour toutes les arêtes, on regarde si elle a comme sommet 1 et 2, les sommets si, sj
        for (Arete arete : listeArete) {
            if ((arete.accesSommet1().equals(si) && arete.accesSommet2().equals(sj)) || (arete.accesSommet1().equals(sj) && arete.accesSommet2().equals(si))){
                //En fonction de la caractéristique voulue, on retourne la bonne valeur
                switch (caracteristique){
                    case 1 : return arete.accesFiabilite();
                    case 2 : return arete.accesDistance();
                    case 3 : return arete.accesDuree();
                }
            }
        }
        //Si elle n'existe pas, retourne +infini
        return INFINI_POSITIF;
    }

    /**
     * Relâche deux arcs entre le sommet étudié et le sommet sj.
     *
     * @param si            Le sommet étudié.
     * @param sj            Un autre sommet pas encore étudié.
     * @param mapDijktra    L'ensemble des sommets étudié.
     * @param mapPrecedents L'ensemble des précédents aux sommets avec leur caractéristique retenue.
     * @param listeArete    L'ensemble des arêtes.
     * @param caracteristique La caractéristique par rapport à laquelle on veut relacher les arcs.
     */
    public static void relacher(Sommet si, Sommet sj, Map<Sommet, Double> mapDijktra, Map<Sommet, Precedent_Valeur> mapPrecedents, List<Arete> listeArete, int caracteristique){

        double caracteristiqueEntreSiEtSj;
        double valeurRetenue = mapDijktra.get(sj);

        if(caracteristique == Algorithmes.FIABILITE){
            //On récupère la fiabilité de l'arc entre si et sj
            caracteristiqueEntreSiEtSj = mapDijktra.get(si) * Algorithmes.CaracteristiqueEntreDeuxSommets(si, sj, listeArete, caracteristique);

            //Si cette fiabilité est plus grande que celle déjà assigné à sj on la remplace
            if (caracteristiqueEntreSiEtSj > mapDijktra.get(sj)) {
                mapDijktra.replace(sj, caracteristiqueEntreSiEtSj);
                //On sauvegarde la nouvelle valeur retenue pour la caractéristique entre si et sj
                valeurRetenue = caracteristiqueEntreSiEtSj;
            }
        } else {
            //On récupère la caractéristique de l'arc entre si et sj
            caracteristiqueEntreSiEtSj = mapDijktra.get(si) + Algorithmes.CaracteristiqueEntreDeuxSommets(si, sj, listeArete, caracteristique);

            //Si cette caractéristique est plus petite que celle déjà assigné à sj on la remplace
            if (caracteristiqueEntreSiEtSj < mapDijktra.get(sj)) {
                mapDijktra.replace(sj, caracteristiqueEntreSiEtSj);
                //On sauvegarde la nouvelle valeur retenue pour la caractéristique entre si et sj
                valeurRetenue = caracteristiqueEntreSiEtSj;
            }
        }


        //Puis, on actualise le précédant de sj
        mapPrecedents.replace(sj, new Precedent_Valeur(si, valeurRetenue));
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Dijktra">

    /**
     * Algorithme Dijktra, il permet de connaître les plus courtes distances, dans un graphe, entre un sommet donné et les autres, grâce à la liste de sommet et la liste des arêtes du graphe, et d'un sommet de départ à étudier.
     *
     * @param listeSommet La liste des sommets du graphe
     * @param listeArete La liste des arêtes du graphe
     * @param s0 le sommet de départ étudié
     * @param caracteristique la caractéristique pour comparer les sommets (fiabilité, distance, durée)
     * @return Une map contenant comme pour chaque sommet, son antécédent avec sa caractéristique retenue.
     */
    public static Map<Sommet, Precedent_Valeur> dijktra(List<Sommet> listeSommet, List<Arete> listeArete, Sommet s0, int caracteristique){

        HashMap<Sommet, Double> mapDijktra              = new HashMap<>();
        HashMap<Sommet, Precedent_Valeur> mapPrecedents = new HashMap<>();

        //Initialisation des map Dijktra et Précédents
        for (Sommet sommet : listeSommet) {

            if (caracteristique == Algorithmes.FIABILITE) {
                //Tous les sommets ont une fiabilité de 0 et pas de précédant
                mapDijktra.put(sommet, 0.0);
                mapPrecedents.put(sommet, null);
                //Sauf pour le sommet étudié, on met la fiabilité à 1 et lui-même comme précédant
                if (sommet.equals(s0)) {
                    mapDijktra.replace(sommet, 1.0);
                    Precedent_Valeur pv = new Precedent_Valeur(sommet, 0.0);
                    mapPrecedents.replace(sommet, pv);
                }

            } else {
                //Tous les sommets ont la caractéristique à +infini et pas de précédant
                mapDijktra.put(sommet, INFINI_POSITIF);
                mapPrecedents.put(sommet, null);

                //Sauf pour le sommet étudié, on met la caractéristique à 0 et lui-même comme précédant
                if (sommet.equals(s0)) {
                    mapDijktra.replace(sommet, 0.0);
                    Precedent_Valeur pv = new Precedent_Valeur(sommet, 0.0);
                    mapPrecedents.replace(sommet, pv);
                }
            }
        }

        while(mapDijktra.size() > 1) {
            //On récupère le sommet ayant le caractère spécifique au sommet étudié
            Sommet si = Algorithmes.rechercherSiAyantCaractSpecifique(mapDijktra, caracteristique);

            //Pour chaque arête, on vérifie si le sommet étudié en fait parti, si c'est le cas, on relâche l'arête
            for (Arete arete : listeArete) {

                //Si le sommet si est sommet 1 dans l'arête
                if (arete.accesSommet1().equals(si) && mapDijktra.containsKey(arete.accesSommet2())) {
                    Sommet sj = arete.accesSommet2();

                    Algorithmes.relacher(si, sj, mapDijktra, mapPrecedents, listeArete, caracteristique);
                }
                //Si le sommet si est sommet 2 dans l'arête
                if (arete.accesSommet2().equals(si) && mapDijktra.containsKey(arete.accesSommet1())) {
                    Sommet sj = arete.accesSommet1();

                    Algorithmes.relacher(si, sj, mapDijktra, mapPrecedents, listeArete, caracteristique);
                }

            }
            //À la fin, on supprime le sommet si des sommets à traiter, vu qu'on vient de le faire
            mapDijktra.remove(si);
        }

        return mapPrecedents;
    }

    // </editor-fold>


    public static List<Sommet> resultatDijktraListePrecedents(Sommet sommetDepart, Sommet sommetDArriver, Map<Sommet, Precedent_Valeur> mapPrecedents){

        List<Sommet> listePrecedents = new ArrayList<>();
        Sommet sommetCourant = sommetDArriver;

        do {
            for (Map.Entry<Sommet, Precedent_Valeur> precedent_val : mapPrecedents.entrySet()) {

                if (precedent_val.getKey().equals(sommetCourant)) {

                    listePrecedents.add(precedent_val.getValue().accesPrecedent());
                    sommetCourant = precedent_val.getValue().accesPrecedent();
                }

            }
        }
        while (!Objects.equals(sommetCourant, sommetDepart));

        return listePrecedents;
    }

    public static double resultatDijktraValeur(Sommet sommetDArriver, Map<Sommet, Precedent_Valeur> mapPrecedents){
        return mapPrecedents.get(sommetDArriver).accesValeur();
    }

    // </editor-fold>
}
