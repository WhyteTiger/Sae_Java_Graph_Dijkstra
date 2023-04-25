package Logiciel.Modele;

import java.util.ArrayList;
import java.util.List;

public class Algorithmes {

    public static int INFINI_POSITIF = Integer.MAX_VALUE;

    /**
     *
     * @param listeSommet liste des sommets qu'on veut copier dans listeDesSommetsATraiter
     * @return la liste chainee des sommets a traiter
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
     *
     * @param listeDesSommetsATraiter
     * @param tabDijktra
     * @return
     */
    private static int rechercherDsiMinimal(ListeChaineeSommet listeDesSommetsATraiter, double[][] tabDijktra){
        int iDsiMinimal = INFINI_POSITIF;
        int n = listeDesSommetsATraiter.accesNombreSommet();
        for (int i = 1; i<n; i++){
            if(tabDijktra[i][0] < iDsiMinimal){
                iDsiMinimal = i;
            }
        }
        return iDsiMinimal;
    }

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

    public static void relacher(int i, int j, double[][] tabDijktra, ListeChaineeSommet listeDesSommetsATraiter, List<Arrete> listeArrete){
        if (tabDijktra[j][0] > (tabDijktra[i][0]+ Algorithmes.distanceEntreDeuxSommets(i, j, listeDesSommetsATraiter, listeArrete))){
            tabDijktra[j][0] = tabDijktra[i][0] + Algorithmes.distanceEntreDeuxSommets(i, j, listeDesSommetsATraiter, listeArrete);
            tabDijktra[j][1] = i;
        }
    }

    /**
     * pas fini
     * @param listeSommet
     * @param listeArrete
     * @param s0
     * @return
     */
    public static double[][] dijktra(List<Sommet> listeSommet, List<Arrete> listeArrete, Sommet s0){
        //Initialisation
        int n = listeSommet.size();
        double[][] tabDijktra = new double[n][2];
        for (int i=1;i<n;i++) {
            tabDijktra[i][0] = INFINI_POSITIF;
        }
        tabDijktra[0][0] = 0;
        ListeChaineeSommet listeDesSommetsATraiter = Algorithmes.ajoutDesSommets(listeSommet);
        //Fin initialisation

        while(listeDesSommetsATraiter.accesPremierSommet() != null){
            int i = Algorithmes.rechercherDsiMinimal(listeDesSommetsATraiter, tabDijktra);
            listeDesSommetsATraiter.supprimerALaPosition(i);
            for (Arrete a: listeArrete) {
                if (a.accesSommet1().equals(s0)){
                    int j = listeDesSommetsATraiter.accesPositionParSommet(a.accesSommet2());
                    Algorithmes.relacher(i, j, tabDijktra, listeDesSommetsATraiter, listeArrete);
                }
                if (a.accesSommet2().equals(s0)){
                    int j = listeDesSommetsATraiter.accesPositionParSommet(a.accesSommet1());
                    Algorithmes.relacher(i, j, tabDijktra, listeDesSommetsATraiter, listeArrete);
                }
            }
        }
        return tabDijktra;
    }
}
