package Logiciel.Modele;

import java.util.ArrayList;

public class Algorithmes {

    public static int INFINI_POSITIF = Integer.MAX_VALUE;

    /**
     *  /!\ MARCHE PAS /!\
     * @param listeDesSommetsATraiter
     * @return
     */
    private static ListeSommet ajoutDesSommets(ListeSommet listeDesSommetsATraiter){
        return listeDesSommetsATraiter;
    }

    /**
     * /!\ MARCHE PAS /!\
     * @param listeDesSommetsATraiter
     * @param tabDijktra
     * @return
     */
    private static int rechercherDsiMinimal(ListeSommet listeDesSommetsATraiter, double[][] tabDijktra){
        return 0;
    }

    public static void relacher(int i, int j, double[][] tabDijktra, double vsisj){
        if (tabDijktra[i][0] > (tabDijktra[j][0]+vsisj)){
            tabDijktra[j][0] = tabDijktra[i][0] + vsisj;
            tabDijktra[j][1] = i;
        }
    }

    /**
     * pas fini
     * @param listeSommet
     * @param listeArrete
     * @param listeV
     * @param s0
     * @return
     */
    public static double[][] dijktra(ArrayList<Sommet> listeSommet, ArrayList<Arrete> listeArrete, double[][] listeV, Sommet s0){
        //Initialisation
        int n = listeSommet.size();
        double[][] tabDijktra = new double[n][2];
        for (int i=1;i<n;i++) {
            tabDijktra[i][0] = INFINI_POSITIF;
        }
        tabDijktra[0][0] = 0;
        ListeSommet listeDesSommetsATraiter = new ListeSommet();
        Algorithmes.ajoutDesSommets(listeDesSommetsATraiter); // marche pas
        //Fin initialisation

        while(listeDesSommetsATraiter.accesPremierSommet() != null){
            int i = Algorithmes.rechercherDsiMinimal(listeDesSommetsATraiter, tabDijktra); // marche pas
            listeDesSommetsATraiter.supprimerALaPosition(i); // marche pas
            for (Arrete a: listeArrete) {
                if (a.accesSommet1().equals(s0) || a.accesSommet2().equals(s0)){
                    int j = listeArrete.indexOf(a);
                    Algorithmes.relacher(i, j, tabDijktra, listeV[i][j]);
                }
            }
        }
        return tabDijktra;
    }
}
