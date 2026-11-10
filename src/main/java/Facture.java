import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Facture {
    public static void main(String[] args) {
        Configuration config = Configuration.depuisLeSysteme();
        String url = config.lireObligatoire("compta.url");
        String cle = config.lireObligatoire("compta.cle");

        afficherFacture("Client démonstration", 3);
        System.out.println("----------");
        afficherFacture("Client fidèle", 6);
        System.out.println("----------");
        System.out.println("Environnement : " + config.environnement());
        System.out.println("Envoi (simulé) vers : " + url);
        System.out.println("Clé d'API : " + masquer(cle));
        System.out.println(exporterJson("Client fidèle", 6));
    }

    static String masquer(String secret) {
        return secret.substring(0, Math.min(4, secret.length())) + "****";
    }

    static void afficherFacture(String client, int quantite) {
        int sousTotal = CalculFacture.calculerSousTotal(quantite);
        int total = CalculFacture.calculerTotal(sousTotal);
        System.out.println("Client : " + client);
        System.out.println(quantite + " article(s) à " + CalculFacture.PRIX_UNITAIRE + " $");
        System.out.println("Sous-total : " + sousTotal + " $");
        System.out.println("Total : " + total + " $");
    }

    static String exporterJson(String client, int quantite) {
        int sousTotal = CalculFacture.calculerSousTotal(quantite);
        int total = CalculFacture.calculerTotal(sousTotal);
        ResumeFacture resume = new ResumeFacture(client, quantite, sousTotal, total);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(resume);
    }
}
