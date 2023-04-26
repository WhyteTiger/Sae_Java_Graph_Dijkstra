package logiciel.modele;

import java.util.List;

public class Algorithmes {

    public static int INFINI_POSITIF = Integer.MAX_VALUE;

    /**
     * Crée une liste chainée de sommet grâce à une liste de sommet donnée
     * @param listeSommet liste des sommets qu'on veut copier dans listeDesSommetsATraiter
     * @return la liste chainée des sommets à traiter
     */
    private static ListeChaineeSommet ajoutDesSommets(List<Sommet> listeSommet){
        ListeChaineeSommet listeDesSommetsATraiter = new ListeChaineeSommet();
        int n = listeSommet.size();
        for (int i=1; i<n; i++){
            listeDesSommetsATraiter.ajouterSommet(listeSommet.get(i));
        }
        return listeDesSommetsATraiter;
    }

    /**
     * Donne l'indice de la distance minimal d'une liste chainée de sommet grâce à tabDijktra
     * @param listeDesSommetsATraiter la liste des sommets à traiter
     * @param tabDijktra un tableau à double entrée contenant dans sa première ligne les distance des points en indice
     * @return la distance minimale
     */
    private static int rechercherDsiMinimale(ListeChaineeSommet listeDesSommetsATraiter, double[][] tabDijktra){
        int iDsiMinimale = INFINI_POSITIF;
        int n = listeDesSommetsATraiter.accesNombreDeSommet();
        for (int i = 1; i<n; i++){
            if(tabDijktra[i][0] < iDsiMinimale){
                iDsiMinimale = i;
            }
        }
        return iDsiMinimale;
    }

    /**
     * Donne la distance entre deux sommets aux indices données, grâce à une liste chainnée de sommet et à une liste d'arrête
     * @param i l'indice du premier sommet
     * @param j l'indice du second sommet
     * @param listeDesSommetsATraiter la liste chainée des sommets à traiter
     * @param listeArrete la liste des arrêtes
     * @return la distance recherchée, entre le sommet d'indice i et le sommet d'indice j
     */
    private static double distanceEntreDeuxSommets(int i, int j, ListeChaineeSommet listeDesSommetsATraiter, List<Arrete> listeArrete){
        double distance = -1;
        Sommet si = listeDesSommetsATraiter.accesSommetParPosition(i);
        Sommet sj = listeDesSommetsATraiter.accesSommetParPosition(j);
        for (Arrete a : listeArrete) {
            if (a.accesSommet1().equals(si) && a.accesSommet2().equals(sj)){
                distance = a.accesDistance();
            }
        }
        return distance;
    }

    /**
     * Relache deux arcs entre le sommet à traité et les sommets d'indice i et j
     * @param i l'indice du premier sommet pour le premier arc
     * @param j l'indice du second sommet pour le second arc
     * @param listeDesSommetsATraiter la liste des sommets à traiter
     * @param listeArrete la liste des arrêtes
     */
    public static void relacher(int i, int j, double[][] tabDijktra, ListeChaineeSommet listeDesSommetsATraiter, List<Arrete> listeArrete){
        if (tabDijktra[j][0] > (tabDijktra[i][0]+ Algorithmes.distanceEntreDeuxSommets(i, j, listeDesSommetsATraiter, listeArrete))){
            tabDijktra[j][0] = tabDijktra[i][0] + Algorithmes.distanceEntreDeuxSommets(i, j, listeDesSommetsATraiter, listeArrete);
            tabDijktra[j][1] = i;
        }
    }

    /**
     * Algorithme Dijktra, il permet de connaître les plus courtes distances, dans un graphe, entre un sommet donné et les autres, grâce à la liste de sommet et la liste des arrêtes du graphe, et d'un sommet de départ à étudier.
     * @param listeSommet La liste des sommets du graphe
     * @param listeArrete La liste des arrêtes du graphe
     * @param s0 le sommet de départ étudié
     * @return Un tableau contenant, dans sa première ligne la distance entre un sommet et son précédant pour avoir le chemin le plus court en partant de s0, et dans sa seconde ligne le sommet précédant.
     */
    public static double[][] dijktra(List<Sommet> listeSommet, List<Arrete> listeArrete, Sommet s0){
        //Initialisation → On crée le tableau final "tabDijktra", avec pour chaque colonne un sommet attitré. Donc tous les sommets ont une distance infini et n'ont pas de précédant, sauf le sommet traité, à l'indice 0.
        int n = listeSommet.size();
        double[][] tabDijktra = new double[n][2];
        for (int i=1;i<n;i++) {
            tabDijktra[i][0] = INFINI_POSITIF;
        }
        tabDijktra[0][0] = 0;
        ListeChaineeSommet listeDesSommetsATraiter = Algorithmes.ajoutDesSommets(listeSommet); //Crée une liste chainée des sommets, qui ne sont pas encore traités, avec la liste des sommets du graphes
        //Fin initialisation

        while(listeDesSommetsATraiter.accesPremierSommet() != null){
            //On récupère l'indice du sommet ayant la plus courte distance au sommet étudié
            int i = Algorithmes.rechercherDsiMinimale(listeDesSommetsATraiter, tabDijktra);
            listeDesSommetsATraiter.supprimerALaPosition(i); //Du coup, on le supprime des sommets à traiter, vu qu'on va le faire

            for (Arrete arrete: listeArrete) {
                if (arrete.accesSommet1().equals(s0)){
                    int j = listeDesSommetsATraiter.accesPositionParSommet(arrete.accesSommet2());
                    Algorithmes.relacher(i, j, tabDijktra, listeDesSommetsATraiter, listeArrete);
                }
                if (arrete.accesSommet2().equals(s0)){
                    int j = listeDesSommetsATraiter.accesPositionParSommet(arrete.accesSommet1());
                    Algorithmes.relacher(i, j, tabDijktra, listeDesSommetsATraiter, listeArrete);
                }
            }
        }
        return tabDijktra;
    }
}
