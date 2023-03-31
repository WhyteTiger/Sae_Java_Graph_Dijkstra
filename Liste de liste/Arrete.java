package liste;

public class Arrete {
    public Sommet destination;
    private String nomArrete;
    private double ditance;
    private double duree;
    private double fiabilite;
    private Arrete arreteSuivante = null;
    public Arrete(Sommet destination, String nomArrete, double distance, double duree, double fiabilite){
        this.destination = destination;
        this.nomArrete = nomArrete;
        this.ditance = distance;
        this.duree = duree;
        this.fiabilite = fiabilite;
    }

    public Arrete getArreteSuivante() {
        return arreteSuivante;
    }
    public void setArreteSuivante(Arrete arreteSuivante) {
        this.arreteSuivante = arreteSuivante;
    }
}
