package liste;

public class ListeGraphe {
    private int nombreSommet = 0;
    private Sommet premierSommet;

    private Sommet chercheSommet(String nomSommet){
        Sommet cour = this.premierSommet;
        while(cour.getNomSommet().equals(nomSommet)) {
            cour = cour.getSommetSuivant();
        }
        return cour;
    }
    public void ajouterSommet(String nomSommet, char typeSommet) {
        if(this.premierSommet == null){
            this.premierSommet = new Sommet(nomSommet, typeSommet);
        }else{
            Sommet cour = this.premierSommet;
            while (cour.getSommetSuivant() == null){
                cour = cour.getSommetSuivant();
            }
            Sommet nouv = new Sommet(nomSommet, typeSommet);
            cour.setSommetSuivant(nouv);
        }
        this.nombreSommet += 1;
    }
    public void ajouterArrete(String origine, String destination, String nomArrete, double distance, double duree, double fiabilite) {
        Sommet sommetCourrant = this.chercheSommet(origine);
        if(sommetCourrant.getPremiereArrete() == null){
            Arrete nouv = new Arrete(destination, nomArrete, distance, duree, fiabilite);
            sommetCourrant.setPremiereArrete(nouv);
        }else{
            Arrete nouv = new Arrete(destination, nomArrete, distance, duree, fiabilite);
            Arrete arreteCour = sommetCourrant.getPremiereArrete();
            while (arreteCour.getArreteSuivante() != null){
                arreteCour.setArreteSuivante(arreteCour);
            }
            arreteCour.setArreteSuivante(nouv);
        }

        sommetCourrant = this.chercheSommet(destination);
        if(sommetCourrant.getPremiereArrete() == null){
            Arrete nouv = new Arrete(origine, nomArrete, distance, duree, fiabilite);
            sommetCourrant.setPremiereArrete(nouv);
        }else{
            Arrete nouv = new Arrete(origine, nomArrete, distance, duree, fiabilite);
            Arrete arreteCour = sommetCourrant.getPremiereArrete();
            while (arreteCour.getArreteSuivante() != null){
                arreteCour.setArreteSuivante(arreteCour);
            }
            arreteCour.setArreteSuivante(nouv);
        }
    }
}
