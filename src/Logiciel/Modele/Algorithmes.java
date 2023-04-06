package Logiciel.Modele;

import java.util.ArrayList;

public class Algorithmes {

    public static int INFINI_POSITIF = Integer.MAX_VALUE;

    /**
     *
     * @param listeSommet liste des sommets qu'on veut copier dans listeDesSommetsATraiter
     * @return la liste chainee des sommets a traiter
     */
    private static ListeChaineeSommet ajoutDesSommets(ArrayList<Sommet> listeSommet){
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
        ListeChaineeSommet listeDesSommetsATraiter = Algorithmes.ajoutDesSommets(listeSommet);
        //Fin initialisation

        while(listeDesSommetsATraiter.accesPremierSommet() != null){
            int i = Algorithmes.rechercherDsiMinimal(listeDesSommetsATraiter, tabDijktra); // marche pas
            listeDesSommetsATraiter.supprimerALaPosition(i);
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
