package liste;

public class Main2LeVrai {
    public static void main(String[] args) {
        ListeGraphe g = new ListeGraphe();

        g.ajouterSommet("S1", 'm');
        g.ajouterSommet("S2", 'n');
        g.ajouterSommet("S3", 'o');

        g.ajouterArrete();
    }
}
