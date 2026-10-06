public class Facture {    
    private static final int PRIX_UNITAIRE = 20;
    private static final int FRAIS_LIVRAISON = 5;
    private static final int RABAIS_FIDELITE = 10;
    private static final int SEUIL_LIVRAISON_GRATUITE = 100;

    public static void main(String[] args) {
        affciherFacture("Client démonstration", 3);
		System.out.println("----------");
        affciherFacture("Client fidèle", 6);
    }

    private static void affciherFacture(String client, int quantite) {
        int sousTotal = calculerSousTotal(quantite);
        int total = calculerTotal(sousTotal);
        System.out.println("Client : " + client);
		System.out.println(quantite + " article(s) à " + PRIX_UNITAIRE + " $");
		System.out.println("Sous-total : " + sousTotal + " $");
        System.out.println("Total : " + total + " $");
    }

    private static int calculerTotal(int sousTotal) {
        int total = sousTotal + calculerFraisLivraison(sousTotal) - RABAIS_FIDELITE;
        return total;
    }

    private static int calculerFraisLivraison(int sousTotal) {
        if (sousTotal >= SEUIL_LIVRAISON_GRATUITE) {
            return 0;
        }
        return FRAIS_LIVRAISON;
    }

    private static int calculerSousTotal(int quantite) {
        int sousTotal = PRIX_UNITAIRE * quantite;
        return sousTotal;
    }
}