public class Facture {
    public static void main(String[] args) {
        String client = "Client démonstration";
        int prixUnitaire = 20;
        int quantite = 3;
		int fraisLivraison = 5;
		int rabais = 10;
        int sousTotal = prixUnitaire * quantite;
        int total = sousTotal + fraisLivraison - rabais;


        System.out.println("Client : " + client);
		System.out.println(quantite + " article(s) à " + prixUnitaire + " $");
		System.out.println("Sous-total : " + sousTotal + " $");
        System.out.println("Total : " + total + " $");
		System.out.println("----------");
        // client 2 (copie du client 1)
        String c2 = "Client fidèle";
        int q2 = 6;
        int st2 = 20 * q2;
        int t = st2 + 5 - 10;
        System.out.println("Client : " + c2);
        System.out.println(q2 + " article(s) à " + 20 + " $");
        System.out.println("Sous-total : " + st2 + " $");
        System.out.println("Total : " + t + " $");
    }
}
