public class Facture {    
    private static final int PRIX_UNITAIRE = 20;
    private static final int FRAIS_LIVRAISON = 5;
    private static final int RABAIS_FIDELITE = 10;

    public static void main(String[] args) {
        String client = "Client démonstration";
        int quantite = 3;
        int sousTotal = PRIX_UNITAIRE * quantite;
        int total = sousTotal + FRAIS_LIVRAISON - RABAIS_FIDELITE;
        System.out.println("Client : " + client);
		System.out.println(quantite + " article(s) à " + PRIX_UNITAIRE + " $");
		System.out.println("Sous-total : " + sousTotal + " $");
        System.out.println("Total : " + total + " $");
		System.out.println("----------");
        // client 2 (copie du client 1)
        String c2 = "Client fidèle";
        int q2 = 6;
        int st2 = PRIX_UNITAIRE * q2;
        int t = st2 + FRAIS_LIVRAISON - RABAIS_FIDELITE;
        System.out.println("Client : " + c2);
        System.out.println(q2 + " article(s) à " + PRIX_UNITAIRE + " $");
        System.out.println("Sous-total : " + st2 + " $");
        System.out.println("Total : " + t + " $");
    }
}
